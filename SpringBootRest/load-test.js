const http = require('http');
const https = require('https');

// Configuration
const CONFIG = {
    host: 'localhost',
    port: 8080,
    path: '/api/students',
    method: 'GET',
    concurrentUsers: 1000,
    testDuration: 60, // seconds
    rampUpTime: 10, // seconds
};

// Statistics
let stats = {
    totalRequests: 0,
    successfulRequests: 0,
    failedRequests: 0,
    totalResponseTime: 0,
    minResponseTime: Infinity,
    maxResponseTime: 0,
    startTime: Date.now(),
};

// Make a single request
function makeRequest() {
    return new Promise((resolve, reject) => {
        const startTime = Date.now();
        
        const req = http.request({
            hostname: CONFIG.host,
            port: CONFIG.port,
            path: CONFIG.path,
            method: CONFIG.method,
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            }
        }, (res) => {
            let data = '';
            res.on('data', (chunk) => {
                data += chunk;
            });
            
            res.on('end', () => {
                const responseTime = Date.now() - startTime;
                updateStats(true, responseTime);
                resolve({ statusCode: res.statusCode, responseTime, data });
            });
        });
        
        req.on('error', (err) => {
            const responseTime = Date.now() - startTime;
            updateStats(false, responseTime);
            reject(err);
        });
        
        req.setTimeout(5000, () => {
            req.destroy();
            const responseTime = Date.now() - startTime;
            updateStats(false, responseTime);
            reject(new Error('Request timeout'));
        });
        
        req.end();
    });
}

// Update statistics
function updateStats(success, responseTime) {
    stats.totalRequests++;
    if (success) {
        stats.successfulRequests++;
    } else {
        stats.failedRequests++;
    }
    
    stats.totalResponseTime += responseTime;
    stats.minResponseTime = Math.min(stats.minResponseTime, responseTime);
    stats.maxResponseTime = Math.max(stats.maxResponseTime, responseTime);
}

// Print statistics
function printStats() {
    const duration = (Date.now() - stats.startTime) / 1000;
    const avgResponseTime = stats.totalResponseTime / stats.totalRequests;
    const throughput = stats.totalRequests / duration;
    const successRate = (stats.successfulRequests / stats.totalRequests) * 100;
    
    console.log('\n=== LOAD TEST RESULTS ===');
    console.log(`Test Duration: ${duration.toFixed(2)} seconds`);
    console.log(`Total Requests: ${stats.totalRequests}`);
    console.log(`Successful Requests: ${stats.successfulRequests}`);
    console.log(`Failed Requests: ${stats.failedRequests}`);
    console.log(`Success Rate: ${successRate.toFixed(2)}%`);
    console.log(`Throughput: ${throughput.toFixed(2)} requests/second`);
    console.log(`Average Response Time: ${avgResponseTime.toFixed(2)}ms`);
    console.log(`Min Response Time: ${stats.minResponseTime}ms`);
    console.log(`Max Response Time: ${stats.maxResponseTime}ms`);
    console.log('========================\n');
}

// Simulate concurrent users
async function simulateConcurrentUsers() {
    console.log(`Starting load test with ${CONFIG.concurrentUsers} concurrent users...`);
    console.log(`Ramp-up time: ${CONFIG.rampUpTime} seconds`);
    console.log(`Test duration: ${CONFIG.testDuration} seconds`);
    
    const promises = [];
    const startTime = Date.now();
    
    // Create concurrent requests
    for (let i = 0; i < CONFIG.concurrentUsers; i++) {
        const delay = (i / CONFIG.concurrentUsers) * CONFIG.rampUpTime * 1000;
        
        setTimeout(() => {
            const promise = makeRequest()
                .then(result => {
                    if (i % 100 === 0) {
                        console.log(`Request ${i + 1}/${CONFIG.concurrentUsers} completed - Status: ${result.statusCode}, Time: ${result.responseTime}ms`);
                    }
                })
                .catch(error => {
                    if (i % 100 === 0) {
                        console.log(`Request ${i + 1}/${CONFIG.concurrentUsers} failed - ${error.message}`);
                    }
                });
            
            promises.push(promise);
        }, delay);
    }
    
    // Wait for all requests to complete or timeout
    const timeout = setTimeout(() => {
        console.log('Test timeout reached');
        printStats();
        process.exit(0);
    }, (CONFIG.testDuration + CONFIG.rampUpTime) * 1000);
    
    try {
        await Promise.all(promises);
        clearTimeout(timeout);
        printStats();
    } catch (error) {
        console.error('Test error:', error);
        printStats();
    }
}

// Health check before starting
async function healthCheck() {
    try {
        const result = await makeRequest();
        console.log('Health check passed - Application is running');
        return true;
    } catch (error) {
        console.error('Health check failed - Application is not responding');
        console.error('Make sure your Spring Boot application is running on localhost:8080');
        return false;
    }
}

// Main execution
async function main() {
    console.log('=== SPRING BOOT LOAD TEST ===');
    console.log(`Target: http://${CONFIG.host}:${CONFIG.port}${CONFIG.path}`);
    
    const isHealthy = await healthCheck();
    if (!isHealthy) {
        process.exit(1);
    }
    
    await simulateConcurrentUsers();
}

// Run the test
main().catch(console.error); 
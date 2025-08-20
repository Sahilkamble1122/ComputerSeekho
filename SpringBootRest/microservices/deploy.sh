#!/bin/bash

# Microservices Deployment Script
echo "🚀 Starting Microservices Deployment..."

# Function to build and run a service
build_and_run_service() {
    local service_name=$1
    local port=$2
    
    echo "📦 Building $service_name..."
    cd $service_name
    
    # Build the project
    mvn clean package -DskipTests
    
    # Create Dockerfile if it doesn't exist
    if [ ! -f Dockerfile ]; then
        cp ../Dockerfile-template Dockerfile
        # Update port in Dockerfile
        sed -i "s/EXPOSE 8081/EXPOSE $port/" Dockerfile
    fi
    
    # Build Docker image
    docker build -t $service_name .
    
    cd ..
    echo "✅ $service_name built successfully"
}

# Function to start all services
start_all_services() {
    echo "🐳 Starting all services with Docker Compose..."
    docker-compose up -d
}

# Function to check service health
check_service_health() {
    local service_name=$1
    local port=$2
    
    echo "🏥 Checking health of $service_name..."
    sleep 10
    curl -f http://localhost:$port/actuator/health || echo "❌ $service_name health check failed"
}

# Main deployment process
echo "🔧 Setting up microservices..."

# Build all services
build_and_run_service "student-service" "8081"
build_and_run_service "enquiry-service" "8082"
build_and_run_service "payment-service" "8083"
build_and_run_service "staff-service" "8084"
build_and_run_service "contact-service" "8085"
build_and_run_service "auth-service" "8086"
build_and_run_service "eureka-server" "8761"
build_and_run_service "api-gateway" "8080"

# Start all services
start_all_services

# Wait for services to start
echo "⏳ Waiting for services to start..."
sleep 30

# Check service health
echo "🔍 Checking service health..."
check_service_health "eureka-server" "8761"
check_service_health "student-service" "8081"
check_service_health "enquiry-service" "8082"
check_service_health "payment-service" "8083"
check_service_health "staff-service" "8084"
check_service_health "contact-service" "8085"
check_service_health "auth-service" "8086"
check_service_health "api-gateway" "8080"

echo "🎉 Microservices deployment completed!"
echo ""
echo "📋 Service URLs:"
echo "  Eureka Server: http://localhost:8761"
echo "  API Gateway: http://localhost:8080"
echo "  Student Service: http://localhost:8081"
echo "  Enquiry Service: http://localhost:8082"
echo "  Payment Service: http://localhost:8083"
echo "  Staff Service: http://localhost:8084"
echo "  Contact Service: http://localhost:8085"
echo "  Auth Service: http://localhost:8086"
echo ""
echo "🔧 Useful Commands:"
echo "  View logs: docker-compose logs -f [service-name]"
echo "  Stop services: docker-compose down"
echo "  Restart services: docker-compose restart"
echo "  Check status: docker-compose ps"

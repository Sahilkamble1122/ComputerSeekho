const fetch = require('node-fetch');

async function testAuth() {
    try {
        // Test login with admin user
        console.log('🔐 Testing authentication...');
        
        const loginResponse = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: 'admin',
                password: 'admin123'
            })
        });

        if (loginResponse.ok) {
            const authData = await loginResponse.json();
            console.log('✅ Login successful!');
            console.log('Token:', authData.token);
            console.log('Username:', authData.username);
            console.log('Role:', authData.role);
            
            // Test student registration with the token
            console.log('\n🎓 Testing student registration...');
            
            const formData = new FormData();
            formData.append("studentName", "Test Student");
            formData.append("studentAddress", "Test Address");
            formData.append("studentGender", "Male");
            formData.append("studentDob", "1990-01-01");
            formData.append("studentQualification", "Bachelor");
            formData.append("studentMobile", "1234567890");
            formData.append("studentEmail", "test@example.com");
            formData.append("courseId", "1");
            formData.append("studentPassword", "pass123");
            formData.append("studentUsername", "teststudent");
            formData.append("batchId", "1");

            const studentResponse = await fetch('http://localhost:8080/api/students/form', {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${authData.token}`,
                },
                body: formData
            });

            if (studentResponse.ok) {
                console.log('✅ Student registration successful!');
                const studentData = await studentResponse.json();
                console.log('Student ID:', studentData.studentId);
            } else {
                console.log('❌ Student registration failed!');
                console.log('Status:', studentResponse.status);
                const errorText = await studentResponse.text();
                console.log('Error:', errorText);
            }
            
        } else {
            console.log('❌ Login failed!');
            console.log('Status:', loginResponse.status);
            const errorText = await loginResponse.text();
            console.log('Error:', errorText);
        }
        
    } catch (error) {
        console.error('❌ Error:', error.message);
    }
}

testAuth(); 
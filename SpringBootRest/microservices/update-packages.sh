#!/bin/bash

# 🔄 Package Name Update Script
# Updates all package declarations and imports in microservices

echo "🔄 Updating package names for all microservices..."

cd microservices

# ===============================
# 1. UPDATE STUDENT SERVICE
# ===============================
echo "🎓 Updating Student Service packages..."

find student-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo;/package com.example.student;/g' {} \;
find student-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo\./package com.example.student./g' {} \;
find student-service -name "*.java" -type f -exec sed -i 's/import com\.example\.demo\./import com.example.student./g' {} \;

# ===============================
# 2. UPDATE ENQUIRY SERVICE  
# ===============================
echo "📋 Updating Enquiry Service packages..."

find enquiry-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo;/package com.example.enquiry;/g' {} \;
find enquiry-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo\./package com.example.enquiry./g' {} \;
find enquiry-service -name "*.java" -type f -exec sed -i 's/import com\.example\.demo\./import com.example.enquiry./g' {} \;

# ===============================
# 3. UPDATE PAYMENT SERVICE
# ===============================
echo "💳 Updating Payment Service packages..."

find payment-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo;/package com.example.payment;/g' {} \;
find payment-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo\./package com.example.payment./g' {} \;
find payment-service -name "*.java" -type f -exec sed -i 's/import com\.example\.demo\./import com.example.payment./g' {} \;

# ===============================
# 4. UPDATE STAFF SERVICE
# ===============================
echo "👥 Updating Staff Service packages..."

find staff-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo;/package com.example.staff;/g' {} \;
find staff-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo\./package com.example.staff./g' {} \;
find staff-service -name "*.java" -type f -exec sed -i 's/import com\.example\.demo\./import com.example.staff./g' {} \;

# ===============================
# 5. UPDATE CONTACT SERVICE
# ===============================
echo "📞 Updating Contact Service packages..."

find contact-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo;/package com.example.contact;/g' {} \;
find contact-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo\./package com.example.contact./g' {} \;
find contact-service -name "*.java" -type f -exec sed -i 's/import com\.example\.demo\./import com.example.contact./g' {} \;

# ===============================
# 6. UPDATE AUTH SERVICE
# ===============================
echo "🔐 Updating Auth Service packages..."

find auth-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo;/package com.example.auth;/g' {} \;
find auth-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo\./package com.example.auth./g' {} \;
find auth-service -name "*.java" -type f -exec sed -i 's/import com\.example\.demo\./import com.example.auth./g' {} \;

# ===============================
# 7. UPDATE MEDIA SERVICE (if exists)
# ===============================
if [ -d "media-service" ]; then
    echo "🎬 Updating Media Service packages..."
    
    find media-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo;/package com.example.media;/g' {} \;
    find media-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo\./package com.example.media./g' {} \;
    find media-service -name "*.java" -type f -exec sed -i 's/import com\.example\.demo\./import com.example.media./g' {} \;
fi

echo "✅ Package updates completed!"
echo ""
echo "📋 Manual fixes needed:"
echo "1. Cross-service imports will need manual adjustment"
echo "2. Some specific imports may need to be updated manually"
echo "3. Check for any compilation errors after updates"
echo ""
echo "🚀 Next: Run 'mvn clean compile' in each service directory to check for errors"

module.exports = {

"[project]/.next-internal/server/app/api/placements/route/actions.js [app-rsc] (server actions loader, ecmascript)": ((__turbopack_context__) => {

var { m: module, e: exports } = __turbopack_context__;
{
}}),
"[externals]/next/dist/compiled/next-server/app-route-turbo.runtime.dev.js [external] (next/dist/compiled/next-server/app-route-turbo.runtime.dev.js, cjs)": ((__turbopack_context__) => {

var { m: module, e: exports } = __turbopack_context__;
{
const mod = __turbopack_context__.x("next/dist/compiled/next-server/app-route-turbo.runtime.dev.js", () => require("next/dist/compiled/next-server/app-route-turbo.runtime.dev.js"));

module.exports = mod;
}}),
"[externals]/next/dist/compiled/@opentelemetry/api [external] (next/dist/compiled/@opentelemetry/api, cjs)": ((__turbopack_context__) => {

var { m: module, e: exports } = __turbopack_context__;
{
const mod = __turbopack_context__.x("next/dist/compiled/@opentelemetry/api", () => require("next/dist/compiled/@opentelemetry/api"));

module.exports = mod;
}}),
"[externals]/next/dist/compiled/next-server/app-page-turbo.runtime.dev.js [external] (next/dist/compiled/next-server/app-page-turbo.runtime.dev.js, cjs)": ((__turbopack_context__) => {

var { m: module, e: exports } = __turbopack_context__;
{
const mod = __turbopack_context__.x("next/dist/compiled/next-server/app-page-turbo.runtime.dev.js", () => require("next/dist/compiled/next-server/app-page-turbo.runtime.dev.js"));

module.exports = mod;
}}),
"[externals]/next/dist/server/app-render/work-unit-async-storage.external.js [external] (next/dist/server/app-render/work-unit-async-storage.external.js, cjs)": ((__turbopack_context__) => {

var { m: module, e: exports } = __turbopack_context__;
{
const mod = __turbopack_context__.x("next/dist/server/app-render/work-unit-async-storage.external.js", () => require("next/dist/server/app-render/work-unit-async-storage.external.js"));

module.exports = mod;
}}),
"[externals]/next/dist/server/app-render/work-async-storage.external.js [external] (next/dist/server/app-render/work-async-storage.external.js, cjs)": ((__turbopack_context__) => {

var { m: module, e: exports } = __turbopack_context__;
{
const mod = __turbopack_context__.x("next/dist/server/app-render/work-async-storage.external.js", () => require("next/dist/server/app-render/work-async-storage.external.js"));

module.exports = mod;
}}),
"[externals]/next/dist/shared/lib/no-fallback-error.external.js [external] (next/dist/shared/lib/no-fallback-error.external.js, cjs)": ((__turbopack_context__) => {

var { m: module, e: exports } = __turbopack_context__;
{
const mod = __turbopack_context__.x("next/dist/shared/lib/no-fallback-error.external.js", () => require("next/dist/shared/lib/no-fallback-error.external.js"));

module.exports = mod;
}}),
"[project]/lib/config.js [app-route] (ecmascript)": ((__turbopack_context__) => {
"use strict";

// API Configuration
__turbopack_context__.s({
    "API_CONFIG": ()=>API_CONFIG,
    "getApiUrl": ()=>getApiUrl
});
const API_CONFIG = {
    // Replace with your Spring Boot backend URL
    BASE_URL: process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080',
    // API Endpoints
    ENDPOINTS: {
        STUDENTS: '/api/students',
        PAYMENT_HISTORY: '/api/receipts',
        PAYMENT_TYPES: '/api/payment-types',
        PROCESS_PAYMENT: '/api/payment-with-type',
        PLACEMENTS: '/api/placements',
        BATCHES: '/api/batches',
        COURSES: '/api/courses'
    }
};
const getApiUrl = (endpoint)=>{
    return `${API_CONFIG.BASE_URL}${endpoint}`;
};
}),
"[project]/app/api/placements/route.js [app-route] (ecmascript)": ((__turbopack_context__) => {
"use strict";

__turbopack_context__.s({
    "GET": ()=>GET
});
var __TURBOPACK__imported__module__$5b$project$5d2f$lib$2f$config$2e$js__$5b$app$2d$route$5d$__$28$ecmascript$29$__ = __turbopack_context__.i("[project]/lib/config.js [app-route] (ecmascript)");
;
// Helper function to get real student counts for batches
async function getStudentCounts(batchIds) {
    try {
        const studentCounts = {};
        // Fetch all students at once (more efficient)
        const studentsResponse = await fetch((0, __TURBOPACK__imported__module__$5b$project$5d2f$lib$2f$config$2e$js__$5b$app$2d$route$5d$__$28$ecmascript$29$__["getApiUrl"])(__TURBOPACK__imported__module__$5b$project$5d2f$lib$2f$config$2e$js__$5b$app$2d$route$5d$__$28$ecmascript$29$__["API_CONFIG"].ENDPOINTS.STUDENTS));
        if (studentsResponse.ok) {
            const allStudents = await studentsResponse.json();
            // Calculate counts for each batch
            batchIds.forEach((batchId)=>{
                const batchStudents = allStudents.filter((student)=>student.batchId === batchId);
                const totalStudents = batchStudents.length;
                const placedStudents = batchStudents.filter((student)=>student.isPlaced === true).length;
                studentCounts[batchId] = {
                    totalStudents,
                    placedStudents
                };
            });
        } else {
            // Fallback if API fails
            batchIds.forEach((batchId)=>{
                studentCounts[batchId] = {
                    totalStudents: 0,
                    placedStudents: 0
                };
            });
        }
        return studentCounts;
    } catch (error) {
        console.error("Error fetching student counts:", error);
        // Fallback if error occurs
        const studentCounts = {};
        batchIds.forEach((batchId)=>{
            studentCounts[batchId] = {
                totalStudents: 0,
                placedStudents: 0
            };
        });
        return studentCounts;
    }
}
async function GET() {
    try {
        // Fetch batches from Spring Boot backend
        const batchesResponse = await fetch((0, __TURBOPACK__imported__module__$5b$project$5d2f$lib$2f$config$2e$js__$5b$app$2d$route$5d$__$28$ecmascript$29$__["getApiUrl"])(__TURBOPACK__imported__module__$5b$project$5d2f$lib$2f$config$2e$js__$5b$app$2d$route$5d$__$28$ecmascript$29$__["API_CONFIG"].ENDPOINTS.BATCHES));
        if (!batchesResponse.ok) {
            throw new Error(`Failed to fetch batches: ${batchesResponse.status}`);
        }
        const batches = await batchesResponse.json();
        // Course ID to name mapping based on your data
        const courseMapping = {
            101: "PG DBDA",
            98: "PG DAC",
            103: "PRE - CAT" // Special course
        };
        // Get real student counts for all batches
        const batchIds = batches.map((batch)=>batch.batchId);
        const studentCounts = await getStudentCounts(batchIds);
        // Format the batches data for the frontend
        const formattedBatches = batches.filter((batch)=>batch.batchIsActive) // Only show active batches
        .map((batch)=>{
            const courseName = courseMapping[batch.courseId] || `Course ${batch.courseId}`;
            const studentData = studentCounts[batch.batchId] || {
                totalStudents: 0,
                placedStudents: 0
            };
            return {
                course: courseName,
                batch: batch.batchName,
                slug: `${courseName.toLowerCase().replace(/\s+/g, '-')}-${batch.batchName.toLowerCase().replace(/\s+/g, '-')}`,
                logo: batch.batchLogo || "/batches/default-batch-logo.png",
                totalStudents: studentData.totalStudents,
                placedStudents: studentData.placedStudents,
                courseId: batch.courseId,
                batchId: batch.batchId,
                presentationDate: batch.presentationDate,
                courseFees: batch.courseFees
            };
        }).sort((a, b)=>new Date(b.presentationDate) - new Date(a.presentationDate)); // Sort by presentation date
        return Response.json(formattedBatches);
    } catch (error) {
        console.error("Error fetching placement data:", error);
        // Fallback to static data if backend is not available
        return Response.json([
            {
                course: "PG DBDA",
                batch: "Aug 2024",
                slug: "pg-dbda-aug-2024",
                logo: "/batches/default-batch-logo.png",
                totalStudents: 40,
                placedStudents: 36
            },
            {
                course: "PG DBDA",
                batch: "Jan 2024",
                slug: "pg-dbda-jan-2024",
                logo: "/batches/default-batch-logo.png",
                totalStudents: 50,
                placedStudents: 45
            },
            {
                course: "PG DAC",
                batch: "Feb 2023",
                slug: "pg-dac-feb-2023",
                logo: "/batches/default-batch-logo.png",
                totalStudents: 60,
                placedStudents: 51
            }
        ]);
    }
}
}),

};

//# sourceMappingURL=%5Broot-of-the-server%5D__e3a94b33._.js.map
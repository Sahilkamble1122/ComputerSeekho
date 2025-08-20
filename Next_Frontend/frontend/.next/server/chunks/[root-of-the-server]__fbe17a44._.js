module.exports = {

"[project]/.next-internal/server/app/api/placements/[slug]/route/actions.js [app-rsc] (server actions loader, ecmascript)": ((__turbopack_context__) => {

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
"[project]/app/api/placements/[slug]/route.js [app-route] (ecmascript)": ((__turbopack_context__) => {
"use strict";

// export async function GET() {
//   return Response.json({
//     course: "PG DBDA",
//     batch: "Aug 2024",
//     slug: "pg-dbda-aug-2024",
//     students: [
//       {
//         name: "Rohit Sharma",
//         company: "TCS",
//         photo: "/students/rohit.jpg",
//       },
//       {
//         name: "Anjali Verma",
//         company: "Infosys",
//         photo: "/students/anjali.jpg",
//       },
//       {
//         name: "Aditya Patil",
//         company: "Capgemini",
//         photo: "/students/aditya.jpg",
//       },
//       {
//         name: "Neha Singh",
//         company: "Wipro",
//         photo: "/students/neha.jpg",
//       },
//       {
//         name: "Manish Gupta",
//         company: "Cognizant",
//         photo: "/students/manish.jpg",
//       },
//       {
//         name: "Sneha Joshi",
//         company: "Tech Mahindra",
//         photo: "/students/sneha.jpg",
//       },
//       {
//         name: "Karan Mehta",
//         company: "LTI",
//         photo: "/students/karan.jpg",
//       },
//       {
//         name: "Pooja Shah",
//         company: "Persistent",
//         photo: "/students/pooja.jpg",
//       },
//       {
//         name: "Vikas Rao",
//         company: "HCL",
//         photo: "/students/vikas.jpg",
//       },
//       {
//         name: "Riya Jain",
//         company: "Accenture",
//         photo: "/students/riya.jpg",
//       },
//       {
//         name: "Amit Dubey",
//         company: "Mindtree",
//         photo: "/students/amit.jpg",
//       },
//       {
//         name: "Divya Nair",
//         company: "IBM",
//         photo: "/students/divya.jpg",
//       },
//       {
//         name: "Saurabh Mishra",
//         company: "Capgemini",
//         photo: "/students/saurabh.jpg",
//       },
//       {
//         name: "Shraddha Kulkarni",
//         company: "TCS",
//         photo: "/students/shraddha.jpg",
//       },
//       {
//         name: "Rajesh Shetty",
//         company: "Infosys",
//         photo: "/students/rajesh.jpg",
//       },
//       {
//         name: "Meena Raut",
//         company: "LTI",
//         photo: "/students/meena.jpg",
//       },
//     ],
//   });
// }
// app/api/placements/[slug]/route.js
__turbopack_context__.s({
    "GET": ()=>GET
});
var __TURBOPACK__imported__module__$5b$project$5d2f$lib$2f$config$2e$js__$5b$app$2d$route$5d$__$28$ecmascript$29$__ = __turbopack_context__.i("[project]/lib/config.js [app-route] (ecmascript)");
;
async function GET(req, { params }) {
    const { slug } = params;
    try {
        // Resolve slug -> batchId using batches API
        const batchesResponse = await fetch((0, __TURBOPACK__imported__module__$5b$project$5d2f$lib$2f$config$2e$js__$5b$app$2d$route$5d$__$28$ecmascript$29$__["getApiUrl"])(__TURBOPACK__imported__module__$5b$project$5d2f$lib$2f$config$2e$js__$5b$app$2d$route$5d$__$28$ecmascript$29$__["API_CONFIG"].ENDPOINTS.BATCHES));
        if (!batchesResponse.ok) {
            throw new Error(`Failed to fetch batches: ${batchesResponse.status}`);
        }
        const batches = await batchesResponse.json();
        const courseMapping = {
            101: "PG DBDA",
            98: "PG DAC",
            103: "PRE - CAT"
        };
        const slugify = (text)=>text?.toLowerCase().replace(/\s+/g, "-") ?? "";
        const matchedBatch = batches.find((batch)=>{
            const courseName = courseMapping[batch.courseId] || `Course ${batch.courseId}`;
            const computedSlug = `${slugify(courseName)}-${slugify(batch.batchName)}`;
            return computedSlug === slug;
        });
        if (!matchedBatch) {
            return Response.json({
                students: []
            }, {
                status: 200
            });
        }
        const batchId = matchedBatch.batchId;
        // Fetch all students and filter by batchId and isPlaced
        const studentsResponse = await fetch((0, __TURBOPACK__imported__module__$5b$project$5d2f$lib$2f$config$2e$js__$5b$app$2d$route$5d$__$28$ecmascript$29$__["getApiUrl"])(__TURBOPACK__imported__module__$5b$project$5d2f$lib$2f$config$2e$js__$5b$app$2d$route$5d$__$28$ecmascript$29$__["API_CONFIG"].ENDPOINTS.STUDENTS));
        if (!studentsResponse.ok) {
            throw new Error(`Failed to fetch students: ${studentsResponse.status}`);
        }
        const allStudents = await studentsResponse.json();
        const students = (Array.isArray(allStudents) ? allStudents : []).filter((s)=>s.batchId === batchId && s.isPlaced === true).map((s)=>{
            const raw = String(s.photoUrl || "");
            if (raw.startsWith("http")) {
                return {
                    name: s.studentName,
                    photo: raw
                };
            }
            // Normalize to Next.js public path
            let normalized = raw.replace(/\\/g, "/"); // Windows -> URL slashes
            normalized = normalized.replace(/^\/+/, ""); // leading slashes
            normalized = normalized.replace(/^public\//i, ""); // drop leading public/
            const photo = `/${normalized}`; // serve from Next public
            return {
                name: s.studentName,
                photo
            };
        });
        return Response.json({
            students
        });
    } catch (error) {
        console.error("Error in placement details GET:", error);
        return Response.json({
            students: []
        }, {
            status: 200
        });
    }
} //future database connect hone ke baad isko replace karna hai slug ka kuch to dekhna padega isme fetching ke time
 // import { db } from "@/lib/db"; // your DB connection (e.g., Prisma)
 // import { NextResponse } from "next/server";
 // export async function GET(_, { params }) {
 //   const { slug } = params;
 //   const batch = await db.placement.findFirst({
 //     where: { slug },
 //     include: {
 //       students: {
 //         where: { placed: true },
 //         select: {
 //           name: true,
 //           company: true,
 //           photoUrl: true,
 //         },
 //       },
 //     },
 //   });
 //   if (!batch) {
 //     return NextResponse.json({ students: [] }, { status: 404 });
 //   }
 //   const students = batch.students.map((s) => ({
 //     name: s.name,
 //     company: s.company,
 //     photo: s.photoUrl,
 //   }));
 //   return NextResponse.json({ students });
 // }
}),

};

//# sourceMappingURL=%5Broot-of-the-server%5D__fbe17a44._.js.map
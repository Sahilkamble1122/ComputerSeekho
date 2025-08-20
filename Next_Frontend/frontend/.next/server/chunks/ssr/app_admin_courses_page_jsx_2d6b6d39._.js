module.exports = {

"[project]/app/admin/courses/page.jsx [app-ssr] (ecmascript)": ((__turbopack_context__) => {
"use strict";

__turbopack_context__.s({
    "default": ()=>CoursePage
});
var __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__ = __turbopack_context__.i("[project]/node_modules/next/dist/server/route-modules/app-page/vendored/ssr/react-jsx-dev-runtime.js [app-ssr] (ecmascript)");
var __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__ = __turbopack_context__.i("[project]/node_modules/next/dist/server/route-modules/app-page/vendored/ssr/react.js [app-ssr] (ecmascript)");
"use client";
;
;
function CoursePage() {
    const [courses, setCourses] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])([]);
    const [batches, setBatches] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])([]);
    const [activeTab, setActiveTab] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])("courses");
    const [formVisible, setFormVisible] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])(false);
    const [formData, setFormData] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])({
        courseId: "",
        courseName: "",
        courseDescription: "",
        courseDuration: "",
        courseSyllabus: "",
        ageGrpType: "",
        courseIsActive: true,
        coverPhoto: "",
        videoId: null
    });
    const [batchFormData, setBatchFormData] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])({
        batchId: null,
        batchName: "",
        batchStartTime: "",
        batchEndTime: "",
        courseId: "",
        presentationDate: "",
        courseFees: "",
        courseFeesFrom: "",
        courseFeesTo: "",
        batchIsActive: true,
        batchLogo: ""
    });
    const [coverPhotoFile, setCoverPhotoFile] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])(null);
    const [batchLogoFile, setBatchLogoFile] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])(null);
    const [searchTerm, setSearchTerm] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])("");
    const [batchSearchTerm, setBatchSearchTerm] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])("");
    const [isSubmitting, setIsSubmitting] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])(false);
    const [submitError, setSubmitError] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])("");
    const [submitSuccess, setSubmitSuccess] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])("");
    // Pagination state for courses
    const [currentCoursePage, setCurrentCoursePage] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])(1);
    const [coursesPerPage] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])(10);
    // Pagination state for batches
    const [currentBatchPage, setCurrentBatchPage] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])(1);
    const [batchesPerPage] = (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useState"])(10);
    const API_BASE = "http://localhost:8080/api";
    (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useEffect"])(()=>{
        fetchCourses();
        fetchBatches();
    }, []);
    const fetchCourses = async ()=>{
        try {
            const token = sessionStorage.getItem("token");
            const response = await fetch(`${API_BASE}/courses`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            if (!response.ok) {
                if (response.status === 404) {
                    console.error("Backend API endpoint not found. Please ensure your Spring Boot backend is running.");
                    return;
                }
                throw new Error(`HTTP ${response.status}: ${response.statusText}`);
            }
            const data = await response.json();
            setCourses(data);
        } catch (error) {
            console.error("Error fetching courses:", error);
        }
    };
    const fetchBatches = async ()=>{
        try {
            const token = sessionStorage.getItem("token");
            const response = await fetch(`${API_BASE}/batches`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            if (!response.ok) {
                if (response.status === 404) {
                    console.error("Backend API endpoint not found. Please ensure your Spring Boot backend is running.");
                    return;
                }
                throw new Error(`HTTP ${response.status}: ${response.statusText}`);
            }
            const data = await response.json();
            setBatches(data);
        } catch (error) {
            console.error("Error fetching batches:", error);
        }
    };
    const handleChange = (e)=>{
        const { name, value, type, checked } = e.target;
        setFormData({
            ...formData,
            [name]: type === "checkbox" ? checked : value
        });
    };
    const handleBatchChange = (e)=>{
        const { name, value, type, checked } = e.target;
        setBatchFormData({
            ...batchFormData,
            [name]: type === "checkbox" ? checked : value
        });
    };
    const handleFileChange = (e)=>{
        const file = e.target.files[0];
        setCoverPhotoFile(file);
    };
    const uploadFile = async (file)=>{
        if (!file) return "";
        const formData = new FormData();
        formData.append("file", file);
        try {
            const token = sessionStorage.getItem("token");
            const res = await fetch("/api/courses/upload", {
                method: "POST",
                headers: {
                    Authorization: `Bearer ${token}`
                },
                body: formData
            });
            if (res.ok) {
                const data = await res.json();
                return data.filePath || "";
            }
        } catch (err) {
            console.error("File upload error", err);
        }
        return "";
    };
    const resetCourseForm = ()=>{
        setFormData({
            courseId: "",
            courseName: "",
            courseDescription: "",
            courseDuration: "",
            courseSyllabus: "",
            ageGrpType: "",
            courseIsActive: true,
            coverPhoto: "",
            videoId: null
        });
        setCoverPhotoFile(null);
    };
    const resetBatchForm = ()=>{
        setBatchFormData({
            batchId: null,
            batchName: "",
            batchStartTime: "",
            batchEndTime: "",
            courseId: "",
            presentationDate: "",
            courseFees: "",
            courseFeesFrom: "",
            courseFeesTo: "",
            batchIsActive: true,
            batchLogo: ""
        });
        setBatchLogoFile(null);
    };
    const closeForm = ()=>{
        setFormVisible(false);
        if (activeTab === "courses") {
            resetCourseForm();
        } else {
            resetBatchForm();
        }
    };
    // Function to scroll to form smoothly
    const scrollToForm = ()=>{
        setTimeout(()=>{
            // More specific selector to find the form container
            let formElement = null;
            // Try to find the form based on the active tab
            if (activeTab === "courses") {
                formElement = document.querySelector('form[onSubmit]')?.closest('.bg-white.p-6.rounded.shadow');
            } else if (activeTab === "batches") {
                // For batch form, look for the specific batch form container
                formElement = document.querySelector('form[onSubmit="handleBatchSubmit"]')?.closest('.bg-white.p-6.rounded.shadow');
            }
            // Fallback: try to find any form container
            if (!formElement) {
                formElement = document.querySelector('.bg-white.p-6.rounded.shadow.space-y-6') || document.querySelector('.bg-white.p-6.rounded.shadow.space-y-4:last-child');
            }
            if (formElement) {
                formElement.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start',
                    inline: 'nearest'
                });
            } else {
                console.log('Form element not found for scrolling');
            }
        }, 150); // Increased delay to ensure form is rendered
    };
    const handleSubmit = async (e)=>{
        e.preventDefault();
        setIsSubmitting(true);
        setSubmitError("");
        // Validate required fields
        if (!formData.courseName || !formData.courseDescription || !formData.courseDuration || !formData.courseSyllabus || !formData.ageGrpType) {
            setSubmitError("Please fill in all required fields: Course Name, Description, Duration, Syllabus, and Age Group");
            setIsSubmitting(false);
            return;
        }
        // Validate field lengths and formats
        if (formData.courseName.trim().length < 2 || formData.courseName.trim().length > 100) {
            setSubmitError("Course name must be between 2 and 100 characters");
            setIsSubmitting(false);
            return;
        }
        if (formData.courseDescription.trim().length > 1000) {
            setSubmitError("Course description must not exceed 1000 characters");
            setIsSubmitting(false);
            return;
        }
        if (formData.courseSyllabus.trim().length > 2000) {
            setSubmitError("Course syllabus must not exceed 2000 characters");
            setIsSubmitting(false);
            return;
        }
        // Validate course duration is a positive number
        const duration = parseInt(formData.courseDuration);
        if (isNaN(duration) || duration <= 0) {
            setSubmitError("Course duration must be a positive number");
            setIsSubmitting(false);
            return;
        }
        const uploadedCoverPath = await uploadFile(coverPhotoFile);
        const payload = {
            courseId: formData.courseId || null,
            courseName: formData.courseName.trim(),
            courseDescription: formData.courseDescription.trim(),
            courseDuration: duration,
            courseSyllabus: formData.courseSyllabus.trim(),
            ageGrpType: formData.ageGrpType.trim(),
            courseIsActive: formData.courseIsActive,
            coverPhoto: uploadedCoverPath || formData.coverPhoto || "",
            videoId: null
        };
        try {
            const token = sessionStorage.getItem("token");
            if (!token) {
                setSubmitError("Authentication token not found. Please log in again.");
                setIsSubmitting(false);
                return;
            }
            const url = formData.courseId ? `${API_BASE}/courses/${formData.courseId}` : `${API_BASE}/courses`;
            let requestBody;
            try {
                requestBody = JSON.stringify(payload);
            } catch (serializeError) {
                console.error("Error serializing payload:", serializeError);
                setSubmitError("Error preparing data for submission");
                setIsSubmitting(false);
                return;
            }
            const response = await fetch(url, {
                method: formData.courseId ? "PUT" : "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`,
                    "Accept": "application/json",
                    "Cache-Control": "no-cache"
                },
                body: requestBody
            });
            if (response.ok) {
                setSubmitSuccess(formData.courseId ? "Course Updated Successfully!" : "Course Added Successfully!");
                setTimeout(()=>{
                    resetCourseForm();
                    closeForm();
                    fetchCourses();
                }, 1500);
            } else {
                let errorMessage = 'Unknown error';
                let responseText = '';
                try {
                    responseText = await response.text();
                    if (responseText && responseText.trim()) {
                        try {
                            const errorData = JSON.parse(responseText);
                            errorMessage = errorData.message || errorData.error || `HTTP ${response.status}: ${response.statusText}`;
                        } catch (jsonParseError) {
                            errorMessage = responseText;
                        }
                    } else {
                        errorMessage = `HTTP ${response.status}: ${response.statusText}`;
                    }
                } catch (textError) {
                    errorMessage = `HTTP ${response.status}: ${response.statusText}`;
                }
                if (response.status === 400) {
                    setSubmitError(`Bad Request: ${errorMessage}. Please check your input data.`);
                } else {
                    setSubmitError(`Save failed: ${errorMessage}`);
                }
            }
        } catch (err) {
            console.error("Error saving course:", err);
            setSubmitError("Error saving course. Please try again.");
        } finally{
            setIsSubmitting(false);
        }
    };
    const handleBatchSubmit = async (e)=>{
        e.preventDefault();
        // Validate required fields
        if (!batchFormData.batchName || !batchFormData.courseId || !batchFormData.courseFees) {
            alert("Please fill in all required fields");
            return;
        }
        let logoPath = batchFormData.batchLogo || "";
        // Upload logo if new file is selected
        if (batchLogoFile) {
            const formData = new FormData();
            formData.append("file", batchLogoFile);
            try {
                const res = await fetch("/api/uploadBatchLogo", {
                    method: "POST",
                    body: formData
                });
                if (!res.ok) throw new Error("Upload failed");
                const data = await res.json();
                logoPath = data.path;
            } catch (error) {
                alert("Logo upload failed. Please try again.");
                return;
            }
        }
        const method = batchFormData.batchId ? "PUT" : "POST";
        const url = batchFormData.batchId ? `${API_BASE}/batches/${batchFormData.batchId}` : `${API_BASE}/batches`;
        const batchData = {
            ...batchFormData,
            batchLogo: logoPath
        };
        try {
            const token = sessionStorage.getItem("token");
            const res = await fetch(url, {
                method,
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`
                },
                body: JSON.stringify(batchData)
            });
            if (res.ok) {
                alert(`✅ Batch ${batchFormData.batchId ? "updated" : "created"} successfully!`);
                resetBatchForm();
                closeForm();
                fetchBatches();
            } else {
                const errorData = await res.json();
                alert(`❌ Failed to save batch: ${errorData.message || 'Unknown error'}`);
            }
        } catch (err) {
            console.error("Error saving batch", err);
            alert("Error saving batch. Please try again.");
        }
    };
    const handleEdit = (course)=>{
        setFormVisible(true);
        setFormData({
            ...course,
            courseDuration: course.courseDuration?.toString() || "",
            videoId: course.videoId || null
        });
    };
    const handleDelete = async (courseId)=>{
        if (!window.confirm("Are you sure you want to delete this course?")) return;
        try {
            const token = sessionStorage.getItem("token");
            const res = await fetch(`${API_BASE}/courses/${courseId}`, {
                method: "DELETE",
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            if (res.ok) {
                fetchCourses();
                alert("Course deleted successfully!");
            } else {
                alert("Delete failed");
            }
        } catch (err) {
            console.error("Error deleting course:", err);
        }
    };
    const handleBatchEdit = (batch)=>{
        setFormVisible(true);
        setBatchFormData({
            ...batch,
            courseFees: batch.courseFees?.toString() || ""
        });
    };
    const handleBatchLogoChange = (e)=>{
        const file = e.target.files[0];
        setBatchLogoFile(file);
    };
    const handleBatchDelete = async (batchId)=>{
        if (!window.confirm("Are you sure you want to delete this batch?")) return;
        try {
            const token = sessionStorage.getItem("token");
            const res = await fetch(`${API_BASE}/batches/${batchId}`, {
                method: "DELETE",
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            if (res.ok) {
                fetchBatches();
                alert("Batch deleted successfully!");
            } else {
                alert("Delete failed");
            }
        } catch (err) {
            console.error("Error deleting batch", err);
        }
    };
    const filteredCourses = courses.filter((course)=>course.courseName.toLowerCase().includes(searchTerm.toLowerCase()) || course.courseDescription.toLowerCase().includes(searchTerm.toLowerCase()));
    const filteredBatches = batches.filter((batch)=>(batch.batchName || "").toLowerCase().includes(batchSearchTerm.toLowerCase()));
    // Pagination logic for courses
    const indexOfLastCourse = currentCoursePage * coursesPerPage;
    const indexOfFirstCourse = indexOfLastCourse - coursesPerPage;
    const currentCourses = filteredCourses.slice(indexOfFirstCourse, indexOfLastCourse);
    const totalCoursePages = Math.ceil(filteredCourses.length / coursesPerPage);
    // Pagination logic for batches
    const indexOfLastBatch = currentBatchPage * batchesPerPage;
    const indexOfFirstBatch = indexOfLastBatch - batchesPerPage;
    const currentBatches = filteredBatches.slice(indexOfFirstBatch, indexOfLastBatch);
    const totalBatchPages = Math.ceil(filteredBatches.length / batchesPerPage);
    // Pagination handlers for courses
    const handleCoursePageChange = (pageNumber)=>{
        setCurrentCoursePage(pageNumber);
    };
    // Pagination handlers for batches
    const handleBatchPageChange = (pageNumber)=>{
        setCurrentBatchPage(pageNumber);
    };
    // Reset pagination when search changes
    (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useEffect"])(()=>{
        setCurrentCoursePage(1);
    }, [
        searchTerm
    ]);
    (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["useEffect"])(()=>{
        setCurrentBatchPage(1);
    }, [
        batchSearchTerm
    ]);
    return /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
        className: "p-6 max-w-6xl mx-auto space-y-8",
        children: [
            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("h1", {
                className: "text-3xl font-bold text-center text-black mb-4",
                children: "Course & Batch Management"
            }, void 0, false, {
                fileName: "[project]/app/admin/courses/page.jsx",
                lineNumber: 525,
                columnNumber: 7
            }, this),
            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                className: "flex space-x-1 bg-gray-100 p-1 rounded-lg",
                children: [
                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                        onClick: ()=>setActiveTab("courses"),
                        className: `flex-1 py-2 px-4 rounded-md font-medium transition-colors ${activeTab === "courses" ? "bg-white text-black shadow-sm" : "text-gray-600 hover:text-gray-800"}`,
                        children: "Courses"
                    }, void 0, false, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 531,
                        columnNumber: 9
                    }, this),
                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                        onClick: ()=>setActiveTab("batches"),
                        className: `flex-1 py-2 px-4 rounded-md font-medium transition-colors ${activeTab === "batches" ? "bg-white text-black shadow-sm" : "text-gray-600 hover:text-gray-800"}`,
                        children: "Batches"
                    }, void 0, false, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 541,
                        columnNumber: 9
                    }, this)
                ]
            }, void 0, true, {
                fileName: "[project]/app/admin/courses/page.jsx",
                lineNumber: 530,
                columnNumber: 7
            }, this),
            activeTab === "courses" && /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                className: "bg-white p-6 rounded shadow space-y-4",
                children: [
                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                        className: "flex justify-between items-center",
                        children: [
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("h2", {
                                className: "text-xl font-semibold text-black",
                                children: "Courses List"
                            }, void 0, false, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 557,
                                columnNumber: 13
                            }, this),
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                onClick: ()=>{
                                    resetCourseForm();
                                    setFormVisible(true);
                                    scrollToForm(); // Call the new function here
                                },
                                className: "bg-black text-white px-4 py-2 rounded hover:opacity-90",
                                children: "Add New Course"
                            }, void 0, false, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 558,
                                columnNumber: 13
                            }, this)
                        ]
                    }, void 0, true, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 556,
                        columnNumber: 11
                    }, this),
                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                        type: "text",
                        placeholder: "Search by name or description",
                        value: searchTerm,
                        onChange: (e)=>setSearchTerm(e.target.value),
                        className: "border p-2 rounded w-full md:w-1/2"
                    }, void 0, false, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 570,
                        columnNumber: 11
                    }, this),
                    filteredCourses.length === 0 ? /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("p", {
                        className: "text-gray-500",
                        children: "No courses found."
                    }, void 0, false, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 579,
                        columnNumber: 13
                    }, this) : /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                        className: "overflow-x-auto",
                        children: [
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("table", {
                                className: "min-w-full divide-y divide-gray-200 border",
                                children: [
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("thead", {
                                        className: "bg-gray-50",
                                        children: /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("tr", {
                                            children: [
                                                /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("th", {
                                                    className: "px-6 py-3 text-left text-sm font-medium text-gray-700",
                                                    children: "Name"
                                                }, void 0, false, {
                                                    fileName: "[project]/app/admin/courses/page.jsx",
                                                    lineNumber: 585,
                                                    columnNumber: 21
                                                }, this),
                                                /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("th", {
                                                    className: "px-6 py-3 text-left text-sm font-medium text-gray-700",
                                                    children: "Description"
                                                }, void 0, false, {
                                                    fileName: "[project]/app/admin/courses/page.jsx",
                                                    lineNumber: 588,
                                                    columnNumber: 21
                                                }, this),
                                                /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("th", {
                                                    className: "px-6 py-3 text-left text-sm font-medium text-gray-700",
                                                    children: "Duration"
                                                }, void 0, false, {
                                                    fileName: "[project]/app/admin/courses/page.jsx",
                                                    lineNumber: 591,
                                                    columnNumber: 21
                                                }, this),
                                                /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("th", {
                                                    className: "px-6 py-3 text-left text-sm font-medium text-gray-700",
                                                    children: "Age Group"
                                                }, void 0, false, {
                                                    fileName: "[project]/app/admin/courses/page.jsx",
                                                    lineNumber: 594,
                                                    columnNumber: 21
                                                }, this),
                                                /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("th", {
                                                    className: "px-6 py-3 text-left text-sm font-medium text-gray-700",
                                                    children: "Actions"
                                                }, void 0, false, {
                                                    fileName: "[project]/app/admin/courses/page.jsx",
                                                    lineNumber: 597,
                                                    columnNumber: 21
                                                }, this)
                                            ]
                                        }, void 0, true, {
                                            fileName: "[project]/app/admin/courses/page.jsx",
                                            lineNumber: 584,
                                            columnNumber: 19
                                        }, this)
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 583,
                                        columnNumber: 17
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("tbody", {
                                        className: "bg-white divide-y divide-gray-200",
                                        children: currentCourses.map((course)=>/*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("tr", {
                                                children: [
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("td", {
                                                        className: "px-6 py-4 whitespace-nowrap text-sm text-gray-900",
                                                        children: course.courseName
                                                    }, void 0, false, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 605,
                                                        columnNumber: 23
                                                    }, this),
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("td", {
                                                        className: "px-6 py-4 whitespace-nowrap text-sm text-gray-600",
                                                        children: /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("span", {
                                                            title: course.courseDescription,
                                                            className: "cursor-help",
                                                            children: course.courseDescription.length > 50 ? `${course.courseDescription.substring(0, 50)}...` : course.courseDescription
                                                        }, void 0, false, {
                                                            fileName: "[project]/app/admin/courses/page.jsx",
                                                            lineNumber: 609,
                                                            columnNumber: 25
                                                        }, this)
                                                    }, void 0, false, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 608,
                                                        columnNumber: 23
                                                    }, this),
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("td", {
                                                        className: "px-6 py-4 whitespace-nowrap text-sm text-gray-600",
                                                        children: [
                                                            course.courseDuration,
                                                            " days"
                                                        ]
                                                    }, void 0, true, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 619,
                                                        columnNumber: 23
                                                    }, this),
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("td", {
                                                        className: "px-6 py-4 whitespace-nowrap text-sm text-gray-600",
                                                        children: course.ageGrpType
                                                    }, void 0, false, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 622,
                                                        columnNumber: 23
                                                    }, this),
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("td", {
                                                        className: "px-6 py-4 whitespace-nowrap text-sm flex gap-4",
                                                        children: [
                                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                                                onClick: ()=>handleEdit(course),
                                                                className: "text-blue-600 underline hover:opacity-80",
                                                                children: "Edit"
                                                            }, void 0, false, {
                                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                                lineNumber: 626,
                                                                columnNumber: 25
                                                            }, this),
                                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                                                onClick: ()=>handleDelete(course.courseId),
                                                                className: "text-red-600 underline hover:opacity-80",
                                                                children: "Delete"
                                                            }, void 0, false, {
                                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                                lineNumber: 632,
                                                                columnNumber: 25
                                                            }, this)
                                                        ]
                                                    }, void 0, true, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 625,
                                                        columnNumber: 23
                                                    }, this)
                                                ]
                                            }, course.courseId, true, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 604,
                                                columnNumber: 21
                                            }, this))
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 602,
                                        columnNumber: 17
                                    }, this)
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 582,
                                columnNumber: 15
                            }, this),
                            totalCoursePages > 1 && /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                className: "flex justify-center items-center space-x-2 mt-4",
                                children: [
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                        onClick: ()=>handleCoursePageChange(currentCoursePage - 1),
                                        disabled: currentCoursePage === 1,
                                        className: "px-3 py-2 text-sm font-medium text-gray-500 bg-white border border-gray-300 rounded-md hover:bg-gray-50 disabled:opacity-50 disabled:cursor-not-allowed",
                                        children: "Previous"
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 647,
                                        columnNumber: 19
                                    }, this),
                                    Array.from({
                                        length: totalCoursePages
                                    }, (_, index)=>index + 1).map((page)=>/*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                            onClick: ()=>handleCoursePageChange(page),
                                            className: `px-3 py-2 text-sm font-medium rounded-md ${currentCoursePage === page ? "bg-black text-white" : "text-gray-500 bg-white border border-gray-300 hover:bg-gray-50"}`,
                                            children: page
                                        }, page, false, {
                                            fileName: "[project]/app/admin/courses/page.jsx",
                                            lineNumber: 661,
                                            columnNumber: 21
                                        }, this)),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                        onClick: ()=>handleCoursePageChange(currentCoursePage + 1),
                                        disabled: currentCoursePage === totalCoursePages,
                                        className: "px-3 py-2 text-sm font-medium text-gray-500 bg-white border border-gray-300 rounded-md hover:bg-gray-50 disabled:opacity-50 disabled:cursor-not-allowed",
                                        children: "Next"
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 674,
                                        columnNumber: 19
                                    }, this)
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 646,
                                columnNumber: 17
                            }, this),
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                className: "text-sm text-gray-500 text-center mt-2",
                                children: [
                                    "Showing ",
                                    indexOfFirstCourse + 1,
                                    " to",
                                    " ",
                                    Math.min(indexOfLastCourse, filteredCourses.length),
                                    " of",
                                    " ",
                                    filteredCourses.length,
                                    " courses"
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 686,
                                columnNumber: 15
                            }, this)
                        ]
                    }, void 0, true, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 581,
                        columnNumber: 13
                    }, this)
                ]
            }, void 0, true, {
                fileName: "[project]/app/admin/courses/page.jsx",
                lineNumber: 555,
                columnNumber: 9
            }, this),
            activeTab === "batches" && /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                className: "bg-white p-6 rounded shadow space-y-4",
                children: [
                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                        className: "flex justify-between items-center",
                        children: [
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("h2", {
                                className: "text-xl font-semibold text-black",
                                children: "Batches List"
                            }, void 0, false, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 700,
                                columnNumber: 13
                            }, this),
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                onClick: ()=>{
                                    resetBatchForm();
                                    setFormVisible(true);
                                    scrollToForm(); // Add automatic scrolling for batches
                                },
                                className: "bg-black text-white px-4 py-2 rounded hover:opacity-90",
                                children: "Add New Batch"
                            }, void 0, false, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 701,
                                columnNumber: 13
                            }, this)
                        ]
                    }, void 0, true, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 699,
                        columnNumber: 11
                    }, this),
                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                        type: "text",
                        placeholder: "Search by batch name",
                        value: batchSearchTerm,
                        onChange: (e)=>setBatchSearchTerm(e.target.value),
                        className: "border p-2 rounded w-full md:w-1/2"
                    }, void 0, false, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 713,
                        columnNumber: 11
                    }, this),
                    filteredBatches.length === 0 ? /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("p", {
                        className: "text-gray-500",
                        children: "No batches found."
                    }, void 0, false, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 722,
                        columnNumber: 13
                    }, this) : /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                        className: "overflow-x-auto",
                        children: [
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("table", {
                                className: "min-w-full divide-y divide-gray-200 border",
                                children: [
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("thead", {
                                        className: "bg-gray-50",
                                        children: /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("tr", {
                                            children: [
                                                /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("th", {
                                                    className: "px-6 py-3 text-left text-sm font-medium text-gray-700",
                                                    children: "Batch ID"
                                                }, void 0, false, {
                                                    fileName: "[project]/app/admin/courses/page.jsx",
                                                    lineNumber: 728,
                                                    columnNumber: 21
                                                }, this),
                                                /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("th", {
                                                    className: "px-6 py-3 text-left text-sm font-medium text-gray-700",
                                                    children: "Name"
                                                }, void 0, false, {
                                                    fileName: "[project]/app/admin/courses/page.jsx",
                                                    lineNumber: 731,
                                                    columnNumber: 21
                                                }, this),
                                                /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("th", {
                                                    className: "px-6 py-3 text-left text-sm font-medium text-gray-700",
                                                    children: "Time"
                                                }, void 0, false, {
                                                    fileName: "[project]/app/admin/courses/page.jsx",
                                                    lineNumber: 734,
                                                    columnNumber: 21
                                                }, this),
                                                /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("th", {
                                                    className: "px-6 py-3 text-left text-sm font-medium text-gray-700",
                                                    children: "Course"
                                                }, void 0, false, {
                                                    fileName: "[project]/app/admin/courses/page.jsx",
                                                    lineNumber: 737,
                                                    columnNumber: 21
                                                }, this),
                                                /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("th", {
                                                    className: "px-6 py-3 text-left text-sm font-medium text-gray-700",
                                                    children: "Fees"
                                                }, void 0, false, {
                                                    fileName: "[project]/app/admin/courses/page.jsx",
                                                    lineNumber: 740,
                                                    columnNumber: 21
                                                }, this),
                                                /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("th", {
                                                    className: "px-6 py-3 text-left text-sm font-medium text-gray-700",
                                                    children: "Presentation"
                                                }, void 0, false, {
                                                    fileName: "[project]/app/admin/courses/page.jsx",
                                                    lineNumber: 743,
                                                    columnNumber: 21
                                                }, this),
                                                /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("th", {
                                                    className: "px-6 py-3 text-left text-sm font-medium text-gray-700",
                                                    children: "Status"
                                                }, void 0, false, {
                                                    fileName: "[project]/app/admin/courses/page.jsx",
                                                    lineNumber: 746,
                                                    columnNumber: 21
                                                }, this),
                                                /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("th", {
                                                    className: "px-6 py-3 text-left text-sm font-medium text-gray-700",
                                                    children: "Actions"
                                                }, void 0, false, {
                                                    fileName: "[project]/app/admin/courses/page.jsx",
                                                    lineNumber: 749,
                                                    columnNumber: 21
                                                }, this)
                                            ]
                                        }, void 0, true, {
                                            fileName: "[project]/app/admin/courses/page.jsx",
                                            lineNumber: 727,
                                            columnNumber: 19
                                        }, this)
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 726,
                                        columnNumber: 17
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("tbody", {
                                        className: "bg-white divide-y divide-gray-200",
                                        children: currentBatches.map((batch)=>/*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("tr", {
                                                children: [
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("td", {
                                                        className: "px-6 py-4 whitespace-nowrap text-sm text-gray-900",
                                                        children: batch.batchId
                                                    }, void 0, false, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 757,
                                                        columnNumber: 23
                                                    }, this),
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("td", {
                                                        className: "px-6 py-4 whitespace-nowrap text-sm text-gray-900",
                                                        children: batch.batchName
                                                    }, void 0, false, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 760,
                                                        columnNumber: 23
                                                    }, this),
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("td", {
                                                        className: "px-6 py-4 whitespace-nowrap text-sm text-gray-600",
                                                        children: [
                                                            batch.batchStartTime,
                                                            " - ",
                                                            batch.batchEndTime
                                                        ]
                                                    }, void 0, true, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 763,
                                                        columnNumber: 23
                                                    }, this),
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("td", {
                                                        className: "px-6 py-4 whitespace-nowrap text-sm text-gray-600",
                                                        children: batch.courseId
                                                    }, void 0, false, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 766,
                                                        columnNumber: 23
                                                    }, this),
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("td", {
                                                        className: "px-6 py-4 whitespace-nowrap text-sm text-gray-600",
                                                        children: [
                                                            "₹",
                                                            batch.courseFees
                                                        ]
                                                    }, void 0, true, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 769,
                                                        columnNumber: 23
                                                    }, this),
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("td", {
                                                        className: "px-6 py-4 whitespace-nowrap text-sm text-gray-600",
                                                        children: batch.presentationDate
                                                    }, void 0, false, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 772,
                                                        columnNumber: 23
                                                    }, this),
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("td", {
                                                        className: "px-6 py-4 whitespace-nowrap text-sm",
                                                        children: /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("span", {
                                                            className: `inline-flex px-2 py-1 text-xs font-semibold rounded-full ${batch.batchIsActive ? "bg-green-100 text-green-800" : "bg-red-100 text-red-800"}`,
                                                            children: batch.batchIsActive ? "Active" : "Inactive"
                                                        }, void 0, false, {
                                                            fileName: "[project]/app/admin/courses/page.jsx",
                                                            lineNumber: 776,
                                                            columnNumber: 25
                                                        }, this)
                                                    }, void 0, false, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 775,
                                                        columnNumber: 23
                                                    }, this),
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("td", {
                                                        className: "px-6 py-4 whitespace-nowrap text-sm flex gap-4",
                                                        children: [
                                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                                                onClick: ()=>handleBatchEdit(batch),
                                                                className: "text-blue-600 underline hover:opacity-80",
                                                                children: "Edit"
                                                            }, void 0, false, {
                                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                                lineNumber: 787,
                                                                columnNumber: 25
                                                            }, this),
                                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                                                onClick: ()=>handleBatchDelete(batch.batchId),
                                                                className: "text-red-600 underline hover:opacity-80",
                                                                children: "Delete"
                                                            }, void 0, false, {
                                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                                lineNumber: 793,
                                                                columnNumber: 25
                                                            }, this)
                                                        ]
                                                    }, void 0, true, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 786,
                                                        columnNumber: 23
                                                    }, this)
                                                ]
                                            }, batch.batchId, true, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 756,
                                                columnNumber: 21
                                            }, this))
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 754,
                                        columnNumber: 17
                                    }, this)
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 725,
                                columnNumber: 15
                            }, this),
                            totalBatchPages > 1 && /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                className: "flex justify-center items-center space-x-2 mt-4",
                                children: [
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                        onClick: ()=>handleBatchPageChange(currentBatchPage - 1),
                                        disabled: currentBatchPage === 1,
                                        className: "px-3 py-2 text-sm font-medium text-gray-500 bg-white border border-gray-300 rounded-md hover:bg-gray-50 disabled:opacity-50 disabled:cursor-not-allowed",
                                        children: "Previous"
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 808,
                                        columnNumber: 19
                                    }, this),
                                    Array.from({
                                        length: totalBatchPages
                                    }, (_, index)=>index + 1).map((page)=>/*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                            onClick: ()=>handleBatchPageChange(page),
                                            className: `px-3 py-2 text-sm font-medium rounded-md ${currentBatchPage === page ? "bg-black text-white" : "text-gray-500 bg-white border border-gray-300 hover:bg-gray-50"}`,
                                            children: page
                                        }, page, false, {
                                            fileName: "[project]/app/admin/courses/page.jsx",
                                            lineNumber: 820,
                                            columnNumber: 21
                                        }, this)),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                        onClick: ()=>handleBatchPageChange(currentBatchPage + 1),
                                        disabled: currentBatchPage === totalBatchPages,
                                        className: "px-3 py-2 text-sm font-medium text-gray-500 bg-white border border-gray-300 rounded-md hover:bg-gray-50 disabled:opacity-50 disabled:cursor-not-allowed",
                                        children: "Next"
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 833,
                                        columnNumber: 19
                                    }, this)
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 807,
                                columnNumber: 17
                            }, this),
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                className: "text-sm text-gray-500 text-center mt-2",
                                children: [
                                    "Showing ",
                                    indexOfFirstBatch + 1,
                                    " to",
                                    " ",
                                    Math.min(indexOfLastBatch, filteredBatches.length),
                                    " of",
                                    " ",
                                    filteredBatches.length,
                                    " batches"
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 843,
                                columnNumber: 15
                            }, this)
                        ]
                    }, void 0, true, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 724,
                        columnNumber: 13
                    }, this)
                ]
            }, void 0, true, {
                fileName: "[project]/app/admin/courses/page.jsx",
                lineNumber: 698,
                columnNumber: 9
            }, this),
            formVisible && activeTab === "courses" && /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                className: "bg-white p-6 rounded shadow space-y-4",
                children: [
                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                        className: "flex justify-between items-center",
                        children: [
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("h2", {
                                className: "text-xl font-semibold text-black",
                                children: formData.courseId ? "Edit Course" : "Add New Course"
                            }, void 0, false, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 857,
                                columnNumber: 13
                            }, this),
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                onClick: closeForm,
                                className: "text-gray-500 hover:text-gray-700 text-2xl font-bold",
                                children: "×"
                            }, void 0, false, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 860,
                                columnNumber: 13
                            }, this)
                        ]
                    }, void 0, true, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 856,
                        columnNumber: 11
                    }, this),
                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("form", {
                        onSubmit: handleSubmit,
                        className: "space-y-4",
                        children: [
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                className: "grid grid-cols-1 md:grid-cols-2 gap-4",
                                children: [
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                        className: "block",
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("span", {
                                                className: "text-gray-700",
                                                children: "Course Name *"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 871,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                                type: "text",
                                                name: "courseName",
                                                value: formData.courseName,
                                                onChange: handleChange,
                                                className: "border p-2 rounded w-full",
                                                required: true
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 872,
                                                columnNumber: 17
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 870,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                        className: "block",
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("span", {
                                                className: "text-gray-700",
                                                children: "Description *"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 883,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                                type: "text",
                                                name: "courseDescription",
                                                value: formData.courseDescription,
                                                onChange: handleChange,
                                                className: "border p-2 rounded w-full",
                                                required: true
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 884,
                                                columnNumber: 17
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 882,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                        className: "block",
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("span", {
                                                className: "text-gray-700",
                                                children: "Duration (days) *"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 895,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                                type: "number",
                                                name: "courseDuration",
                                                value: formData.courseDuration,
                                                onChange: handleChange,
                                                className: "border p-2 rounded w-full",
                                                min: "1",
                                                required: true
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 896,
                                                columnNumber: 17
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 894,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                        className: "block",
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("span", {
                                                className: "text-gray-700",
                                                children: "Age Group Type *"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 908,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                                type: "text",
                                                name: "ageGrpType",
                                                value: formData.ageGrpType,
                                                onChange: handleChange,
                                                className: "border p-2 rounded w-full",
                                                placeholder: "e.g., 20-30, Adults, Teens, etc.",
                                                required: true
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 909,
                                                columnNumber: 17
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 907,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                        className: "block",
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("span", {
                                                className: "text-gray-700",
                                                children: "Cover Photo"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 921,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                                type: "file",
                                                accept: "image/*",
                                                onChange: handleFileChange,
                                                className: "border p-2 rounded w-full"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 922,
                                                columnNumber: 17
                                            }, this),
                                            formData.coverPhoto && /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("img", {
                                                src: formData.coverPhoto,
                                                alt: "Current cover",
                                                className: "mt-2 w-24 h-24 object-cover border rounded"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 929,
                                                columnNumber: 19
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 920,
                                        columnNumber: 15
                                    }, this)
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 869,
                                columnNumber: 13
                            }, this),
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                className: "block",
                                children: [
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("span", {
                                        className: "text-gray-700",
                                        children: "Syllabus *"
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 939,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("textarea", {
                                        name: "courseSyllabus",
                                        value: formData.courseSyllabus,
                                        onChange: handleChange,
                                        className: "border rounded p-2 w-full",
                                        rows: 3,
                                        placeholder: "Enter course syllabus...",
                                        required: true
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 940,
                                        columnNumber: 15
                                    }, this)
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 938,
                                columnNumber: 13
                            }, this),
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                className: "flex items-center space-x-2",
                                children: [
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                        type: "checkbox",
                                        name: "courseIsActive",
                                        checked: formData.courseIsActive,
                                        onChange: handleChange,
                                        style: {
                                            accentColor: "rgb(0,0,0)"
                                        }
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 952,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("span", {
                                        children: "Is Active?"
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 959,
                                        columnNumber: 15
                                    }, this)
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 951,
                                columnNumber: 13
                            }, this),
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                className: "flex gap-4",
                                children: [
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                        type: "submit",
                                        disabled: isSubmitting,
                                        className: "bg-black text-white px-6 py-2 rounded hover:opacity-90 disabled:opacity-50 disabled:cursor-not-allowed",
                                        children: isSubmitting ? "Saving..." : formData.courseId ? "Update Course" : "Add Course"
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 963,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                        type: "button",
                                        onClick: closeForm,
                                        disabled: isSubmitting,
                                        className: "bg-gray-500 text-white px-6 py-2 rounded hover:opacity-90",
                                        children: "Cancel"
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 970,
                                        columnNumber: 15
                                    }, this)
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 962,
                                columnNumber: 13
                            }, this),
                            submitError && /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                className: "bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded",
                                children: [
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("strong", {
                                        children: "Error:"
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 983,
                                        columnNumber: 17
                                    }, this),
                                    " ",
                                    submitError
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 982,
                                columnNumber: 15
                            }, this)
                        ]
                    }, void 0, true, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 868,
                        columnNumber: 11
                    }, this)
                ]
            }, void 0, true, {
                fileName: "[project]/app/admin/courses/page.jsx",
                lineNumber: 855,
                columnNumber: 9
            }, this),
            formVisible && activeTab === "batches" && /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                className: "bg-white p-6 rounded shadow space-y-6",
                children: [
                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                        className: "flex justify-between items-center",
                        children: [
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("h2", {
                                className: "text-xl font-semibold text-black mb-2",
                                children: batchFormData.batchId ? "Edit Batch" : "Create New Batch"
                            }, void 0, false, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 994,
                                columnNumber: 13
                            }, this),
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                onClick: closeForm,
                                className: "text-gray-500 hover:text-gray-700 text-2xl font-bold",
                                children: "×"
                            }, void 0, false, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 997,
                                columnNumber: 13
                            }, this)
                        ]
                    }, void 0, true, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 993,
                        columnNumber: 11
                    }, this),
                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("form", {
                        onSubmit: handleBatchSubmit,
                        className: "space-y-6",
                        children: [
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                className: "grid grid-cols-1 md:grid-cols-2 gap-6",
                                children: [
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                                className: "block mb-1 font-medium text-gray-700",
                                                children: "Batch Name *"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1008,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                                type: "text",
                                                name: "batchName",
                                                value: batchFormData.batchName,
                                                onChange: handleBatchChange,
                                                className: "border p-2 rounded w-full",
                                                required: true
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1011,
                                                columnNumber: 17
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 1007,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                                className: "block mb-1 font-medium text-gray-700",
                                                children: "Start Time *"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1021,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                                type: "time",
                                                name: "batchStartTime",
                                                value: batchFormData.batchStartTime,
                                                onChange: handleBatchChange,
                                                className: "border p-2 rounded w-full",
                                                required: true
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1024,
                                                columnNumber: 17
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 1020,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                                className: "block mb-1 font-medium text-gray-700",
                                                children: "End Time *"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1034,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                                type: "time",
                                                name: "batchEndTime",
                                                value: batchFormData.batchEndTime,
                                                onChange: handleBatchChange,
                                                className: "border p-2 rounded w-full",
                                                required: true
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1037,
                                                columnNumber: 17
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 1033,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                                className: "block mb-1 font-medium text-gray-700",
                                                children: "Course *"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1047,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("select", {
                                                name: "courseId",
                                                value: batchFormData.courseId,
                                                onChange: handleBatchChange,
                                                className: "border p-2 rounded w-full",
                                                required: true,
                                                children: [
                                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("option", {
                                                        value: "",
                                                        children: "-- Select Course --"
                                                    }, void 0, false, {
                                                        fileName: "[project]/app/admin/courses/page.jsx",
                                                        lineNumber: 1057,
                                                        columnNumber: 19
                                                    }, this),
                                                    courses.map((c)=>/*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("option", {
                                                            value: c.courseId,
                                                            children: [
                                                                c.courseId,
                                                                " - ",
                                                                c.courseName
                                                            ]
                                                        }, c.courseId, true, {
                                                            fileName: "[project]/app/admin/courses/page.jsx",
                                                            lineNumber: 1059,
                                                            columnNumber: 21
                                                        }, this))
                                                ]
                                            }, void 0, true, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1050,
                                                columnNumber: 17
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 1046,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                                className: "block mb-1 font-medium text-gray-700",
                                                children: "Presentation Date *"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1066,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                                type: "datetime-local",
                                                name: "presentationDate",
                                                value: batchFormData.presentationDate,
                                                onChange: handleBatchChange,
                                                className: "border p-2 rounded w-full",
                                                required: true
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1069,
                                                columnNumber: 17
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 1065,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                                className: "block mb-1 font-medium text-gray-700",
                                                children: "Total Fees *"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1079,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                                type: "number",
                                                name: "courseFees",
                                                value: batchFormData.courseFees,
                                                onChange: handleBatchChange,
                                                className: "border p-2 rounded w-full",
                                                min: "0",
                                                required: true
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1082,
                                                columnNumber: 17
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 1078,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                                className: "block mb-1 font-medium text-gray-700",
                                                children: "Fees From *"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1093,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                                type: "date",
                                                name: "courseFeesFrom",
                                                value: batchFormData.courseFeesFrom,
                                                onChange: handleBatchChange,
                                                className: "border p-2 rounded w-full",
                                                required: true
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1096,
                                                columnNumber: 17
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 1092,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                                className: "block mb-1 font-medium text-gray-700",
                                                children: "Fees To *"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1106,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                                type: "date",
                                                name: "courseFeesTo",
                                                value: batchFormData.courseFeesTo,
                                                onChange: handleBatchChange,
                                                className: "border p-2 rounded w-full",
                                                required: true
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1109,
                                                columnNumber: 17
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 1105,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                        children: [
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                                className: "block mb-1 font-medium text-gray-700",
                                                children: "Batch Logo"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1119,
                                                columnNumber: 17
                                            }, this),
                                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                                type: "file",
                                                accept: "image/*",
                                                onChange: handleBatchLogoChange,
                                                className: "border p-2 rounded w-full"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1122,
                                                columnNumber: 17
                                            }, this),
                                            batchFormData.batchLogo && /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("img", {
                                                src: batchFormData.batchLogo,
                                                alt: "Batch Logo",
                                                className: "mt-2 w-24 h-24 object-contain border rounded"
                                            }, void 0, false, {
                                                fileName: "[project]/app/admin/courses/page.jsx",
                                                lineNumber: 1129,
                                                columnNumber: 19
                                            }, this)
                                        ]
                                    }, void 0, true, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 1118,
                                        columnNumber: 15
                                    }, this)
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 1006,
                                columnNumber: 13
                            }, this),
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                className: "flex items-center space-x-2",
                                children: [
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("input", {
                                        type: "checkbox",
                                        name: "batchIsActive",
                                        checked: batchFormData.batchIsActive,
                                        onChange: handleBatchChange,
                                        className: "accent-black"
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 1138,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("label", {
                                        className: "text-gray-700 font-medium",
                                        children: "Is Active"
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 1145,
                                        columnNumber: 15
                                    }, this)
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 1137,
                                columnNumber: 13
                            }, this),
                            /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("div", {
                                className: "flex gap-4",
                                children: [
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                        type: "submit",
                                        className: "bg-black text-white px-6 py-2 rounded hover:bg-gray-800",
                                        children: batchFormData.batchId ? "Update" : "Submit"
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 1148,
                                        columnNumber: 15
                                    }, this),
                                    /*#__PURE__*/ (0, __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$dist$2f$server$2f$route$2d$modules$2f$app$2d$page$2f$vendored$2f$ssr$2f$react$2d$jsx$2d$dev$2d$runtime$2e$js__$5b$app$2d$ssr$5d$__$28$ecmascript$29$__["jsxDEV"])("button", {
                                        type: "button",
                                        onClick: closeForm,
                                        className: "bg-gray-500 text-white px-6 py-2 rounded hover:opacity-90",
                                        children: "Cancel"
                                    }, void 0, false, {
                                        fileName: "[project]/app/admin/courses/page.jsx",
                                        lineNumber: 1154,
                                        columnNumber: 15
                                    }, this)
                                ]
                            }, void 0, true, {
                                fileName: "[project]/app/admin/courses/page.jsx",
                                lineNumber: 1147,
                                columnNumber: 13
                            }, this)
                        ]
                    }, void 0, true, {
                        fileName: "[project]/app/admin/courses/page.jsx",
                        lineNumber: 1005,
                        columnNumber: 11
                    }, this)
                ]
            }, void 0, true, {
                fileName: "[project]/app/admin/courses/page.jsx",
                lineNumber: 992,
                columnNumber: 9
            }, this)
        ]
    }, void 0, true, {
        fileName: "[project]/app/admin/courses/page.jsx",
        lineNumber: 524,
        columnNumber: 5
    }, this);
}
}),

};

//# sourceMappingURL=app_admin_courses_page_jsx_2d6b6d39._.js.map
module.exports = {

"[project]/.next-internal/server/app/api/albums/route/actions.js [app-rsc] (server actions loader, ecmascript)": ((__turbopack_context__) => {

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
"[externals]/next/dist/server/app-render/after-task-async-storage.external.js [external] (next/dist/server/app-render/after-task-async-storage.external.js, cjs)": ((__turbopack_context__) => {

var { m: module, e: exports } = __turbopack_context__;
{
const mod = __turbopack_context__.x("next/dist/server/app-render/after-task-async-storage.external.js", () => require("next/dist/server/app-render/after-task-async-storage.external.js"));

module.exports = mod;
}}),
"[project]/app/api/albums/route.js [app-route] (ecmascript)": ((__turbopack_context__) => {
"use strict";

__turbopack_context__.s({
    "GET": ()=>GET
});
var __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$server$2e$js__$5b$app$2d$route$5d$__$28$ecmascript$29$__ = __turbopack_context__.i("[project]/node_modules/next/server.js [app-route] (ecmascript)");
;
async function GET(request) {
    try {
        const { searchParams } = new URL(request.url);
        const page = searchParams.get('page') || '1';
        const limit = searchParams.get('limit') || '8';
        // Fetch albums from Spring Boot backend
        const albumsResponse = await fetch(`http://localhost:8080/api/albums?page=${page}&limit=${limit}`);
        if (!albumsResponse.ok) {
            throw new Error(`Backend error: ${albumsResponse.status}`);
        }
        const albums = await albumsResponse.json();
        // Fetch all images to get cover images
        let allImages = [];
        try {
            const imagesResponse = await fetch('http://localhost:8080/api/images');
            if (imagesResponse.ok) {
                allImages = await imagesResponse.json();
                console.log('Fetched images from backend:', allImages.length, 'images');
            } else {
                console.warn(`Images backend error: ${imagesResponse.status}, proceeding without cover images`);
            }
        } catch (error) {
            console.warn('Failed to fetch images, proceeding without cover images:', error.message);
        }
        // Enhance albums with cover images
        const albumsWithCovers = albums.map((album)=>{
            // Find the cover image for this album
            const coverImage = allImages.find((img)=>{
                // Handle both string and number types for albumId
                const imgAlbumId = typeof img.albumId === 'string' ? parseInt(img.albumId) : img.albumId;
                const albumId = typeof album.albumId === 'string' ? parseInt(album.albumId) : album.albumId;
                return imgAlbumId === albumId && img.isAlbumCover === true;
            });
            console.log(`Album ${album.albumId} (${album.albumName}):`, {
                hasCoverImage: !!coverImage,
                coverImageData: coverImage,
                allImagesForAlbum: allImages.filter((img)=>{
                    const imgAlbumId = typeof img.albumId === 'string' ? parseInt(img.albumId) : img.albumId;
                    const albumId = typeof album.albumId === 'string' ? parseInt(album.albumId) : album.albumId;
                    return imgAlbumId === albumId;
                })
            });
            // Construct the cover image URL properly
            let coverImageUrl = null;
            if (coverImage) {
                if (coverImage.imagePath) {
                    // Handle both full paths and just filenames
                    if (coverImage.imagePath.startsWith('images/')) {
                        coverImageUrl = `/${coverImage.imagePath}`;
                    } else if (coverImage.imagePath.includes('/')) {
                        coverImageUrl = `/images/${coverImage.imagePath.split('/').pop()}`;
                    } else {
                        coverImageUrl = `/images/${coverImage.imagePath}`;
                    }
                    console.log(`Constructed cover URL for album ${album.albumId}:`, coverImageUrl);
                }
            } else {
                // Fallback: use the first image from the album if no cover image is set
                const firstImage = allImages.find((img)=>{
                    const imgAlbumId = typeof img.albumId === 'string' ? parseInt(img.albumId) : img.albumId;
                    const albumId = typeof album.albumId === 'string' ? parseInt(album.albumId) : album.albumId;
                    return imgAlbumId === albumId;
                });
                if (firstImage && firstImage.imagePath) {
                    if (firstImage.imagePath.startsWith('images/')) {
                        coverImageUrl = `/${firstImage.imagePath}`;
                    } else if (firstImage.imagePath.includes('/')) {
                        coverImageUrl = `/images/${firstImage.imagePath.split('/').pop()}`;
                    } else {
                        coverImageUrl = `/images/${firstImage.imagePath}`;
                    }
                    console.log(`Using fallback image for album ${album.albumId}:`, coverImageUrl);
                }
            }
            return {
                ...album,
                coverImage: coverImageUrl
            };
        });
        return __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$server$2e$js__$5b$app$2d$route$5d$__$28$ecmascript$29$__["NextResponse"].json(albumsWithCovers);
    } catch (error) {
        console.error('Error in GET /api/albums:', error);
        return __TURBOPACK__imported__module__$5b$project$5d2f$node_modules$2f$next$2f$server$2e$js__$5b$app$2d$route$5d$__$28$ecmascript$29$__["NextResponse"].json({
            error: 'Failed to fetch albums with cover images',
            details: error.message
        }, {
            status: 500
        });
    }
}
}),

};

//# sourceMappingURL=%5Broot-of-the-server%5D__28f6f695._.js.map
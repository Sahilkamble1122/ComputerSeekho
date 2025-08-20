"use client";
import { useRouter } from "next/navigation";
import Image from "next/image";

export default function BatchPlacementCard({
  logo,
  batch,
  slug,
  totalStudents,
  placedStudents,
  courseFees,
  presentationDate,
}) {
  const router = useRouter();

  // ✅ Calculate placement % here
  const placement =
    totalStudents > 0 ? Math.round((placedStudents / totalStudents) * 100) : 0;

  // Extract year from presentation date
  const getYear = (dateString) => {
    if (!dateString) return "";
    const date = new Date(dateString);
    return date.getFullYear();
  };

  const year = getYear(presentationDate);

  return (
    <div className="text-center shadow-md p-4 rounded-xl border hover:scale-105 transition-all">
      <Image
        src={logo}
        alt={batch}
        width={200}
        height={200}
        className="mx-auto mb-2 rounded-lg"
        onError={(e) => {
          e.target.src = "/batches/default-batch-logo.png";
        }}
      />

      {/* Batch Name with Year in Button */}
      <div className="mb-2">
        <button
          onClick={() => router.push(`/placement-details/${slug}`)}
          className="bg-white border border-blue-600 text-blue-600 px-3 py-1 rounded-full font-semibold cursor-pointer hover:bg-blue-600 hover:text-white transition"
        >
          {batch} {year && `(${year})`}
        </button>
      </div>

      {/* ✅ Render dynamically calculated percentage or placeholder */}
      {totalStudents > 0 ? (
        <p className="text-gray-600 font-medium">{placement}% Placement</p>
      ) : (
        <p className="text-gray-400 text-sm italic">Student data coming soon</p>
      )}
    </div>
  );
}

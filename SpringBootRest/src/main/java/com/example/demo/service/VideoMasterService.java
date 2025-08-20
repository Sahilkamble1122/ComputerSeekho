package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.VideoMaster;

public interface VideoMasterService {
    
    // CREATE OPERATIONS
    VideoMaster saveVideo(VideoMaster video);
    List<VideoMaster> saveAllVideos(List<VideoMaster> videos);
    
    // READ OPERATIONS
    List<VideoMaster> getAllVideos();
    Optional<VideoMaster> getVideoById(Integer videoId);
    List<VideoMaster> getVideosByName(String videoName);
    List<VideoMaster> getVideosByType(String videoType);
    List<VideoMaster> getVideosByStatus(String status);
    List<VideoMaster> getVideosByNameContaining(String videoName);
    List<VideoMaster> getVideosByDurationRange(Integer minDuration, Integer maxDuration);
    List<VideoMaster> getActiveVideos();
    Long countActiveVideos();
    
    // UPDATE OPERATIONS
    VideoMaster updateVideo(Integer videoId, VideoMaster videoDetails);
    VideoMaster updateVideoName(Integer videoId, String videoName);
    VideoMaster updateVideoType(Integer videoId, String videoType);
    VideoMaster updateVideoDuration(Integer videoId, Integer duration);
    VideoMaster updateVideoStatus(Integer videoId, String status);
    
    // DELETE OPERATIONS
    void deleteVideo(Integer videoId);
    void deleteVideosByType(String videoType);
    void deleteVideosByStatus(String status);
    void deleteInactiveVideos();
    void deleteAllVideos();
    
    // BUSINESS LOGIC OPERATIONS
    List<VideoMaster> searchVideos(String searchTerm);
    List<VideoMaster> getVideosByTypeAndDuration(String videoType, Integer duration);
    List<VideoMaster> getVideosWithPagination(int page, int size);
    List<VideoMaster> getVideosSortedByName();
    List<VideoMaster> getVideosSortedByDuration();
    List<VideoMaster> getVideosSortedByType();
    List<VideoMaster> getFeaturedVideos();
    List<VideoMaster> getRecentVideos();
    List<VideoMaster> getPopularVideos();
    List<VideoMaster> getShortVideos();
    List<VideoMaster> getLongVideos();
} 
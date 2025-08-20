package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.VideoMaster;
import com.example.demo.repository.VideoMasterRepository;
import com.example.demo.service.VideoMasterService;

@Service
public class VideoMasterServiceImpl implements VideoMasterService {

    @Autowired
    private VideoMasterRepository videoMasterRepository;

    // CREATE OPERATIONS
    @Override
    public VideoMaster saveVideo(VideoMaster video) {
        return videoMasterRepository.save(video);
    }

    @Override
    public List<VideoMaster> saveAllVideos(List<VideoMaster> videos) {
        return videoMasterRepository.saveAll(videos);
    }

    // READ OPERATIONS
    @Override
    public List<VideoMaster> getAllVideos() {
        return videoMasterRepository.findAll();
    }

    @Override
    public Optional<VideoMaster> getVideoById(Integer videoId) {
        return videoMasterRepository.findById(videoId);
    }

    @Override
    public List<VideoMaster> getVideosByName(String videoName) {
        return videoMasterRepository.findByVideoDescription(videoName);
    }

    @Override
    public List<VideoMaster> getVideosByType(String videoType) {
        return videoMasterRepository.findByVideoUrl(videoType);
    }

    @Override
    public List<VideoMaster> getVideosByStatus(String status) {
        Boolean isActive = "Active".equalsIgnoreCase(status);
        return videoMasterRepository.findByVideoIsActive(isActive);
    }

    @Override
    public List<VideoMaster> getVideosByNameContaining(String videoName) {
        return videoMasterRepository.findByVideoDescriptionContainingIgnoreCase(videoName);
    }

    @Override
    public List<VideoMaster> getVideosByDurationRange(Integer minDuration, Integer maxDuration) {
        // Since VideoMaster doesn't have duration, we'll use batchId as a substitute
        return videoMasterRepository.findByBatchIdBetween(minDuration, maxDuration);
    }

    @Override
    public List<VideoMaster> getActiveVideos() {
        return videoMasterRepository.findByVideoIsActive(true);
    }

    @Override
    public Long countActiveVideos() {
        return videoMasterRepository.countByVideoIsActive(true);
    }

    // UPDATE OPERATIONS
    @Override
    public VideoMaster updateVideo(Integer videoId, VideoMaster videoDetails) {
        VideoMaster existingVideo = videoMasterRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found with id: " + videoId));
        
        existingVideo.setVideoDescription(videoDetails.getVideoDescription());
        existingVideo.setVideoUrl(videoDetails.getVideoUrl());
        existingVideo.setBatchId(videoDetails.getBatchId());
        existingVideo.setStartDate(videoDetails.getStartDate());
        existingVideo.setEndDate(videoDetails.getEndDate());
        existingVideo.setVideoIsActive(videoDetails.getVideoIsActive());
        existingVideo.setUpdatedDate(java.time.LocalDateTime.now());
        
        return videoMasterRepository.save(existingVideo);
    }

    @Override
    public VideoMaster updateVideoName(Integer videoId, String videoName) {
        VideoMaster video = videoMasterRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found with id: " + videoId));
        video.setVideoDescription(videoName);
        video.setUpdatedDate(java.time.LocalDateTime.now());
        return videoMasterRepository.save(video);
    }

    @Override
    public VideoMaster updateVideoType(Integer videoId, String videoType) {
        VideoMaster video = videoMasterRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found with id: " + videoId));
        video.setVideoUrl(videoType);
        video.setUpdatedDate(java.time.LocalDateTime.now());
        return videoMasterRepository.save(video);
    }

    @Override
    public VideoMaster updateVideoDuration(Integer videoId, Integer duration) {
        VideoMaster video = videoMasterRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found with id: " + videoId));
        video.setBatchId(duration);
        video.setUpdatedDate(java.time.LocalDateTime.now());
        return videoMasterRepository.save(video);
    }

    @Override
    public VideoMaster updateVideoStatus(Integer videoId, String status) {
        VideoMaster video = videoMasterRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found with id: " + videoId));
        Boolean isActive = "Active".equalsIgnoreCase(status);
        video.setVideoIsActive(isActive);
        video.setUpdatedDate(java.time.LocalDateTime.now());
        return videoMasterRepository.save(video);
    }

    // DELETE OPERATIONS
    @Override
    public void deleteVideo(Integer videoId) {
        if (!videoMasterRepository.existsById(videoId)) {
            throw new RuntimeException("Video not found with id: " + videoId);
        }
        videoMasterRepository.deleteById(videoId);
    }

    @Override
    public void deleteVideosByType(String videoType) {
        videoMasterRepository.deleteByVideoUrl(videoType);
    }

    @Override
    public void deleteVideosByStatus(String status) {
        Boolean isActive = "Active".equalsIgnoreCase(status);
        videoMasterRepository.deleteByVideoIsActive(isActive);
    }

    @Override
    public void deleteInactiveVideos() {
        videoMasterRepository.deleteByVideoIsActive(false);
    }

    @Override
    public void deleteAllVideos() {
        videoMasterRepository.deleteAll();
    }

    // BUSINESS LOGIC OPERATIONS
    @Override
    public List<VideoMaster> searchVideos(String searchTerm) {
        return videoMasterRepository.findByVideoDescriptionContainingIgnoreCaseOrVideoUrlContainingIgnoreCase(searchTerm, searchTerm);
    }

    @Override
    public List<VideoMaster> getVideosByTypeAndDuration(String videoType, Integer duration) {
        return videoMasterRepository.findByVideoUrlAndBatchId(videoType, duration);
    }

    @Override
    public List<VideoMaster> getVideosWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VideoMaster> videoPage = videoMasterRepository.findAll(pageable);
        return videoPage.getContent();
    }

    @Override
    public List<VideoMaster> getVideosSortedByName() {
        return videoMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "videoDescription"));
    }

    @Override
    public List<VideoMaster> getVideosSortedByDuration() {
        return videoMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "batchId"));
    }

    @Override
    public List<VideoMaster> getVideosSortedByType() {
        return videoMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "videoUrl"));
    }

    @Override
    public List<VideoMaster> getFeaturedVideos() {
        // This would typically involve more complex logic, but for now we'll return active videos
        return videoMasterRepository.findByVideoIsActive(true);
    }

    @Override
    public List<VideoMaster> getRecentVideos() {
        // This would typically involve more complex logic, but for now we'll return active videos
        return videoMasterRepository.findByVideoIsActive(true);
    }

    @Override
    public List<VideoMaster> getPopularVideos() {
        // This would typically involve more complex logic, but for now we'll return active videos
        return videoMasterRepository.findByVideoIsActive(true);
    }

    @Override
    public List<VideoMaster> getShortVideos() {
        // Since VideoMaster doesn't have duration, we'll return videos with batchId less than 5
        return videoMasterRepository.findByBatchIdBetween(1, 5);
    }

    @Override
    public List<VideoMaster> getLongVideos() {
        // Since VideoMaster doesn't have duration, we'll return videos with batchId greater than 10
        return videoMasterRepository.findByBatchIdBetween(10, 999);
    }
} 
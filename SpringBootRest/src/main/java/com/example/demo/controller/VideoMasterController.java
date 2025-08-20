package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.VideoMaster;
import com.example.demo.service.VideoMasterService;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "*")
public class VideoMasterController {

    @Autowired
    private VideoMasterService videoMasterService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<VideoMaster> createVideo(@RequestBody VideoMaster video) {
        try {
            VideoMaster savedVideo = videoMasterService.saveVideo(video);
            return new ResponseEntity<>(savedVideo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<VideoMaster>> createMultipleVideos(@RequestBody List<VideoMaster> videos) {
        try {
            List<VideoMaster> savedVideos = videoMasterService.saveAllVideos(videos);
            return new ResponseEntity<>(savedVideos, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<VideoMaster>> getAllVideos() {
        try {
            List<VideoMaster> videos = videoMasterService.getAllVideos();
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<VideoMaster> getVideoById(@PathVariable Integer videoId) {
        try {
            Optional<VideoMaster> video = videoMasterService.getVideoById(videoId);
            return video.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name/{videoName}")
    public ResponseEntity<List<VideoMaster>> getVideosByName(@PathVariable String videoName) {
        try {
            List<VideoMaster> videos = videoMasterService.getVideosByName(videoName);
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/type/{videoType}")
    public ResponseEntity<List<VideoMaster>> getVideosByType(@PathVariable String videoType) {
        try {
            List<VideoMaster> videos = videoMasterService.getVideosByType(videoType);
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<VideoMaster>> getVideosByStatus(@PathVariable String status) {
        try {
            List<VideoMaster> videos = videoMasterService.getVideosByStatus(status);
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name-contains/{videoName}")
    public ResponseEntity<List<VideoMaster>> getVideosByNameContaining(@PathVariable String videoName) {
        try {
            List<VideoMaster> videos = videoMasterService.getVideosByNameContaining(videoName);
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/duration-range")
    public ResponseEntity<List<VideoMaster>> getVideosByDurationRange(
            @RequestParam Integer minDuration,
            @RequestParam Integer maxDuration) {
        try {
            List<VideoMaster> videos = videoMasterService.getVideosByDurationRange(minDuration, maxDuration);
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<VideoMaster>> getActiveVideos() {
        try {
            List<VideoMaster> videos = videoMasterService.getActiveVideos();
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/active")
    public ResponseEntity<Long> countActiveVideos() {
        try {
            Long count = videoMasterService.countActiveVideos();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{videoId}")
    public ResponseEntity<VideoMaster> updateVideo(@PathVariable Integer videoId, @RequestBody VideoMaster videoDetails) {
        try {
            VideoMaster updatedVideo = videoMasterService.updateVideo(videoId, videoDetails);
            return new ResponseEntity<>(updatedVideo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{videoId}/name")
    public ResponseEntity<VideoMaster> updateVideoName(@PathVariable Integer videoId, @RequestParam String videoName) {
        try {
            VideoMaster updatedVideo = videoMasterService.updateVideoName(videoId, videoName);
            return new ResponseEntity<>(updatedVideo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{videoId}/type")
    public ResponseEntity<VideoMaster> updateVideoType(@PathVariable Integer videoId, @RequestParam String videoType) {
        try {
            VideoMaster updatedVideo = videoMasterService.updateVideoType(videoId, videoType);
            return new ResponseEntity<>(updatedVideo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{videoId}/duration")
    public ResponseEntity<VideoMaster> updateVideoDuration(@PathVariable Integer videoId, @RequestParam Integer duration) {
        try {
            VideoMaster updatedVideo = videoMasterService.updateVideoDuration(videoId, duration);
            return new ResponseEntity<>(updatedVideo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{videoId}/status")
    public ResponseEntity<VideoMaster> updateVideoStatus(@PathVariable Integer videoId, @RequestParam String status) {
        try {
            VideoMaster updatedVideo = videoMasterService.updateVideoStatus(videoId, status);
            return new ResponseEntity<>(updatedVideo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{videoId}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Integer videoId) {
        try {
            videoMasterService.deleteVideo(videoId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/type/{videoType}")
    public ResponseEntity<Void> deleteVideosByType(@PathVariable String videoType) {
        try {
            videoMasterService.deleteVideosByType(videoType);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/status/{status}")
    public ResponseEntity<Void> deleteVideosByStatus(@PathVariable String status) {
        try {
            videoMasterService.deleteVideosByStatus(status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/inactive")
    public ResponseEntity<Void> deleteInactiveVideos() {
        try {
            videoMasterService.deleteInactiveVideos();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllVideos() {
        try {
            videoMasterService.deleteAllVideos();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/search/global/{searchTerm}")
    public ResponseEntity<List<VideoMaster>> searchVideos(@PathVariable String searchTerm) {
        try {
            List<VideoMaster> videos = videoMasterService.searchVideos(searchTerm);
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/type-duration")
    public ResponseEntity<List<VideoMaster>> getVideosByTypeAndDuration(
            @RequestParam String videoType,
            @RequestParam Integer duration) {
        try {
            List<VideoMaster> videos = videoMasterService.getVideosByTypeAndDuration(videoType, duration);
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<VideoMaster>> getVideosWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<VideoMaster> videos = videoMasterService.getVideosWithPagination(page, size);
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/name")
    public ResponseEntity<List<VideoMaster>> getVideosSortedByName() {
        try {
            List<VideoMaster> videos = videoMasterService.getVideosSortedByName();
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/duration")
    public ResponseEntity<List<VideoMaster>> getVideosSortedByDuration() {
        try {
            List<VideoMaster> videos = videoMasterService.getVideosSortedByDuration();
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/type")
    public ResponseEntity<List<VideoMaster>> getVideosSortedByType() {
        try {
            List<VideoMaster> videos = videoMasterService.getVideosSortedByType();
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/featured")
    public ResponseEntity<List<VideoMaster>> getFeaturedVideos() {
        try {
            List<VideoMaster> videos = videoMasterService.getFeaturedVideos();
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recent")
    public ResponseEntity<List<VideoMaster>> getRecentVideos() {
        try {
            List<VideoMaster> videos = videoMasterService.getRecentVideos();
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/popular")
    public ResponseEntity<List<VideoMaster>> getPopularVideos() {
        try {
            List<VideoMaster> videos = videoMasterService.getPopularVideos();
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/short")
    public ResponseEntity<List<VideoMaster>> getShortVideos() {
        try {
            List<VideoMaster> videos = videoMasterService.getShortVideos();
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/long")
    public ResponseEntity<List<VideoMaster>> getLongVideos() {
        try {
            List<VideoMaster> videos = videoMasterService.getLongVideos();
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 
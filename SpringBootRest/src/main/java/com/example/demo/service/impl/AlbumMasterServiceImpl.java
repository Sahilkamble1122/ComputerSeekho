package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.AlbumMaster;
import com.example.demo.repository.AlbumMasterRepository;
import com.example.demo.service.AlbumMasterService;

@Service
public class AlbumMasterServiceImpl implements AlbumMasterService {

    @Autowired
    private AlbumMasterRepository albumMasterRepository;

    // Create operations
    @Override
    public AlbumMaster saveAlbum(AlbumMaster album) {
        return albumMasterRepository.save(album);
    }

    @Override
    public List<AlbumMaster> saveAllAlbums(List<AlbumMaster> albums) {
        return albumMasterRepository.saveAll(albums);
    }

    // Read operations
    @Override
    public List<AlbumMaster> getAllAlbums() {
        return albumMasterRepository.findAll();
    }

    @Override
    public Optional<AlbumMaster> getAlbumById(Integer albumId) {
        return albumMasterRepository.findById(albumId);
    }

    @Override
    public List<AlbumMaster> getAlbumsByName(String albumName) {
        return albumMasterRepository.findByAlbumName(albumName);
    }

    @Override
    public List<AlbumMaster> getAlbumsByDescription(String description) {
        return albumMasterRepository.findByAlbumDescription(description);
    }

    @Override
    public List<AlbumMaster> getAlbumsByStatus(String status) {
        // Convert status string to boolean
        Boolean isActive = "ACTIVE".equalsIgnoreCase(status);
        return albumMasterRepository.findByAlbumIsActive(isActive);
    }

    @Override
    public List<AlbumMaster> getAlbumsByNameContaining(String albumName) {
        return albumMasterRepository.findByAlbumNameContainingIgnoreCase(albumName);
    }

    @Override
    public List<AlbumMaster> getActiveAlbums() {
        return albumMasterRepository.findByAlbumIsActiveTrue();
    }

    @Override
    public Long countActiveAlbums() {
        return albumMasterRepository.countByAlbumIsActive(true);
    }

    // Update operations
    @Override
    public AlbumMaster updateAlbum(Integer albumId, AlbumMaster albumDetails) {
        Optional<AlbumMaster> existingAlbum = albumMasterRepository.findById(albumId);
        if (existingAlbum.isPresent()) {
            AlbumMaster album = existingAlbum.get();
            album.setAlbumName(albumDetails.getAlbumName());
            album.setAlbumDescription(albumDetails.getAlbumDescription());
            album.setStartDate(albumDetails.getStartDate());
            album.setEndDate(albumDetails.getEndDate());
            album.setAlbumIsActive(albumDetails.getAlbumIsActive());
            album.setUpdatedDate(java.time.LocalDateTime.now());
            return albumMasterRepository.save(album);
        }
        return null;
    }

    @Override
    public AlbumMaster updateAlbumName(Integer albumId, String albumName) {
        Optional<AlbumMaster> existingAlbum = albumMasterRepository.findById(albumId);
        if (existingAlbum.isPresent()) {
            AlbumMaster album = existingAlbum.get();
            album.setAlbumName(albumName);
            album.setUpdatedDate(java.time.LocalDateTime.now());
            return albumMasterRepository.save(album);
        }
        return null;
    }

    @Override
    public AlbumMaster updateAlbumDescription(Integer albumId, String description) {
        Optional<AlbumMaster> existingAlbum = albumMasterRepository.findById(albumId);
        if (existingAlbum.isPresent()) {
            AlbumMaster album = existingAlbum.get();
            album.setAlbumDescription(description);
            album.setUpdatedDate(java.time.LocalDateTime.now());
            return albumMasterRepository.save(album);
        }
        return null;
    }

    @Override
    public AlbumMaster updateAlbumStatus(Integer albumId, String status) {
        Optional<AlbumMaster> existingAlbum = albumMasterRepository.findById(albumId);
        if (existingAlbum.isPresent()) {
            AlbumMaster album = existingAlbum.get();
            // Convert status string to boolean
            album.setAlbumIsActive("ACTIVE".equalsIgnoreCase(status));
            album.setUpdatedDate(java.time.LocalDateTime.now());
            return albumMasterRepository.save(album);
        }
        return null;
    }

    // Delete operations
    @Override
    public void deleteAlbum(Integer albumId) {
        if (!albumMasterRepository.existsById(albumId)) {
            throw new RuntimeException("Album not found with id: " + albumId);
        }
        albumMasterRepository.deleteById(albumId);
    }

    @Override
    public void deleteAlbumsByStatus(String status) {
        // Convert status string to boolean
        Boolean isActive = "ACTIVE".equalsIgnoreCase(status);
        albumMasterRepository.deleteByAlbumIsActive(isActive);
    }

    @Override
    public void deleteInactiveAlbums() {
        albumMasterRepository.deleteByAlbumIsActiveFalse();
    }

    @Override
    public void deleteAllAlbums() {
        albumMasterRepository.deleteAll();
    }

    // Business logic operations
    @Override
    public List<AlbumMaster> searchAlbums(String searchTerm) {
        return albumMasterRepository.findByAlbumNameContainingIgnoreCaseOrAlbumDescriptionContainingIgnoreCase(searchTerm);
    }

    @Override
    public List<AlbumMaster> getAlbumsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AlbumMaster> albumPage = albumMasterRepository.findAll(pageable);
        return albumPage.getContent();
    }

    @Override
    public List<AlbumMaster> getAlbumsSortedByName() {
        return albumMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "albumName"));
    }

    @Override
    public List<AlbumMaster> getAlbumsSortedByStatus() {
        return albumMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "albumIsActive"));
    }

    @Override
    public List<AlbumMaster> getFeaturedAlbums() {
        // Assuming featured albums are active albums, you can modify this logic
        return albumMasterRepository.findByAlbumIsActiveTrue();
    }
} 
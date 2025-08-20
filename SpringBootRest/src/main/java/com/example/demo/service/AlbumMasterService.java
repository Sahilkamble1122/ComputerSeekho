package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.AlbumMaster;

public interface AlbumMasterService {
    
    // CREATE OPERATIONS
    AlbumMaster saveAlbum(AlbumMaster album);
    List<AlbumMaster> saveAllAlbums(List<AlbumMaster> albums);
    
    // READ OPERATIONS
    List<AlbumMaster> getAllAlbums();
    Optional<AlbumMaster> getAlbumById(Integer albumId);
    List<AlbumMaster> getAlbumsByName(String albumName);
    List<AlbumMaster> getAlbumsByDescription(String description);
    List<AlbumMaster> getAlbumsByStatus(String status);
    List<AlbumMaster> getAlbumsByNameContaining(String albumName);
    List<AlbumMaster> getActiveAlbums();
    Long countActiveAlbums();
    
    // UPDATE OPERATIONS
    AlbumMaster updateAlbum(Integer albumId, AlbumMaster albumDetails);
    AlbumMaster updateAlbumName(Integer albumId, String albumName);
    AlbumMaster updateAlbumDescription(Integer albumId, String description);
    AlbumMaster updateAlbumStatus(Integer albumId, String status);
    
    // DELETE OPERATIONS
    void deleteAlbum(Integer albumId);
    void deleteAlbumsByStatus(String status);
    void deleteInactiveAlbums();
    void deleteAllAlbums();
    
    // BUSINESS LOGIC OPERATIONS
    List<AlbumMaster> searchAlbums(String searchTerm);
    List<AlbumMaster> getAlbumsWithPagination(int page, int size);
    List<AlbumMaster> getAlbumsSortedByName();
    List<AlbumMaster> getAlbumsSortedByStatus();
    List<AlbumMaster> getFeaturedAlbums();
} 
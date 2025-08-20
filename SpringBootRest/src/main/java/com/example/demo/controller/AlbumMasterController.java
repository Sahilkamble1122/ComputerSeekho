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

import com.example.demo.model.AlbumMaster;
import com.example.demo.service.AlbumMasterService;

@RestController
@RequestMapping("/api/albums")
@CrossOrigin(origins = "*")
public class AlbumMasterController {

    @Autowired
    private AlbumMasterService albumMasterService;

    // ===========================================
    // CREATE OPERATIONS
    // ===========================================

    @PostMapping
    public ResponseEntity<AlbumMaster> createAlbum(@RequestBody AlbumMaster album) {
        try {
            AlbumMaster savedAlbum = albumMasterService.saveAlbum(album);
            return new ResponseEntity<>(savedAlbum, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<AlbumMaster>> createMultipleAlbums(@RequestBody List<AlbumMaster> albums) {
        try {
            List<AlbumMaster> savedAlbums = albumMasterService.saveAllAlbums(albums);
            return new ResponseEntity<>(savedAlbums, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===========================================
    // READ OPERATIONS
    // ===========================================

    @GetMapping
    public ResponseEntity<List<AlbumMaster>> getAllAlbums() {
        try {
            List<AlbumMaster> albums = albumMasterService.getAllAlbums();
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumMaster> getAlbumById(@PathVariable Integer albumId) {
        try {
            Optional<AlbumMaster> album = albumMasterService.getAlbumById(albumId);
            return album.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Removed test endpoint for album existence

    @GetMapping("/search/name/{albumName}")
    public ResponseEntity<List<AlbumMaster>> getAlbumsByName(@PathVariable String albumName) {
        try {
            List<AlbumMaster> albums = albumMasterService.getAlbumsByName(albumName);
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/description/{description}")
    public ResponseEntity<List<AlbumMaster>> getAlbumsByDescription(@PathVariable String description) {
        try {
            List<AlbumMaster> albums = albumMasterService.getAlbumsByDescription(description);
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<AlbumMaster>> getAlbumsByStatus(@PathVariable String status) {
        try {
            List<AlbumMaster> albums = albumMasterService.getAlbumsByStatus(status);
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name-contains/{albumName}")
    public ResponseEntity<List<AlbumMaster>> getAlbumsByNameContaining(@PathVariable String albumName) {
        try {
            List<AlbumMaster> albums = albumMasterService.getAlbumsByNameContaining(albumName);
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<AlbumMaster>> getActiveAlbums() {
        try {
            List<AlbumMaster> albums = albumMasterService.getActiveAlbums();
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count/active")
    public ResponseEntity<Long> countActiveAlbums() {
        try {
            Long count = albumMasterService.countActiveAlbums();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // UPDATE OPERATIONS
    // ===========================================

    @PutMapping("/{albumId}")
    public ResponseEntity<AlbumMaster> updateAlbum(@PathVariable Integer albumId, @RequestBody AlbumMaster albumDetails) {
        try {
            AlbumMaster updatedAlbum = albumMasterService.updateAlbum(albumId, albumDetails);
            return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{albumId}/name")
    public ResponseEntity<AlbumMaster> updateAlbumName(@PathVariable Integer albumId, @RequestParam String albumName) {
        try {
            AlbumMaster updatedAlbum = albumMasterService.updateAlbumName(albumId, albumName);
            return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{albumId}/description")
    public ResponseEntity<AlbumMaster> updateAlbumDescription(@PathVariable Integer albumId, @RequestParam String description) {
        try {
            AlbumMaster updatedAlbum = albumMasterService.updateAlbumDescription(albumId, description);
            return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{albumId}/status")
    public ResponseEntity<AlbumMaster> updateAlbumStatus(@PathVariable Integer albumId, @RequestParam String status) {
        try {
            AlbumMaster updatedAlbum = albumMasterService.updateAlbumStatus(albumId, status);
            return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // DELETE OPERATIONS
    // ===========================================

    @DeleteMapping("/{albumId}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer albumId) {
        try {
            System.out.println("Attempting to delete album with ID: " + albumId);
            albumMasterService.deleteAlbum(albumId);
            System.out.println("Successfully deleted album with ID: " + albumId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            System.err.println("Album not found with ID: " + albumId + " - " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println("Error deleting album with ID: " + albumId + " - " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/status/{status}")
    public ResponseEntity<Void> deleteAlbumsByStatus(@PathVariable String status) {
        try {
            albumMasterService.deleteAlbumsByStatus(status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/inactive")
    public ResponseEntity<Void> deleteInactiveAlbums() {
        try {
            albumMasterService.deleteInactiveAlbums();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllAlbums() {
        try {
            albumMasterService.deleteAllAlbums();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===========================================
    // BUSINESS LOGIC OPERATIONS
    // ===========================================

    @GetMapping("/search/global/{searchTerm}")
    public ResponseEntity<List<AlbumMaster>> searchAlbums(@PathVariable String searchTerm) {
        try {
            List<AlbumMaster> albums = albumMasterService.searchAlbums(searchTerm);
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<AlbumMaster>> getAlbumsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<AlbumMaster> albums = albumMasterService.getAlbumsWithPagination(page, size);
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/name")
    public ResponseEntity<List<AlbumMaster>> getAlbumsSortedByName() {
        try {
            List<AlbumMaster> albums = albumMasterService.getAlbumsSortedByName();
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted/status")
    public ResponseEntity<List<AlbumMaster>> getAlbumsSortedByStatus() {
        try {
            List<AlbumMaster> albums = albumMasterService.getAlbumsSortedByStatus();
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/featured")
    public ResponseEntity<List<AlbumMaster>> getFeaturedAlbums() {
        try {
            List<AlbumMaster> albums = albumMasterService.getFeaturedAlbums();
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 
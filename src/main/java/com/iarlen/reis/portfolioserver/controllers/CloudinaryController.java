package com.iarlen.reis.portfolioserver.controllers;

import com.iarlen.reis.portfolioserver.DTOs.ImageResponse;
import com.iarlen.reis.portfolioserver.DTOs.ImageUploadResponse;
import com.iarlen.reis.portfolioserver.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/portfolio")
public class CloudinaryController {
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/images")
    public ResponseEntity<List<ImageResponse>> allImages() {
        return this.cloudinaryService.cloudinaryAllImages();
    }

    @DeleteMapping("/images/delete/{slug}")
    public ResponseEntity<Map> deleteImage(@PathVariable("slug") String slug) throws IOException {
        return this.cloudinaryService.cloudinaryDelete(slug);
    }

    @PostMapping("/upload")
    public ResponseEntity<ImageUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
            return this.cloudinaryService.cloudinaryUpload("portfolio", file);
    }
}

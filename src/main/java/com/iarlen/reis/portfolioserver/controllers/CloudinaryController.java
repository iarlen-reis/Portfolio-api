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

@RestController
@RequestMapping("api/portfolio")
public class CloudinaryController {
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/images")
    public ResponseEntity<List<ImageResponse>> allImages() {
        List<ImageResponse> images = this.cloudinaryService.cloudinaryAllImages();

        return ResponseEntity.ok(images);
    }

    @PostMapping("/upload")
    public ResponseEntity<ImageUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = this.cloudinaryService.cloudinaryUpload("portfolio", file);

            return ResponseEntity.ok(new ImageUploadResponse(imageUrl));
        } catch (IOException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}

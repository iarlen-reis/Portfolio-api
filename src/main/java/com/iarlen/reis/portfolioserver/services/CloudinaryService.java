package com.iarlen.reis.portfolioserver.services;

import com.cloudinary.Cloudinary;
import com.iarlen.reis.portfolioserver.DTOs.ImageResponse;
import com.iarlen.reis.portfolioserver.DTOs.ImageUploadResponse;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryService {
    @Resource
    private Cloudinary cloudinary;

    public ResponseEntity<ImageUploadResponse> cloudinaryUpload(String folder, MultipartFile file) throws IOException {
        try {
            HashMap<Object, Object> options = new HashMap<>();

            options.put("folder", folder);
            options.put("public_id", file.getOriginalFilename());

            Map<String, String> uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);

            return ResponseEntity.ok(new ImageUploadResponse(uploadedFile.get("url")));
        } catch (IOException exception) {
            throw new IOException(exception);
        }
    }

    public ResponseEntity<List<ImageResponse>> cloudinaryAllImages() {
        try {
            List<Map<String, Object>> resources = (List<Map<String, Object>>) cloudinary.search()
                    .expression("folder=portfolio")
                    .execute()
                    .get("resources");

            return ResponseEntity.ok(resources.stream().map(ImageResponse::new).toList());
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public ResponseEntity<Map> cloudinaryDelete(String slug) throws IOException {
        return ResponseEntity.ok(cloudinary.uploader().destroy("portfolio/" + slug, null));
    }
}

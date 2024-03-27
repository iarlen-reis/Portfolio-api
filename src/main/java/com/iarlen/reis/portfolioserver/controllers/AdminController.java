package com.iarlen.reis.portfolioserver.controllers;

import com.iarlen.reis.portfolioserver.DTOs.ProjectRequest;
import com.iarlen.reis.portfolioserver.DTOs.ProjectResponse;
import com.iarlen.reis.portfolioserver.DTOs.ProjectResponseWithPagination;
import com.iarlen.reis.portfolioserver.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/portfolio/admin/projects")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping
    public ResponseEntity<ProjectResponseWithPagination> allProjects(@RequestParam(defaultValue = "") String filter, @RequestParam(defaultValue = "1") String page) {
        return adminService.allProjects(filter, page);
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest data) {
        return adminService.createProject(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable UUID id, @RequestBody @Valid ProjectRequest data) {
        return adminService.updateProject(id, data);
    }

    @DeleteMapping("/{id}/disable")
    public ResponseEntity<ProjectResponse> disableProject(@PathVariable UUID id) {
        return adminService.disableProject(id);
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<ProjectResponse> enableProject(@PathVariable UUID id) {
        return adminService.enableProject(id);
    }
}

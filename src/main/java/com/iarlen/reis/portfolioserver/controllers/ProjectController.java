package com.iarlen.reis.portfolioserver.controllers;

import com.iarlen.reis.portfolioserver.DTOs.ProjectRequest;
import com.iarlen.reis.portfolioserver.DTOs.ProjectResponse;
import com.iarlen.reis.portfolioserver.DTOs.ProjectResponseWithPagination;
import com.iarlen.reis.portfolioserver.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/portfolio/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectRequest> createProject(@RequestBody @Valid ProjectRequest data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(data));
    }

    @GetMapping
    public ResponseEntity<ProjectResponseWithPagination> allProjects(@RequestParam String filter, @RequestParam(defaultValue = "1") String page) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.allProjects(filter, page));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable UUID id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable UUID id, @RequestBody @Valid ProjectRequest data) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.updateProject(id, data));
    }

    @DeleteMapping("/{id}/disable")
    public ResponseEntity<ProjectResponse> disableProject(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.disableProject(id));
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<ProjectResponse> enableProject(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.enableProject(id));
    }

}

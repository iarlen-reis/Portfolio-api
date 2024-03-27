package com.iarlen.reis.portfolioserver.services;

import com.iarlen.reis.portfolioserver.DTOs.Pagination;
import com.iarlen.reis.portfolioserver.DTOs.ProjectRequest;
import com.iarlen.reis.portfolioserver.DTOs.ProjectResponse;
import com.iarlen.reis.portfolioserver.DTOs.ProjectResponseWithPagination;
import com.iarlen.reis.portfolioserver.models.ProjectModel;
import com.iarlen.reis.portfolioserver.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminService {
    @Autowired
    private ProjectRepository projectRepository;

    public ResponseEntity<ProjectResponseWithPagination> allProjects(String filter, String page) {
        PageRequest pageable = PageRequest.of(Integer.parseInt(page) - 1, 8);

        Page<ProjectModel> projects = projectRepository.findAllByTypeContainsOrderByCreatedAtDesc(filter.toLowerCase(), pageable);

        long total = projects.getTotalElements();
        long pages = projects.getTotalPages();
        boolean hasPrevious = projects.hasPrevious();
        boolean hasNext = projects.hasNext();

        Pagination pagination = new Pagination(total, pages, hasPrevious, hasNext);

        List<ProjectResponse> projectsResponse = projects.stream().map(ProjectResponse::new).toList();

        return ResponseEntity.ok(new ProjectResponseWithPagination(pagination, projectsResponse));
    }

    public ResponseEntity<ProjectResponse> createProject(ProjectRequest data) {
        ProjectModel project = projectRepository.save(new ProjectModel(data));

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProjectResponse(project));
    }

    @Transactional
    public ResponseEntity<ProjectResponse> updateProject(UUID id, ProjectRequest data) {
        ProjectModel project = projectRepository.getReferenceById(id);
        project.update(data);

        return ResponseEntity.status(HttpStatus.OK).body(new ProjectResponse(project));
    }

    @Transactional
    public ResponseEntity<ProjectResponse> disableProject(UUID id) {
        ProjectModel project = projectRepository.getReferenceById(id);
        project.disable();

        return ResponseEntity.status(HttpStatus.OK).body(new ProjectResponse(project));
    }

    @Transactional
    public ResponseEntity<ProjectResponse> enableProject(UUID id) {
        ProjectModel project = projectRepository.getReferenceById(id);
        project.enable();

        return ResponseEntity.status(HttpStatus.OK).body(new ProjectResponse(project));
    }

}

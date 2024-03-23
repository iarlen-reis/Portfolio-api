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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectRequest createProject(ProjectRequest data) {
        ProjectModel project = projectRepository.save(new ProjectModel(data));

        return new ProjectRequest(project);
    }

    public ProjectResponseWithPagination allProjects(String filter, String page) {
        if(Integer.parseInt(page) < 1) {
            page = "1";
        }

        PageRequest pageable = PageRequest.of(Integer.parseInt(page) - 1, 8);

        Page<ProjectModel> projects = projectRepository.findAllByTypeContainsAndActiveIsTrue(filter.toLowerCase(), pageable);

        long total = projects.getTotalElements();
        long pages = projects.getTotalPages();
        boolean hasPrevious = projects.hasPrevious();
        boolean hasNext = projects.hasNext();


        Pagination pagination = new Pagination(total, pages, hasPrevious, hasNext);
        List<ProjectResponse> projectsResponse = projects.stream().map(ProjectResponse::new).toList();

        return new ProjectResponseWithPagination(pagination, projectsResponse);
    }

    public ProjectResponse getProject(UUID id) {
        return new ProjectResponse(projectRepository.getReferenceById(id));
    }

    @Transactional
    public ProjectResponse updateProject(UUID id, ProjectRequest data) {
        ProjectModel project = projectRepository.getReferenceById(id);
        project.update(data);

        return new ProjectResponse(project);
    }

    @Transactional
    public ProjectResponse disableProject(UUID id) {
        ProjectModel project = projectRepository.getReferenceById(id);
        project.disable();

        return new ProjectResponse(project);
    }

    @Transactional
    public ProjectResponse enableProject(UUID id) {
        ProjectModel project = projectRepository.getReferenceById(id);
        project.enable();

        return new ProjectResponse(project);
    }

}

package com.iarlen.reis.portfolioserver.services;

import com.iarlen.reis.portfolioserver.DTOs.ProjectRequest;
import com.iarlen.reis.portfolioserver.DTOs.ProjectResponse;
import com.iarlen.reis.portfolioserver.models.ProjectModel;
import com.iarlen.reis.portfolioserver.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<ProjectResponse> allProjects() {
        return projectRepository.findAllByActiveIsTrue().stream().map(ProjectResponse::new).toList();
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

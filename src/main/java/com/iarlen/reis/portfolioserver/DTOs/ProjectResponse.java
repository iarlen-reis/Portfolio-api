package com.iarlen.reis.portfolioserver.DTOs;

import com.iarlen.reis.portfolioserver.models.ProjectModel;

public record ProjectResponse(String id, String name, String type, String image, String deploy, String github, String description, String finished, String started, String[] technologies) {

    public ProjectResponse(ProjectModel project) {
        this(
                project.getId().toString(),
                project.getNome(),
                project.getType().toString(),
                project.getImage(),
                project.getDeploy(),
                project.getGithub(),
                project.getDescription(),
                project.getFinished(),
                project.getStarted(),
                project.getTechnologies()
        );
    }
}

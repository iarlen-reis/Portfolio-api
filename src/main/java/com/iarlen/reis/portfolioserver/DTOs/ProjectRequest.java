package com.iarlen.reis.portfolioserver.DTOs;

import com.iarlen.reis.portfolioserver.enums.Type;
import com.iarlen.reis.portfolioserver.models.ProjectModel;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record ProjectRequest(
        @NotBlank
        String name,

        @Enumerated
        Type type,

        @NotBlank
        String image,

        @NotBlank
        String deploy,

        @NotBlank
        String github,

        @NotBlank
        String description,

        @NotBlank
        String finished,

        @NotBlank
        String started,

        @NotEmpty
        String[] technologies
) {
    public ProjectRequest(ProjectModel project) {
        this(
                project.getNome(),
                project.getType(),
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

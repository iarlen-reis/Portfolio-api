package com.iarlen.reis.portfolioserver.models;

import com.iarlen.reis.portfolioserver.DTOs.ProjectRequest;
import com.iarlen.reis.portfolioserver.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "Project")
@Entity(name = "projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProjectModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String image;

    private String deploy;

    private String github;

    private String description;

    private String finished;

    private String started;

    private String[] technologies;

    private Boolean active = true;

    public ProjectModel(String nome, Type type, String image, String deploy, String github, String description, String finished, String started, String[] technologies) {
        this.nome = nome;
        this.type = type;
        this.image = image;
        this.deploy = deploy;
        this.github = github;
        this.started = started;
        this.finished = finished;
        this.description = description;
        this.technologies = technologies;

        this.active = true;
    }

    public ProjectModel(ProjectRequest data) {
        this.nome = data.name();
        this.type = data.type();
        this.image = data.image();
        this.deploy = data.deploy();
        this.github = data.github();
        this.finished = data.finished();
        this.started = data.started();
        this.technologies = data.technologies();
        this.description = data.description();

        this.active = true;
    }

    public void update (ProjectRequest data) {
        this.nome = data.name();
        this.type = data.type();
        this.image = data.image();
        this.deploy = data.deploy();
        this.github = data.github();
        this.finished = data.finished();
        this.started = data.started();
        this.technologies = data.technologies();
        this.description = data.description();
    }
    public void disable () {
        this.active = false;
    }

    public void enable () {
        this.active = true;
    }
}

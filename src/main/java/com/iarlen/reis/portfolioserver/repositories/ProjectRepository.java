package com.iarlen.reis.portfolioserver.repositories;

import com.iarlen.reis.portfolioserver.models.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<ProjectModel, UUID> {
    List<ProjectModel> findAllByTypeContainsAndActiveIsTrue(String filter);
}

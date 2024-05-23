package com.iarlen.reis.portfolioserver.repositories;

import com.iarlen.reis.portfolioserver.models.ProjectModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<ProjectModel, UUID> {
    Page<ProjectModel> findAllByTypeContainsAndActiveIsTrueAndOrderByCreatedAtDesc(String filter, Pageable pageable);

    Page<ProjectModel> findAllByTypeContainsOrderByCreatedAtDesc(String filter, Pageable pageable);
}

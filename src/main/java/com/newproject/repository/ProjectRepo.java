package com.newproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.newproject.model.Project;

import java.util.List;

@Repository

public interface ProjectRepo extends MongoRepository<Project, String> {
    Page<Project> findAll(Pageable pageable);
    List<Project> findByTitleContaining(String title);


}

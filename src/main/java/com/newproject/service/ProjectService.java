package com.newproject.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.newproject.model.Project;
import com.newproject.repository.ProjectRepo;

import javax.validation.ValidationException;

@Service
public class ProjectService {

	private final ProjectRepo projectRepo;

	@Autowired
	public ProjectService(ProjectRepo projectRepo) {
		this.projectRepo = projectRepo;
	}

	public Project createProject(Project project) {
		project.setCreatedOn(new Date());
		if(project.getStartDate() == null){
			throw new ValidationException("StartDate cannot be null");

		}
		if(project.getEndDate() == null){
			throw new ValidationException("EndDate cannot be null");

		}
		return projectRepo.save(project);
	}

//	public List<String> getAllProjectTitles() {
//		Sort sort = Sort.by(Sort.Direction.ASC, "title");
//		List<Project> projectTitles = projectRepo.findAll(sort);
//		return projectTitles.stream()
//				.map(Project::getTitle)
//				.collect(Collectors.toList());
//	}
	public List<Map<String, Object>> getAllTitles(){
		List<Map<String, Object>> titles = new ArrayList<>();
		projectRepo.findAll().forEach(project -> {
			Map<String, Object> titleAndId = new HashMap<>();
			titleAndId.put("title", project.getTitle());
			titleAndId.put("id", project.getId());
			titles.add(titleAndId);
		});
		return titles;
	}
	public Page<Project> getAllProjects(Pageable pageable) {

		return projectRepo.findAll(pageable);
	}
	public List<Project> getAllProjectList(){

		return projectRepo.findAll();
	}
	public Project getProjectById(String id) {
		return projectRepo.findById(id).orElse(null);
	}

//	public List<Project> getAllProjectTitles() {
//		return projectRepo.findAll();
//	}

	public void deleteProject(String id) {
		projectRepo.deleteById(id);
	}

	public Project updateProject(String id, Project updatedProject) {
		Optional<Project> existingProject = projectRepo.findById(id);
		if (existingProject.isPresent()) {
			Project project = existingProject.get();
			
			if (updatedProject.getTitle()!= null && !updatedProject.getTitle().equalsIgnoreCase("")) {
				project.setTitle(updatedProject.getTitle());
			}
			if (updatedProject.getDescription()!= null && !updatedProject.getDescription().equalsIgnoreCase("")) {
				project.setDescription(updatedProject.getDescription());
			}

			if (updatedProject.getStartDate()!= null) {
				project.setStartDate(updatedProject.getStartDate());
			}else{
				project.setStartDate(project.getStartDate());
			}
			if (updatedProject.getEndDate()!= null) {
				project.setEndDate(updatedProject.getEndDate());
			}else{
				project.setEndDate(project.getEndDate());
			}
			return projectRepo.save(project);
		}
		return null;
	}
	public List<Project> filterProjectsByTitle(String title) {
		return projectRepo.findByTitleContaining(title);
	}

}
//}

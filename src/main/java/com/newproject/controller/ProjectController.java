package com.newproject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.newproject.model.Project;
import com.newproject.service.ProjectService;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class ProjectController {

	@Autowired
	private ProjectService projectService;


	@PostMapping("/create")
	public ResponseEntity<Project> createProject(@RequestBody Project project) {

		  Project createdProject = projectService.createProject(project);
		return ResponseEntity.ok(createdProject);
	}

	@GetMapping("/getAllProjects")
	public ResponseEntity<Page<Project>>getAllProjects(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size)
			//@RequestParam(defaultValue = "") String sortBy)
	{
		Sort sort = Sort.by(Sort.Direction.DESC, "createdOn");
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Project> projects = projectService.getAllProjects(pageable);
  		System.out.println(page+ " "+size);
		System.out.println(projects);
		return ResponseEntity.ok(projects);
	}
	@GetMapping("/getAllProjectList")
	public List<Project>getAllProjectlist(){

		return projectService.getAllProjectList();
	}
//@GetMapping("/getAllProjectTitle")
//public List<Map<String, Object>> getAllProjectTitles() {
//	return  projectService.getAllTitles();
//
//}

	@GetMapping("/getProject/{id}")
	public ResponseEntity<Object> getProjectById(@PathVariable String id) {

		Project data = projectService.getProjectById(id);
		if(data!=null){
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is Not Available");
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteProject(@PathVariable String id) {
	Project data = projectService.getProjectById(id);
	if(data!=null){
		projectService.deleteProject(id);
		return ResponseEntity.ok().body("\"Deleted Successfully...!!!\"");
	}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is Not Available");
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateProject(@PathVariable String id,@RequestBody Project updatedProject) {
		//project.setId(id);
		Project data = projectService.getProjectById(id);
		if(data!=null){
			Project updated = projectService.updateProject(id, updatedProject);
			return ResponseEntity.status(HttpStatus.CREATED).body(updatedProject);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is Not available");
		}



	@GetMapping("/filter")
	public ResponseEntity<List<Project>> filterProjectsByTitle(@RequestParam String title) {
		List<Project> filteredProjects = projectService.filterProjectsByTitle(title);
		return ResponseEntity.ok(filteredProjects);
	}

//	@org.jetbrains.annotations.NotNull
}

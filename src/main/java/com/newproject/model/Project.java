package com.newproject.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Document(collection = "project")
 
public class Project {
	@Id
	private String id;
//	@JsonView(Views.Titles.class)
	private String title;
	private String description;
	@NotNull(message = "StartDate cannot be null")
	private LocalDate startDate;
	@NotNull(message = "Enddate cannot be null")
	private LocalDate endDate;
	@Field("createdOn")
	private Date createdOn;

	public Project() {
	}

	public Project(String title, String description, LocalDate startDate, LocalDate endDate, Date createdOn) {

		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdOn=createdOn;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Date getCreatedOn() {return createdOn;}

	public void setCreatedOn(Date createdOn) {this.createdOn = createdOn;}
}

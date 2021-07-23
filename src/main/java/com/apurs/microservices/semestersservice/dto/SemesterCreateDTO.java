package com.apurs.microservices.semestersservice.dto;

import java.time.ZonedDateTime;

public class SemesterCreateDTO {
	private int syllabusId;
	private int courseId;
	private int number;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
	
	public SemesterCreateDTO() {
		super();
	}

	public SemesterCreateDTO(int syllabusId, int courseId, int number) {
		super();
		this.syllabusId = syllabusId;
		this.courseId = courseId;
		this.number = number;
		this.setCreatedAt(ZonedDateTime.now());
		this.setUpdatedAt(ZonedDateTime.now());
	}

	public int getSyllabusId() {
		return syllabusId;
	}

	public void setSyllabusId(int syllabusId) {
		this.syllabusId = syllabusId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public ZonedDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}

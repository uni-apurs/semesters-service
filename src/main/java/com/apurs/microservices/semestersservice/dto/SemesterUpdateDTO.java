package com.apurs.microservices.semestersservice.dto;

import java.time.ZonedDateTime;

public class SemesterUpdateDTO {
	private int id;
	private int syllabusId;
	private int courseId;
	private int number;
	private ZonedDateTime updatedAt;
	
	public SemesterUpdateDTO(int id, int syllabusId, int courseId, int number) {
		super();
		this.id = id;
		this.syllabusId = syllabusId;
		this.courseId = courseId;
		this.number = number;
		this.setUpdatedAt(ZonedDateTime.now());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public ZonedDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}

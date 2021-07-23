package com.apurs.microservices.semestersservice.dto;

public class SemesterDTO {
	private int id;
	private int syllabusId;
	private int courseId;
	private int number;
	
	public SemesterDTO() {
		super();
	}
	
	public SemesterDTO(int id, int syllabusId, int courseId, int number) {
		super();
		this.id = id;
		this.syllabusId = syllabusId;
		this.courseId = courseId;
		this.number = number;
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
}

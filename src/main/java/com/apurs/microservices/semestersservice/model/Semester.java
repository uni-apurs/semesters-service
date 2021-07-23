package com.apurs.microservices.semestersservice.model;

import java.time.ZonedDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "\"semester\"")
@SequenceGenerator(name = "semester_id_seq", initialValue = 1, allocationSize = 1)
public class Semester {
	@Id
	@Column(name = "\"id\"")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "semester_id_seq")
	private int id;
	
	@Column(name = "\"syllabusId\"")
	private int syllabusId;
	
	@Column(name = "\"courseId\"")
	private int courseId;
	
	@Column(name = "\"number\"")
	private int number;

	@Column(name = "\"createdAt\"")
	private ZonedDateTime createdAt;
	
	@Column(name = "\"updatedAt\"")
	private ZonedDateTime updatedAt;

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

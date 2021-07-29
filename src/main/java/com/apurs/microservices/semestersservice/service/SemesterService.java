package com.apurs.microservices.semestersservice.service;

import java.util.List;

import com.apurs.microservices.coursesservice.dto.CourseDTO;
import com.apurs.microservices.semestersservice.dto.SemesterCreateDTO;
import com.apurs.microservices.semestersservice.dto.SemesterDTO;
import com.apurs.microservices.semestersservice.dto.SemesterUpdateDTO;

public interface SemesterService {
	public abstract List<SemesterDTO> findAll();
	public abstract SemesterDTO findOne(Integer id);
	public abstract SemesterDTO insert(SemesterCreateDTO semester) throws Exception;
	public abstract SemesterDTO update(SemesterUpdateDTO semester) throws Exception;
	public abstract boolean delete(Integer id);
	
	public abstract List<CourseDTO> findCoursesBySyllabusNameAndSemesterNumber(String syllabusName, Integer number);
}

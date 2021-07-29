package com.apurs.microservices.semestersservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apurs.microservices.coursesservice.dto.CourseDTO;
import com.apurs.microservices.semestersservice.dto.SemesterCreateDTO;
import com.apurs.microservices.semestersservice.dto.SemesterDTO;
import com.apurs.microservices.semestersservice.dto.SemesterUpdateDTO;
import com.apurs.microservices.semestersservice.service.SemesterService;

@RestController
@RequestMapping("/semesters")
public class SemesterRestController {

	@Autowired
	private SemesterService semesterService;
	
	@GetMapping("")
	public List<SemesterDTO> getSemesters() {
		return semesterService.findAll();
	}
	
	@GetMapping("/{id}")
	public SemesterDTO getSemester(@PathVariable("id") Integer id) {
		return semesterService.findOne(id);
	}
	
	@PostMapping("")
	public ResponseEntity<SemesterDTO> insertSemester(@RequestBody SemesterCreateDTO semester) throws Exception {
		if (semesterService.insert(semester) != null)
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("")
	public ResponseEntity<SemesterDTO> updateSemester(@RequestBody SemesterUpdateDTO semester) throws Exception {
		if (semesterService.update(semester) != null)
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SemesterDTO> deleteSemester(@PathVariable("id") Integer id) {
		if (semesterService.delete(id))
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/courses")
	public ResponseEntity<List<CourseDTO>> getCoursesBySyllabusNameAndSemesterNumber(@RequestParam(required = false) String syllabusName, @RequestParam(required = false) Integer number) {
		if (number > 0 && syllabusName != null)
			return new ResponseEntity<List<CourseDTO>> (semesterService.findCoursesBySyllabusNameAndSemesterNumber(syllabusName, number), HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

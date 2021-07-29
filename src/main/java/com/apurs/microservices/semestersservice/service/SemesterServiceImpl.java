package com.apurs.microservices.semestersservice.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apurs.microservices.coursesservice.dto.CourseDTO;
import com.apurs.microservices.semestersservice.dto.SemesterCreateDTO;
import com.apurs.microservices.semestersservice.dto.SemesterDTO;
import com.apurs.microservices.semestersservice.dto.SemesterUpdateDTO;
import com.apurs.microservices.semestersservice.model.Semester;
import com.apurs.microservices.semestersservice.repository.SemesterRepository;
import com.apurs.microservices.syllabusesservice.dto.SyllabusDTO;

@Service
public class SemesterServiceImpl implements SemesterService {

	private SemesterRepository semesterRepository;
	
	private RestTemplate restTemplate = new RestTemplate();
	private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Value("${app.syllabusesEndpoint}")
	private String syllabusesEndpoint;
	
	@Value("${app.coursesEndpoint}")
	private String coursesEndpoint;
	
	public SemesterServiceImpl(SemesterRepository semesterRepository) {
		this.semesterRepository = semesterRepository;
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	@Override
	public List<SemesterDTO> findAll() {
		List<Semester> semesters = semesterRepository.findAll();
		List<SemesterDTO> dtos = new ArrayList<SemesterDTO>();
		
		for (Semester semester : semesters)
			dtos.add(modelMapper.map(semester, SemesterDTO.class));
		
		return dtos;
	}

	@Override
	public SemesterDTO findOne(Integer id) {
		Semester semester = semesterRepository.getById(id);
		return modelMapper.map(semester, SemesterDTO.class);
	}

	@Override
	public SemesterDTO insert(SemesterCreateDTO semester) throws Exception {
		ResponseEntity<String> resSyllabus = restTemplate.getForEntity(syllabusesEndpoint + semester.getSyllabusId(), String.class);
		ResponseEntity<String> resCourse = restTemplate.getForEntity(coursesEndpoint + semester.getCourseId(), String.class);

		if (!resSyllabus.getStatusCode().equals(HttpStatus.OK))
			throw new Exception("Invalid syllabusId.");
		
		if (!resCourse.getStatusCode().equals(HttpStatus.OK))
			throw new Exception("Invalid courseId.");
		
		Semester createSemester = modelMapper.map(semester, Semester.class);
		createSemester = semesterRepository.save(createSemester);
		return modelMapper.map(createSemester, SemesterDTO.class);
	}

	@Override
	public SemesterDTO update(SemesterUpdateDTO semester) throws Exception {
		if(!semesterRepository.existsById(semester.getId()))
			return null;
		
		ResponseEntity<String> resSyllabus = restTemplate.getForEntity(syllabusesEndpoint + semester.getSyllabusId(), String.class);
		ResponseEntity<String> resCourse = restTemplate.getForEntity(coursesEndpoint + semester.getCourseId(), String.class);

		if (!resSyllabus.getStatusCode().equals(HttpStatus.OK))
			throw new Exception("Invalid syllabusId.");
		
		if (!resCourse.getStatusCode().equals(HttpStatus.OK))
			throw new Exception("Invalid courseId.");
		
		Semester tempSemester = semesterRepository.getById(semester.getId());
		Semester updatedSemester = modelMapper.map(semester, Semester.class);
		updatedSemester.setCreatedAt(tempSemester.getCreatedAt());
		updatedSemester = semesterRepository.save(updatedSemester);
		return modelMapper.map(updatedSemester, SemesterDTO.class);
	}

	@Override
	public boolean delete(Integer id) {
		if(!semesterRepository.existsById(id))
			return false;
		
		semesterRepository.deleteById(id);
		return true;
	}
	
	@Override
	public List<CourseDTO> findCoursesBySyllabusNameAndSemesterNumber(String syllabusName, Integer number) {
		ResponseEntity<SyllabusDTO[]> resSyllabus = restTemplate.getForEntity(syllabusesEndpoint + "?name=" + syllabusName, SyllabusDTO[].class);
		
		List<SyllabusDTO> syllabuses = new ArrayList<SyllabusDTO>();
		List<CourseDTO> courses = new ArrayList<CourseDTO>();
		String sql = "";

		for (int i = 0; i < resSyllabus.getBody().length; i++) {
			syllabuses.add(resSyllabus.getBody()[i]);
			sql = "SELECT * FROM course c JOIN semester sem ON c.\"id\" = sem.\"courseId\" "
					+ "WHERE sem.\"number\" = " + number + " AND sem.\"syllabusId\" = " + resSyllabus.getBody()[i].getId();
			courses.addAll(jdbcTemplate.query(sql, (rs, rowNum) -> (
							new CourseDTO(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("year")
							))));
			sql = "";
		}
		return courses;
	}
}

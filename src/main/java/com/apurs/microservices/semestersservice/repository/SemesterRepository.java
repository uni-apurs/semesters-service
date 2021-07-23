package com.apurs.microservices.semestersservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apurs.microservices.semestersservice.model.Semester;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
	List<Semester> findBySyllabusId(Integer id);
	List<Semester> findByCourseId(Integer id);
	List<Semester> findByNumber(Integer number);
}

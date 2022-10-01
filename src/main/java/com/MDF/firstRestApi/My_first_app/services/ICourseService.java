package com.MDF.firstRestApi.My_first_app.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.MDF.firstRestApi.My_first_app.models.Course;
import com.MDF.firstRestApi.My_first_app.payloads.ApiResponse;

public interface ICourseService {
	ResponseEntity<ApiResponse> saveCourse(Course course);
	Course getCourse(String name);
	List<Course>getAllCourses(int from, int limit);
	List<Course> updateCourse(Course course, Long id);
	Course deleteCourse (Long id);

}

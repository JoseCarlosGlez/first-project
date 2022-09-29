package com.MDF.firstRestApi.My_first_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MDF.firstRestApi.My_first_app.models.Course;
import com.MDF.firstRestApi.My_first_app.services.ICourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private ICourseService _courseService;

	@PostMapping
	public ResponseEntity<Course> createCourse(@RequestBody Course course) {
		return new ResponseEntity<>(this._courseService.saveCourse(course), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<Course> getCourse(@RequestParam(name = "courseName", required = false) String courseName) {
		return new ResponseEntity<>(this._courseService.getCourse(courseName), HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Course>> getAllCourses(
			@RequestParam(name = "from", defaultValue = "0") int from,
			@RequestParam(name = "limit", defaultValue = "20") int limit) {
		return new ResponseEntity<>(this._courseService.getAllCourses(from, limit), HttpStatus.OK);
	}

}

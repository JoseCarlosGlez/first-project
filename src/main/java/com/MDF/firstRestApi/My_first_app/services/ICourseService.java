package com.MDF.firstRestApi.My_first_app.services;

import java.util.List;

import com.MDF.firstRestApi.My_first_app.models.Course;

public interface ICourseService {
	Course saveCourse(Course course);
	Course getCourse(String name);
	List<Course>getAllCourses(int from, int limit);
	Course updateCourse(Course course);
	Course deleteCourse (Long id);

}

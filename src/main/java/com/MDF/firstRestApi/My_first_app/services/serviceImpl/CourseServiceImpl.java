package com.MDF.firstRestApi.My_first_app.services.serviceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.MDF.firstRestApi.My_first_app.exception.CourseApiException;
import com.MDF.firstRestApi.My_first_app.models.Course;
import com.MDF.firstRestApi.My_first_app.payloads.ApiResponse;
import com.MDF.firstRestApi.My_first_app.services.ICourseService;

@Component
public class CourseServiceImpl implements ICourseService {

	private List<Course> courseList = new ArrayList<>();

	@Override
	public ResponseEntity<ApiResponse> saveCourse(Course course) {
		// TODO Auto-generated method stub

		if (!courseList.isEmpty()) {
			course.setId(courseList.get(courseList.size() - 1).getId() + 1);
		}

		if (courseList.stream().filter(_course -> _course.getName().equals(course.getName())).findFirst().isPresent()) {
			throw new CourseApiException(HttpStatus.BAD_REQUEST, "The course already exist");
		}

		this.courseList.add(course);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "The course was created correctly"),HttpStatus.CREATED );
	}

	@Override
	public Course getCourse(String name) {
		// TODO Auto-generated method stub

		if (name == null || name.isBlank()) {
			throw new CourseApiException(HttpStatus.BAD_REQUEST,
					"it's neccessary the 'Coursename' param to execute the search");
		}

		return this.courseList.stream().filter(_course -> _course.getName().contains(name))
				.findFirst()
				.orElseThrow(
				() -> new CourseApiException(HttpStatus.BAD_REQUEST, "Did not find course with the name provided"));

	}

	@Override
	public List<Course> getAllCourses(int from, int limit) {
		// TODO Auto-generated method stub
		return this.courseList.stream()
								.sorted(Comparator.comparingLong(Course::getId))
								.skip(from)
								.limit(limit)
								.toList();
								
	}
	
	@Override
	public List<Course> updateCourse(Course course, Long id) {
		// TODO Auto-generated method stub
		Course findCourse = this.courseList.stream()
						.filter(_course->_course.getId() == id)
						.findFirst()
						.orElseThrow(()-> new CourseApiException(HttpStatus.BAD_REQUEST, "The course with ID" + id + " does not exist" ));

		 this.courseList.remove(findCourse);
		 
		 Course courseUpdated = new Course(course.getName(), course.getAuthor());
		 
		 courseUpdated.setId(findCourse.getId());
 
		 this.courseList.add(courseUpdated);
		 
		 return this.getAllCourses((int)courseUpdated.getId(), 20);
	}

	@Override
	public Course deleteCourse(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

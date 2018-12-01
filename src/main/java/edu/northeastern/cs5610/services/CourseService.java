package edu.northeastern.cs5610.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5610.models.Course;
import edu.northeastern.cs5610.repositories.CourseRepository;


@RestController
@CrossOrigin(origins = "*")
public class CourseService {
	@Autowired
	UserService userService;

	@Autowired
	CourseRepository courseRepository;
	List<Course> allCourses = null;

	@GetMapping("/api/user/{userId}/course")
	public Iterable<Course> findAllCourses(@PathVariable("userId") int userId) {
		return courseRepository.findAll(); 
//		User user = userService.findUserById(userId);
//	    return user.getCourses();
	}

	@PostMapping("/api/user/{userId}/course")
	public Course createCourse(@PathVariable("userId") int userId, @RequestBody Course course) {
		return courseRepository.save(course);
//		User user = userService.findUserById(userId);
//		user.getCourses().add(course);
//		return user.getCourses();
	}
	
    @GetMapping("/api/course/{course_id}")
	public Course findCourseById(@PathVariable("course_id") int cId) {
    	Optional<Course> resultSet = courseRepository.findById(cId);
		if(resultSet.isPresent()) {
			Course course = resultSet.get();
			return course;
		}
//		List<User> allUsers = userService.findAllUsers();
//		for(User u: allUsers) { List<Course> courses = u.getCourses();
//			for(Course c: courses)
//				if(c.getId() == cId)
//					return c;
//		}
		return null;
    }
    
    @DeleteMapping("/api/course/{course_id}")
    public void deleteCourse(@PathVariable("course_id") int cId) {
    	 courseRepository.deleteById(cId);
//        List<User> allUsers = userService.findAllUsers();
//        for (User u : allUsers) {
//            List<Course> allCourses = u.getCourses();
//            for (Course course : allCourses) {
//                if (course.getId() == cId) {
//                    allCourses.remove(course);
//                    u.setCourses(allCourses);
//                    return u.getCourses();
//                }
//            }
//        }
//        return null;
    }
    
	@PutMapping("/api/course/{course_id}")
	public Course updateCourse(@PathVariable("course_id") int courseId, @RequestBody Course c) {
		Optional<Course> resultSet = courseRepository.findById(courseId);
		
		if(resultSet.isPresent()) {
			Course course = resultSet.get();
			course.setTitle(c.getTitle());
			return courseRepository.save(course);
		}
//	    Course c1 = findCourseById(courseId);
//	    if (c1 != null) {
//	        c1.setTitle(c.getTitle());
//	        c1.setModules(c.getModules());
//	        return c;
//	    }
	    return null;
	} 
}
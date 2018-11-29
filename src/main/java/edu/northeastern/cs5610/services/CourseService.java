package edu.northeastern.cs5610.services;
import java.util.List;

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
import edu.northeastern.cs5610.models.User;


@RestController
@CrossOrigin(origins = "*")
public class CourseService {
	@Autowired
	UserService userService;

	List<Course> allCourses = null;

	@GetMapping("/api/user/{userId}/course")
	public List<Course> findAllCourses(@PathVariable("userId") int userId) {
		User user = userService.findUserById(userId);
	    return user.getCourses();
	}

	@PostMapping("/api/user/{userId}/course")
	public List<Course> createCourse(@PathVariable("userId") int userId, @RequestBody Course course) {
		User user = userService.findUserById(userId);
		user.getCourses().add(course);
		return user.getCourses();
	}
	
    @GetMapping("/api/course/{course_id}")
	public Course findCourseById(@PathVariable("course_id") int cId) {
		List<User> allUsers = userService.findAllUsers();
		for(User u: allUsers) { List<Course> courses = u.getCourses();
			for(Course c: courses)
				if(c.getId() == cId)
					return c;
		}
		return null;
    }
    
    @DeleteMapping("/api/course/{course_id}")
    public List<Course> deleteCourse(@PathVariable("course_id") int cId) {
        List<User> allUsers = userService.findAllUsers();
        for (User u : allUsers) {
            List<Course> allCourses = u.getCourses();
            for (Course course : allCourses) {
                if (course.getId() == cId) {
                    allCourses.remove(course);
                    u.setCourses(allCourses);
                    return u.getCourses();
                }
            }
        }
        return null;
    }
    
	@PutMapping("/api/course/{course_id}")
	public Course updateCourse(@PathVariable("course_id") int courseId, @RequestBody Course c) {
	    Course c1 = findCourseById(courseId);
	    if (c1 != null) {
	        c1.setTitle(c.getTitle());
	        c1.setModules(c.getModules());
	        return c;
	    }
	    return null;
	} 
}
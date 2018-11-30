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
import edu.northeastern.cs5610.models.Lesson;
import edu.northeastern.cs5610.models.Module;
import edu.northeastern.cs5610.models.User;

@RestController
@CrossOrigin(origins = "*")
public class LessonService {
	@Autowired
	UserService userService;
	@Autowired
	CourseService courseService;
	@Autowired
	ModuleService moduleService;

	@PostMapping("/api/module/{mid}/lesson")
	public List<Lesson> createLesson(@PathVariable("mid") int moduleId, @RequestBody Lesson lesson) {
		Module module = moduleService.findModuleById(moduleId);
		module.getLessons().add(lesson);
		return module.getLessons();
	}

	@GetMapping("/api/module/{moduleId}/lesson")
	public List<Lesson> findAllLessons(@PathVariable("moduleId") int moduleId) {
		Module module = moduleService.findModuleById(moduleId);
		return module.getLessons();
	}

	@GetMapping("/api/lesson/{lid}")
	public Lesson findLessonById(@PathVariable("lid") int lessonId) {
		List<User> allUsers = userService.findAllUsers();
		for (User u : allUsers) {
			List<Course> allCourses = u.getCourses();
			for (Course c : allCourses) {
				List<Module> allModules = c.getModules();
				for (Module m : allModules) {
					List<Lesson> allLessons = m.getLessons();
					for (Lesson l : allLessons)
						if (l.getId() == lessonId)
							return l;
				}
			}
		}
		return null;
	}

	@PutMapping("/api/lesson/{lid}")
	public Lesson updateLesson(@PathVariable("lid") int lessonId, @RequestBody Lesson new_lesson) {
		Lesson curr_lesson = findLessonById(lessonId);
		curr_lesson.setTitle(new_lesson.getTitle());
		curr_lesson.setTopics(new_lesson.getTopics());
		return curr_lesson;
	}

	@DeleteMapping("/api/lesson/{lid}")
	public List<Lesson> deleteLesson(@PathVariable("lid") int lid) {
		List<User> allUsers = userService.findAllUsers();
		for (User u : allUsers) {
			List<Course> allCourses = u.getCourses();
			for (Course c : allCourses) {
				List<Module> allModules = c.getModules();
				for (Module m : allModules) {
					List<Lesson> allLessons = m.getLessons();
					for (Lesson l : allLessons) {
						if (l.getId() == lid) {
							allLessons.remove(l);
							m.setLessons(allLessons);
							return m.getLessons();
						}
					}
				}
			}
		}
		return null;
	}
}

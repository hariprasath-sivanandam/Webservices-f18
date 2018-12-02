package edu.northeastern.cs5610.services;

import java.util.Iterator;
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

import edu.northeastern.cs5610.models.Lesson;
import edu.northeastern.cs5610.models.Module;
import edu.northeastern.cs5610.repositories.CourseRepository;
import edu.northeastern.cs5610.repositories.LessonRepository;
import edu.northeastern.cs5610.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*")
public class LessonService {
	@Autowired
	UserService userService;
	@Autowired
	CourseService courseService;
	@Autowired
	ModuleService moduleService;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	CourseRepository courseRepository;

	@PostMapping("/api/module/{mid}/lesson")
	public Lesson createLesson(@PathVariable("mid") int moduleId, @RequestBody Lesson newLesson) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			newLesson.setModule(module);
			List<Lesson> new_lesson = module.getLessons();
		    new_lesson.add(newLesson);
		    module.setLessons(new_lesson);
			return lessonRepository.save(newLesson);
		}
		return null;
//		Module module = moduleService.findModuleById(moduleId);
//		module.getLessons().add(lesson);
//		return module.getLessons();
	}

	@GetMapping("/api/module/{moduleId}/lesson")
	public Iterable<Lesson> findAllLessons(@PathVariable("moduleId") int moduleId) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			return module.getLessons();
		}
		return null;
//		Module module = moduleService.findModuleById(moduleId);
//		return module.getLessons();
	}

	@GetMapping("/api/lesson/{lid}")
	public Lesson findLessonById(@PathVariable("lid") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return lesson;
		}
//		List<User> allUsers = userService.findAllUsers();
//		for (User u : allUsers) {
//			List<Course> allCourses = u.getCourses();
//			for (Course c : allCourses) {
//				List<Module> allModules = c.getModules();
//				for (Module m : allModules) {
//					List<Lesson> allLessons = m.getLessons();
//					for (Lesson l : allLessons)
//						if (l.getId() == lessonId)
//							return l;
//				}
//			}
//		}
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
	public void deleteLesson(@PathVariable("lid") int lid) {
		Module module  = findLessonById(lid).getModule();
    	List<Lesson> lessons = module.getLessons();
    	Iterator<Lesson> iterator= lessons.iterator();
    	while (iterator.hasNext()) {
    		   Lesson l = iterator.next();
    		  if(l.getId()==lid)
    		    iterator.remove();
    	}
//		List<User> allUsers = userService.findAllUsers();
//		for (User u : allUsers) {
//			List<Course> allCourses = u.getCourses();
//			for (Course c : allCourses) {
//				List<Module> allModules = c.getModules();
//				for (Module m : allModules) {
//					List<Lesson> allLessons = m.getLessons();
//					for (Lesson l : allLessons) {
//						if (l.getId() == lid) {
//							allLessons.remove(l);
//							m.setLessons(allLessons);
//							return m.getLessons();
//						}
//					}
//				}
//			}
//		}
		lessonRepository.deleteById(lid);
	}
}

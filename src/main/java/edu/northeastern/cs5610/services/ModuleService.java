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
import edu.northeastern.cs5610.models.Module;
import edu.northeastern.cs5610.models.User;

@RestController
@CrossOrigin(origins = "*")
public class ModuleService {
	
  @Autowired
  	UserService userService;
  @Autowired
  	CourseService courseService;
  
    @GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModules(@PathVariable("courseId") int courseId) {
		Course course = courseService.findCourseById(courseId);
		return course.getModules();
  	}
    
    @PostMapping("/api/course/{cid}/module")
    public List<Module> createModule(@PathVariable("cid") int courseId, @RequestBody Module module) {
      Course course = courseService.findCourseById(courseId);
      course.getModules().add(module);
      return course.getModules();
    }
    
    @GetMapping("/api/module/{mid}")
    public Module findModuleById(@PathVariable("mid") int moduleId) {
      List<User> allUsers = userService.findAllUsers();
      for (User u : allUsers) {
          List<Course> allCourses = u.getCourses();
          for (Course c : allCourses) {
              List<Module> allModules = c.getModules();
              for (Module m : allModules) 
                  if (m.getId() == moduleId) 
                      return m;
          }
      }
      return null;
    }
    
    @PutMapping("/api/module/{mid}")
    public Module updateModule(@PathVariable("mid") int moduleId, @RequestBody Module newModule) {
      Module currModule = findModuleById(moduleId);
      currModule.setTitle(newModule.getTitle());
      currModule.setLessons(newModule.getLessons());
      return currModule;
    }
    
    @DeleteMapping("/api/module/{mid}")
    public List<Module> deleteModule(@PathVariable("mid") int moduleId) {
       List<User> allUsers = userService.findAllUsers();
       for (User u : allUsers) {
           List<Course> allCourses = u.getCourses();
           for (Course c : allCourses) {
              List<Module> allModules = c.getModules();
              for (Module m : allModules)
                  if (m.getId() == moduleId) {
                      allModules.remove(m);
                      c.setModules(allModules);
                      return c.getModules();
                  }
          }
      }
      return null;
  }	
}
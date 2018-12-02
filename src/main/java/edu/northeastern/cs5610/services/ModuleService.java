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

import edu.northeastern.cs5610.models.Course;
import edu.northeastern.cs5610.models.Module;
import edu.northeastern.cs5610.models.User;
import edu.northeastern.cs5610.repositories.CourseRepository;
import edu.northeastern.cs5610.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*")
public class ModuleService {
	
  @Autowired
  	UserService userService;
  @Autowired
  	CourseService courseService;
  @Autowired
	CourseRepository courseRepository;
  @Autowired
	ModuleRepository moduleRepository;
  
    @GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModules(@PathVariable("courseId") int courseId) {
    	Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			return course.getModules();
		}
		return null;
//		Course course = courseService.findCourseById(courseId);
//		return course.getModules();
  	}
    
    @PostMapping("/api/course/{cid}/module")
    public Module createModule(@PathVariable("cid") int courseId, @RequestBody Module newModule) {
    	Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			newModule.setCourse(course);
		    List<Module> new_modules = course.getModules();
		    new_modules.add(newModule);
		    course.setModules(new_modules);
			return moduleRepository.save(newModule);
		}
		return null;
    }
    
    @GetMapping("/api/module/{mid}")
    public Module findModuleById(@PathVariable("mid") int moduleId) {
    	Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			return module;
		}
//      List<User> allUsers = userService.findAllUsers();
//      for (User u : allUsers) {
//          List<Course> allCourses = u.getCourses();
//          for (Course c : allCourses) {
//              List<Module> allModules = c.getModules();
//              for (Module m : allModules) 
//                  if (m.getId() == moduleId) 
//                      return m;
//          }
//      }
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
    	Course course  = findModuleById(moduleId).getCourse();
    	List<Module> modules = course.getModules();
    	Iterator<Module> iterator= modules.iterator();
    	while (iterator.hasNext()) {
    		   Module m = iterator.next();
    		  if(m.getId()==moduleId)
    		    iterator.remove();
    		}
    	moduleRepository.deleteById(moduleId);
//       List<User> allUsers = userService.findAllUsers();
//       for (User u : allUsers) {
//           List<Course> allCourses = u.getCourses();
//           for (Course c : allCourses) {
//              List<Module> allModules = c.getModules();
//              for (Module m : allModules)
//                  if (m.getId() == moduleId) {
//                      allModules.remove(m);
//                      c.setModules(allModules);
//                      return c.getModules();
//                  }
//          }
//      }
      return null;
  }	
}
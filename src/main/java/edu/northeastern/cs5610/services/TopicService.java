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
import edu.northeastern.cs5610.models.Topic;
import edu.northeastern.cs5610.models.User;


@RestController
@CrossOrigin(origins = "*")
public class TopicService {
	@Autowired
		UserService userService;
	@Autowired
		LessonService lessonService;
	
    @GetMapping("/api/lesson/{lid}/topic")
    public List<Topic> findAllTopics(@PathVariable("lid") int lessonId) {
      Lesson lesson = lessonService.findLessonById(lessonId);
      return lesson.getTopics();
    }
    
    @PostMapping("/api/lesson/{lid}/topic")
    public List<Topic> createTopic(@PathVariable("lid") int lid, @RequestBody Topic topic) {
      Lesson lesson = lessonService.findLessonById(lid);
      lesson.getTopics().add(topic);
      return lesson.getTopics();
    }
    
	  @PutMapping("/api/topic/{tid}")
	  public Topic updateTopic(@PathVariable("tid") int tid, @RequestBody Topic new_topic) {
	      Topic curr_topic = findTopicById(tid);
          curr_topic.setTitle(new_topic.getTitle());
          curr_topic.setWidgets(new_topic.getWidgets());
          return curr_topic;
	  }
    
	  @GetMapping("/api/topic/{tid}")
	  public Topic findTopicById(@PathVariable("tid") int tid) {
	      List<User> allUsers = userService.findAllUsers();
	      for (User u : allUsers) {
	          List<Course> allCourses = u.getCourses();
	          for (Course c : allCourses) {
	              List<Module> allModules = c.getModules();
	              for (Module m : allModules) {
	                  List<Lesson> allLessons = m.getLessons();
	                  for (Lesson l : allLessons) {
	                      List<Topic> allTopics = l.getTopics();
	                      for (Topic t : allTopics)
	                          if (t.getId() == tid)
	                              return t;
	                  }
	              }
	          }
	      }
	      return null;
	  }   
	  
	  @DeleteMapping("/api/topic/{tid}")
	  public List<Topic> deleteTopic(@PathVariable("tid") int tid) {
	        List<User> allUSers = userService.findAllUsers();
	        for (User u : allUSers) {
	            List<Course> allCourses = u.getCourses();
	            for (Course c : allCourses) {
	                List<Module> allModules = c.getModules();
	                for (Module m : allModules) {
	                    List<Lesson> allLessons = m.getLessons();
	                    for (Lesson l : allLessons) {
	                        List<Topic> allTopics = l.getTopics();
	                        for (Topic t : allTopics) {
	                            if (t.getId() == tid) {
	                                allTopics.remove(t);
	                                l.setTopics(allTopics);
	                                return l.getTopics();
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        return null;
	  }
}
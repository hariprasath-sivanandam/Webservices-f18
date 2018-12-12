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
import edu.northeastern.cs5610.models.Topic;
import edu.northeastern.cs5610.repositories.CourseRepository;
import edu.northeastern.cs5610.repositories.LessonRepository;
import edu.northeastern.cs5610.repositories.ModuleRepository;
import edu.northeastern.cs5610.repositories.TopicRepository;


@RestController
@CrossOrigin(origins = "*")
public class TopicService {
	@Autowired
		UserService userService;
	@Autowired
		LessonService lessonService;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	TopicRepository topicRepository;
	
    @GetMapping("/api/lesson/{lid}/topic")
    public Iterable<Topic> findAllTopics(@PathVariable("lid") int lessonId) {
    	Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getTopics();
		}
		return null;
//      Lesson lesson = lessonService.findLessonById(lessonId);
//      return lesson.getTopics();
    }
    
    @PostMapping("/api/lesson/{lid}/topic")
    public Topic createTopic(@PathVariable("lid") int lid, @RequestBody Topic newTopic) {
    	Optional<Lesson> data = lessonRepository.findById(lid);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newTopic.setLesson(lesson);
			List<Topic> new_topic = lesson.getTopics();
			new_topic.add(newTopic);
		    lesson.setTopics(new_topic);
			return topicRepository.save(newTopic);
		}
		return null;
//      Lesson lesson = lessonService.findLessonById(lid);
//      lesson.getTopics().add(topic);
//      return lesson.getTopics();
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
		  Optional<Topic> data = topicRepository.findById(tid);
			if(data.isPresent()) {
				Topic topic = data.get();
				return topic;
			}
//	      List<User> allUsers = userService.findAllUsers();
//	      for (User u : allUsers) {
//	          List<Course> allCourses = u.getCourses();
//	          for (Course c : allCourses) {
//	              List<Module> allModules = c.getModules();
//	              for (Module m : allModules) {
//	                  List<Lesson> allLessons = m.getLessons();
//	                  for (Lesson l : allLessons) {
//	                      List<Topic> allTopics = l.getTopics();
//	                      for (Topic t : allTopics)
//	                          if (t.getId() == tid)
//	                              return t;
//	                  }
//	              }
//	          }
//	      }
	      return null;
	  }   
	  
	  @DeleteMapping("/api/topic/{tid}")
	  public void deleteTopic(@PathVariable("tid") int tid) {
		    Lesson lesson  = findTopicById(tid).getLesson();
	    	List<Topic> topics = lesson.getTopics();
	    	Iterator<Topic> iterator= topics.iterator();
	    	while (iterator.hasNext()) {
	    		   Topic t = iterator.next();
	    		  if(t.getId()==tid)
	    		    iterator.remove();
	    	}
			topicRepository.deleteById(tid);
//	        List<User> allUSers = userService.findAllUsers();
//	        for (User u : allUSers) {
//	            List<Course> allCourses = u.getCourses();
//	            for (Course c : allCourses) {
//	                List<Module> allModules = c.getModules();
//	                for (Module m : allModules) {
//	                    List<Lesson> allLessons = m.getLessons();
//	                    for (Lesson l : allLessons) {
//	                        List<Topic> allTopics = l.getTopics();
//	                        for (Topic t : allTopics) {
//	                            if (t.getId() == tid) {
//	                                allTopics.remove(t);
//	                                l.setTopics(allTopics);
//	                                return l.getTopics();
//	                            }
//	                        }
//	                    }
//	                }
//	            }
//	        }
//	        return null;
	  }
}
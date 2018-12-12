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
import edu.northeastern.cs5610.models.Lesson;
import edu.northeastern.cs5610.models.Module;
import edu.northeastern.cs5610.models.Topic;
import edu.northeastern.cs5610.models.User;
import edu.northeastern.cs5610.models.Widget;
import edu.northeastern.cs5610.repositories.TopicRepository;
import edu.northeastern.cs5610.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins="*")
public class WidgetService {
	@Autowired
	UserService userService;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	TopicRepository topicRepository;
	@GetMapping("/api/topic/{topicId}/widget")
	public List<Widget> findWidgetsForTopic(@PathVariable("topicId") int topicId) {
		return (List<Widget>)topicRepository.findById(topicId).get().getWidgets();
	}
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}
	
	@GetMapping("/api/user/{userId}/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic/{topicId}/widget")
	public List<Widget> findWidgetForTopic(
			@PathVariable("userId") int userId,
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("lessonId") int lessonId,
			@PathVariable("topicId") int topicId) {
		User user = userService.findUserById(userId);
		for(Course course: user.getCourses()) {
			if(course.getId() == courseId) {
				for(Module module: course.getModules()) {
					if(module.getId() == moduleId) {
						for(Lesson lesson: module.getLessons()) {
							if(lesson.getId() == lessonId) {
								for(Topic topic: lesson.getTopics()) {
									if(topic.getId() == topicId) {
										return topic.getWidgets();
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	@PostMapping("/api/topic/{topic_id}/widget")
    public Widget createWidget(@PathVariable("topic_id") int topicId, @RequestBody Widget newWidget) {
	 	Optional<Topic> data = topicRepository.findById(topicId);		
		if(data.isPresent()) {
			Topic topic = data.get();
			newWidget.setTopic(topic);
			List<Widget> new_widgets = topic.getWidgets();
			new_widgets.add(newWidget);
		    topic.setWidgets(new_widgets);
		    topicRepository.save(topic);
			return widgetRepository.save(newWidget);

		}
		return null;
//	      Lesson lesson = lessonService.findLessonById(lid);
//	      lesson.getTopics().add(topic);
//	      return lesson.getTopics();
	 }
	 
	 @GetMapping("/api/widget/{wid}")
	 public  Widget findWidgetById(@PathVariable("wid") int widgetId) {
		 Optional<Widget> data = widgetRepository.findById(widgetId);
			if(data.isPresent())
				return data.get();
			return null;
	 }
	 
	 @PutMapping("/api/widget/{widget_id}")
	 public Widget updateWidget(@PathVariable("widget_id") int widget_id, @RequestBody Widget new_widget) {
		 Widget curr_widget = findWidgetById(widget_id);
		 curr_widget.setTitle(new_widget.getTitle());
		 curr_widget.setText(new_widget.getText());
		 curr_widget.setName(new_widget.getName());
		 curr_widget.setWidgetOrder(new_widget.getWidgetOrder());
		 curr_widget.setWidgetType(new_widget.getWidgetType());
         return curr_widget;
	 }
	 
		
	 @DeleteMapping("/api/widget/{wid}")
	 public void deleteWidget(@PathVariable("wid") int widgetId) {
		    Topic topic = findWidgetById(widgetId).getTopic();
	    	List<Widget> widgets = topic.getWidgets();
	    	Iterator<Widget> iterator= widgets.iterator();
	    	while (iterator.hasNext()) {
	    		  Widget w = iterator.next();
	    		  if(w.getId()==widgetId)
	    		    iterator.remove();
	    	}
	    	topicRepository.save(topic);
			widgetRepository.deleteById(widgetId);
	  }
	 
}
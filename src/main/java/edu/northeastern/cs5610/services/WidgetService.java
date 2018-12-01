package edu.northeastern.cs5610.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public List<Widget> findWidgetsForTopic(
			@PathVariable("topicId") int topicId) {
		return (List<Widget>)
				topicRepository.findById(topicId)
				.get().getWidgets();
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

}

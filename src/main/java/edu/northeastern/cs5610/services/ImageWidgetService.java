package edu.northeastern.cs5610.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.northeastern.cs5610.models.Course;
import edu.northeastern.cs5610.models.ImageWidget;
import edu.northeastern.cs5610.models.Lesson;
import edu.northeastern.cs5610.models.Module;
import edu.northeastern.cs5610.models.Topic;
import edu.northeastern.cs5610.models.User;
import edu.northeastern.cs5610.models.Widget;
import edu.northeastern.cs5610.repositories.ImageWidgetRepository;
import edu.northeastern.cs5610.repositories.TopicRepository;
import edu.northeastern.cs5610.repositories.WidgetRepository;

public class ImageWidgetService {
	@Autowired
	UserService userService;
	@Autowired
	ImageWidgetRepository imageWidgetRepository;
	@Autowired
	TopicRepository topicRepository;

	@PostMapping("/api/topic/{topic_id}/image/widget")
    public ImageWidget createImageWidget(@PathVariable("topic_id") int topicId, @RequestBody ImageWidget newWidget) {
	 	Optional<Topic> data = topicRepository.findById(topicId);		
		if(data.isPresent()) {
			Topic topic = data.get();
			newWidget.setTopic(topic);
			newWidget.setType("IMAGE");
			List<Widget> new_widgets = topic.getWidgets();
			new_widgets.add(newWidget);
		    topic.setWidgets(new_widgets);
		    topicRepository.save(topic);
			return imageWidgetRepository.save(newWidget);

		}
		return null;
	 }
	 
	 @GetMapping("/api/image/widget/{wid}")
	 public  ImageWidget findWidgetById(@PathVariable("wid") int widgetId) {
		 Optional<ImageWidget> data = imageWidgetRepository.findById(widgetId);
			if(data.isPresent())
				return (ImageWidget) data.get();
			return null;
	 }
	 
	 @PutMapping("/api/image/widget/{widget_id}")
	 public ImageWidget updateWidget(@PathVariable("widget_id") int widget_id, @RequestBody ImageWidget new_widget) {
		 ImageWidget curr_widget = findWidgetById(widget_id);
		 curr_widget.setSrc(new_widget.getSrc());
         return imageWidgetRepository.save(curr_widget);
	 }
	 
		
	 @DeleteMapping("/api/image/widget/{wid}")
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
	    	imageWidgetRepository.deleteById(widgetId);
	  }
}
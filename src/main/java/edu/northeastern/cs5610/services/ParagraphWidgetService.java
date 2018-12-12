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
import edu.northeastern.cs5610.models.ParagraphWidget;
import edu.northeastern.cs5610.models.Lesson;
import edu.northeastern.cs5610.models.Module;
import edu.northeastern.cs5610.models.Topic;
import edu.northeastern.cs5610.models.User;
import edu.northeastern.cs5610.models.Widget;
import edu.northeastern.cs5610.repositories.ParagraphWidgetRepository;
import edu.northeastern.cs5610.repositories.TopicRepository;
import edu.northeastern.cs5610.repositories.WidgetRepository;

public class ParagraphWidgetService {
	@Autowired
	UserService userService;
	@Autowired
	ParagraphWidgetRepository paragraphWidgetRepository;
	@Autowired
	TopicRepository topicRepository;

	@PostMapping("/api/topic/{topic_id}/paragraph/widget")
    public ParagraphWidget createParagraphWidget(@PathVariable("topic_id") int topicId, @RequestBody ParagraphWidget newWidget) {
	 	Optional<Topic> data = topicRepository.findById(topicId);		
		if(data.isPresent()) {
			Topic topic = data.get();
			newWidget.setTopic(topic);
			newWidget.setType("PARAGRAPH");
			List<Widget> new_widgets = topic.getWidgets();
			new_widgets.add(newWidget);
		    topic.setWidgets(new_widgets);
		    topicRepository.save(topic);
			return paragraphWidgetRepository.save(newWidget);

		}
		return null;
	 }
	 
	 @GetMapping("/api/paragraph/widget/{wid}")
	 public  ParagraphWidget findWidgetById(@PathVariable("wid") int widgetId) {
		 Optional<ParagraphWidget> data = paragraphWidgetRepository.findById(widgetId);
			if(data.isPresent())
				return (ParagraphWidget) data.get();
			return null;
	 }
	 
	 @PutMapping("/api/paragraph/widget/{widget_id}")
	 public ParagraphWidget updateWidget(@PathVariable("widget_id") int widget_id, @RequestBody ParagraphWidget new_widget) {
		 ParagraphWidget curr_widget = findWidgetById(widget_id);
		 //curr_widget.setSize(new_widget.getSize());
         return paragraphWidgetRepository.save(curr_widget);
	 }
	 
		
	 @DeleteMapping("/api/paragraph/widget/{wid}")
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
	    	paragraphWidgetRepository.deleteById(widgetId);
	  }
}
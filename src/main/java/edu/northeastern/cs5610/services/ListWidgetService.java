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
import edu.northeastern.cs5610.models.ListWidget;
import edu.northeastern.cs5610.models.Lesson;
import edu.northeastern.cs5610.models.Module;
import edu.northeastern.cs5610.models.Topic;
import edu.northeastern.cs5610.models.User;
import edu.northeastern.cs5610.models.Widget;
import edu.northeastern.cs5610.repositories.ListWidgetRepository;
import edu.northeastern.cs5610.repositories.TopicRepository;
import edu.northeastern.cs5610.repositories.WidgetRepository;

public class ListWidgetService {
	@Autowired
	UserService userService;
	@Autowired
	ListWidgetRepository listWidgetRepository;
	@Autowired
	TopicRepository topicRepository;

	@PostMapping("/api/topic/{topic_id}/list/widget")
    public ListWidget createListWidget(@PathVariable("topic_id") int topicId, @RequestBody ListWidget newWidget) {
	 	Optional<Topic> data = topicRepository.findById(topicId);		
		if(data.isPresent()) {
			Topic topic = data.get();
			newWidget.setTopic(topic);
			newWidget.setWidgetType("LIST");
			List<Widget> new_widgets = topic.getWidgets();
			new_widgets.add(newWidget);
		    topic.setWidgets(new_widgets);
		    topicRepository.save(topic);
			return listWidgetRepository.save(newWidget);

		}
		return null;
	 }
	 
	 @GetMapping("/api/list/widget/{wid}")
	 public  ListWidget findWidgetById(@PathVariable("wid") int widgetId) {
		 Optional<ListWidget> data = listWidgetRepository.findById(widgetId);
			if(data.isPresent())
				return (ListWidget) data.get();
			return null;
	 }
	 
	 @PutMapping("/api/list/widget/{widget_id}")
	 public ListWidget updateWidget(@PathVariable("widget_id") int widget_id, @RequestBody ListWidget new_widget) {
		 ListWidget curr_widget = findWidgetById(widget_id);
		 curr_widget.setTitle(new_widget.getTitle());
		 curr_widget.setText(new_widget.getText());
		 curr_widget.setName(new_widget.getName());
		 curr_widget.setWidgetOrder(new_widget.getWidgetOrder());
		 curr_widget.setItems(new_widget.getItems());
		 curr_widget.setListType(new_widget.getListType());
         return listWidgetRepository.save(curr_widget);
	 }
	 
		
	 @DeleteMapping("/api/list/widget/{wid}")
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
	    	listWidgetRepository.deleteById(widgetId);
	  }
}
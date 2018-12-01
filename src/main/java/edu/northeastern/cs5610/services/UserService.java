package edu.northeastern.cs5610.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import edu.northeastern.cs5610.repositories.UserRepository;

@RestController
@CrossOrigin(origins="*", allowCredentials = "true", allowedHeaders = "*")
public class UserService {
	static List<User> allUsers = new ArrayList<User>();
	static String[] usernames    = {"alice", "bob", "charlie"};
	static String[] courseTitles = {"cs5200", "cs5610", "cs5500"};
	static String[] moduleTitles = {"Module 1", "Module 2"};
	static String[] lessonTitles = {"lesson 1", "lesson 2"};
	static String[] topicTitles = {"topic 1", "topic 2"};
	static String[] widgetTitles = {"widget 1", "widget 2"};
	
	{
        List<Widget> widgets = new ArrayList<>();
        for (String widgetTitle : widgetTitles)
            widgets.add(new Widget(widgetTitle));

        List<Topic> topics = new ArrayList<>();
        for (String topicTitle : topicTitles) {
            Topic topic = new Topic(topicTitle);
            if (topicTitle.equals("topic 1"))
                topic.setWidgets(widgets);
            topics.add(topic);
        }

        List<Lesson> lessons = new ArrayList<>();
        for (String lessonTitle : lessonTitles) {
            Lesson lesson = new Lesson(lessonTitle);
            if (lessonTitle.equals("lesson 1"))
                lesson.setTopics(topics);
            lessons.add(lesson);
        }

        List<Module> modules = new ArrayList<>();
        for (String moduleTitle : moduleTitles) {
            Module module = new Module(moduleTitle);
            if (moduleTitle.equals("Module 1"))
                module.setLessons(lessons);
            modules.add(module);
        }

        List<Course> courses = new ArrayList<>();
        for (String courseTitle : courseTitles) {
            Course course = new Course(courseTitle);
            if (courseTitle.equals("cs5200"))
                course.setModules(modules);
            courses.add(course);
        }

        for (String username : usernames) {
            User user = new User(username, "password");
            if (username.equals("alice"))
                user.setCourses(courses);
            allUsers.add(user);
        }
    }
	
	@Autowired
	UserRepository userRepository;

    @GetMapping("/api/user")
    public List<User> findAllUsers() {
    	return (List<User>) userRepository.findAll();
    	// return allUsers;
    }
    
    @PutMapping("/api/user/{user_id}")
    public User updateUser(@PathVariable("user_id") int userId, @RequestBody User updatedUser, HttpSession session) {
        for (User u : allUsers)
            if (u.getId() == userId) {
            	u.setPhoneNumber(updatedUser.getPhoneNumber());
            	u.setFirstName(updatedUser.getFirstName());
            	u.setLastName(updatedUser.getLastName());
            	u.setEmail(updatedUser.getEmail());
            	u.setRole(updatedUser.getRole());
                u.setDob(updatedUser.getDob());
                session.setAttribute("currentUser", u);
                return u;
            }
        return null;
    }
    
    @GetMapping("/api/user/{id}")
    public User findUserById(@PathVariable("id") int userId) {
    	Optional<User> resultSet= userRepository.findById(userId);
    	if(resultSet.isPresent())
    		return resultSet.get();
    	return null;
//        for (User user : allUsers)
//            if (user.getId() == userId)
//                return user;
//        return null;
    }
    
    @PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
//		allUsers.add(user);
//		return allUsers;
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User curr_user,HttpSession session, HttpServletResponse response) {
		for (User u : findAllUsers()) 
          if (u.getUsername().equals(curr_user.getUsername())) {
        	  System.out.println("repeated");
        	  response.setStatus(HttpServletResponse.SC_CONFLICT);
        	  return null;
          }
		User saved_user = userRepository.save(curr_user);
		session.setAttribute("currentUser", saved_user);
		return saved_user;
//		session.setAttribute("currentUser", curr_user);
//		allUsers.add(curr_user);
//		return curr_user;
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		userRepository.deleteById(id);
		//users.deleteById(id);
//		List<User> new_users = new ArrayList<User>();
//		for (User u : allUsers) {
//            if (u.getId() == id)
//            	continue;
//            else
//            	new_users.add(u);
//        }
//		allUsers = new_users;   
	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {	
		return (User)session.getAttribute("currentUser");
	}

    @PostMapping("/api/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @PostMapping("/api/login")
	public User login(@RequestBody User credentials,HttpSession session, HttpServletResponse response) {
	 for (User u : allUsers) 
	  if( u.getUsername().equals(credentials.getUsername())
	   && u.getPassword().equals(credentials.getPassword())) {
	    session.setAttribute("currentUser", u);
	    return u;
	  }
	 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	 return null;
	}
}
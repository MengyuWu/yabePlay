package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class Admin extends Controller{

	 @Before
	    static void setConnectedUser() {
	        if(Security.isConnected()) {
	            User user = User.find("byEmail", Security.connected()).first();
	            renderArgs.put("user", user.fullname);
	        }
	    }
	 
	    public static void index() {
	    	String user=Security.connected();
	    	List<Post> posts=Post.find("author.email", user).fetch();
	        render(posts);
	    }
	    
	    public static void form(){
	    	render();
	    }
	    
	    public static void save(){
	    	//not implemented yet
	    }
}
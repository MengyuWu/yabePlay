package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Post extends Model{
    public String title;
    public Date postedAt;
    
    @Lob
    public String content;
    
    @ManyToOne 
    //Many:self post objects, One: User. One user could have multiple post;
    public User author;
    
    // mappedBy attribute to tell JPA that the Comment class’ post field is the owning side that maintains the relationship. 
   // When you define a bi-directional relation with JPA it is important to specify which side will maintain the relationship. 
    //In this case, since Comment instances belong to a Post, we define the relationship on the Comment.post inverse relation.
    //We have set the cascade property to tell JPA that we want Post deletion be cascaded to comments.
    //This way, if you delete a post, all related comments will be deleted as well.
    @OneToMany(mappedBy="post", cascade=CascadeType.ALL)
    public List<Comment> comments;
    
    
    public Post(User author, String title, String content){
    	this.comments=new ArrayList<Comment>();
    	this.author=author;
    	this.title=title;
    	this.content=content;
    	this.postedAt=new Date();
    }
    
   
    public Post addComment(String author, String content){
    	Comment newComment=new Comment(this, author,content).save();
    	this.comments.add(newComment);
    	this.save();
    	return this;
    }
    
    public Post previous() {
        return Post.find("postedAt < ? order by postedAt desc", postedAt).first();
    }
     
    public Post next() {
        return Post.find("postedAt > ? order by postedAt asc", postedAt).first();
    }
    
}

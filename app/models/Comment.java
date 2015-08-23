package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import play.data.validation.*;
@Entity
public class Comment extends Model { // supercalss Model will automatically create id
	@Required
	public String author;
	@Required
	public Date postedAt;
	
	@Lob  // use larger space to store the content
	 @Required
	    @MaxSize(10000)
	public String content;
	
	@ManyToOne //many comments to one post
	 @Required
	public Post post; 
	
	public Comment(Post post, String author, String content){
		this.post=post;
		this.author=author;
		this.content=content;
		this.postedAt=new Date();
	}
	
	public String toString() {
	    return content;
	}
}

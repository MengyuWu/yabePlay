package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Comment extends Model { // supercalss Model will automatically create id

	public String author;
	public Date postedAt;
	
	@Lob  // use larger space to store the content
	public String content;
	
	@ManyToOne //many comments to one post
	public Post post; 
	
	public Comment(Post post, String author, String content){
		this.post=post;
		this.author=author;
		this.content=content;
		this.postedAt=new Date();
	}
}

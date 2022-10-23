package cubes.main.entity;







import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

@Table
@Entity
public class Comment {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String description;
	@Column
	private boolean isEnabled;
	@Column
	private boolean isSeen;
	@Column
	
	private String date;
	@Column
	private String image;

	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "blog_id")
	private Blog blog;
	@Transient
    private long count;
	
	public Comment() {
		
	}

	

	






	public Comment(String name, String email, String description, boolean isEnabled, boolean isSeen, String date,
			String image, Blog blog, long count) {
		super();
		this.name = name;
		this.email = email;
		this.description = description;
		this.isEnabled = isEnabled;
		this.isSeen = isSeen;
		this.date = date;
		this.image = image;
		this.blog = blog;
		this.count = count;
	}










	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
	
	public boolean getIsSeen() {
		return isSeen;
	}

	public void setIsSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}
	
	

	
	
	

	public String getDate() {
		return date;
	}










	public void setDate(String date) {
		this.date = date;
	}










	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}



	public Blog getBlog() {
		return blog;
	}





	public void setBlog(Blog blog) {
		this.blog = blog;
	}





	public long getCount() {
		return count;
	}





	public void setCount(long count) {
		this.count = count;
	}





	@Override
	public String toString() {
		
		return "("+ id +") - " + description;
	}
	
}

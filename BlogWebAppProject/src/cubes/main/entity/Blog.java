package cubes.main.entity;




import java.net.URLEncoder;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;



@Table
@Entity
public class Blog {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String image;
	@Column
	
	private String date;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "categoryId")
	private Category category;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="authorId")
	private Author author;
	@Column
	private String image2;
	@Column
	@Size(min=20,max=255, message="min 20 max 255 characters")
	private String title;
	@Column
	private String createdAt;
	@Column
	private String image3;
	@Column
	private String thumbnail;
	@Column
	@Size (min=50, max=500, message="min 50, max 500 characters")
	private String description;
	@Column
	private String body;
	@Column
	private boolean isImportant;
	@Column
	private int seen;

	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="blog_tag",joinColumns = @JoinColumn(name = "blogId"), inverseJoinColumns = @JoinColumn (name = "tagId"))
	private List<Tag> tags;
	



	public Blog() {
		
	}


	public Blog(String image, String date, Category category, Author author, String image2, String title, String createdAt,String image3, 
			    String thumbnail,String description, String body,boolean isImportant, int seen, List<Tag> tags) {
		super();
		this.image = image;
		this.date = date;
		this.category = category;
		this.author = author;
		this.image2 = image2;
		this.title = title;
		this.createdAt = createdAt;
		this.image3 = image3;
		this.thumbnail = thumbnail;
		this.description = description;
		this.body = body;
		this.isImportant = isImportant;
		this.seen = seen;
		this.tags = tags;
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Author getAuthor() {
		return author;
	}



	public void setAuthor(Author author) {
		this.author = author;
	}



	public String getImage2() {
		return image2;
	}



	public void setImage2(String image2) {
		this.image2 = image2;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle() {
		this.title =title;
	}



	public String getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public boolean getIsImportant() {
		return isImportant;
	}



	public void setIsImportant(boolean isImportant) {
		this.isImportant = isImportant;
	}



	public int getSeen() {
		return seen;
	}



	public void setSeen(int seen) {
		this.seen = seen;
	}



	public List<Tag> getTags() {
		return tags;
	}



	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}



	public String getImage3() {
		return image3;
	}



	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getThumbnail() {
		return thumbnail;
	}



	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	
	//SEO frinedly

	//public String getCeo() {
		//return URLEncoder.encode(title);
	//}
	


	


	@Override
	public String toString() {
		
		return "("+ id +") - " + title;
	}

}

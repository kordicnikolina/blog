package cubes.main.entity;

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
import javax.persistence.Table;

@Entity
@Table
public class Tag {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	


	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="blog_tag",joinColumns = @JoinColumn(name = "tagId"), 
    inverseJoinColumns = @JoinColumn (name = "blogId"))
	private List<Blog> blogList;
	
	
	public Tag() {
	}


	


	





	public Tag(String name, List<Blog> blogList) {
		super();
		this.name = name;
		this.blogList = blogList;
	}











	public Tag(String name) {
		super();
		this.name = name;
	}


	public Tag(int id) {
		super();
		this.id = id;
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
	








	public List<Blog> getBlogList() {
		return blogList;
	}











	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}











	@Override
	public String toString() {
		
		return "("+ id +") - " + name;
	}
}

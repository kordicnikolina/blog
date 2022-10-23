package cubes.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Item {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String image;
	
	@Column
	private String title;
	@Column
	private String link;
	@Column
	private boolean isOnSlidebar;
	@Column
	private String linkUrl;
	
	public Item() {
	
	}

	public Item(String image, String title, String link, boolean isOnSlidebar, String linkUrl) {
		super();
		this.image = image;
		this.title = title;
		this.link = link;
		this.isOnSlidebar = isOnSlidebar;
		this.linkUrl = linkUrl;
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
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
	
	  public boolean getIsOnSlidebar() {
		return isOnSlidebar;
	}

	public void setIsOnSlidebar(boolean isOnSlidebar) {
		this.isOnSlidebar = isOnSlidebar;
	}

	
	
	
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Override
	    public String toString() {
	    	
	    	return "("+ id +") - " + title;
	    }

}

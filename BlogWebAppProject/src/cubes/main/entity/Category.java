package cubes.main.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table
@Entity
public class Category {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private String description;

	@Column
	private boolean isUncategorized;
	@Transient
	private long count;
	
	public Category() {
	
	}


	


	




	public Category(String name, String description, boolean isUncategorized, long count) {
		super();
		this.name = name;
		this.description = description;
		this.isUncategorized = isUncategorized;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	


	public long getCount() {
		return count;
	}





	public void setCount(long count) {
		this.count = count;
	}





	public boolean getIsUncategorized() {
		return isUncategorized;
	}










	public void setIsUncategorized(boolean isUncategorized) {
		this.isUncategorized = isUncategorized;
	}










	@Override
	public String toString() {
		
		return "("+ id +") - " + name;
	}

	
	
}

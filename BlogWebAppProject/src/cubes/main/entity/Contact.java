package cubes.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table

public class Contact {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	@NotNull
	@NotEmpty(message="Required")
	private String name;
	@Column
	@NotNull
	@NotEmpty(message="Required")
	private String email;
	@Column
    @Size(min=50, max=500, message="min 50, max 1000 characters")
	private String message;
	@Column
    private boolean isRead;
	
	
	public Contact() {
		
	}


	public Contact(String name, String email, String message, boolean isRead) {
		super();
		this.name = name;
		this.email = email;
		this.message = message;
		this.isRead = isRead;
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


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public boolean getIsRead() {
		return isRead;
	}


	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}
	
	@Override
	public String toString() {
		
		return "("+ id +") - " + name;
	}
	
	
}

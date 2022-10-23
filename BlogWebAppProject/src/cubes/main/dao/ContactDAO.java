package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Contact;

public interface ContactDAO {

	public void saveContact(Contact contact);
	
	public List<Contact> getAllContacts();
	
	public long getUnreadContactsCount();
	
	public Contact getContact(int id);
	
	
	
}

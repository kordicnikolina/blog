package cubes.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import cubes.main.entity.Contact;

@Repository
public class ContactDAOImplementation implements ContactDAO {
	

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public void saveContact(Contact contact) {
		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(contact);
	}

	@Transactional
	@Override
	public List<Contact> getAllContacts() {
		Session session = sessionFactory.getCurrentSession();
		Query<Contact> query= session.createQuery("from Contact");
		
		return query.getResultList();
	}

	@Transactional
	@Override
	public long getUnreadContactsCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Contact c where c.isRead = 0");
		
		return (long) query.uniqueResult();
	}

	@Transactional
	@Override
	public Contact getContact(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Contact.class, id);
	}

	
		
		
	

}

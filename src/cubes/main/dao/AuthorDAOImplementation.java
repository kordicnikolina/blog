package cubes.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Author;
import cubes.main.entity.Category;
@Repository
public class AuthorDAOImplementation implements AuthorDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<Author> getAuthorList() {
		Session session = sessionFactory.getCurrentSession();
		Query<Author>query = session.createQuery("from Author", Author.class);
		List<Author>authorList = query.getResultList();
		return authorList;
	}

	@Transactional
	@Override
	public void saveAuthor(Author author) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(author);
		
	}

	@Transactional
	@Override
	public Author getAuthor(int id) {
		Session session = sessionFactory.getCurrentSession();
		Author author= session.get(Author.class, id);
		return author;
	}

	@Transactional
	@Override
	public void deleteAuthor(int id) {
		Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("delete from Author where id=:id");
    	query.setParameter("id", id);
    	query.executeUpdate();
		
	}

}

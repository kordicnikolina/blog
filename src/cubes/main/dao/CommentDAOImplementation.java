package cubes.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Blog;
import cubes.main.entity.Comment;



@Repository
public class CommentDAOImplementation implements CommentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	
	public void saveComment(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(comment);
		
	}

	@Transactional
	@Override
	public List<Comment> getAllComments() {
		Session session = sessionFactory.getCurrentSession();
		Query<Comment> query= session.createQuery("from Comment");
		return query.getResultList();
	}

	@Transactional
	@Override
	public long getNewCommentsCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Comment c where c.isSeen=0");
		return (long) query.uniqueResult();
	}

	@Transactional
	@Override
	public Comment getComment(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Comment.class, id);
	}

	@Transactional
	@Override
	public List<Comment> getEnabledComments() {
		Session session = sessionFactory.getCurrentSession();
		Query<Comment> query = session.createQuery("select c from Comment c where c.isEnabled=0");
		List<Comment> list = query.getResultList();
		return list;
	}

	
	@Transactional
	@Override
	public List<Comment> getBlogComments(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Comment> query = session.createQuery("from Comment c where c.blog.id=:id and c.isEnabled=0");
		
	    query.setParameter("id", id);
		return query.getResultList();
	}
	
	@Transactional
	@Override
	
	public long getCommentsCountByBlog(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Comment c where c.blog.id=:id");
		query.setParameter("id", id);
		return (long) query.uniqueResult();
	}
}

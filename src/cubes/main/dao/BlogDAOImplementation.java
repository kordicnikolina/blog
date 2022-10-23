package cubes.main.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import cubes.main.entity.Blog;
import cubes.main.entity.Category;
import cubes.main.entity.Tag;


@Repository
public class BlogDAOImplementation implements BlogDAO {
	
	
	@Autowired
	private SessionFactory sessionFactory;

	
	
	@Transactional
	@Override
	public List<Blog> getBlogList() {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog>query = session.createQuery("from Blog", Blog.class);
		List<Blog> blogList = query.getResultList();
		return blogList;
		
	}
	

	
	
	
	@Transactional
	@Override
	public List<Blog> getBlogsForIndexPage() {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog>query = session.createQuery("select b from Blog b where b.isImportant = 1 order by b.date desc");
		query.setMaxResults(3);
		List<Blog> blogList = query.getResultList();
		return blogList;
	}

	@Transactional
	@Override
	public List<Blog> getLatestBlogs() {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog>query = session.createQuery("select b from Blog b order by b.date desc");
		query.setMaxResults(3);
		List<Blog> blogList = query.getResultList();
		
		return blogList;
	}
	
	
	
	
	@Transactional
	@Override
	public void saveBlog(Blog blog) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(blog);
		
	}

	@Transactional
	@Override
	public Blog getBlog(int id) {
		Session session = sessionFactory.getCurrentSession();
		Blog blog = session.get(Blog.class, id);
		return blog;
	}
	
	

	@Transactional
	@Override
	public void deleteBlog(int id) {
		Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("delete from Blog where id=:id");
    	query.setParameter("id", id);
    	query.executeUpdate();
	}

	@Transactional
	@Override
	public List<Blog> getBlogListByCategory(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog>query = session.createQuery("from Blog blog where blog.category.id=:id");
		query.setParameter("id", id);
		
		return query.getResultList();
	}

	@Transactional
	@Override
	public Blog getBlogWithTag(int id) {
		Session session = sessionFactory.getCurrentSession();
		Blog blog = session.get(Blog.class, id);
		Hibernate.initialize(blog.getTags());
		return blog;
	}
	
	@Transactional
	@Override
	public List<Blog> getBlogListByTag(int id) {
		Session session = sessionFactory.getCurrentSession();
		Tag tag= session.get(Tag.class, id);
		Hibernate.initialize(tag.getBlogList());
		return tag.getBlogList();
		}

	@Transactional
	@Override
	public List<Blog> getBlogListByAuthor(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog>query = session.createQuery("from Blog blog where blog.author.id=:id");
		query.setParameter("id", id);
		
		return query.getResultList();
	}
	
	//pokusaj paginacije-metoda pronadjena na internetu
	
		//@Transactional
		//@Override
	   // public List<Blog> getBlogByPage() {
			
	   //Session session = sessionFactory.getCurrentSession();
	 //  Query<Blog> query = session.createQuery("from Blog" , Blog.class);
	
	    //query.setFirstResult(0);
	    //query.setMaxResults(4);
	   // List<Blog> blogByPage = query.getResultList();
	    
	   // return blogByPage;
		//}

		
		
		@Transactional
		@Override
		public List<Blog> getBlogList(int orderBy) {
			
			Session session = sessionFactory.getCurrentSession();
			
			Query<Blog>query;
			
			if(orderBy==1) {
				query=session.createQuery("from Blog b order by b.title", Blog.class);
			}
			
			else if(orderBy==2) {
				query=session.createQuery("from Blog b order by b.description", Blog.class);
			}
			
			else {
				query=session.createQuery("from Blog b order by b.date desc", Blog.class);
			}
		
			List<Blog> blogList = query.getResultList();
			return blogList;
		}

// pokusaj search metode
		
		//@Transactional
		//@Override
		//public List<Blog> blogSearch(String text){
			//Session session = sessionFactory.getCurrentSession();
			//Query<Blog> query = session.createQuery("from Blog b where b.title like '%sa%'");
		   // List<Blog> blogList = query.getResultList();
			//return blogList;
		//}


		

	
		
}

package cubes.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import cubes.main.entity.Item;

@Repository
public class ItemDAOImplementation implements ItemDAO {
	

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<Item> getItemList() {
		Session session = sessionFactory.getCurrentSession();
		Query<Item> query = session.createQuery("from Item", Item.class);
		List<Item> itemList = query.getResultList();
		return itemList;
	}

	@Transactional
	@Override
	public void saveItem(Item item) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(item);
	}

	@Transactional
	@Override
	public Item getItem(int id) {
		Session session = sessionFactory.getCurrentSession();
		Item item = session.get(Item.class, id);
		return item;
	}

	@Transactional
	@Override
	public void deleteItem(int id) {
		Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("delete from Item where id=:id");
    	query.setParameter("id", id);
    	query.executeUpdate();
		
	}

	@Transactional
	@Override
	public List<Item> getItemsOnSlideBar() {
	Session session = sessionFactory.getCurrentSession();
	Query<Item> query = session.createQuery("select i from Item i where i.isOnSlidebar = 1");
	List<Item> list = query.getResultList();
	return list;
	}

}

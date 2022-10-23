package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Blog;
import cubes.main.entity.Category;

public interface BlogDAO {
	
	public List<Blog> getBlogList();
	
	public List<Blog> getBlogsForIndexPage();
	
	public List<Blog> getLatestBlogs();
	

	
	public void saveBlog(Blog blog);
	
    public Blog getBlog(int id);
   
	public void deleteBlog(int id);
	
	public Blog getBlogWithTag(int id);
	
	public List<Blog> getBlogListByTag(int id);
	
	public List<Blog> getBlogListByCategory(int id);

	public List<Blog> getBlogListByAuthor(int id);
	
	//public List<Blog> getBlogByPage();
	
	public List<Blog> getBlogList(int orderBy);
	

	
	//public List<Blog> blogSearch(String text);
	
	
}

















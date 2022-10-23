package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Comment;

public interface CommentDAO {

	public void saveComment(Comment comment);
	
	public List<Comment> getAllComments();
	
	public long getNewCommentsCount();
	
	public Comment getComment(int id);
	
	public List<Comment> getEnabledComments();
	
	public List<Comment> getBlogComments(int id);
	
	public long getCommentsCountByBlog(int id);
	
}

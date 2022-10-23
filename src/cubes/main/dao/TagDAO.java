package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Tag;

public interface TagDAO {
	
	List<Tag> getTagList();
	
	public void saveTag(Tag tag);
	
	public Tag getTag(int id);
	
	public void deleteTag(int id);
	
	public List<Tag> getTagsById(List<Integer> ids);
	
	public List<Tag> getTagListWithBlogs();
	
	public List <Tag> getTagsByBlog(int id);

}

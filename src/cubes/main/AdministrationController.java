package cubes.main;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cubes.main.dao.AuthorDAO;
import cubes.main.dao.BlogDAO;

import cubes.main.dao.CategoryDAO;
import cubes.main.dao.CommentDAO;
import cubes.main.dao.ContactDAO;
import cubes.main.dao.ItemDAO;
import cubes.main.dao.TagDAO;
import cubes.main.dao.UserDAO;
import cubes.main.entity.Author;
import cubes.main.entity.Blog;

import cubes.main.entity.Category;
import cubes.main.entity.ChangePassword;
import cubes.main.entity.Comment;
import cubes.main.entity.Contact;
import cubes.main.entity.Item;
import cubes.main.entity.Tag;
import cubes.main.entity.User;

@Controller
@RequestMapping("/administration")
public class AdministrationController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private TagDAO tagDAO;
	
	@Autowired
	private BlogDAO blogDAO;

	@Autowired
	private AuthorDAO authorDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	//CATEGORY!!!!!!!!!!!!!!!!!!
	
	
	@RequestMapping("/category-list")
	public String getCategoryList(Model model) {
		
		List<Category>list = categoryDAO.getCategoryList();
		
		model.addAttribute("categoryList", list);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		
		return "category-list";
	}
	
	@RequestMapping("/category-form")
	public String getCategoryForm(Model model) {
		
		Category category = new Category();
		model.addAttribute("category", category);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		
		return "category-form";
	}
	
	@RequestMapping("/category-save")
	public String saveCategory( @ModelAttribute Category category) {
	
		categoryDAO.saveCategory(category);
		return "redirect:/administration/category-list";
		
	}
	
	@RequestMapping("/category-update-form")
	public String getCategoryUpdateForm(@RequestParam int id, Model model) {
		Category category = categoryDAO.getCategory(id);
		model.addAttribute("category", category);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		
		return"category-form";
	}
	
	@RequestMapping("/category-delete")
	public String deleteCategory(@RequestParam int id) {
		categoryDAO.deleteCategory(id);
		return "redirect:/administration/category-list";
	}
		
	
	//TAGS!!!!!!!!!!!!!!!!
	
	@RequestMapping("/tag-list")
	public String getTagList(Model model) {
		
		List<Tag> tagList = tagDAO.getTagList();
		
		model.addAttribute("tagList", tagList);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
	
		return "tag-list";
	}
	
	@RequestMapping("/tag-form")
	public String getTagForm(Model model) { 
		
		Tag tag = new Tag();
		model.addAttribute("tag", tag);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		tagDAO.saveTag(tag);
		return "tag-form";
		
	}
	
	@RequestMapping("/tag-form-update")
	public String getTagUpdateForm( @RequestParam int id, Model model) {
		Tag tag = tagDAO.getTag(id);
		model.addAttribute("tag", tag);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		
		return "tag-form";
	
	}
	
	@RequestMapping("/tag-save")
	public String saveTag ( @ModelAttribute Tag tag) {
		
		tagDAO.saveTag(tag);
		return "redirect:/administration/tag-list";
		
	}
	@RequestMapping("/tag-delete")
	public String deleteTag(@RequestParam int id) {
		tagDAO.deleteTag(id);
		return "redirect:/administration/tag-list";
	} 
	
	//BLOG!!!!!!!!!!!!!!!!!!!!!
	
	@RequestMapping("/blog-list")
	public String getBlogList(Model model) {
		List<Blog> blogList = blogDAO.getBlogList();
		
		model.addAttribute("blogList", blogList);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		return "blog-list";
	}
	
	@RequestMapping("/blog-form")
	public String getBlogForm(Model model) {
		Blog blog = new Blog();
		List<Category>categoryList = categoryDAO.getCategoryList();
		List<Author> authorList = authorDAO.getAuthorList();
		List<Tag> tagList = tagDAO.getTagList();
		model.addAttribute("blog", blog);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("authorList",authorList);
		model.addAttribute("tagList", tagList);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		return "blog-form";
	}
	
	@RequestMapping("/blog-form-update")
	public String getBlogFormUpdate(@RequestParam int id, Model model) {
		Blog blog = blogDAO.getBlogWithTag(id);
		List<Category>categoryList = categoryDAO.getCategoryList();
		List<Author> authorList = authorDAO.getAuthorList();
		List<Tag> tagList = tagDAO.getTagList();
		model.addAttribute("blog", blog);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("tagList", tagList);
		model.addAttribute("authorList", authorList);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		return "blog-form";
	}
	@RequestMapping("/blog-save")
	public String saveBlog(@Valid @ModelAttribute Blog blog, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			List<Category>categoryList=categoryDAO.getCategoryList();
			List<Tag>tagList = tagDAO.getTagList();
			List<Author> authorList = authorDAO.getAuthorList();
			
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("authorList", authorList);
			model.addAttribute("tagList", tagList);
			
			return "blog-form";
		}
		
	Category category = categoryDAO.getCategory(blog.getCategory().getId());
	
	Author author = authorDAO.getAuthor(blog.getAuthor().getId());
		
		List<Integer>ids = new ArrayList<Integer>();
		
		for(Tag tag:blog.getTags()) {
			ids.add(Integer.parseInt(tag.getName()));
			}
		
		List<Tag>tags = tagDAO.getTagsById(ids);
		
		
		blog.setAuthor(author);
		blog.setCategory(category);
		blog.setTags(tags);
		
		blogDAO.saveBlog(blog);
		
		return "redirect:/administration/blog-list";
	}
	
	@RequestMapping("/blog-delete")
	public String deleteBlog(@RequestParam int id) {
		blogDAO.deleteBlog(id);
		return "redirect:/administration/blog-list";
	} 
	
	
	
	
	//AUTHOR!!!!
	
	@RequestMapping("/author-list")
	public String getAuthorList(Model model) {
		
		List<Author>authorList = authorDAO.getAuthorList();
		
		model.addAttribute("authorList", authorList);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		
		return "author-list";

    }
	@RequestMapping("/author-form")
	public String getAuthorForm(Model model) {
		
		Author author = new Author();
		model.addAttribute("author", author);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		
		return "author-form";
	}
	
	@RequestMapping("/author-save")
	public String saveAuthor(@ModelAttribute Author author) {
		
		authorDAO.saveAuthor(author);
		return "redirect:/administration/author-list";
		
	}
	@RequestMapping("/author-form-update")
	public String getAuthorUpdateForm(@RequestParam int id, Model model) {
		Author author = authorDAO.getAuthor(id);
		model.addAttribute("author", author);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		
		return"author-form";
	}
	
	@RequestMapping("/author-delete")
	public String deleteAuthor(@RequestParam int id) {
		authorDAO.deleteAuthor(id);
		return "redirect:/administration/author-list";
	}
	
	//SLIDER ITEM!!!!!!!!!!!!!!!!
	
	
	@RequestMapping("/item-list")
	public String getSliderItemList(Model model) {
		
		List<Item>itemList = itemDAO.getItemList();
		
		model.addAttribute("itemList", itemList);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		
		return "item-list";

    }
	@RequestMapping("/item-form")
	public String getItemForm(Model model) {
		
		Item item = new Item();
		model.addAttribute("item", item);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		
		return "item-form";
	}
	
	@RequestMapping("/item-save")
	public String saveItem(@ModelAttribute Item item) {
		
		itemDAO.saveItem(item);
		return "redirect:/administration/item-list";
		
	}
	@RequestMapping("/item-form-update")
	public String getItemUpdateForm(@RequestParam int id, Model model) {
		Item item = itemDAO.getItem(id);
		model.addAttribute("item", item);
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		
		return"item-form";
	}
	
	@RequestMapping("/item-delete")
	public String deleteItem(@RequestParam int id) {
		itemDAO.deleteItem(id);
		return "redirect:/administration/item-list";
	}
	
	
	//CONTACT!!!!!!!!!!!
	
	@RequestMapping("/contact-list")
	public String getContactList(Model model) {
		model.addAttribute("contactList", contactDAO.getAllContacts());
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		return "contact-list";
	}
	
	@RequestMapping("/contact-read")
	public String getMarkContactRead(@RequestParam int id) {
		Contact contact = contactDAO.getContact(id);
		contact.setIsRead(true);
		contactDAO.saveContact(contact);
		return "redirect: contact-list";
	}
	
	//COMMENT!!!!
	
	
	@RequestMapping("/comment-list")
	public String getCommentList(Model model) {
		model.addAttribute("commentList", commentDAO.getAllComments());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		return "comment-list";
	}
	
	
	
	@RequestMapping("/comment-seen")
	public String getMarkCommentRead(@RequestParam int id) {
		Comment comment = commentDAO.getComment(id);
		comment.setIsSeen(true);
		
		commentDAO.saveComment(comment);
		return "redirect: comment-list";
	}
	
	@RequestMapping("/comment-enabled")
	public String getEnabledComments(@RequestParam int id) {
		Comment comment = commentDAO.getComment(id);
		comment.setIsEnabled(!comment.getIsEnabled());
		
		commentDAO.saveComment(comment);
		return "redirect: comment-list";
	}
	
	
	
	@RequestMapping("/comment-save")
	public String saveComment(@ModelAttribute Comment comment) {
		
		Blog blog = blogDAO.getBlog(comment.getBlog().getId());
		comment.setBlog(blog);
		
		commentDAO.saveComment(comment);
		
		return "redirect: comment-list";
	}
	
	
	//USERS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	
	@RequestMapping("/user-list")
	public String getUserList(Model model) {
		
		model.addAttribute("userList", userDAO.getUserList());
		model.addAttribute("contactCount", contactDAO.getUnreadContactsCount());
		model.addAttribute("commentCount", commentDAO.getNewCommentsCount());
		 model.addAttribute("user", userDAO.getUserByUsername("pera"));
		return "user-list";
	}
	

	@RequestMapping("/user-enable")
	public String enableUser(@RequestParam String username) {
		userDAO.enableUser(username);
		return "redirect:/administration/user-list";
	}
	
	@RequestMapping("/user-form")
	public String getUserForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", userDAO.getRoles());
		return "user-form";
	}
	
	@RequestMapping("/user-save")
	public String getUserForm(@ModelAttribute User user) {
		
		System.out.println("pass - " + user.getPassword());
		
		String passwordEncode = new BCryptPasswordEncoder().encode(user.getPassword());
		
		System.out.println("pass - " + passwordEncode);
		
		user.setEnabled(true);
		
		user.setPassword("{bcrypt}" + passwordEncode);
		
		userDAO.saveUser(user);
		
		return "redirect:/administration/user-list";
   }
	
	
	
	@RequestMapping("/user-delete")
	public String getUserForm(@RequestParam String username) {
		
		userDAO.deleteUser(username);
		
		return "redirect:/administration/user-list";
	}
	
	
	@RequestMapping("/user-edit-profile")
    public String getUserEditProfile(Model model, Principal principal) {
		User user = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("user", user);
		
		return "user-edit-profile";
		
	}
	
	@RequestMapping("/user-edit")
	public String getUserEditProfile(@ModelAttribute User user) {
		userDAO.saveUser(user);
		return "redirect:/administration/user-list";
	}
	
	@RequestMapping("/user-change-password")
	public String getUserChangePassword(Model model) {
		//User user = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("changePassword", new ChangePassword());
		return "user-change-password";
	}
	
	@RequestMapping("/user-change-password-action")
	public String getUserChangePasswordAction(@ModelAttribute ChangePassword changePassword, Principal principal, Model model) {
		if(changePassword.getNewPassword().equalsIgnoreCase(changePassword.getConfirmPassword())) {
			
			  User user = userDAO.getUserByUsername(principal.getName());
			  
			  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			  
			    if(encoder.matches(changePassword.getOldPassword(), user.getPassword().replace("{bcrypt}", ""))) {
			    	
			    	user.setPassword("{bcrypt}" + encoder.encode(changePassword.getNewPassword()));
			    	
			    	userDAO.saveUser(user);
			    	
			    }else {
			    	//nije unet dobar stari password
				   return "user-change-password";
			     }
			    
			}else {
				//ne poklapaju se new password i confirm password 
				return "user-change-password";
			}
			
			
			return "redirect:/administration/user-list";
		
		
		
	}
		
		
}	




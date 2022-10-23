package cubes.main;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cubes.main.dao.AuthorDAO;
import cubes.main.dao.BlogDAO;

import cubes.main.dao.CategoryDAO;
import cubes.main.dao.CommentDAO;
import cubes.main.dao.ContactDAO;
import cubes.main.dao.ItemDAO;
import cubes.main.dao.TagDAO;
import cubes.main.entity.Blog;
import cubes.main.entity.Comment;
import cubes.main.entity.Contact;

@Controller
@RequestMapping("/")

public class FrontController {
	
	@Autowired
	private ItemDAO itemDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private BlogDAO blogDAO;
	
	@Autowired
	private TagDAO tagDAO;
	@Autowired
	private AuthorDAO authorDAO;
	@Autowired
   private ContactDAO contactDAO;
	@Autowired
	private CommentDAO commentDAO;
	
	//HOME PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@RequestMapping({"/", "/index-page"})
	public String getIndexPage( Model model) {
		
		model.addAttribute("items", itemDAO.getItemsOnSlideBar());
		model.addAttribute("blogList", blogDAO.getBlogsForIndexPage());
	    model.addAttribute("latestBlogs", blogDAO.getLatestBlogs());
		model.addAttribute("categoryList", categoryDAO.getCategoryListFooter());
	    return "front/index-page";
	    
	}

	// BLOG PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@RequestMapping("/blog-page")
	public String getBlogPage( Model model){
		model.addAttribute("blogList", blogDAO.getBlogList());	    
        model.addAttribute("categoryList", categoryDAO.getCategoriesForBlogFilter());
	    model.addAttribute("latestBlogs", blogDAO.getLatestBlogs());
	    model.addAttribute("tagList", tagDAO.getTagListWithBlogs());
	    model.addAttribute("categoryListFooter", categoryDAO.getCategoryListFooter());
	   
	
        return "front/blog-page";
	}
	
	//pokusaj paginacije
	//@RequestMapping("/blog-by-page")
	//public String getBlogByPage(Model model) {
		//return "front/blog-page";
	//}
	
	
	//AUTHOR-PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@RequestMapping("/blog-author-page")
	public String getBlogAuthorPage( @RequestParam int id, Model model){
		
	    model.addAttribute("blogListAuthor", blogDAO.getBlogListByAuthor(id));
		model.addAttribute("categoryList", categoryDAO.getCategoriesForBlogFilter());
	    model.addAttribute("categoryListFooter", categoryDAO.getCategoryListFooter());
	    model.addAttribute("latestBlogs", blogDAO.getLatestBlogs());
	    model.addAttribute("author", authorDAO.getAuthor(id));
        model.addAttribute("tagList", tagDAO.getTagListWithBlogs());
	    return "front/blog-author-page";
	}
	
	//CATEGORY PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@RequestMapping("/blog-category-page")
	public String getBlogCategoryPage(@RequestParam int id, Model model){
		
		model.addAttribute("categoryList", categoryDAO.getCategoriesForBlogFilter());
		model.addAttribute("categoryListFooter", categoryDAO.getCategoryListFooter());
        model.addAttribute("category", categoryDAO.getCategory(id));
		model.addAttribute("latestBlogs", blogDAO.getLatestBlogs());
		model.addAttribute("blogCategory", blogDAO.getBlogListByCategory(id));
	    model.addAttribute("tagList", tagDAO.getTagListWithBlogs());
   
		return "front/blog-category-page";
	}
	
	// BLOGPOST PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
     
	  @RequestMapping("/blog-post-page")
      public String getBlogpostPage(@RequestParam int id, Model model){
		
		Blog blog =  blogDAO.getBlogWithTag(id);
		
		model.addAttribute("blog",blog);
	    model.addAttribute("latestBlogs", blogDAO.getLatestBlogs());
		model.addAttribute("categoryList", categoryDAO.getCategoriesForBlogFilter());
		model.addAttribute("categoryListFooter", categoryDAO.getCategoryListFooter());
		model.addAttribute("blogList", blogDAO.getBlogListByTag(id));
		model.addAttribute("tagList", tagDAO.getTagListWithBlogs());
	    model.addAttribute("author", authorDAO.getAuthor(id));
	    model.addAttribute("blogCategory", blogDAO.getBlogListByCategory(id));
	    model.addAttribute("blogListAuthor", blogDAO.getBlogListByAuthor(id));
	    Comment comment = new Comment();
		comment.setBlog(blog);
		model.addAttribute("comment", comment);
	    ArrayList<Comment> comments = (ArrayList<Comment>) commentDAO.getBlogComments(id);
		model.addAttribute("commentList", comments);
		model.addAttribute("commentCount",comments.size());
	
		return "front/blog-post-page";
	}
	
	
//	//obo je seo metoda
//	//@RequestMapping("/blog-post-page/{title}")
//	//public String getBlogpostPage(@PathVariable String title,@RequestParam int id, Model model){
//		
//		//Blog blog =  blogDAO.getBlog(id);
//		
//		//model.addAttribute("blog",blog);
//	   // model.addAttribute("latestBlogs", blogDAO.getLatestBlogs());
//		//model.addAttribute("categoryList", categoryDAO.getCategoriesForBlogFilter());
////	    model.addAttribute("tagList", tagDAO.getTagListWithBlogs());
////		model.addAttribute("author", authorDAO.getAuthor(id));
////	    model.addAttribute("blogCategory", blogDAO.getBlogListByCategory(id));
////		model.addAttribute("blogListAuthor", blogDAO.getBlogListByAuthor(id));
////		Comment comment = new Comment();
////		comment.setBlog(blog);
////		model.addAttribute("comment", comment);
////		ArrayList<Comment> commets = (ArrayList<Comment>) commentDAO.getBlogComments(id);
////		model.addAttribute("commentList", commets);
////		model.addAttribute("commentCount",commets.size());
////		
////		return "front/blog-post-page";
////	}
	
	//SEARCH PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
///	@RequestMapping("/blog-search-page")
//	public String getBlogSearchPage(@RequestParam int id, @RequestParam String text, Model model ){
		
		
		//model.addAttribute("blogSearch", blogDAO.blogSearch(text));
		//napraviti metodu u blog dao koja ceimati parametar tekst i koja radi search i query %
		//model.addAttribute("category", categoryDAO.getCategoryList());
//		model.addAttribute("categoryList", categoryDAO.getCategoriesForBlogFilter());
		//model.addAttribute("latestBlogs", blogDAO.getLatestBlogs());
		//model.addAttribute("blogList", blogDAO.getBlogListByTag(id));
	  //  model.addAttribute("tagList", tagDAO.getTagListWithBlogs());
		//model.addAttribute("author", authorDAO.getAuthorList());
	   // return "front/blog-search-page";
	//}
	
	//@RequestMapping("/blog-search")
	//public String searchBlog(@ModelAttribute String text) {
		//blogDAO.blogSearch(text);
		//return "front/blog-search-page";
	//}
	
	//ORDER BY !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@RequestMapping("/blog-post-page-order")
     
	public String getBlogListOrderPage(@RequestParam int orderBy, Model model) {
	
		model.addAttribute("blogList", blogDAO.getBlogList(orderBy));
		model.addAttribute("blog", blogDAO.getBlogList());
		model.addAttribute("category", categoryDAO.getCategoryList());
		model.addAttribute("categoryListFooter", categoryDAO.getCategoryListFooter());
		model.addAttribute("latestBlogs", blogDAO.getLatestBlogs());
		model.addAttribute("categoryList", categoryDAO.getCategoriesForBlogFilter());
		model.addAttribute("tagList", tagDAO.getTagListWithBlogs());
		
		return "front/blog-page";
	}
	
	
	
	@RequestMapping("/blog-tag-page")
	
	public String getBlogTagPage(@RequestParam int id, Model model){
	    model.addAttribute("categoryList", categoryDAO.getCategoriesForBlogFilter());
	    model.addAttribute("categoryListFooter", categoryDAO.getCategoryListFooter());
	    model.addAttribute("category", categoryDAO.getCategory(id));
		model.addAttribute("latestBlogs", blogDAO.getLatestBlogs());
		model.addAttribute("blogTag", blogDAO.getBlogListByTag(id));
	    model.addAttribute("tagList", tagDAO.getTagListWithBlogs());
	    model.addAttribute("tag", tagDAO.getTag(id));
 
	return "front/blog-tag-page";
	}
	
	
	//CONTACT PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@RequestMapping("/contact-page")
	public String getContactPage(Model model){
	
	model.addAttribute("contact", new Contact());
	model.addAttribute("latestBlogs", blogDAO.getLatestBlogs());
		return "front/contact-page";
	}

	
	
	@RequestMapping("/contact-save")
	public String saveContact(@Valid @ModelAttribute Contact contact, BindingResult result) {
		
	if(result.hasErrors()) {
		return "front/contact-page";
	}
	contactDAO.saveContact(contact);
	return "redirect: contact-page";
}



	@RequestMapping("/comment-save")
	public String saveComment(@ModelAttribute Comment comment) {
	
	commentDAO.saveComment(comment);
		
		return "redirect: blog-post-page?id="+comment.getBlog().getId();
	}
	
	
}

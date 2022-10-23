package cubes.main;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import com.mysql.cj.jdbc.MysqlDataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource myDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		        //UserBuilder users = User.withDefaultPasswordEncoder();
		        //auth.inMemoryAuthentication()
				//.withUser(users.username("pera").password("p123").roles("admin"))
				//.withUser(users.username("jovana").password("j123").roles("employee"));
				
		auth.jdbcAuthentication().dataSource(myDataSource);
			
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		
		
		.antMatchers("/administration/category-list").hasRole("admin")
		.antMatchers("/administration/category-form").hasRole("admin")
		.antMatchers("/administration/tag-form").hasRole("admin")
		.antMatchers("/administration/tag-list").hasRole("admin")
	    .antMatchers("/administration/contact-list").hasRole("admin")
		.antMatchers("/administration/comment-list").hasRole("admin")
		.antMatchers("/administration/user-list").hasRole("admin")
		.antMatchers("/administration/item-list").hasRole("admin")
		.antMatchers("/administration/item-form").hasRole("admin")
		
		.antMatchers("/administration/author-list").hasAnyRole("author,admin")
		.antMatchers("/administration/author-form").hasAnyRole("author,admin")
		.antMatchers("/administration/blog-list").hasAnyRole("author,admin")
		.antMatchers("/administration/blog-form").hasAnyRole("author, admin")
		.antMatchers("/administration/**").hasAnyRole("author,admin")
		.and().formLogin()
		.loginPage("/loginPage")
		.loginProcessingUrl("/authenticateTheUser").permitAll();
	}
	
	
	
	
	
	}



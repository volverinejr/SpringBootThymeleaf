package com.claudemirojr.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.claudemirojr.app.auth.handler.LoginSucessoHandler;
import com.claudemirojr.app.model.service.JpaUserDetailsService;


@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginSucessoHandler sucessoHandler;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Autowired
	private DataSource dataSource;
	
	
	@Autowired
	@Qualifier("jpaUserDetailsService")
	private JpaUserDetailsService userDetailsService; 
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/imagens/**", "/clientes/listar").permitAll()
		.antMatchers("/clientes/ver/**").hasAnyRole("USER")
		.antMatchers("/uploads/**").hasAnyRole("USER")
		.antMatchers("/clientes/form/**").hasAnyRole("ADMIN")
		.antMatchers("/fatura/eliminar/**").hasAnyRole("ADMIN")
		.antMatchers("/fatura/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
	    	.formLogin()
		    	.successHandler(sucessoHandler)
		    	.loginPage("/login")
		    	.permitAll()
		.and()
		.logout().permitAll();
		
	}
	
	


	
	@Autowired
	public void configureGlobal( AuthenticationManagerBuilder builder ) throws Exception {
		
		builder.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);

		
		/*
		builder.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder)
			.usersByUsernameQuery("select username, password, enabled from users where username=?")
			.authoritiesByUsernameQuery("select u.username, a.authority from authorities a join users u on ( a.user_id = u.id ) where u.username=?");
		*/
		
		
		
		/*
		PasswordEncoder encoder = this.passwordEncoder;
		UserBuilder users = User.builder().passwordEncoder(password -> encoder.encode(password));
		
		
		builder.inMemoryAuthentication()
			.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
			.withUser(users.username("miro").password("12345").roles("USER"));
		
		*/
		
	}

}

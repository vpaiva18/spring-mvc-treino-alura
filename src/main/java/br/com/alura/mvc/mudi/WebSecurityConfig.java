package br.com.alura.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSoruce;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/home/**")
				.permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin(form -> form
                    .loginPage("/login")
					.defaultSuccessUrl("/usuario/myHome", true)
					.permitAll())
			.logout(logout -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) 
					.logoutSuccessUrl("/home"))
			.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		auth.jdbcAuthentication()
			.dataSource(dataSoruce)
			.passwordEncoder(encoder);
	}
	

}

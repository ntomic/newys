package hr.five.newys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
		            .antMatchers("/h2/**", "/swagger/**", "/index/**", "/management/**")
		            .permitAll()
		            .anyRequest()
		            .authenticated()
		            .and()
		            .formLogin()
		            .permitAll();
		httpSecurity.csrf()
		            .ignoringAntMatchers("/h2/**", "/swagger/**", "/index/**", "/management/**");
		httpSecurity.headers()
		            .frameOptions()
		            .sameOrigin();
	}
	
	final DataSource dataSource;
	
	public WebSecurityConfig(DataSource dataSource) {this.dataSource = dataSource;}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		    .dataSource(dataSource)
		    .passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}

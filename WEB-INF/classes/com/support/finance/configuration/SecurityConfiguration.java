/**
 * 
 */
package com.support.finance.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.support.finance.security.web.webauthentication.CaptchaAuthenticationDetails;
import com.support.finance.security.web.webauthentication.WebAuthenticationProvider;

 
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
	/*@Autowired
	DataSource dataSource;*/
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	CustomFailureHandler customFailureHandler;
	
	@Autowired
    private WebAuthenticationProvider webAuthenticationProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(webAuthenticationProvider);
	}
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
        .antMatchers("/", "/home").permitAll()
        .antMatchers("/home/**").permitAll()
        .antMatchers("/account/dashboard/employee/**").access("hasRole('EMPLOYEE')")
        .antMatchers("/account/dashboard/employer/**").access("hasRole('EMPLOYER')")
        .antMatchers("/account/dashboard/university/**").access("hasRole('UNIVERSITY')")
        .antMatchers("/account/dashboard/college/**").access("hasRole('COLLEGE')")
        .antMatchers("/account/dashboard/university/**").access("hasRole('UNIVERSITY')")
        .antMatchers("/account/training/skill/**").access("hasRole('ROLE_HUNAR')")
        .antMatchers("/account/training/verifier/**").access("hasRole('ROLE_HUNAR_VERIFIER')")
        .antMatchers("/account/dashboard/service/**").access("hasRole('COLLEGE') and hasRole('UNIVERSITY')")
        .antMatchers("/account/dashboard/admin/**").access("hasRole('ADMIN')")
        .antMatchers(HttpMethod.OPTIONS, "/**").denyAll()
        .antMatchers(HttpMethod.PUT, "/**").denyAll()
        .antMatchers(HttpMethod.DELETE, "/**").denyAll()
        .antMatchers(HttpMethod.PATCH, "/**").denyAll()
        .antMatchers(HttpMethod.TRACE, "/**").denyAll()
        .antMatchers(HttpMethod.HEAD, "/**").denyAll()
        .and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
        .authenticationDetailsSource(getSimpleCaptchaAuthenticationDetailsSource())
        .usernameParameter("encryptedid").passwordParameter("password").failureHandler(customFailureHandler)
        .and().csrf().ignoringAntMatchers("/home/user/information/save")
        .and().csrf().ignoringAntMatchers("/home/user/location/save")
        .and().csrf().ignoringAntMatchers("/home/user/information/update")
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/login?logout"))
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .deleteCookies("remember-me", "JSESSIONID")
        .and().exceptionHandling().accessDeniedPage("/login?accessdenied")
        .and().headers()
        //.frameOptions().deny()
        .addHeaderWriter(new StaticHeadersWriter("Server","jhtesd server maintained by Sanjay Singh for nicsi ranchi "))
        //.addHeaderWriter(new StaticHeadersWriter("Cache-Control","no-cache, no-store, max-age=0, must-revalidate"))
        .addHeaderWriter(new StaticHeadersWriter("Pragma","no-cache"))
        .cacheControl().disable()
        //.contentTypeOptions().disable()
        //.httpStrictTransportSecurity().disable()
        //.xssProtection()
		//.block(false)
		.disable();
     http
      .exceptionHandling().accessDeniedPage("/404");
      
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(webAuthenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
    private  AuthenticationDetailsSource<HttpServletRequest, CaptchaAuthenticationDetails> getSimpleCaptchaAuthenticationDetailsSource(){
        return context -> new CaptchaAuthenticationDetails(context);
    }
    
    
}

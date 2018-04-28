package com.support.finance.security.web.webauthentication;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.support.finance.beans.CustomUser;
import com.support.finance.security.utils.OpenSSLEncryption;
import com.support.finance.security.web.webauthentication.CaptchaAuthenticationDetails;



@Component("webAuthenticationProvider")
public class WebAuthenticationProvider implements AuthenticationProvider {

	 /*@Autowired
	 private CustomUserService userService;*/
	 
	 @Autowired
	 private UserDetailsService userService;

	 public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=null;
         //String username = authentication.getName();
		 //String txtpassword = (String) authentication.getCredentials();
		 String encrypttext=OpenSSLEncryption.decrypt(authentication.getName());
         String[] userpassword=encrypttext.split("##");
         String username = userpassword[0]+"";
         String password = userpassword[1]+"";
         CaptchaAuthenticationDetails captchaDetails = (CaptchaAuthenticationDetails) authentication.getDetails();
         if (!(captchaDetails instanceof CaptchaAuthenticationDetails)) {       	 
             throw new InsufficientAuthenticationException(
                     "Captcha not found.");
         }
         
           String captcha = captchaDetails.getCaptcha();
           if (captcha != null) {
         		String expected = captcha;
         		String actual = captchaDetails.getAnswer();
         		if (!expected.equals(actual)) {
         			throw new BadCredentialsException("captcha");
                 
         		}
         	}
           CustomUser user = (CustomUser) userService.loadUserByUsername(username);
           if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
        	   throw new BadCredentialsException("userid");
           }
    
           if (!password.equals(user.getPassword())) {
        	   throw new BadCredentialsException("password");
           }
           
           if(user != null && user.getUsername().equalsIgnoreCase(username) && password.equals(user.getPassword())){
          	 
           //if(user != null && user.getUsername().equalsIgnoreCase(username) && SecurityUtils.checkPassword(password, user.getPassword())){
        	   usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);       	   
           }
           return usernamePasswordAuthenticationToken;
   }

   public boolean supports(Class<?> arg0) {
       return true;
   }
   
   
}

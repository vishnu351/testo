/**
 * 
 */
package com.support.finance.controller;




import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

//import java.io.IOException;

//import in.dignify.sanjay.wscontroller.VerifyRecaptcha;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;



/**
 * @author vishnu
 *
 */

@Controller
@Scope("request")
public class HomeController implements ServletContextAware{
	
	private ServletContext servletContext;
	private static final Logger logger = Logger.getLogger(HomeController.class);
	//private @Autowired UserService userService;

	
	@RequestMapping(value={"/login","/home"},method=RequestMethod.GET)
	public String indexDashboardPage(Model model,HttpServletRequest request, HttpServletResponse response){
		model.addAttribute("setting","index");		
		//model.addAttribute("newsBeanList",userService.getAllNewsListWise("all"));
		return "homelayoutindex";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;	
	}
	 private String getPrincipal(){
	        String userName = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	        if (principal instanceof UserDetails) {
	            userName = ((UserDetails)principal).getUsername();            
	        } else {
	            userName = principal.toString();
	        }
	        return userName;
	    }
}

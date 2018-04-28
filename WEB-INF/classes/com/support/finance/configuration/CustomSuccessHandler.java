/**
 * 
 */
package com.support.finance.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.support.finance.beans.Message;


 
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
 
	private static final Logger logger = Logger.getLogger(CustomSuccessHandler.class);
    //private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    private String urlredirect;
 
    @ResponseBody
    protected void handle(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        
        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }
        //redirectStrategy.sendRedirect(request, response, targetUrl);
        response.setStatus(HttpServletResponse.SC_OK);
        Message obj = new Message(targetUrl, "success");
        Gson gson = new Gson();
        response.getWriter().print(gson.toJson(obj));      
        response.getWriter().flush();
        
        
    }
 
    
    
    /*
     * This method extracts the roles of currently logged-in user and returns
     * appropriate URL according to his/her role.
     */
    protected String determineTargetUrl(Authentication authentication) {
        String url = "";
 
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
 
        List<String> roles = new ArrayList<String>();
 
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        if (isEmployer(roles)) {
            url = "/account/dashboard/employer";
         }
        else if (isEmployee(roles)) {
            url = "/account/dashboard/employee";
         } 
        else if (isAdmin(roles)) {
        	 url = "/account/dashboard/admin";
        }
        else if (isSiteAdmin(roles)) {
            url = "/account/dashboard/siteadmin";
        }
        else if (isStudent(roles)) {
        	url = "/account/dashboard/student?appliedfor=UGE";
        } 
        else if (isCollege(roles)) {
            url = "/account/dashboard/college";
        } 
        else if (isCollegeVerifier(roles)){
            url = "/account/dashboard/collegeverifier";
        } 
        else if (isCollegeDept(roles)) {
            url = "/account/dashboard/nodalofficer";
        } 
        else if (isUniversity(roles)) {
        	 url = "/account/dashboard/university";
        } 
        else if (isHunar(roles)) {
       	 url = "/account/training/skill";
       }
        else if (isHunarVerifier(roles)) {
          	 url = "/account/training/verifier/profile?action=verify";
          }
        else if (isUser(roles)) {
            url = "/home";
        } else {
            url = "/accessDenied";
        }
 
        return url;
    }
 
    private boolean isUser(List<String> roles) {
        if (roles.contains("ROLE_USER")) {
            return true;
        }
        return false;
    }
    
    private boolean isEmployee(List<String> roles) {
        if (roles.contains("ROLE_EMPLOYEE")) {
            return true;
        }
        return false;
    }
    
    private boolean isEmployer(List<String> roles) {
        if (roles.contains("ROLE_EMPLOYER")) {
            return true;
        }
        return false;
    }
    
    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }
    
    private boolean isStudent(List<String> roles) {
        if (roles.contains("ROLE_STUDENT")) {
            return true;
        }
        return false;
    }
    
    private boolean isCollege(List<String> roles) {
        if (roles.contains("ROLE_COLLEGE")) {
            return true;
        }
        return false;
    }
    private boolean isCollegeVerifier(List<String> roles) {
        if (roles.contains("ROLE_COLLEGE_VERIFIER")) {
            return true;
        }
        return false;
    }
    private boolean isCollegeDept(List<String> roles) {
        if (roles.contains("ROLE_COLLEGE_DEPTT")) {
            return true;
        }
        return false;
    }
    
    private boolean isUniversity(List<String> roles) {
        if (roles.contains("ROLE_UNIVERSITY")) {
            return true;
        }
        return false;
    }
    private boolean isSiteAdmin(List<String> roles) {
        if (roles.contains("ROLE_SITEADMIN")) {
            return true;
        }
        return false;
    }
    private boolean isHunar(List<String> roles) {
        if (roles.contains("ROLE_HUNAR")) {
            return true;
        }
        return false;
    }
    private boolean isHunarVerifier(List<String> roles) {
        if (roles.contains("ROLE_HUNAR_VERIFIER")) {
            return true;
        }
        return false;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
    
    /*public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
 
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }*/

	public String getUrlredirect() {
		return urlredirect;
	}

	public void setUrlredirect(String urlredirect) {
		this.urlredirect = urlredirect;
	}
 
}
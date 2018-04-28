package com.support.finance.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.support.finance.beans.Message;

@Component
public class CustomFailureHandler implements AuthenticationFailureHandler{
	private static final Logger logger = Logger.getLogger(CustomFailureHandler.class);

	 @ResponseBody
	 protected void failurehandle(HttpServletRequest request,
	            HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
		 	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		 	// response.setStatus(HttpServletResponse.SC_OK);   
		 	Message obj = new Message(authenticationException.getMessage()+"", "false");
	        Gson gson = new Gson();
	        response.getWriter().print(gson.toJson(obj));      
	        response.getWriter().flush();
	 }
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException)
			throws IOException, ServletException {
		 failurehandle(request, response, authenticationException);
		 clearAuthenticationAttributes(request);
	}
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
    
}

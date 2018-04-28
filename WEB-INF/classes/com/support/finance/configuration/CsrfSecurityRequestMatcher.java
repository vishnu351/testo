/**
 * 
 */
package com.support.finance.configuration;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author vishnu
 *
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {
    //private Pattern allowedMethods = Pattern.compile("^(GET||TRACE|OPTIONS)$");
	private Pattern allowedMethods = Pattern.compile("^(GET)$");
    private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("/account/home/payment/**", null);

    @Override
    public boolean matches(HttpServletRequest request) {          
        if(allowedMethods.matcher(request.getMethod()).matches()){
            return false;
        }
        return !unprotectedMatcher.matches(request);
    }

	
}

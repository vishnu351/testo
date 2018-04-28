package com.support.finance.security.web.webauthentication;


import nl.captcha.Captcha;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class CaptchaAuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = 8047091036777813803L;

	private final String answer;
	private final String captcha;

	public CaptchaAuthenticationDetails(HttpServletRequest req) {
        super(req);
		this.answer = req.getParameter("logincaptcha");
		this.captcha = (String) WebUtils.getSessionAttribute(req, "captcha");
		System.out.println("answer "+answer);
	}

	public String getAnswer() {
		return answer;
	}

	public String getCaptcha() {
		return captcha;
	}

}


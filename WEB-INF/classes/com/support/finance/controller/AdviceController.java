package com.support.finance.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nk sanjay on 28/5/17.
 */
@ControllerAdvice
public class AdviceController {

    private static final String HEADER_X_FORWARDED_FOR = "X-FORWARDED-FOR";
    private static final Logger logger = LoggerFactory.getLogger(AdviceController.class);


    @Autowired
    ResourceUrlProvider resourceUrlProvider;

    
    @ModelAttribute("resourceUrlProvider")
    public ResourceUrlProvider urls() {
        return this.resourceUrlProvider;
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
        return "403";
    }

/*    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
        return "redirect:/404";
    }
*/
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public String noHandlerFound(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return "404";
    }
 
    
    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestPartException.class,
            BindException.class
            
           
    })
    public String badRequest(HttpServletRequest request, HttpServletResponse response,Exception e) throws Exception {
        logger.info(e.getClass().getName(),e);
        if(e instanceof BindException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        if(e instanceof HttpRequestMethodNotSupportedException){
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
        if(e instanceof HttpMediaTypeNotSupportedException){
            response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
        if(e instanceof HttpMediaTypeNotAcceptableException){
            response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
        /*if(e instanceof MissingPathVariableException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }*/
        if(e instanceof MissingServletRequestParameterException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        if(e instanceof ServletRequestBindingException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        if(e instanceof ConversionNotSupportedException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        if(e instanceof TypeMismatchException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        if(e instanceof HttpMessageNotReadableException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        if(e instanceof HttpMessageNotWritableException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        if(e instanceof MethodArgumentNotValidException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        if(e instanceof MissingServletRequestPartException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
       /* if(e instanceof AsyncRequestTimeoutException){
            response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
        }*/
        //return modelAndView;
        return "404";
    }
    
    @RequestMapping(value = {"/403","/404","/500","error","invalid-request"}, method = RequestMethod.GET)
    public String NotFoudPage() {
        return "404";

    }
    
    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;

        if(e instanceof ServletException){
            throw e;
        }
        /*if(user != null){
            bugsnag.addCallback(report -> {
                report.setUserName(user.getName());
                report.setUserEmail(user.getEmail());
                report.setUserId(user.getId().toString());
            });
        }
        bugsnag.addCallback(new Callback() {
            @Override
            public void beforeNotify(Report report) {
                if (request == null) {
                    return;
                }
                // Add request information to metaData
                report
                        .addToTab("request", "url", request.getRequestURL().toString())
                        .addToTab("request", "method", request.getMethod())
                        .addToTab("request", "params", request.getParameterMap())
                        .addToTab("request", "clientIp", getClientIp(request))
                        .addToTab("request", "headers", getHeaderMap(request));

                // Set default context
                report.setContext(request.getMethod() + " " + request.getRequestURI());
            }
        });

        bugsnag.notify(e);*/
        e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return "404";
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwardedAddr = request.getHeader(HEADER_X_FORWARDED_FOR);
        if (forwardedAddr != null) {
            remoteAddr = forwardedAddr;
            int idx = remoteAddr.indexOf(',');
            if (idx > -1) {
                remoteAddr = remoteAddr.substring(0, idx);
            }
        }
        return remoteAddr;
    }

    private Map<String, String> getHeaderMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            map.put(key, request.getHeader(key));
        }

        return map;
    }

}
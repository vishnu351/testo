package com.support.finance.beans;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
 
public class Role implements GrantedAuthority{
    
    private String name;
     
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getAuthority() {
        return this.name;
    }
}
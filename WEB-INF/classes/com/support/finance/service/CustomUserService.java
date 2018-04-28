package com.support.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.support.finance.beans.CustomUser;
import com.support.finance.dao.UserDao;


@Service("customUserService")
@Transactional
public class CustomUserService implements UserDetailsService {
	 
    @Autowired
    private UserDao userDao;
    
    
   public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
       return userDao.loadUserByUsername(username);
   }

}

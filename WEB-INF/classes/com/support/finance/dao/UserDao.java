package com.support.finance.dao;

import java.util.List;

import com.support.finance.beans.CustomUser;


public interface UserDao {

	//String saveUser(UserRegister userRegister);
	boolean checkusername(String username);
	//UserRegister getUserSerialNo(String username);
	boolean checkmobilenumber(String mobilenumber);
	boolean checkregistrationno(String registrationno);
	
	CustomUser loadUserByUsername(String username);
}


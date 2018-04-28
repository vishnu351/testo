package com.support.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.support.finance.dao.UserDao;



@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	/*@Override
	public String saveUser(UserRegister userRegister) {
		return userDao.saveUser(userRegister);
	}*/
	
	
	@Override
	public boolean checkusername(String username) {
		return userDao.checkusername(username);
	}

	/*@Override
	public UserRegister getUserSerialNo(String username) {
		return userDao.getUserSerialNo(username);
	}*/

	@Override
	public boolean checkmobilenumber(String mobilenumber) {
		return userDao.checkmobilenumber(mobilenumber);
	}

	
}

package com.capgemini.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.UsersDao;
import com.capgemini.model.Users;
@Service
public class LoginService implements LoginServiceI {
	@Autowired
	UsersDao userDao;
	
	@Override
	public String login(String email,String userPassword){
		long id=userDao.getIdByEmail(email);
		Optional<Users> findById =  userDao.findById(id);
		if(findById.isPresent()) {
			Users user = findById.get();
			if(user.getUserPassword().equals(userPassword)) {
				if(user.getIsAdmin().equals("True")){
					return "Admin";
					}
				else { 
					return "User";
				}
			}
			return "Incorrect Password";
		}
		return "The User is Not Present" ;
	}

}

package com.capgemini.service;

import java.util.List;
import java.util.Optional;
import com.capgemini.dao.UsersDao;
import com.capgemini.exception.InvalidException;
import com.capgemini.exception.NullException;
import com.capgemini.exception.UserNotAddedException;
import com.capgemini.exception.UserNotRemovedException;
import com.capgemini.exception.WrongValueException;
import com.capgemini.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
	@Autowired
	UsersDao usersDao;

	public Users addUsers(Users users) {
		Optional<Users> findById = usersDao.findById(users.getUserId());
		if (!findById.isPresent()) {
			usersDao.save(users);
			return users;

		} else
			throw new UserNotAddedException("User is already Exists!!!!");
	}

	public String deleteUsers(long id) {
		if (id != 0) {
			Optional<Users> findById = usersDao.findById(id);
			if (findById.isPresent()) {
				usersDao.deleteById(id);
				return "User  Remove";
			} else {
				throw new UserNotRemovedException("Users is Not Delete!!!!!!");
			}
		} else {
			throw new WrongValueException("Id is Incorrect");

		}
	}

	public String updateUsers(long id,Users users) {
		if (users != null) {
			Optional<Users> findById = usersDao.findById(id);
			if (findById.isPresent()) {
				findById.get().setAge(users.getAge());
				findById.get().setContactNo(users.getContactNo());
				findById.get().setEmail(users.getEmail());
				findById.get().setGender(users.getGender());
				findById.get().setUserName(users.getUserName());
				findById.get().setUserPassword(users.getUserPassword());
				usersDao.save(findById.get());
				return " users updated";

			} else {
				throw new NullException("User is not Updated");
			}
		} else {
			throw new WrongValueException("Users Details are Incorrect!!!");
		}
	}

	public Optional<Users> checkUserById(long id) {
		if (id != 0) {
			Optional<Users> findById = usersDao.findById(id);
			return findById;
		} else {
			throw new InvalidException("UserID invalid");
		}
	}
	

	public List<Users> getAll() {
		// TODO Auto-generated method stub
		List<Users> uList = usersDao.findAll();
		if (uList == null) {
			throw new NullException("Diagnostic Center List is Empty !!!!!!");
		} else {
			return uList;
		}
	}
}

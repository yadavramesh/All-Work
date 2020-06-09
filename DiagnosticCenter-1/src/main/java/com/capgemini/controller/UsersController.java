package com.capgemini.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import com.capgemini.exception.NullException;
import com.capgemini.exception.WrongValueException;
import com.capgemini.model.Users;
import com.capgemini.service.UsersService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "http://localhost:4200", maxAge = 80)
public class UsersController {
	@Autowired
	UsersService usersService;
	Logger logger = LoggerFactory.getLogger(UsersController.class);
	// SHOW ALL Users
	@GetMapping("/getUser")
	public ResponseEntity<List<Users>> getAll() {
		try {
			List<Users> list = usersService.getAll();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			throw new NullException(e.getMessage());
		}
	}

	// SEARCH USER
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<Users> findUser(@PathVariable("userId") long userId) {
		Optional<Users> findById = usersService.checkUserById(userId);
		if (findById.isPresent()) {
			return new ResponseEntity<Users>(findById.get(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

	// Add USER
	@PostMapping("/add")
	public ResponseEntity<String> saveCustomer(@Valid @RequestBody Users customer) {
		try {
			usersService.addUsers(customer);
			return new ResponseEntity<String>("Your User ID is "+customer.getUserId(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new WrongValueException(e.getMessage());
		}
	}

	// DELETE USER
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> removeUser(@PathVariable("userId") long userId) {
		try {
			usersService.deleteUsers(userId);
			return new ResponseEntity<String>("Deleted", HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			throw new NullException(e.getMessage());
		}
	}

	// UPDATE CUSTOMER
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUsers(@Valid @PathVariable("id") long userId,@RequestBody Users customer) {
		try {
			usersService.updateUsers(userId,customer);
			return new ResponseEntity<String>("Updated", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new WrongValueException(e.getMessage());
		}
	}
	
	@GetMapping("/getUserLogin/{userId}/{userPassword}")
	public ResponseEntity<String> loginUser(@PathVariable("userId") long userId,@PathVariable("userPassword") String userPassword) {
		Optional<Users> findById = usersService.checkUserById(userId);
		if (findById.isPresent()) {
			Users u=findById.get();
			if((u.getUserId()==userId)&&(u.getUserPassword().equals(userPassword))){
			return new ResponseEntity<String>("true", HttpStatus.OK);
		}
			else
				return new ResponseEntity<>("false", HttpStatus.NOT_FOUND);
			} else
			return new ResponseEntity<>("Empty", HttpStatus.NOT_FOUND);
	}
}
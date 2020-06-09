package com.capgemini;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.dao.UsersDao;
import com.capgemini.model.Users;
import com.capgemini.service.UsersService;

public class DiagnosticCenter1ApplicationTests {
	@MockBean

	 private UsersDao dao;

	@Autowired
	private UsersService service;

	 @Test

	 public void getUsersTest() {

	 when(dao.findAll()).thenReturn(Stream

	  .of(new Users(0, "hEllo1$", "Danile","983713456","User","daniel@gmail.com" ,31, "Male"), new Users(0, "capg1$", "Huy","983713890","User","huy@gmail.com" ,23, "female")).collect(Collectors.toList()));

	 assertEquals(2, service.getAll().size());

	 }

	  /*

	 @Test

	 public void checkUserByIdTest() {

	 long userId = 376;

	 when(dao.findById (userId))

	  .thenReturn(Stream.of(new Users(376,"hEllo1$", "Danile","983713456","User","daniel@gmail.com" ,31, "Male")).collect(Collectors.toList()));

	 assertEquals(1, service.checkUserById(userId).size());

	 }*/



	 @Test

	 public void AddUsersTest() {

	 Users user = new Users(0, "hEll21$", "Krish","9837134821","User","krish12@gmail.com" ,82, "Male");

	 when(dao.save(user)).thenReturn(user);

	 assertEquals(user, service.addUsers(user));

	 }



	 @Test

	 public void deleteUsersTest() {

	 Users user = new Users(0, "hEll21$", "Krish","9837134821","User","krish12@gmail.com" ,82, "Male");

	 long userId = 567;



	 service.deleteUsers(userId);

	 verify(dao, times(1)).delete(user);

	 }
}

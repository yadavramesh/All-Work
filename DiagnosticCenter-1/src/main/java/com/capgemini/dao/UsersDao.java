package com.capgemini.dao;


import com.capgemini.model.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<Users, Long>{
	@Query("select u.userId from Users u where u.email=?1")
	public long getIdByEmail(String email);

}
package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Appointment;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Long >{
	
}

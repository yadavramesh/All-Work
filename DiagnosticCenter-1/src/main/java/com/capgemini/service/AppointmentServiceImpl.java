package com.capgemini.service;



import java.util.ArrayList;

import java.util.List;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import com.capgemini.dao.AppointmentDao;

import com.capgemini.exception.AppointmentNotFoundException;

import com.capgemini.exception.AppointmentNotRemovedException;

import com.capgemini.exception.NullException;

import com.capgemini.exception.WrongValueException;

import com.capgemini.model.Appointment;



@Service

public class AppointmentServiceImpl implements AppointmentService {

 @Autowired

 AppointmentDao appointmentDao;



 @Override

 public String saveAppointment(Appointment appointment) {

 try {

  if (appointment.getDatetime().equals(null))

  throw new NullException("Any field in appointment cannot be null");

  else {

  appointmentDao.save(appointment);

  return "Appointment saved";

  }

 } catch (NullException ex) {

  System.out.println(ex);

 }

 return null;

 }



 @Override

 public String removeAppointment(Appointment appointment) {

 if (appointmentDao.findById(appointment.getAppointmentId()).isPresent()) {

  appointmentDao.delete(appointment);

  return "Appointment deleted";

 } else

  return "Appointment does not exist";

 }



 @Override

 public Optional<Appointment> findById(long appointmentId) {

 Optional<Appointment> appointment = appointmentDao.findById(appointmentId);

 try {

  if (appointment != null)

  return appointment;

  else

  throw new AppointmentNotFoundException("Oops!! Appointment not found!");

 } catch (AppointmentNotFoundException ex) {

  System.out.println(ex);

 }

 return null;

 }



 @Override

 public String updateAppointment(Appointment appointment) {

 if (appointment != null) {

  Optional<Appointment> findById = appointmentDao.findById(appointment.getAppointmentId());

  if (findById.isPresent()) {

  appointmentDao.save(appointment);

  return "AppointmentList updated";

  } else {

  throw new NullException("Diagnostic Center Is not Updated!!!!!!!");

  }

 } else {

  throw new WrongValueException("Details are Incorrect!!!!!!!!!!");

 }

 }



 @Override

 public String deleteAppointment(long appointmentId) {

 // TODO Auto-generated method stub



 if (appointmentId != 0) {

  Optional<Appointment> findById = appointmentDao.findById(appointmentId);

  if (findById.isPresent()) {

  appointmentDao.deleteById(appointmentId);

  return "Appointment Removed";

  } else {

  throw new AppointmentNotRemovedException("Appointment is Not Removed ");

  }

 }



 else {

  throw new WrongValueException("Appointment Incorrect!!!!!!");

 }

 }



 @Override

 public List<String> getAllAppointment() {

 // TODO Auto-generated method stub

 List<String> list = new ArrayList<String>();

 List<Appointment> aList = appointmentDao.findAll();

 System.out.println(aList);

 if (aList == null) {

  throw new NullException("Diagnostic Center List is Empty !!!!!!");

 } else {

  for (Appointment a : aList) {

  list.add(new String("<span class='h-bold'>APPOINTMENT ID:</span>" + a.getAppointmentId() + "<span class='h-bold'>USERID: </span>" + a.getUsers().getUserId()

   + " <span class='h-bold'> USER_CONTACT:</span> " + a.getUsers().getContactNo() + " <span class='h-bold'> CENTER_ID: </span>"

   + a.getCenter().getCenterId() + " <span class='h-bold'> CENTER_NAME: </span>" + a.getCenter().getCenterName()

    + "<span class='h-bold'> TEST_NAME:</span> " + a.getTest().getTestName() + " <span class='h-bold'> APPROVED: </span>" + a.getApproved() + " <span class='h-bold'> DATE: </span>"

   + a.getDatetime()));

  }

  return list;

 }

 }

}


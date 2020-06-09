package com.capgemini.controller;



import java.util.List;

import java.util.Optional;



import javax.validation.Valid;



import com.capgemini.model.DiagnosticCenter;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;



import com.capgemini.exception.NullException;

import com.capgemini.model.Appointment;

import com.capgemini.service.AppointmentServiceImpl;

import com.capgemini.service.DiagnosticCenterService;

import com.capgemini.service.UsersService;



@RestController

@RequestMapping("/Controller")

@CrossOrigin("http://localhost:4200")

public class AppointmentController {

 @Autowired

 AppointmentServiceImpl appointmentService;

 @Autowired

 DiagnosticCenterService diagnosticCenterService;

 @Autowired

 UsersService usersService;



 Logger logger = LoggerFactory.getLogger(AppointmentController.class);



 /************************************************************************************

 * Method: makeAppointment Description: To add appointment

 *

 * @param appointment- all necessary details for making an appointment like

 * userid ,center details, test details

 * @returns String - Appointment added if all details are correct

 * @throws NullException- It is raised due to wrong format of data

 ************************************************************************************/

 @PostMapping("/makeAppointment")

 public ResponseEntity<?> makeAppointment(@RequestBody Appointment appointment) {

 try {

  Optional<DiagnosticCenter> ds = diagnosticCenterService.findById(appointment.getCenter().getCenterId());

  if (ds != null) {

  appointmentService.saveAppointment(appointment);

  return new ResponseEntity<String>(

   "Wait for confirmation! Your AppointmentId is " + appointment.getAppointmentId(),

   HttpStatus.OK);

  } else

  return new ResponseEntity<String>("No center found", HttpStatus.OK);

 } catch (Exception e) {

  // TODO: handle exception

  throw new NullException(e.getMessage());

 }

 }



 /************************************************************************************

 * Method: approveAppointment Description: To return list if appointment to the

 * admin for approval

 *

 * @param null- NA

 * @returns ResponseEntity - 200,Ok if there is atleast one appointment

 * @throws NullException- It is raised due to empty list of appointment

 ************************************************************************************/

 @GetMapping("/approveAppointment")

 public ResponseEntity<?> approveAppointment() {

 try {

  return new ResponseEntity<List<String>>(appointmentService.getAllAppointment(), HttpStatus.OK);

 } catch (Exception e) {

  // TODO: handle exception

  throw new NullException(e.getMessage());

 }

 }



 /************************************************************************************

 * Method: approved Description: To approve an appointment

 *

 * @param appointmentId- appointment's id

 * @returns String - Either appointment approved or incorrect appointment id

 * @throws NullException- It is raised due to invalid appointment id

 ************************************************************************************/

 @PostMapping("/approved/{id}")

 public ResponseEntity<?> approved(@Valid @PathVariable("id") long appointmentId) {

 try {

  if (appointmentService.findById(appointmentId).isPresent()) {

  Optional<Appointment> ap = appointmentService.findById(appointmentId);

  ap.get().setApproved("true");

  appointmentService.updateAppointment(ap.get());

  return new ResponseEntity<String>("Appointment Approved!", HttpStatus.OK);

  } else

  return new ResponseEntity<String>("Incorrect Appointment ID", HttpStatus.OK);

 } catch (Exception e) {

  // TODO: handle exception

  throw new NullException(e.getMessage());

 }

 }



 /************************************************************************************

 * Method: notApproved Description: To approve an appointment

 *

 * @param appointmentId- appointment's id

 * @returns String - Either appointment is disapproved or incorrect appointment

 * id

 * @throws NullException- It is raised due to invalid appointment id

 ************************************************************************************/

 @PostMapping("/notApproved/{id}")

 public ResponseEntity<?> notApproved(@Valid @PathVariable("id") long appointmentId) {

 try {

  if (appointmentService.findById(appointmentId).isPresent()) {

  Optional<Appointment> ap = appointmentService.findById(appointmentId);

  ap.get().setApproved("false");

  appointmentService.updateAppointment(ap.get());

  return new ResponseEntity<String>("Appointment Disapproved!", HttpStatus.OK);

  } else

  return new ResponseEntity<String>("Incorrect Appointment ID", HttpStatus.OK);

 } catch (Exception e) {

  // TODO: handle exception

  throw new NullException(e.getMessage());

 }

 }



 /************************************************************************************

 * Method: appointmentStatus Description: To check status of appointment

 * previously booked

 *

 * @param appointmentId- appointment's id

 * @returns String - Either appointment approved/ disapproved or pending

 * @throws NullException- It is raised due to invalid appointment id

 ************************************************************************************/

 @GetMapping("/appointmentStatus/{id}")

 public ResponseEntity<String> appointmentStatus(@Valid @PathVariable("id") long appointmentId) {

 try {

  if (appointmentService.findById(appointmentId).isPresent())

  if (appointmentService.findById(appointmentId).get().getApproved().matches("true"))

   return new ResponseEntity<String>("Your appointment is confirmed", HttpStatus.OK);

  else if (appointmentService.findById(appointmentId).get().getApproved().matches("false"))

   return new ResponseEntity<String>("Your appointment is disApproved!", HttpStatus.OK);

  else if (appointmentService.findById(appointmentId).get().getApproved().matches("empty"))

   return new ResponseEntity<String>("Wait for the confirmation!", HttpStatus.OK);

 } catch (Exception e) {

  // TODO: handle exception

  throw new NullException(e.getMessage());

 }

 return new ResponseEntity<String>("Incorrect Appointment ID", HttpStatus.OK);

 }

}


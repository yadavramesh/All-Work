package com.capgemini.service;



import java.util.List;

import java.util.Optional;



import com.capgemini.model.Appointment;



public interface AppointmentService {

 public String saveAppointment(Appointment appointment);

 public String removeAppointment(Appointment appointment) ;

 public Optional<Appointment> findById(long appointmentId);

 public String updateAppointment(Appointment appointment) ;

 public String deleteAppointment(long appointmentId);

 public List<String> getAllAppointment();

}


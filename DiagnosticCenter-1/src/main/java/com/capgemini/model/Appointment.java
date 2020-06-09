package com.capgemini.model;

import java.sql.Date;

import javax.persistence.CascadeType;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;

import javax.persistence.Id;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;

import javax.validation.constraints.NotNull;



@Entity

@SequenceGenerator(name = "sq", initialValue = 10, allocationSize = 100)

public class Appointment {

 @Id

 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sq")

 @NotNull(message = "appointment ID cannot be null")

 private long appointmentId;

 @ManyToOne(cascade = CascadeType.ALL)

 @JoinColumn(name = "testId")

 @NotNull(message = "Test cannot be null")

 private Test test;

 private Date datetime;

 private String approved="empty";

 @ManyToOne

 @JoinColumn(name = "userId", nullable = false)

 private Users users;

  @ManyToOne

  @JoinColumn(name = "centerId")

  private DiagnosticCenter center;



public Appointment()

{



}

 public Appointment(Test test, Date datetime, Users users, DiagnosticCenter center) {

 super();

 this.test = test;

 this.datetime = datetime;

 this.users.setUserId(users.getUserId());

 this.center.setCenterId(center.getCenterId());

 }



 public DiagnosticCenter getCenter() {

 return center;

 }

 public void setCenter(DiagnosticCenter center) {

 this.center = center;

 }

 public String getApproved() {

 return approved;

 }

 public Users getUsers() {

 return users;

 }



 public void setUsers(Users users) {

 this.users = users;

 }



 public long getAppointmentId() {

 return appointmentId;

 }



 public void setAppointmentId(long appointmentId) {

 this.appointmentId = appointmentId;

 }



 public Test getTest() {

 return test;

 }



 public void setTest(Test test) {

 this.test = test;

 }



 public Date getDatetime() {

 return datetime;

 }



 public void setDatetime(Date datetime) {

 this.datetime = datetime;

 }



 public void setApproved(String approved) {

 this.approved = approved;

 }



}


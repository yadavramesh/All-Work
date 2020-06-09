package com.capgemini.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@SequenceGenerator(name = "seq", initialValue = 1000, allocationSize = 100)
public class DiagnosticCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	private long centerId;
	@NotNull(message = "Center Name cannot be null")
	@Size(min = 3, max = 10, message = "Center Name must be equal or greater than 3 character but less than 10 character")
	@Pattern(regexp = "[A-za-z]+")
	private String centerName;
	@NotNull(message = "Contact No. cannot be null")
	@Size(max = 10)
	@Pattern(regexp = "(0/91)?[7-9][0-9]{9}")
	private String contactNo;
	@NotNull(message = "Address cannot be null")
	private String address;
	@NotNull(message = "Email cannot be null")
	@Email
	private String email;
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Test> listOfTest;

	public DiagnosticCenter() {
	}

	public DiagnosticCenter(long centerId) {
		this.centerId = centerId;
	}

	public DiagnosticCenter(long centerId,@NotNull @Size(min = 3, max = 5) String centerName, String contactNo, String address,
			String email) {
		super();
		this.centerId=centerId;
		this.centerName = centerName;
		this.contactNo = contactNo;
		this.address = address;
		this.email = email;

	}

	public long getCenterId() {
		return centerId;
	}

	public void setCenterId(long centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public List<Test> getListOfTest() {
		return listOfTest;
	}

	public void setListOfTest(List<Test> listOfTest) {
		this.listOfTest = listOfTest;
	}

	public String getEmail() {
		return email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "DiagnosticCenter [centerId=" + centerId + ", centerName=" + centerName + ", ContactNo=" + contactNo
				+ ", Address=" + address + ", email=" + email + ", listOfTest=" + listOfTest + "]";
	}

}

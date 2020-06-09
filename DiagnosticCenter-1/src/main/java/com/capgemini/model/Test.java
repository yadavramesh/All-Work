package com.capgemini.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Test {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int testId;
	private String testName;
	private int cost;
	public Test() {
		
	}

	public Test(String testName, int cost) {
		this.testName = testName;
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}

	@Override
	public String toString() {
		return "Test{" +
				"testId=" + testId +
				", testName='" + testName + '\'' +
				", cost=" + cost +
				'}';
	}
}

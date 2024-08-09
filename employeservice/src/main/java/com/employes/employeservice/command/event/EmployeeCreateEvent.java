package com.employes.employeservice.command.event;

public class EmployeeCreateEvent {
	private String employeeId;
	private String firstName;
	private String lastName;
	private String kin;
	private Boolean isDisciplined;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getKin() {
		return kin;
	}
	public void setKin(String kin) {
		this.kin = kin;
	}
	public Boolean getIsDisciplined() {
		return isDisciplined;
	}
	public void setIsDisciplined(Boolean isDisciplined) {
		this.isDisciplined = isDisciplined;
	}
	@Override
	public String toString() {
		return "EmployeeCreateEvent [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", kin=" + kin + ", isDisciplined=" + isDisciplined + "]";
	}
	
	
	
	
	
}

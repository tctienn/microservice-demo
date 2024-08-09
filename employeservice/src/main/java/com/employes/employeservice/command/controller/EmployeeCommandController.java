package com.employes.employeservice.command.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employes.employeservice.command.command.CreateEmployeeCommand;
import com.employes.employeservice.command.command.DeleteEmployeeCommand;
import com.employes.employeservice.command.command.UpdateEmployeeCommand;
import com.employes.employeservice.command.model.EmployeeRequestModel;

@RestController
@RequestMapping("/api/v1/employes")
public class EmployeeCommandController {
	
	@Autowired
	private CommandGateway commandGateway;
	
	@PostMapping
	public String addEmployee(@RequestBody EmployeeRequestModel model) {
		
		CreateEmployeeCommand command = new CreateEmployeeCommand(UUID.randomUUID().toString(),model.getFirstName(),model.getLastName(), model.getKin(), false);
		
		commandGateway.sendAndWait(command);
		return "employ add";
		
	}
	
	@PutMapping
	public String updateEmoloyee(@RequestBody EmployeeRequestModel model) {
		UpdateEmployeeCommand command = new UpdateEmployeeCommand(model.getEmployeeId(),model.getFirstName(), model.getLastName(),model.getKin(), model.getIsDisciplined());
		commandGateway.sendAndWait(command);
		return "employee update";
	}
	
	@DeleteMapping("/{employeeId}")
	public String deleteEmployee(@PathVariable String employeeId ) {
		DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
		commandGateway.sendAndWait(command);
		return "employee deleted";
	}
	
	
	
}

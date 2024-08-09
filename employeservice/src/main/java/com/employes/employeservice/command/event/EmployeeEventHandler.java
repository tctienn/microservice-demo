package com.employes.employeservice.command.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employes.employeservice.command.data.Employee;
import com.employes.employeservice.command.model.EmployeeRepository;

@Component
public class EmployeeEventHandler {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@EventHandler
	public void on(EmployeeCreateEvent event) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(event, employee);
		employeeRepository.save(employee);
	}
	
	@EventHandler
	public void on(EmployeeUpdateEvent event) {
		Employee employee = employeeRepository.findById(event.getEmployeeId()).orElse(null);
		if(employee==null) {
			
			System.out.println("lỗi trong event handle không tạo thành công employ");
			return;
		}
		employee.setFirstName(event.getFirstName());
		employee.setLastName(event.getLastName());
		employee.setKin(event.getKin());
		employee.setIsDisciplined(event.getIsDisciplined());
		employeeRepository.save(employee);
	}
	
	@EventHandler
	public void on(EmployeeDeleteEvent event) {
		
		employeeRepository.deleteById(event.getEmployeeId());
	}
	
	
}

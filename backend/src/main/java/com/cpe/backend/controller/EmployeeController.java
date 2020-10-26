package com.cpe.backend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import javax.validation.Valid;

import com.cpe.backend.entity.Admin;
import com.cpe.backend.entity.Gender;
import com.cpe.backend.entity.Employee;
import com.cpe.backend.entity.EmployeePayload;
import com.cpe.backend.repository.AdminRepository;
import com.cpe.backend.repository.GenderRepository;
import com.cpe.backend.repository.EmployeeRepository;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class EmployeeController { 
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private GenderRepository genderRepository;

    EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employee")
    public Collection<Employee> Employees() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/SearchEmployee/{id}")   
    public Employee SearchBed(@PathVariable("id") long id) { 
        return employeeRepository.findById(id);
    }

    @PostMapping("/employee/{name}/{phone}/{gender_id}/{address}/{admin_id}")
    public Employee newEmployee(Employee newEmployee,
    @PathVariable String name,
    @PathVariable String phone,
    @PathVariable String address,
    @PathVariable long admin_id,
    @PathVariable long gender_id
    ) {
  

    Admin rentAdmin = adminRepository.findById(admin_id);
    Gender createdBy = genderRepository.findById(gender_id);

    newEmployee.setName(name);
    newEmployee.setPhone(phone);
    newEmployee.setAddress(address);
    newEmployee.setCreatedBy(createdBy);
    newEmployee.setRentAdmin(rentAdmin);


    return employeeRepository.save(newEmployee); 
    
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteEmp(@Valid @PathVariable Long id) {
        employeeRepository.deleteById(id);
        return true;
    }


    @PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeePayload payload) {

		Optional<Employee> employeeData = employeeRepository.findById(id);
		if (employeeData.isPresent()) {
			 Employee _employee = employeeData.get();
             _employee.setName(payload.getName());
			_employee.setPhone(payload.getPhone());
			_employee.setAddress(payload.getAddress());
			return new ResponseEntity<>(employeeRepository.save(_employee), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    

    @PutMapping("/update/status/{id}")
	public ResponseEntity<Employee> updateStatus(@PathVariable("id") Long id) {

		Optional<Employee> employeeData = employeeRepository.findById(id);
		if (employeeData.isPresent()) {
            employeeData.get().setStatus(!employeeData.get().isStatus());  
            //เซ็ตตรงข้าม
			return new ResponseEntity<>(employeeRepository.save(employeeData.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    

}
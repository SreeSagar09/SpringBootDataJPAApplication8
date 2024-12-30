package com.app.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.app.model.Employee;
import com.app.repository.EmployeeCustomRepository;

@Component
public class TestRunner implements ApplicationRunner{
	@Autowired
	private EmployeeCustomRepository employeeCustomRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("From run method in TestRunner class.");
		
		System.out.println("----- To get all records by getAllEmployees() method -----");
		List<Employee> employeeList1 = employeeCustomRepository.getAllEmployees();
		employeeList1.forEach(e -> {
			System.out.println(e.getEmployeeId()+" --> "+e.getEmployeeName()+"["+e.getEmployeeCode()+"]");
		});
		
		System.out.println("----- To get all records by getEmployeeByEmployeeId(Integer employeeId) method -----");
		Employee employee1 = employeeCustomRepository.getEmployeeByEmployeeId(4);
		if(employee1 != null) {
			System.out.println(employee1.getEmployeeId()+" --> "+employee1.getEmployeeName()+"["+employee1.getEmployeeCode()+"]");
		}
		
		System.out.println("----- To get all records by getEmployeesByDesignation(String designation) method -----");
		List<Employee> employeeList2 = employeeCustomRepository.getEmployeesByDesignation("Software Engineer");
		employeeList2.forEach(e -> {
			System.out.println(e.getEmployeeId()+" --> "+e.getEmployeeName()+"["+e.getEmployeeCode()+"] --> "+e.getDesignation());
		});
		
		System.out.println("----- To get all records by getEmployeesByEmployeeIds(List<Integer> employeeIds) method -----");
		List<Integer> employeeIdList1 = Arrays.asList(2,3);
		List<Employee> employeeList3 = employeeCustomRepository.getEmployeesByEmployeeIds(employeeIdList1);
		employeeList3.forEach(e -> {
			System.out.println(e.getEmployeeId()+" --> "+e.getEmployeeName()+"["+e.getEmployeeCode()+"] --> "+e.getDesignation());
		});
		
		System.out.println("----- To get all records by getEmployeeNamesOrderBy(String orderBy) method -----");
		List<String> employeeNameList1 = employeeCustomRepository.getEmployeeNamesOrderBy("desc");
		employeeNameList1.forEach(e -> {
			System.out.println(e);
		});
		
		System.out.println("----- To get all records by getEmployees(Integer firstResult, Integer maxResult) method -----");
		List<Employee> employeeList4 = employeeCustomRepository.getEmployees(1, 2);
		employeeList4.forEach(e -> {
			System.out.println(e.getEmployeeId()+" --> "+e.getEmployeeName()+"["+e.getEmployeeCode()+"] --> "+e.getDesignation());
		});
	}

}

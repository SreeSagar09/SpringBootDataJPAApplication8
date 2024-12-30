package com.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.app.model.Employee;

@Repository
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Employee> getAllEmployees() {
		try {
			String queryString = "from Employee e";
			
			Query query = entityManager.createQuery(queryString);
			List<Employee> employeeList = query.getResultList();
			
			return employeeList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Employee getEmployeeByEmployeeId(Integer employeeId) {
		try {
			String queryString = "from Employee e where e.employeeId = ?0";
			
			Query query = entityManager.createQuery(queryString);
			query.setParameter(0, employeeId);
			List<Employee> employeeList = query.getResultList();
			
			Employee employee = null;
			if(employeeList != null && !employeeList.isEmpty()) {
				employee = employeeList.get(0);
			}
			
			return employee;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Employee> getEmployeesByDesignation(String designation) {
		try {
			String queryString = "from Employee e where e.designation = :designation";
			
			Query query = entityManager.createQuery(queryString);
			query.setParameter("designation", designation);
			List<Employee> employeeList = query.getResultList();
			
			return employeeList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Employee> getEmployeesByEmployeeIds(List<Integer> employeeIds) {
		try {
			String queryString = "from Employee e where e.employeeId in (?0)";
			
			Query query = entityManager.createQuery(queryString);
			query.setParameter(0, employeeIds);
			List<Employee> employeeList = query.getResultList();
			
			return employeeList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<String> getEmployeeNamesOrderBy(String orderBy) {
		try {
			String queryString = "select e.employeeName from Employee e";
			
			if(orderBy != null && orderBy.equalsIgnoreCase("desc")) {
				queryString = queryString + " order by e.employeeName desc";
			}else if(orderBy != null && orderBy.equalsIgnoreCase("asc")) {
				queryString = queryString + " order by e.employeeName asc";
			}
			
			Query query = entityManager.createQuery(queryString);
			List<String> employeeNameList = query.getResultList();
			
			return employeeNameList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Employee> getEmployees(Integer firstResult, Integer maxResult) {
		try {
			String queryString = "from Employee e";
			
			Query query = entityManager.createQuery(queryString);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			List<Employee> employeeList = query.getResultList();
			
			return employeeList;
		} catch (Exception e) {
			throw e;
		}
	}

}

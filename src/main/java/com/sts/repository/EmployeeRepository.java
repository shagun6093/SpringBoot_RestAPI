package com.sts.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.sts.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}

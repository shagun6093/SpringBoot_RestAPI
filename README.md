
# SpringBoot RestAPI.

This contains the REST API where all CRUD operations are performed on an employee database. The tech stack used includes Spring Boot and MySQL. You can test this API on Postman.

Operations 	API Path or URLs	What Action will it do ?
POST	/api/employees	Create a new Employee
GET	/api/employees	Retrieve all Employees
GET	/api/employee/:id	Retrieve a Employee by :empid
PUT	/api/employee/:id	Update a Employee by :empid
DELETE	/api/employee/:id	Delete a Employee by :empid
DELETE	/api/employees	Delete all Employees
GET	/api/employees?city=[keyword]	Find all Employee based on Employee City
GET	/api/employees?empAge=[keyword]	Find all Employe whose age > empAge

## 

Creating Employee Database :

```sql
CREATE DATABASE IF NOT EXISTS Emp_Crud;
USE Emp_Crud ;
drop table if exists Employee;
CREATE TABLE Employee ( empid bigint NOT NULL AUTO_INCREMENT, emp_name VARCHAR(50) DEFAULT NULL, emp_salary float DEFAULT NULL, emp_age integer DEFAULT NULL, emp_city VARCHAR(50) DEFAULT NULL, PRIMARY KEY (empid) );

USE Emp_Crud ;

select * from Employee;
```
## 

Operation : **POST**  
Url or API Path : /api/employees  
 Action : create new Employee

```java
@PostMapping("/employees")
	public String createNewEmployee(@RequestBody Employee employee) {
		System.out.println("Received Employee: " + employee);
		employeeRepository.save(employee);
		return "Employee created in the database";
	}
```

## 

Operation : **GET**  
Url or API Path : /api/employees   
Action : retrieve all Employees

```java
@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> empList = new ArrayList<>();
		employeeRepository.findAll().forEach(empList::add);
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}
```

## 

Operation : **GET**   
Url or API Path : /api/employees/:id  
Action : retrieve a Employee by :empid

```java
@GetMapping("/employees/{empid}")
	public ResponseEntity<Employee> getEmployeeId(@PathVariable long empid) {
		employeeRepository.findById(empid);
		Optional<Employee> emp = employeeRepository.findById(empid);
		if (emp.isPresent()) {
			return new ResponseEntity<Employee>(emp.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}
```
## 

Operation : **PUT**  
Url or API Path : /api/employees/:id  
Action : update a Employee by :empid

```java
@PutMapping("/employees/{empid}")
	public ResponseEntity<String> updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee) {
	    Optional<Employee> emp = employeeRepository.findById(empid);

	    if (emp.isPresent()) {
	        Employee existEmp = emp.get();
	        existEmp.setEmp_age(employee.getEmp_age());
	        existEmp.setEmp_city(employee.getEmp_city());
	        existEmp.setEmp_name(employee.getEmp_name());
	        existEmp.setEmp_salary(employee.getEmp_salary());
	        employeeRepository.save(existEmp);
	        return ResponseEntity.ok("Employee Details against Id " + empid + " updated");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Details do not exist for empid " + empid);
	    }
	}
```

## 

Operation : **DELETE**   
Url or API Path : /api/employees/:id  
Action : delete a Employee by :empid

```java
@DeleteMapping("/employees/{empid}")
	public String deleteEmployeeByEmpId(@PathVariable Long empid) {
		employeeRepository.deleteById(empid);
		return "Employee Deleted Sucessfully";
	}
```
## 

Operation : **DELETE**   
Url or API Path : /api/employees  
Action : delete all Employees

```java
@DeleteMapping("employees")
	public String deleteAllEmployee()
	{
		employeeRepository.deleteAll();
		return "Employee deleted Successfully...";
	}
```




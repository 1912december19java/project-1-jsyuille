package com.revature.repository;

import java.util.List;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface EmployeeDao {

  Employee get(int id);
  
  List<Employee> getAll();
  
  void save(Reimbursement r);
  
  void update(Employee e);
  
  public Employee validateLogin(Employee user, String username, String password);

 
}

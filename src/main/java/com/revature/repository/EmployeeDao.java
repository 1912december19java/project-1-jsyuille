package com.revature.repository;

import java.util.List;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface EmployeeDao {

  Employee get(int id);
  
  List<Employee> getAllEmployees();
  
  List<Reimbursement> getAllPending(String name, Boolean isManager);
  
  List<Reimbursement> getAllProcessed(String name, Boolean isManager);
  
  List<Reimbursement> getAll(String name);
  
  void save(Reimbursement r);
  
  void update(Employee e);
  
  public Employee validateLogin(String username, String password);
  
  void updateApprovalStatus(Reimbursement r);

 
}

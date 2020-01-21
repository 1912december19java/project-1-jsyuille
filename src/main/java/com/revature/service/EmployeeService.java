package com.revature.service;

import java.util.List;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repository.EmployeeDao;

public class EmployeeService {
  
  private EmployeeDao empDao;
  
private EmployeeDao employeeDao;
  
  public EmployeeService(EmployeeDao employeeDao) {
    this.employeeDao = employeeDao;
  }
  
  /**
   * Get a Employee by its id
   * @param id
   * @return
   */
  public Employee get(int id) {
    return employeeDao.get(id);
  }
  
  /**
   * Gets all Employees
   * @return
   */
  public List<Reimbursement> getAllPending(String name, Boolean isManager) {
    return employeeDao.getAllPending(name,isManager);
  }
  
  public List<Reimbursement> getAllProcessed(String name, Boolean isManager) {
    return employeeDao.getAllProcessed(name, isManager);
  }
  
  public void updateApprovalStatus(Reimbursement r) {
    employeeDao.updateApprovalStatus(r);
  }
  
  public List<Employee> getAllEmployees() {
    return employeeDao.getAllEmployees();
  }

  public List<Reimbursement> getAll(String str) {
    return employeeDao.getAll(str);
  }
  
  public void save (Reimbursement r) {
    employeeDao.save(r);
  }
  
  /**
   * Update an existing employee.  Works by id.
   * @param employee
   */
  public void update(Employee employee) {
    employeeDao.update(employee);
  }
  
  public Employee validaLogin(String u, String p) {
    return employeeDao.validateLogin(u, p);

}

}

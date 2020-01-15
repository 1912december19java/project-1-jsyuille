package com.revature.service;

import com.revature.model.Employee;
import com.revature.repository.EmployeeDao;

public class EmployeeService {
  
  private EmployeeDao empDao;
  
  public EmployeeService(EmployeeDao empDao) {
    this.empDao = empDao;
  }
      //Reimbursement instead?
//  public void save(Employee emp) {
//    empDao.save(emp);
//  }

}

package com.revature.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.repository.EmployeeDao;
import com.revature.repository.EmployeeDaoPostgres;
import com.revature.service.EmployeeService;

@WebServlet(name = "Employee", urlPatterns = {"/login/*"})
public class EmployeeServlet extends HttpServlet {

  private static ObjectMapper om = new ObjectMapper();
  
  @Override                                                            
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    
    System.out.println("In POST employee servlet");
    Employee employee = new Employee();
    EmployeeDao empDao = new EmployeeDaoPostgres();
    EmployeeService empService = new EmployeeService(empDao);
    
    employee = om.readValue(req.getReader(),Employee.class);
    
    System.out.println("Mapped out the request in POST");
    
    System.out.println("Employee object before validation: " + employee.toString());

    employee = empService.validaLogin(employee.getEmp_name(), employee.getEmp_password());
    System.out.println("After validation: " + employee.toString());
    
    if (employee.getEmp_position().equals("employee")) {
      resp.setStatus(206);
    }
    else if (employee.getEmp_position().equals("manager")) {
      resp.setStatus(200);
    }
    else {
      resp.setStatus(400);
    }
    
    HttpSession session = req.getSession(true);
    session.setAttribute("user", employee);
  }
  
  
}
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

@WebServlet(name = "UpdateInfo", urlPatterns = {"/updateInfo/*"})
public class ProfileUpdateServlet extends HttpServlet {

  private static ObjectMapper om = new ObjectMapper();
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Employee e;
    HttpSession session = req.getSession();
    
    resp.getWriter().write(session.getAttribute("user").toString());
  }
  
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("Inside UpdateInfo servlet");
    
    Employee emp_updatedInfo = new Employee();
    HttpSession session = req.getSession();
    EmployeeDao empDao = new EmployeeDaoPostgres();
    EmployeeService empService = new EmployeeService(empDao);
    
    System.out.println(session.getAttribute("user"));
    
    emp_updatedInfo = om.readValue(req.getReader(),Employee.class);

    System.out.println(emp_updatedInfo.toString());
    
    Employee emp_oldInfo = (Employee) session.getAttribute("user");
    emp_updatedInfo.setEmp_id(emp_oldInfo.getEmp_id());
    
    empService.update(emp_updatedInfo);
    session.setAttribute("user", emp_updatedInfo);
    
    resp.setStatus(200);
    
  }
}


//When the user is logged in, they do to the profile page, put in what they want their new password to be
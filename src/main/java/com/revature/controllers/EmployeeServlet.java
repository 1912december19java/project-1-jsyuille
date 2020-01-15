package com.revature.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.repository.EmployeeDao;
import com.revature.repository.EmployeeDaoPostgres;

@WebServlet(name = "Employee", urlPatterns = {"/employee"})
public class EmployeeServlet extends HttpServlet {

  @Override                                                              //CHANGED TO GET FOR TESTING
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("Employee servlet working");
    
    EmployeeDao empDao = new EmployeeDaoPostgres();
    
//    System.out.println(ed.save(r));
    System.out.println("Hello from employee servlet");
  }
  
  //Override service method to update
  
  @Override
  public void destroy() {
    System.out.println("Servlet Destroyed");
  }
  
}
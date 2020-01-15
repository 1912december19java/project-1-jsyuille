package com.revature.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.repository.EmployeeDaoPostgres;
import com.revature.service.EmployeeService;

@WebServlet (name="FrontController", urlPatterns= {"/api/*"})
public class FrontController extends HttpServlet {
  
  private EmployeeService empService;
  private ObjectMapper om;
  
  @Override
  public void init() throws ServletException {
    this.empService = new EmployeeService(new EmployeeDaoPostgres());
    this.om = new ObjectMapper();
    super.init();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
      System.out.println("URI: " + req.getRequestURI());
      String[] tokens = req.getRequestURI().split("/");
      
//      if(tokens[3].equals("employee")) {
//        List<Employee> emp = EmployeeService.getAll();
//        resp.getWriter().write(om.writeValueAsString(emp));
//      }
  }
}

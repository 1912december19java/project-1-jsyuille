package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repository.EmployeeDao;
import com.revature.repository.EmployeeDaoPostgres;
import com.revature.service.EmployeeService;

@WebServlet(name = "View", urlPatterns = {"/view/*"})
public class ViewListServlet extends HttpServlet {

  private static ObjectMapper om = new ObjectMapper();


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Employee> list = new ArrayList<Employee>();
    System.out.println("\nInside View GET servlet");

    EmployeeDao empDao = new EmployeeDaoPostgres();
    EmployeeService service = new EmployeeService(empDao);
    ObjectMapper om = new ObjectMapper();
    
 

    list = service.getAllEmployees();
    
    
    String jsonStr = om.writeValueAsString(list);
    System.out.println("Employee names list: " + list.toString());
    
    PrintWriter out = resp.getWriter();
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    out.print(jsonStr);
    out.flush();
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //Gets by name, returns everything
    
    List<Reimbursement> list = new ArrayList<Reimbursement>();
    System.out.println("\nInside View POST servlet");

    EmployeeDao empDao = new EmployeeDaoPostgres();
    EmployeeService service = new EmployeeService(empDao);
    ObjectMapper om = new ObjectMapper();
    
    Reimbursement re = om.readValue(req.getReader(), Reimbursement.class);
    System.out.println("reimburse Full: " + re.toString());
    System.out.println("emp name: " + re.getEmployeeName());
    list = service.getAll(re.getEmployeeName());
    
    String jsonStr = om.writeValueAsString(list);
    
    PrintWriter out = resp.getWriter();
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    out.print(jsonStr);
    out.flush();
  }
}

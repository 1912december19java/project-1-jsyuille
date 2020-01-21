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
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repository.EmployeeDao;
import com.revature.repository.EmployeeDaoPostgres;
import com.revature.service.EmployeeService;

@WebServlet(name = "ManagerReimbursements", urlPatterns = {"/managerReimburse/*"})
public class ManagerReimbursementServlet extends HttpServlet {

  private static ObjectMapper om = new ObjectMapper();


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Reimbursement> list = new ArrayList<Reimbursement>();
    System.out.println("\nInside Manager Pending GET servlet");

    EmployeeDao empDao = new EmployeeDaoPostgres();
    EmployeeService service = new EmployeeService(empDao);
    ObjectMapper om = new ObjectMapper();

    list = service.getAllPending(null, true);
    
    String jsonStr = om.writeValueAsString(list);
    System.out.println(list.toString());
    
    PrintWriter out = resp.getWriter();
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    out.print(jsonStr);
    out.flush();
    System.out.println("Sent response from Manager GET");
    
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Reimbursement> list = new ArrayList<Reimbursement>();
    System.out.println("\nInside Manager Processed GET servlet");

    EmployeeDao empDao = new EmployeeDaoPostgres();
    EmployeeService service = new EmployeeService(empDao);
    ObjectMapper om = new ObjectMapper();
 
    list = service.getAllProcessed(null, true);
    
    String jsonStr = om.writeValueAsString(list);
    System.out.println("Processed list: "  + list.toString());
    
    PrintWriter out = resp.getWriter();
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    out.print(jsonStr);
    out.flush();
    
    
  }
  
}

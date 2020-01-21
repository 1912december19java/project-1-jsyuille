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

@WebServlet(name = "Processed", urlPatterns = {"/processed/*"})
public class ProcessedReimbursementServlet extends HttpServlet {

  private static ObjectMapper om = new ObjectMapper();


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Reimbursement> list = new ArrayList<Reimbursement>();
    System.out.println("\nInside Processed GET servlet");

    EmployeeDao empDao = new EmployeeDaoPostgres();
    EmployeeService service = new EmployeeService(empDao);
    ObjectMapper om = new ObjectMapper();
    Employee emp = new Employee();
    
    HttpSession session = req.getSession();         //Gets the current session
    emp = (Employee) session.getAttribute("user");    //Gets current user information
    System.out.println("Getting employee set equal to session: " + emp.toString());
    list = service.getAllProcessed(emp.getEmp_name(), false);
    
    
    String jsonStr = om.writeValueAsString(list);
    System.out.println("Processed list: "  + list.toString());
    
    PrintWriter out = resp.getWriter();
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    out.print(jsonStr);
    out.flush();
    
    
    
  }
}

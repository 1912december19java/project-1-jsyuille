package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

@WebServlet(name = "Reimbursements", urlPatterns = {"/reimbursements/*"})
public class EmployeeReimbursementServlet extends HttpServlet {


  private static ObjectMapper om = new ObjectMapper();


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Reimbursement> list = new ArrayList<Reimbursement>();
    System.out.println("\nInside Reimbursement GET servlet");

    EmployeeDao empDao = new EmployeeDaoPostgres();
    EmployeeService service = new EmployeeService(empDao);
    ObjectMapper om = new ObjectMapper();
    
    HttpSession session = req.getSession();
    Employee e = (Employee) session.getAttribute("user");

    list = service.getAllPending(e.getEmp_name(), false);
    
//    Iterator<Reimbursement> myIterator = list.iterator();
    
//    while(myIterator.hasNext()) {
//        System.out.println(myIterator.next());
//         String jsonString;
//         om.writeValueAsString(myIterator.next());
//    }
    
    String jsonStr = om.writeValueAsString(list);
    System.out.println(list.toString());
    
    PrintWriter out = resp.getWriter();
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    out.print(jsonStr);
//    out.print("Hello");
    out.flush();
//    System.out.println(Arrays.toString(list.toArray()));
    
  }
  
  

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    System.out.println("\nIn POST Reinbursement servlet");
    Employee employee = new Employee();
    Reimbursement re = new Reimbursement();
    EmployeeDao empDao = new EmployeeDaoPostgres();
    EmployeeService service = new EmployeeService(empDao);
    HttpSession session = req.getSession();

    re = om.readValue(req.getReader(), Reimbursement.class);

    if (re.getAmount() == null || re.getAmount() == 0) {
      resp.setStatus(400);
    } else {

      System.out.println("Mapped out the request in POST");

      System.out.println("Reimbursement object before tied to employee: " + re.toString());
      employee = (Employee) session.getAttribute("user");
      re.setEmployeeName(employee.getEmp_name());

      System.out.println("Reimbursement object AFTER tied to employee: " + re.toString());

      service.save(re);

      // Where DAO function goes (Trying to save a new reimbursement to the database
      // employee = empService.validaLogin(employee.getEmp_name(), employee.getEmp_password());
      System.out.println("After validation: " + re.toString());
    }
  }
}

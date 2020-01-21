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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repository.EmployeeDao;
import com.revature.repository.EmployeeDaoPostgres;
import com.revature.service.EmployeeService;

@JsonFormat(shape=JsonFormat.Shape.ARRAY)
@WebServlet(name = "Approval", urlPatterns = {"/approval/*"})
public class ApprovalStatusServlet extends HttpServlet {

  private static ObjectMapper om = new ObjectMapper();


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {


    EmployeeDao empDao = new EmployeeDaoPostgres();
    EmployeeService service = new EmployeeService(empDao);
    Reimbursement re = new Reimbursement();
    HttpSession session = req.getSession();
    ObjectMapper om = new ObjectMapper();

    
    Employee employee = (Employee) session.getAttribute("user");
    re = om.readValue(req.getReader(), Reimbursement.class);

    re.setApprovedBy(employee.getEmp_name());
    service.updateApprovalStatus(re);

  }
}

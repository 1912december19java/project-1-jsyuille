package com.revature.repository;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class EmployeeDaoPostgres implements EmployeeDao {

  protected static Connection conn;



  static {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
    }
    try {
      System.out.println("Attempting to establish connection");
      conn = DriverManager.getConnection(System.getenv("connstring"), System.getenv("username"),
          System.getenv("password"));
    } catch (SQLException e) {
      System.out.println("Failed to connect to database");
    }
  }

  public void save(Reimbursement r) {
    PreparedStatement stmt = null;
    try {
      stmt = conn.prepareStatement(
          "INSERT INTO reimbursement_table(amount, status, approvedBy, emp_name) VALUES (?,?,?,?)");

      stmt.setDouble(1, r.getAmount());
      stmt.setString(2, r.getStatus());
      stmt.setString(3, r.getApprovedBy());
      stmt.setString(4, r.getEmployeeName());

      stmt.execute();
      System.out.println("The Reimbursement request was saved successfully!");

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Employee get(int id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Reimbursement> getAllPending(String name, Boolean isManager) {
    List<Reimbursement> allPending = new ArrayList<Reimbursement>();

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      if (isManager == false) {
      stmt = conn.prepareStatement("SELECT * FROM reimbursement_table WHERE status = 'pending' AND emp_name = ? ");
      stmt.setString(1, name);
      } else {
        stmt = conn.prepareStatement("SELECT * FROM reimbursement_table WHERE status = 'pending'");
      }
      
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        allPending.add(new Reimbursement(rs.getInt("id"), rs.getDouble("amount"),
            rs.getString("status"), rs.getString("approval"),rs.getString("approvedBy"), rs.getString("emp_name")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return allPending;
  }
  
  @Override
  public List<Reimbursement> getAllProcessed(String name, Boolean isManager) {
    List<Reimbursement> allProcessed = new ArrayList<Reimbursement>();

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      
      if (isManager == false) {
      stmt = conn.prepareStatement("SELECT * FROM reimbursement_table WHERE status = 'processed' AND emp_name = ? ");
      stmt.setString(1, name);
      }
      else {
        stmt = conn.prepareStatement("SELECT * FROM reimbursement_table WHERE status = 'processed'");
      }
      
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        allProcessed.add(new Reimbursement(rs.getInt("id"), rs.getDouble("amount"),
            rs.getString("status"), rs.getString("approval"), rs.getString("approvedBy"), rs.getString("emp_name")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println("In DAO: " + allProcessed.toString());
    return allProcessed;
  }

  @Override
  public void update(Employee emp) {
    PreparedStatement stmt = null;

    try {
      stmt = conn.prepareStatement(
          "UPDATE employee_table SET emp_name = ?, emp_password = ? WHERE id = ?");
      stmt.setString(1, emp.getEmp_name());
      stmt.setString(2, emp.getEmp_password());
      stmt.setInt(3, emp.getEmp_id());

      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public Employee validateLogin(String username, String password) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Employee employee = new Employee();
    boolean exists = false;

    System.out.println("Inside validate");
    try {
      System.out.println("Preparing to connect");
      System.out.println("username entered: " + username + " | Password entered: " + password);

      stmt = conn
          .prepareStatement("SELECT * FROM employee_table WHERE emp_name = ? AND emp_password = ?");

      System.out.println("Setting prepared statement fields");
      stmt.setString(1, username);
      stmt.setString(2, password);
      System.out.println("Set prepared statement, preparing to execute statement");

      if (stmt.execute()) {
        System.out.println("Statement executed, getting results");
        rs = stmt.getResultSet();
        System.out.println("Obtained result set, validating input");
      }
      while (rs.next()) {

        if (username.equals(rs.getString(2)) && password.equals(rs.getString(3))) {
          System.out.println("Input matches results from the database, copying results");
          employee = new Employee(rs.getInt("id"), rs.getString("emp_name"),
              rs.getString("emp_password"), rs.getString("emp_position"));
          System.out.println("Results copied, setting individual paramenters");
          employee.setEmp_id(rs.getInt("id"));
          employee.setEmp_name(rs.getString("emp_name"));
          employee.setEmp_password(rs.getString("emp_password"));
          employee.setEmp_position(rs.getString("emp_position"));
          exists = true;
          System.out.println("Done verifying.");
          break;
        }

      }

      if (exists == false) {
        System.out.println("Account does not exist");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return employee;

  }

  @Override
  public void updateApprovalStatus(Reimbursement r) {
    PreparedStatement stmt = null;

    try {
      stmt = conn.prepareStatement(
          "UPDATE reimbursement_table SET status = ?, approval = ?, approvedBy = ? WHERE id = ?");
      stmt.setString(1, r.getStatus());
      stmt.setString(2, r.getApproval());
      stmt.setString(3, r.getApprovedBy());
      stmt.setInt(4, r.getId());

      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }    
  }

  @Override
  public List<Employee> getAllEmployees() {
    List<Employee> allNames = new ArrayList<Employee>();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = conn.prepareStatement("SELECT emp_name FROM employee_table");
      
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        allNames.add(new Employee(rs.getString("emp_name")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return allNames;
  }

  @Override
  public List<Reimbursement> getAll(String name) {
    List<Reimbursement> all = new ArrayList<Reimbursement>();

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      
      stmt = conn.prepareStatement("SELECT * FROM reimbursement_table WHERE emp_name = ?");
      stmt.setString(1, name);
      
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        all.add(new Reimbursement(rs.getInt("id"), rs.getDouble("amount"),
            rs.getString("status"), rs.getString("approval"), rs.getString("approvedBy"), rs.getString("emp_name")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println("In DAO ListALL: " + all.toString());
    return all;
  }
  

}

package com.revature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class EmployeeDaoPostgres implements EmployeeDao {

  protected static Connection conn;
  
  static {
    try {
      conn = DriverManager.getConnection(System.getenv("connstring"), System.getenv("username"), System.getenv("password"));
    } catch (SQLException e) {
      System.out.println("Couldn't connect to database");
      e.printStackTrace();
    }
  }

  public void save(Reimbursement r) {
    PreparedStatement stmt = null;
    try {
      stmt = conn.prepareStatement(
          "INSERT INTO reimbursement_table(amount, status, approvedBy) VALUES (?,?,?)");

      stmt.setDouble(1, r.getAmount());
      stmt.setString(2, r.getStatus());
      stmt.setString(3, r.getApprovedBy());

      stmt.execute();
      
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
  public List<Employee> getAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void update(Employee e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Employee validateLogin(Employee employee, String username, String password) {
      PreparedStatement stmt = null;
      ResultSet rs = null;
      
      try {
        stmt = conn
            .prepareStatement("SELECT * FROM employee_table WHERE emp_name = ? AND emp_password = ?");
        stmt.setString(1, username);
        stmt.setString(2, password);
        if (stmt.execute()) {
          rs = stmt.getResultSet();
        }
        while (rs.next()) {

          if (username.equals(rs.getString(2)) && password.equals(rs.getString(3))) {
            employee = new Employee(rs.getInt("id"), rs.getString("emp_name"), rs.getString("emp_password"));

            employee.setEmp_id(rs.getInt("id"));
            employee.setEmp_name(rs.getString("user_name"));
            employee.setEmp_password(rs.getString("user_password"));

            //Redirect goes here?
            
            break;
          }

        }


      } catch (SQLException e) {
        e.printStackTrace();
      }

      return employee;

    }

}

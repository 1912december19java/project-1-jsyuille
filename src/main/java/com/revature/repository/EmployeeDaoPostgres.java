package com.revature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class EmployeeDaoPostgres implements EmployeeDao {

  protected static Connection conn;
  
  protected static void Connect () throws SQLException {
    conn = DriverManager.getConnection(System.getenv("connstring"), System.getenv("username"), System.getenv("password"));
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

}

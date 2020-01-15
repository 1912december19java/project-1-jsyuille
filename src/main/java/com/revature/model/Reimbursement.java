package com.revature.model;

public class Reimbursement {
  
  private int id;
  private Double amount;
  private String status;
  private String approvedBy;
  
  public Reimbursement() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getApprovedBy() {
    return approvedBy;
  }

  public void setApprovedBy(String approvedBy) {
    this.approvedBy = approvedBy;
  }

 

  
}

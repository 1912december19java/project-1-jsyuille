package com.revature.model;

public class Reimbursement {
  
  private int id;
  private Double amount;
  private String status;
  private String approval;
  private String approvedBy;
  private String employeeName;
  
  //TO DO:

//  private String proofPurchase
  
  public Reimbursement() {
    super();
  }

  public Reimbursement(int id, Double amount, String status, String approval, String approvedBy,
      String employeeName) {
    super();
    this.id = id;
    this.amount = amount;
    this.status = status;
    this.approval = approval;
    this.approvedBy = approvedBy;
    this.employeeName = employeeName;
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

  public String getApproval() {
    return approval;
  }

  public void setApproval(String approval) {
    this.approval = approval;
  }

  public String getApprovedBy() {
    return approvedBy;
  }

  public void setApprovedBy(String approvedBy) {
    this.approvedBy = approvedBy;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((amount == null) ? 0 : amount.hashCode());
    result = prime * result + ((approval == null) ? 0 : approval.hashCode());
    result = prime * result + ((approvedBy == null) ? 0 : approvedBy.hashCode());
    result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
    result = prime * result + id;
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Reimbursement other = (Reimbursement) obj;
    if (amount == null) {
      if (other.amount != null)
        return false;
    } else if (!amount.equals(other.amount))
      return false;
    if (approval == null) {
      if (other.approval != null)
        return false;
    } else if (!approval.equals(other.approval))
      return false;
    if (approvedBy == null) {
      if (other.approvedBy != null)
        return false;
    } else if (!approvedBy.equals(other.approvedBy))
      return false;
    if (employeeName == null) {
      if (other.employeeName != null)
        return false;
    } else if (!employeeName.equals(other.employeeName))
      return false;
    if (id != other.id)
      return false;
    if (status == null) {
      if (other.status != null)
        return false;
    } else if (!status.equals(other.status))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Reimbursement [id=" + id + ", amount=" + amount + ", status=" + status + ", approval="
        + approval + ", approvedBy=" + approvedBy + ", employeeName=" + employeeName + "]";
  }
  

  
}

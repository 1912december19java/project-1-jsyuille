package com.revature.model;

public class Employee {
  
  private Integer emp_id;
  private String emp_name;
  private String emp_password;
  
  
  public Employee() {
    super();
  }
  
  public Employee(int id, String name, String password) {
    
  }
  
  
  public Integer getEmp_id() {
    return emp_id;
  }
  public void setEmp_id(Integer emp_id) {
    this.emp_id = emp_id;
  }
  public String getEmp_name() {
    return emp_name;
  }
  public void setEmp_name(String emp_name) {
    this.emp_name = emp_name;
  }
  public String getEmp_password() {
    return emp_password;
  }
  public void setEmp_password(String emp_password) {
    this.emp_password = emp_password;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((emp_id == null) ? 0 : emp_id.hashCode());
    result = prime * result + ((emp_name == null) ? 0 : emp_name.hashCode());
    result = prime * result + ((emp_password == null) ? 0 : emp_password.hashCode());
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
    Employee other = (Employee) obj;
    if (emp_id == null) {
      if (other.emp_id != null)
        return false;
    } else if (!emp_id.equals(other.emp_id))
      return false;
    if (emp_name == null) {
      if (other.emp_name != null)
        return false;
    } else if (!emp_name.equals(other.emp_name))
      return false;
    if (emp_password == null) {
      if (other.emp_password != null)
        return false;
    } else if (!emp_password.equals(other.emp_password))
      return false;
    return true;
  }


  @Override
  public String toString() {
    return "Employee [emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_password="
        + emp_password + "]";
  }
  
  
}

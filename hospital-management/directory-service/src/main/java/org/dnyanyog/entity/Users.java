package org.dnyanyog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import org.dnyanyog.utility.CustomIdGenerator;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users {

  @Id @Column private String userId;

  @Column private String userName;

  @Column private String email;

  @Column private String mobileNumber;

  @Column private String password;

  @Column private String status;

  @Column private String userRole;

  public static final String ACTIVE = "ACTIVE";
  public static final String INACTIVE = "INACTIVE";

  public static Users getInstance() {
    return new Users();
  }

  public String getUserId() {
    return userId;
  }

  public Users setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public Users setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public Users setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public String getPassword() {
    return password;
  }

  public Users setPassword(String password) {
    this.password = password;
    return this;
  }

  public Users setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public Users setStatus(String status) {
    this.status = status;
    return this;
  }

  public String getUserRole() {
    return userRole;
  }

  public Users setUserRole(String userRole) {
    this.userRole = userRole;
    return this;
  }

  public Users build() {

    return Users.this;
  }

  @PrePersist
  private void generateUserId() {
    if (this.userId == null) {
      this.userId = CustomIdGenerator.generatePatientId();
    }
  }
}

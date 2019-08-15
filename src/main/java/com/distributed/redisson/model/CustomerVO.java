package com.distributed.redisson.model;

// Generated 2019-1-17 17:42:08 by Hibernate Tools 3.6.1
import java.math.BigDecimal;
import java.util.Date;

/**
 * CustomerVO generated by hbm2java
 */
public class CustomerVO  implements Cloneable {
  private static final long serialVersionUID = 154771812908497L;
  
  private Long customerId;
  
  private String gender;
  
  private Date birthdate;
  
  private Integer certiType;
  
  private String certiCode;

  private String firstName;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public Integer getCertiType() {
    return certiType;
  }

  public void setCertiType(Integer certiType) {
    this.certiType = certiType;
  }

  public String getCertiCode() {
    return certiCode;
  }

  public void setCertiCode(String certiCode) {
    this.certiCode = certiCode;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
}
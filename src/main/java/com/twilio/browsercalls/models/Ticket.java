package com.twilio.browsercalls.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tickets")
public class Ticket {
  @Id
  @GeneratedValue(generator = "sqlite")
  @TableGenerator(name = "sqlite", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "tableSeq")
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "description")
  private String description;

  @Column(name = "date")
  private Date date;

  public Ticket() {}

  public Ticket(String name, String phoneNumber, String description, Date date) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.description = description;
    this.date = date;
  }

  Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}

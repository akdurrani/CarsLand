package com.carsland.carslandrest.objects;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

//You will need to create a Java Object. Jersey uses these to contruct requests and responses.

public class Order {
  
  private int orderid;
  private String fname;
  private String lname;
  private String phone;
  private String email;
  private String date;
  private String delivery;
  private String addr;
  private String city;
  private String state;
  private String zip;
  private String country;
  private String cardname;
  private String lastFour;
  private float total;
  
  public String getFname() {
      return fname;
  }

  public String getLname() {
      return lname;
  }

  public int getOrderId() {
      return orderid;
  }
  
  public String getPhone() {
      return phone;
  }
  
  public String getemail() {
      return email;
  }
  
  public String getDate() {
      return date;
  }
  
  public String getDelivery() {
      return delivery;
  }
  
  public String getAddr() {
      return addr;
  }
  
  public String getCity() {
      return city;
  }
  
  public String getState() {
      return state;
  }
  
  public String getZip() {
      return zip;
  }
  
  public String getCountry() {
      return country;
  }
  
  public String getCardname() {
      return cardname;
  }
  
  public String getLastFour() {
      return lastFour;
  }
  
  public float getTotal() {
      return total;
  }
  
  public void setFname(String fname) {
      this.fname = fname;
  }

  public void setLname(String lname) {
      this.lname = lname;
  }

  public void setOrderId(int id) {
      this.orderid = id;
  }
  
  public void setPhone(String phone) {
      this.phone = phone;
  }
  
  public void setEmail(String email) {
      this.email = email;
  }
  
  public void setDate(String date) {
      this.date = date;
  }
  
  public void setDelivery(String delivery) {
      this.delivery = delivery;
  }
  
  public void setAddr(String addr) {
      this.addr = addr;
  }
  
  public void setCity(String city) {
      this.city = city;
  }
  
  public void setState(String state) {
      this.state = state;
  }
  
  public void setZip(String zip) {
      this.zip = zip;
  }
  
  public void setCountry(String country) {
      this.country = country;
  }
  
  public void setCardname(String cardname) {
      this.cardname = cardname;
  }
  
  public void setLastFour(String lastFour) {
      this.lastFour = lastFour;
  }
  
  public void setTotal(float total) {
      this.total = total;
  }
}

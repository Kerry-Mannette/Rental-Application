package com.example.rental_application;


public class CustomersView  {
int customerid;
String fname;
String lname;
String email;
String phone;
String address;

 public CustomersView(int customerid, String fname, String lname, String email, String phone, String address) {
 this.customerid = customerid;
 this.fname = fname;
 this.lname = lname;
 this.email = email;
 this.phone = phone;
 this.address = address;


 }

 public CustomersView() {

 }

 public int getCustomerid() {
  return customerid;
 }

 public void setCustomerid(int customerid) {
  this.customerid = customerid;
 }

 public String getFname() {
  return fname;
 }

 public void setFname(String fname) {
  this.fname = fname;
 }

 public String getLname() {
  return lname;
 }

 public void setLname(String lname) {
  this.lname = lname;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getPhone() {
  return phone;
 }

 public void setPhone(String phone) {
  this.phone = phone;
 }

 public String getAddress() {
  return address;
 }

 public void setAddress(String address) {
  this.address = address;
 }
}

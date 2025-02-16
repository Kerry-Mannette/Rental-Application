package com.example.rental_application;

public class RentalsView {
    int rentalid;
    String customerid;
    String toolid;
    String rentalstartdate;
    String rentalenddate;
    String totalcost;


    public RentalsView(int rentalid, String customerid, String toolid, String rentalstartdate, String rentalenddate, String totalcost) {
        this.rentalid = rentalid;
        this.customerid = customerid;
        this.toolid = toolid;
        this.rentalstartdate = rentalstartdate;
        this.rentalenddate = rentalenddate;
        this.totalcost = totalcost;
    }

    public RentalsView() {

    }

    public int getRentalid() {
        return rentalid;
    }

    public void setRentalid(int rentalid) {
        this.rentalid = rentalid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getToolid() {
        return toolid;
    }

    public void setToolid(String toolid) {
        this.toolid = toolid;
    }

    public String getRentalstartdate() {
        return rentalstartdate;
    }

    public void setRentalstartdate(String rentalstartdate) {
        this.rentalstartdate = rentalstartdate;
    }

    public String getRentalenddate() {
        return rentalenddate;
    }

    public void setRentalenddate(String rentalenddate) {
        this.rentalenddate = rentalenddate;
    }

    public String getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(String totalcost) {
        this.totalcost = totalcost;
    }
}

package com.example.rental_application;

public class Rentals {

    private String rentalId;
    private String customerId;
    private String toolId;
    private String RentalsStartDate;
    private String RentalsEndDate;
    private String TotalCost;

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
    }

    public String getRentalsStartDate() {
        return RentalsStartDate;
    }

    public void setRentalsStartDate(String rentalsStartDate) {
        RentalsStartDate = rentalsStartDate;
    }

    public String getRentalsEndDate() {
        return RentalsEndDate;
    }

    public void setRentalsEndDate(String rentalsEndDate) {
        RentalsEndDate = rentalsEndDate;
    }

    public String getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(String totalCost) {
        TotalCost = totalCost;
    }
}

package com.example.rental_application;

public class ToolsView {

    int toolid;
    String brand;
    String model;
    String year;
    String color;
    String rentalrateperday;

    public ToolsView(int toolid, String brand, String model, String year, String color, String rentalrateperday) {
        this.toolid = toolid;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.rentalrateperday = rentalrateperday;
    }

    public ToolsView() {

    }

    public int getToolid() {
        return toolid;
    }

    public void setToolid(int toolid) {
        this.toolid = toolid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRentalrateperday() {
        return rentalrateperday;
    }

    public void setRentalrateperday(String rentalrateperday) {
        this.rentalrateperday = rentalrateperday;
    }
}

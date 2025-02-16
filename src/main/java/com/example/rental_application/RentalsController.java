package com.example.rental_application;

public class RentalsController {

    private Rentals model;
    private RentalsView view;

    public RentalsController(Rentals model, RentalsView view){
        this.model = model;
        this.view = view;
    }

    public Rentals getModel() {
        return model;
    }

    public RentalsView getView() {
        return view;
    }
}

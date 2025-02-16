package com.example.rental_application;

public class CustomersController {
    private Customers model;
    private CustomersView view;

    public CustomersController(Customers model, CustomersView view) {
        this.model = model;
        this.view = view;
    }

    public Customers getModel() {
        return model;
    }

    public CustomersView getView() {
        return view;
    }
}
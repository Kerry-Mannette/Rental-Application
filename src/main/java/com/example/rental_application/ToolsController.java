package com.example.rental_application;

public class ToolsController {

    private Tools model;
    private ToolsView view;

    public ToolsController(Tools model, ToolsView view) {
        this.model = model;
        this.view = view;
    }

    public ToolsController(Rentals rentals, RentalsView rentalsview) {
    }

    public Tools getModel() {
        return model;
    }

    public ToolsView getView() {
        return view;
    }
}

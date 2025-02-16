package com.example.rental_application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
//
//import javax.swing.text.Element;
//import javax.swing.text.html.ImageView;
//import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;



public class RentalApplication extends Application {

    Pane pne_base, pne_toolds;
    MenuPane pne_menu;
    ManagementPane pne_management;
    SelectionPane pne_selection;

    CustomersCreatePane pne_customers_create;

    CustomersUpdatePane pne_customers_update;

    CustomersReadPane pne_customers_read;


    CustomersDeletePane pne_customers_delete;


    ToolsCreatePane pne_tools_create;

    ToolsUpdatePane pne_tools_update;

    ToolsReadPane pne_tools_read;

    ToolsDeletePane pne_tools_delete;


    RentalsCreatePane pne_rentals_create;

    RentalsUpdatePane pne_rentals_update;

    RentalsReadPane pne_rentals_read;

    RentalsDeletePane pne_rentals_delete;

    AvailabilityPane pne_availability;

    HelpPane pne_help;
    FooterPane pne_footer;

    public String pane_selection = "", pane_form_selection = "";


    private static final String URL = "jdbc:mysql://localhost:3306/rental_application?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void databaseConnection(){
        try (Connection connection = getConnection()) {

            if (connection != null) {
                System.out.println("Connect to the database!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void start(Stage stage) throws FileNotFoundException {

        pne_toolds = new StackPane();
        pne_menu = new MenuPane();
        pne_management = new ManagementPane();

        pne_selection =  new SelectionPane();

        pne_customers_create =  new CustomersCreatePane();

        pne_customers_update =  new CustomersUpdatePane();

        pne_customers_read =  new CustomersReadPane();

        pne_customers_delete = new CustomersDeletePane();



        pne_tools_create =  new ToolsCreatePane();

        pne_tools_update =  new ToolsUpdatePane();

        pne_tools_read =  new ToolsReadPane();

        pne_tools_delete = new ToolsDeletePane();




        pne_rentals_create =  new RentalsCreatePane();

        pne_rentals_update =  new RentalsUpdatePane();

        pne_rentals_read =  new RentalsReadPane();

        pne_rentals_delete = new RentalsDeletePane();

        pne_availability = new AvailabilityPane();

        pne_help = new HelpPane();
        pne_footer = new FooterPane();


        //Adding individual pages to the toold manager panel
        pne_toolds.getChildren().addAll(pne_availability,pne_rentals_delete,pne_rentals_update,pne_rentals_read,pne_rentals_create,pne_tools_delete,pne_tools_update,pne_tools_read,pne_tools_create,pne_customers_delete,pne_customers_update,pne_customers_read ,pne_customers_create,pne_selection,pne_help,pne_management);
        //Adding toold stack, menu, and footer panel
        pne_base = new VBox(7);
        pne_base.getChildren().addAll(pne_menu,pne_toolds,pne_footer);


        Scene scene = new Scene(pne_base, 1480, 785);

        scene.setFill(Color.DARKGRAY);

        FileInputStream inputStream  = new FileInputStream("Icons/Rent_And_Use_Icon.png");
        Image icon = new Image(inputStream);


        //Setting icon
        stage.getIcons().add(icon);
        //Setting stage title
        stage.setTitle("Rent & Use V1.0");
        //Adding scene to stage
        stage.setScene(scene);
        //Displaying the content of the stage
        stage.show();
    }



    private class MenuPane extends HBox{
        Text logo;
        Button btn_menu, btn_exit, btn_help;

        public MenuPane () {



            logo = new Text("Rent & Use");
            btn_help = new Button("Help");
            btn_menu = new Button("Menu");
            btn_exit = new Button("Exit");


            // Setting all nodes
            btn_help.setStyle("-fx-background-color: White;");
            btn_help.setFont(Font.font(null, FontWeight.BOLD, 14));
            btn_menu.setStyle("-fx-background-color: White;");
            btn_menu.setFont(Font.font(null, FontWeight.BOLD, 14));

            btn_exit.setStyle("-fx-background-color: White;");
            btn_exit.setFont(Font.font(null, FontWeight.BOLD, 14));
            logo.setFont(Font.font(null, FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 50));
            logo.setFill(Color.WHITE);



            btn_help.setMinHeight(40);
            btn_menu.setMinHeight(40);

            btn_help.setMinWidth(60);
            btn_menu.setMinWidth(100);

            btn_exit.setMinHeight(40);


            btn_exit.setMinWidth(80);


            btn_menu.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_menu.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_menu.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_menu.setStyle("-fx-background-color: White");
                }
            });

            btn_menu.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_menu.setStyle("-fx-background-color: White");
                    pne_selection.account_type.setValue("Select");

                    pne_customers_create.txt_fname.clear();
                    pne_customers_create.txt_lname.clear();
                    pne_customers_create.txt_address.clear();
                    pne_customers_create.txt_phone.clear();
                    pne_customers_create.txt_email.clear();

                    pne_customers_update.txt_customerID.clear();
                    pne_customers_update.txt_fname.clear();
                    pne_customers_update.txt_lname.clear();
                    pne_customers_update.txt_address.clear();
                    pne_customers_update.txt_phone.clear();
                    pne_customers_update.txt_email.clear();

                    pne_customers_delete.txt_customerId.clear();


                    pne_tools_create.txt_brand.clear();
                    pne_tools_create.txt_model.clear();
                    pne_tools_create.txt_color.clear();
                    pne_tools_create.txt_year.clear();
                    pne_tools_create.txt_rentalrateperday.clear();

                    pne_tools_update.txt_toolID.clear();
                    pne_tools_update.txt_brand.clear();
                    pne_tools_update.txt_model.clear();
                    pne_tools_update.txt_color.clear();
                    pne_tools_update.txt_year.clear();
                    pne_tools_update.txt_rentalrateperday.clear();

                    pne_tools_delete.txt_toolid.clear();



                    pne_rentals_create.txt_customerid.clear();
                    pne_rentals_create.txt_toolid.clear();
//                    pne_rentals_create.rentalstartdate.clear();
//                    pne_rentals_create.txt_rentalenddate.clear();
                    pne_rentals_create.txt_totalcost.clear();

                    pne_rentals_update.txt_rentalID.clear();
                    pne_rentals_update.txt_customerid.clear();
                    pne_rentals_update.txt_toolid.clear();
//                    pne_rentals_update.txt_rentalstartdate.clear();
//                    pne_rentals_update.txt_rentalenddate.clear();
                    pne_rentals_update.txt_totalcost.clear();

                    pne_rentals_delete.txt_rentalid.clear();


                    pne_toolds.getChildren().clear();
                    pne_toolds.getChildren().add(pne_management);

                }
            });



            btn_help.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_help.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_help.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_help.setStyle("-fx-background-color: White");

                    pne_toolds.getChildren().clear();
                    pne_toolds.getChildren().add(pne_help);

                }
            });


            btn_help.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_help.setStyle("-fx-background-color: White");
                }
            });


            btn_exit.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_exit.setStyle("-fx-background-color: LightBlue");

                }
            });


            btn_exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        Connection connection = getConnection();
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    System.exit(0);
                    btn_exit.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_exit.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_exit.setStyle("-fx-background-color: White");
                }
            });



            //Arranging all nodes
            this.setAlignment(Pos.TOP_RIGHT);
            this.setStyle("-fx-background-color: #87cefa");
            this.getChildren().addAll(logo, btn_menu, btn_exit, btn_help);
            this.setMargin(logo,new Insets(10,100,0,0));
            this.setMargin(btn_menu,new Insets(30,40,0,600));
            this.setMargin(btn_exit,new Insets(30,40,0,6));
            this.setMargin(btn_help,new Insets(30,40,0,6));
            this.setMinSize(90,100);
        }
    }


    private class ManagementPane extends VBox{
        Button btn_customers, btn_tools, btn_rentals, btn_availability;
        Label menu_title;


        public ManagementPane (){


            btn_customers = new Button("Customers");
            btn_tools = new Button("Tools");
            btn_rentals = new Button("Rentals");
            btn_availability = new Button("Availability");

            menu_title = new Label("Management Menu");


            //Setting all nodes

            btn_customers.setStyle("-fx-background-color: White;");
            btn_tools.setStyle("-fx-background-color: White;");
            btn_rentals.setStyle("-fx-background-color: White;");
            btn_availability.setStyle("-fx-background-color: White;");
            btn_customers.setFont(Font.font(null, FontWeight.BOLD, 14));
            btn_tools.setFont(Font.font(null, FontWeight.BOLD, 14));
            btn_rentals.setFont(Font.font(null, FontWeight.BOLD, 14));
            btn_availability.setFont(Font.font(null, FontWeight.BOLD, 14));
            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));
            menu_title.setTextFill(Color.WHITE);



            btn_customers.setMinHeight(40);
            btn_tools.setMinHeight(40);
            btn_rentals.setMinHeight(40);
            btn_availability.setMinHeight(40);


            btn_customers.setMinWidth(240);
            btn_tools.setMinWidth(240);
            btn_rentals.setMinWidth(240);
            btn_availability.setMinWidth(240);


            btn_customers.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_customers.setStyle("-fx-background-color: LightBlue");
                }
            });

            btn_customers.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_customers.setStyle("-fx-background-color: White");
                    pne_toolds.getChildren().clear();
                    pane_selection = "Customers";
                    pne_toolds.getChildren().add(pne_selection);
                }
            });

            btn_customers.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_customers.setStyle("-fx-background-color: White");
                }
            });


            btn_tools.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_tools.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_tools.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_tools.setStyle("-fx-background-color: White");
                    pne_toolds.getChildren().clear();
                    pane_selection = "Tools";
                    pne_toolds.getChildren().add(pne_selection);
                }
            });

            btn_tools.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_tools.setStyle("-fx-background-color: White");
                }
            });


            btn_rentals.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_rentals.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_rentals.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_rentals.setStyle("-fx-background-color: White");
                    pne_toolds.getChildren().clear();
                    pane_selection = "Rentals";
                    pne_toolds.getChildren().add(pne_selection);
                }
            });

            btn_rentals.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_rentals.setStyle("-fx-background-color: White");
                }
            });

            btn_availability.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_availability.setStyle("-fx-background-color: LightBlue");

                }
            });


            btn_availability.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_availability.setStyle("-fx-background-color: White");
                    pne_toolds.getChildren().clear();
                    pne_toolds.getChildren().add(pne_availability);


                }
            });

            btn_availability.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_availability.setStyle("-fx-background-color: White");
                }
            });



            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(0,0,10,0));
            this.setMargin(btn_customers, new Insets(0,0,10,0));
            this.setMargin(btn_tools, new Insets(0,0,10,0));
            this.setMargin(btn_rentals, new Insets(0,0,10,0));
            this.setMargin(btn_availability, new Insets(0,0,10,0));
            this.getChildren().addAll(menu_title,btn_customers,btn_tools,btn_rentals,btn_availability);
            this.setMinSize(90,620);
        }
    }


    private class SelectionPane extends VBox{
        ComboBox account_type;
        Label menu_title;
        String selection = "";

        public SelectionPane (){


            //Setting all nodes
            account_type = new ComboBox();
            account_type.setPromptText("Select");
            account_type.getItems().addAll("Create", "Read","Update", "Delete");
            account_type.setEditable(false);
            account_type.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object o, Object t1) {
                    selection = (String) t1;
                    if(selection == "Create" && pane_selection == "Customers"){
                        pne_toolds.getChildren().clear();

                        System.out.println("Customers");
                        pane_form_selection = "Customers Create";
//                        System.out.println(pane_form_selection);
                        pne_toolds.getChildren().add(pne_customers_create);
                    }else if(selection == "Create" && pane_selection == "Tools") {
                        pne_toolds.getChildren().clear();
                        System.out.println("Tools");
                        pane_form_selection = "Tools Create";
                        pne_toolds.getChildren().add(pne_tools_create);
                    }else if(selection == "Create" && pane_selection == "Rentals") {
                        pne_toolds.getChildren().clear();
                        System.out.println("Rentals");
                        pane_form_selection = "Rentals Create";
                        pne_toolds.getChildren().add(pne_rentals_create);
                    }else if(selection == "Read" && pane_selection == "Customers") {
                        pne_toolds.getChildren().clear();
                        pane_form_selection = "Customers Read";
                        pne_toolds.getChildren().add(pne_customers_read);
                    }else if(selection == "Read" && pane_selection == "Tools") {
                        pne_toolds.getChildren().clear();
                        pane_form_selection = "Tools Read";
                        pne_toolds.getChildren().add(pne_tools_read);
                    }else if(selection == "Read" && pane_selection == "Rentals") {
                        pne_toolds.getChildren().clear();
                        pane_form_selection = "Rentals Read";
                        pne_toolds.getChildren().add(pne_rentals_read);
                    }else if(selection == "Update" && pane_selection == "Customers") {
                        pne_toolds.getChildren().clear();
                        pane_form_selection = "Customers Update";
                        pne_toolds.getChildren().add(pne_customers_update);
                    }else if(selection == "Update" && pane_selection == "Tools") {
                        pne_toolds.getChildren().clear();
                        pane_form_selection = "Tools Update";
                        pne_toolds.getChildren().add(pne_tools_update);
                    }else if(selection == "Update" && pane_selection == "Rentals") {
                        pne_toolds.getChildren().clear();
                        pane_form_selection = "Rentals Update";
                        pne_toolds.getChildren().add(pne_rentals_update);
                    }else if(selection == "Delete" && pane_selection == "Customers") {
                        pne_toolds.getChildren().clear();
                        pane_form_selection = "Customers Delete";
                        pne_toolds.getChildren().add(pne_customers_delete);
                    }else if(selection == "Delete" && pane_selection == "Tools") {
                        pne_toolds.getChildren().clear();
                        pane_form_selection = "Tools Delete";
                        pne_toolds.getChildren().add(pne_tools_delete);
                    }else if(selection == "Delete" && pane_selection == "Rentals") {
                        pne_toolds.getChildren().clear();
                        pane_form_selection = "Rentals Delete";
                        pne_toolds.getChildren().add(pne_rentals_delete);
                    }

                }
            });



            account_type.setMinHeight(40);

            account_type.setMinWidth(200);

            account_type.setStyle("-fx-background-color:white");


            menu_title = new Label("Selection");

            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));
            account_type.setStyle("-fx-font-weight:Bold");

            menu_title.setTextFill(Color.WHITE);


            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(0,0,18,0));
            this.setMargin(account_type, new Insets(0,0,10,0));
            this.getChildren().addAll(menu_title,account_type);
            this.setMinSize(90,600);
        }
    }


    private class CustomersCreatePane extends VBox{
        Button btn_submit;
        Label menu_title, lbl_fname, lbl_lname, lbl_address, lbl_phone, lbl_email;
        TextField txt_fname, txt_lname, txt_address, txt_phone, txt_email;

        public CustomersCreatePane (){

            lbl_fname = new Label("First Name");
            txt_fname = new TextField();

            lbl_lname = new Label("Last Name");
            txt_lname = new TextField();

            lbl_email = new Label("Email");
            txt_email = new TextField();

            lbl_phone = new Label("Phone");
            txt_phone = new TextField();

            lbl_address = new Label("Address");
            txt_address = new TextField();





            menu_title = new Label("Customer Create");

            btn_submit = new Button("Submit");


            //Setting all nodes

            txt_fname.setMaxWidth(280);
            txt_fname.setMinHeight(40);

            txt_lname.setMaxWidth(280);
            txt_lname.setMinHeight(40);

            txt_address.setMaxWidth(280);
            txt_address.setMinHeight(40);

            txt_phone.setMaxWidth(280);
            txt_phone.setMinHeight(40);

            txt_email.setMaxWidth(280);
            txt_email.setMinHeight(40);

            btn_submit.setMaxWidth(80);
            btn_submit.setMinHeight(40);


            txt_fname.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_fname.setMaxWidth(340);

                }
            });


            txt_fname.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_fname.setMaxWidth(280);
                }
            });


            txt_lname.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_lname.setMaxWidth(340);

                }
            });


            txt_lname.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_lname.setMaxWidth(280);
                }
            });


            txt_address.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_address.setMaxWidth(340);

                }
            });


            txt_address.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_address.setMaxWidth(280);
                }
            });



            txt_phone.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_phone.setMaxWidth(340);

                }
            });


            txt_phone.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_phone.setMaxWidth(280);
                }
            });



            txt_email.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_email.setMaxWidth(340);

                }
            });


            txt_email.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_email.setMaxWidth(280);
                }
            });


            btn_submit.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String fname = txt_fname.getText();
                    String lname = txt_lname.getText();
                    String address = txt_address.getText();
                    String phone = txt_phone.getText();
                    String email = txt_email.getText();

                    if (!fname.equals("") & !lname.equals("") & !address.equals("") & !phone.equals("") & !email.equals("")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You are about to create an account for "+fname+ " "+lname);
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.isPresent() && result.get() == ButtonType.OK){

                            Customers customers = new Customers();
                            customers.setFname(fname);
                            customers.setLname(lname);
                            customers.setEmail(email);
                            customers.setPhone(phone);
                            customers.setAddress(address);

                            final String INSERT_CUSTOMERS_SQL = "INSERT INTO customers (FirstName, LastName, Email, Phone, Address) VALUES (?,?,?,?,?);";


                            try (Connection connection = getConnection();
                                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_SQL)) {


                                // For example, employee ID
                                preparedStatement.setString(1, customers.getFname());   // Employee name
                                preparedStatement.setString(2, customers.getLname());
                                preparedStatement.setString(3, customers.getAddress());   // Employee name
                                preparedStatement.setString(4, customers.getPhone());
                                preparedStatement.setString(5, customers.getAddress());   // Employee name


                                preparedStatement.executeUpdate();

                                System.out.println("Records inserted successfully.");


                            } catch (SQLException e) {
                                e.printStackTrace();
                            }


                            pne_toolds.getChildren().clear();
                            pne_toolds.getChildren().add(pne_management);
                        }
                    }else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Please make sure all text field are filled." );
                        Optional<ButtonType> result = alert.showAndWait();
                    }

                }
            });

            btn_submit.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: White");
                }
            });


            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));

            lbl_fname.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_fname.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_lname.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_lname.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_address.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_address.setFont(Font.font(null, FontWeight.BOLD, 14));


            lbl_phone.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_phone.setFont(Font.font(null, FontWeight.BOLD, 14));


            lbl_email.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_email.setFont(Font.font(null, FontWeight.BOLD, 14));


            btn_submit.setFont(Font.font(null, FontWeight.BOLD, 14));

            menu_title.setTextFill(Color.WHITE);

            lbl_fname.setTextFill(Color.WHITE);
            lbl_lname.setTextFill(Color.WHITE);
            lbl_address.setTextFill(Color.WHITE);
            lbl_phone.setTextFill(Color.WHITE);


            lbl_email.setTextFill(Color.WHITE);

            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(20,0,16,0));
            this.setMargin(btn_submit, new Insets(20,0,25,0));
            this.getChildren().addAll(menu_title, lbl_fname,txt_fname,lbl_lname,txt_lname,lbl_address,txt_address,lbl_phone,txt_phone,lbl_email,txt_email,btn_submit);
            this.setMinSize(90,600);
        }
    }


    public class CustomersReadPane extends VBox{
        Button btn_generate;
        Label menu_title;
        public CustomersReadPane (){




            menu_title = new Label("Customer Read");


            btn_generate = new Button("Generate");


            //Setting all nodes

            btn_generate.setMaxWidth(120);
            btn_generate.setMinHeight(40);


            TableView <CustomersView> table = new TableView();
            table.setEditable(true);


            table.setMaxHeight(400);
            table.setMaxWidth(960);


            TableColumn customerIdCol = new TableColumn("Customer ID");
            customerIdCol.setMinWidth(160);

            TableColumn fnameCol = new TableColumn("First Name");
            fnameCol.setMinWidth(160);

            TableColumn lnameCol = new TableColumn("Last Name");
            lnameCol.setMinWidth(160);


            TableColumn emailCol = new TableColumn("Email");
            emailCol.setMinWidth(160);

            TableColumn phoneCol = new TableColumn("Phone");
            phoneCol.setMinWidth(160);


            TableColumn addressCol = new TableColumn("Address");
            addressCol.setMinWidth(160);


            customerIdCol.setCellValueFactory(
                    new PropertyValueFactory<CustomersView, String>("customerid")
            );


            fnameCol.setCellValueFactory(
                    new PropertyValueFactory<CustomersView, String>("fname")
            );

            lnameCol.setCellValueFactory(
                    new PropertyValueFactory<CustomersView, String>("lname")
            );


            emailCol.setCellValueFactory(
                    new PropertyValueFactory<CustomersView, String>("email")
            );


            phoneCol.setCellValueFactory(
                    new PropertyValueFactory<CustomersView, String>("phone")
            );


            addressCol.setCellValueFactory(
                    new PropertyValueFactory<CustomersView, String>("address")
            );




            table.getColumns().addAll(customerIdCol,fnameCol,lnameCol,emailCol,phoneCol,addressCol);



            btn_generate.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_generate.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_generate.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_generate.setStyle("-fx-background-color: White");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You are about to generate all customers." );
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.isPresent() && result.get() == ButtonType.OK) {

                        final String SELECT_ALL_CUSTOMERS_SQL = "SELECT * FROM customers;";


                        try (Connection connection = getConnection();
                             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS_SQL)) {
                            ResultSet resultSet = preparedStatement.executeQuery();

                            ObservableList<CustomersView> data = FXCollections.observableArrayList();

                            while(resultSet.next()){
                                int id = resultSet.getInt("CustomerID");
                                String fname = resultSet.getString("FirstName");
                                String lname = resultSet.getString("LastName");
                                String email = resultSet.getString("Email");
                                String phone = resultSet.getString("Phone");
                                String address = resultSet.getString("Address");

                                Customers customers = new Customers();
                                CustomersView customersview = new CustomersView();
                                CustomersController view = new CustomersController(customers, customersview);
//

                                view.getView().setCustomerid(id);
                                view.getView().setFname(fname);
                                view.getView().setLname(lname);
                                view.getView().setEmail(email);
                                view.getView().setPhone(phone);
                                view.getView().setAddress(address);

                                data.add(new CustomersView(view.getView().getCustomerid(),view.getView().getFname(),view.getView().getLname(),view.getView().getEmail(),view.getView().getPhone(),view.getView().getAddress()));

                                table.setItems(data);

                                System.out.println("ID: "+id+" First Name: "+fname+" Last Name: "+lname+ "Email: "+email+ " Phone: "+phone+ " Address: "+address);

                            }


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }

                }


            });

            btn_generate.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_generate.setStyle("-fx-background-color: White");
                }
            });




            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));
            btn_generate.setFont(Font.font(null, FontWeight.BOLD, 16));
            menu_title.setTextFill(Color.WHITE);

            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(20,0,16,0));
            this.setMargin(btn_generate, new Insets(20,0,25,0));
            this.getChildren().addAll(menu_title,table,btn_generate);
            this.setMinSize(90,600);
        }
    }


    private class CustomersUpdatePane extends VBox{
        Button btn_submit;
        Label menu_title, lbl_customerID, lbl_fname, lbl_lname, lbl_address, lbl_phone, lbl_email;
        TextField txt_fname, txt_customerID, txt_lname, txt_address, txt_phone, txt_email;

        public CustomersUpdatePane (){

            lbl_customerID = new Label("Customer ID");
            txt_customerID = new TextField();

            lbl_fname = new Label("First Name");
            txt_fname = new TextField();

            lbl_lname = new Label("Last Name");
            txt_lname = new TextField();

            lbl_email = new Label("Email");
            txt_email = new TextField();

            lbl_phone = new Label("Phone");
            txt_phone = new TextField();

            lbl_address = new Label("Address");
            txt_address = new TextField();





            menu_title = new Label("Customer Update");

            btn_submit = new Button("Submit");


            //Setting all nodes

            txt_customerID.setMaxWidth(280);
            txt_customerID.setMinHeight(40);

            txt_fname.setMaxWidth(280);
            txt_fname.setMinHeight(40);

            txt_lname.setMaxWidth(280);
            txt_lname.setMinHeight(40);

            txt_address.setMaxWidth(280);
            txt_address.setMinHeight(40);

            txt_phone.setMaxWidth(280);
            txt_phone.setMinHeight(40);

            txt_email.setMaxWidth(280);
            txt_email.setMinHeight(40);

            btn_submit.setMaxWidth(80);
            btn_submit.setMinHeight(40);


            txt_customerID.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_customerID.setMaxWidth(340);

                }
            });


            txt_customerID.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_customerID.setMaxWidth(280);
                }
            });



            txt_fname.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_fname.setMaxWidth(340);

                }
            });


            txt_fname.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_fname.setMaxWidth(280);
                }
            });


            txt_lname.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_lname.setMaxWidth(340);

                }
            });


            txt_lname.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_lname.setMaxWidth(280);
                }
            });


            txt_address.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_address.setMaxWidth(340);

                }
            });


            txt_address.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_address.setMaxWidth(280);
                }
            });



            txt_phone.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_phone.setMaxWidth(340);

                }
            });


            txt_phone.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_phone.setMaxWidth(280);
                }
            });



            txt_email.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_email.setMaxWidth(340);

                }
            });


            txt_email.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_email.setMaxWidth(280);
                }
            });


            btn_submit.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String customerid = txt_customerID.getText();
                    String fname = txt_fname.getText();
                    String lname = txt_lname.getText();
                    String address = txt_address.getText();
                    String phone = txt_phone.getText();
                    String email = txt_email.getText();

                    if (!fname.equals("") & !lname.equals("") & !address.equals("") & !phone.equals("") & !email.equals("")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You are about to update an account for "+fname+ " "+lname);
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.isPresent() && result.get() == ButtonType.OK){

                            final String UPDATE_CUSTOMERS_SQL = "UPDATE customers SET FirstName = ?, LastName = ?, Email = ?, Phone = ?, Address = ? WHERE CustomerID = ? ;";


                            try (Connection connection = getConnection();
                                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMERS_SQL)) {

                                preparedStatement.setString(1, fname);   // Employee name
                                preparedStatement.setString(2, lname);
                                preparedStatement.setString(3, email);   // Employee name
                                preparedStatement.setString(4, phone);
                                preparedStatement.setString(5, address);
                                preparedStatement.setString(6, customerid);

                                preparedStatement.executeUpdate();

                                System.out.println("Records update successfully.");

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            pne_toolds.getChildren().clear();
                            pne_toolds.getChildren().add(pne_management);
                        }
                    }else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Please make sure all text field are filled." );
                        Optional<ButtonType> result = alert.showAndWait();
                    }

                }
            });

            btn_submit.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: White");
                }
            });


            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));

            lbl_customerID.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_customerID.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_fname.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_fname.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_lname.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_lname.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_address.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_address.setFont(Font.font(null, FontWeight.BOLD, 14));


            lbl_phone.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_phone.setFont(Font.font(null, FontWeight.BOLD, 14));


            lbl_email.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_email.setFont(Font.font(null, FontWeight.BOLD, 14));


            btn_submit.setFont(Font.font(null, FontWeight.BOLD, 14));

            menu_title.setTextFill(Color.WHITE);

            lbl_customerID.setTextFill(Color.WHITE);
            lbl_fname.setTextFill(Color.WHITE);
            lbl_lname.setTextFill(Color.WHITE);
            lbl_address.setTextFill(Color.WHITE);
            lbl_phone.setTextFill(Color.WHITE);


            lbl_email.setTextFill(Color.WHITE);

            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(20,0,16,0));
            this.setMargin(btn_submit, new Insets(20,0,25,0));
            this.getChildren().addAll(menu_title,lbl_customerID, txt_customerID, lbl_fname,txt_fname,lbl_lname,txt_lname,lbl_address,txt_address,lbl_phone,txt_phone,lbl_email,txt_email,btn_submit);
            this.setMinSize(90,600);
        }
    }


    private class CustomersDeletePane extends VBox{
        Button btn_submit;
        Label menu_title, lbl_customerId;
        TextField txt_customerId;

        public CustomersDeletePane (){

            lbl_customerId = new Label("Customer ID");
            txt_customerId = new TextField();



            menu_title = new Label("Customer Delete");

            btn_submit = new Button("Submit");


            //Setting all nodes

            txt_customerId.setMaxWidth(280);
            txt_customerId.setMinHeight(40);


            btn_submit.setMaxWidth(80);
            btn_submit.setMinHeight(40);


            txt_customerId.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_customerId.setMaxWidth(340);

                }
            });


            txt_customerId.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_customerId.setMaxWidth(280);
                }
            });


            btn_submit.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String customerId = txt_customerId.getText();


                    if (!customerId.equals("")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You are about to delete customer: "+customerId);
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.isPresent() && result.get() == ButtonType.OK){

                            final String DELETE_CUSTOMERS_SQL = "DELETE FROM customers WHERE CustomerID = ?;";


                            try (Connection connection = getConnection();
                                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMERS_SQL)) {

                                preparedStatement.setInt(1, Integer.parseInt(customerId));        // For example, employee ID


                                preparedStatement.executeUpdate();

                                System.out.println("Records delete successfully.");

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }


                            pne_toolds.getChildren().clear();
                            pne_toolds.getChildren().add(pne_management);
                        }
                    }else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Please make sure all text field are filled." );
                        Optional<ButtonType> result = alert.showAndWait();
                    }

                }
            });

            btn_submit.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: White");
                }
            });


            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));

            lbl_customerId.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_customerId.setFont(Font.font(null, FontWeight.BOLD, 14));

            btn_submit.setFont(Font.font(null, FontWeight.BOLD, 14));

            menu_title.setTextFill(Color.WHITE);

            lbl_customerId.setTextFill(Color.WHITE);


            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(20,0,16,0));
            this.setMargin(btn_submit, new Insets(20,0,25,0));
            this.getChildren().addAll(menu_title, lbl_customerId,txt_customerId,btn_submit);
            this.setMinSize(90,600);
        }
    }


    private class ToolsCreatePane extends VBox{
        Button btn_submit;
        Label menu_title, lbl_brand, lbl_model, lbl_rentalrateperday, lbl_color, lbl_year;
        TextField txt_brand, txt_model, txt_rentalrateperday, txt_color, txt_year;

        public ToolsCreatePane (){

            lbl_brand = new Label("Brand");
            txt_brand = new TextField();

            lbl_model = new Label("Model");
            txt_model = new TextField();

            lbl_year = new Label("Year");
            txt_year = new TextField();

            lbl_color = new Label("Color");
            txt_color = new TextField();

            lbl_rentalrateperday = new Label("Rental Rate Per Day");
            txt_rentalrateperday = new TextField();





            menu_title = new Label("Tool Create");

            btn_submit = new Button("Submit");


            //Setting all nodes

            txt_brand.setMaxWidth(280);
            txt_brand.setMinHeight(40);

            txt_model.setMaxWidth(280);
            txt_model.setMinHeight(40);

            txt_rentalrateperday.setMaxWidth(280);
            txt_rentalrateperday.setMinHeight(40);

            txt_color.setMaxWidth(280);
            txt_color.setMinHeight(40);

            txt_year.setMaxWidth(280);
            txt_year.setMinHeight(40);

            btn_submit.setMaxWidth(80);
            btn_submit.setMinHeight(40);


            txt_brand.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_brand.setMaxWidth(340);

                }
            });


            txt_brand.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_brand.setMaxWidth(280);
                }
            });


            txt_model.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_model.setMaxWidth(340);

                }
            });


            txt_model.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_model.setMaxWidth(280);
                }
            });


            txt_rentalrateperday.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_rentalrateperday.setMaxWidth(340);

                }
            });


            txt_rentalrateperday.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_rentalrateperday.setMaxWidth(280);
                }
            });



            txt_color.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_color.setMaxWidth(340);

                }
            });


            txt_color.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_color.setMaxWidth(280);
                }
            });



            txt_year.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_year.setMaxWidth(340);

                }
            });


            txt_year.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_year.setMaxWidth(280);
                }
            });


            btn_submit.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String brand = txt_brand.getText();
                    String model = txt_model.getText();
                    String year = txt_year.getText();
                    String color = txt_color.getText();

                    String rentalrateperday = txt_rentalrateperday.getText();
                    if (!brand.equals("") & !model.equals("") & !year.equals("") &!color.equals("") & !color.equals("")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Brand: " +brand+" was created successfully.");
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.isPresent() && result.get() == ButtonType.OK){
                            Tools tools = new Tools();
                            tools.setBrand(brand);
                            tools.setModel(model);
                            tools.setYear(year);
                            tools.setColor(color);
                            tools.setRentalRatePerDay(rentalrateperday);

                            final String INSERT_CARS_SQL = "INSERT INTO tools (Brand, Model, Year, color, RentalRatePerDay, Availability) VALUES (?,?,?,?,?,?);";


                            try (Connection connection = getConnection();
                                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARS_SQL)) {


                                // For example, employee ID
                                preparedStatement.setString(1, tools.getBrand());   // Employee name
                                preparedStatement.setString(2, tools.getModel());
                                preparedStatement.setString(3, tools.getYear());   // Employee name
                                preparedStatement.setString(4, tools.getColor());
                                preparedStatement.setString(5, tools.getRentalRatePerDay());
                                preparedStatement.setInt(6, 1);


                                preparedStatement.executeUpdate();

                                System.out.println("Records inserted successfully.");


                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            pne_toolds.getChildren().clear();
                            pne_toolds.getChildren().add(pne_management);
                        }
                    }else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Please make sure all text field are filled." );
                        Optional<ButtonType> result = alert.showAndWait();
                    }

                }
            });

            btn_submit.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: White");
                }
            });


            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));

            lbl_brand.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_brand.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_model.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_model.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_rentalrateperday.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_rentalrateperday.setFont(Font.font(null, FontWeight.BOLD, 14));


            lbl_color.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_color.setFont(Font.font(null, FontWeight.BOLD, 14));


            lbl_year.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_year.setFont(Font.font(null, FontWeight.BOLD, 14));


            btn_submit.setFont(Font.font(null, FontWeight.BOLD, 14));

            menu_title.setTextFill(Color.WHITE);

            lbl_brand.setTextFill(Color.WHITE);
            lbl_model.setTextFill(Color.WHITE);
            lbl_rentalrateperday.setTextFill(Color.WHITE);
            lbl_color.setTextFill(Color.WHITE);


            lbl_year.setTextFill(Color.WHITE);

            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(20,0,16,0));
            this.setMargin(btn_submit, new Insets(20,0,25,0));
            this.getChildren().addAll(menu_title, lbl_brand,txt_brand,lbl_model,txt_model,lbl_color,txt_color,lbl_year,txt_year,lbl_rentalrateperday,txt_rentalrateperday,btn_submit);
            this.setMinSize(90,600);
        }
    }


    public class ToolsReadPane extends VBox{
        Button btn_generate;
        Label menu_title;
        public ToolsReadPane (){




            menu_title = new Label("Tool Read");


            btn_generate = new Button("Generate");


            //Setting all nodes

            btn_generate.setMaxWidth(120);
            btn_generate.setMinHeight(40);


            TableView <ToolsView> table = new TableView();
            table.setEditable(true);


            table.setMaxHeight(400);
            table.setMaxWidth(960);


            TableColumn toolidCol = new TableColumn("Tool ID");
            toolidCol.setMinWidth(160);

            TableColumn brandCol = new TableColumn("Brand");
            brandCol.setMinWidth(160);

            TableColumn modelCol = new TableColumn("Model");
            modelCol.setMinWidth(160);


            TableColumn yearCol = new TableColumn("Year");
            yearCol.setMinWidth(160);

            TableColumn colorCol = new TableColumn("Color");
            colorCol.setMinWidth(160);


            TableColumn rentalratederdayCol = new TableColumn("RentalRatePerday");
            rentalratederdayCol.setMinWidth(160);



            toolidCol.setCellValueFactory(
                    new PropertyValueFactory<ToolsView, String>("toolid")
            );


            brandCol.setCellValueFactory(
                    new PropertyValueFactory<ToolsView, String>("brand")
            );

            modelCol.setCellValueFactory(
                    new PropertyValueFactory<ToolsView, String>("model")
            );


            yearCol.setCellValueFactory(
                    new PropertyValueFactory<ToolsView, String>("year")
            );


            colorCol.setCellValueFactory(
                    new PropertyValueFactory<ToolsView, String>("color")
            );

            rentalratederdayCol.setCellValueFactory(
                    new PropertyValueFactory<ToolsView, String>("rentalrateperday")
            );



            table.getColumns().addAll(toolidCol,brandCol, modelCol,yearCol, colorCol,rentalratederdayCol);



            btn_generate.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_generate.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_generate.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_generate.setStyle("-fx-background-color: White");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You are about to generate all tools." );
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.isPresent() && result.get() == ButtonType.OK) {

                        final String SELECT_ALL_CARS_SQL = "SELECT * FROM tools;";


                        try (Connection connection = getConnection();
                             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CARS_SQL)) {
                            ResultSet resultSet = preparedStatement.executeQuery();

                            ObservableList<ToolsView> data = FXCollections.observableArrayList();

                            while(resultSet.next()){
                                int id = resultSet.getInt("ToolID");
                                String brand = resultSet.getString("Brand");
                                String model = resultSet.getString("Model");
                                String year = resultSet.getString("Year");
                                String color = resultSet.getString("Color");
                                String rentalrateperday = resultSet.getString("RentalRatePerDay");


                                Tools tools = new Tools();
                                ToolsView toolsview = new ToolsView();
                                ToolsController view = new ToolsController(tools, toolsview);

                                view.getView().setToolid(id);
                                view.getView().setBrand(brand);
                                view.getView().setModel(model);
                                view.getView().setYear(year);
                                view.getView().setColor(color);
                                view.getView().setRentalrateperday(rentalrateperday);

                                data.add(new ToolsView(view.getView().getToolid(),view.getView().getBrand(),view.getView().getModel(),view.getView().getYear(),view.getView().getColor(),view.getView().getRentalrateperday()));

                                table.setItems(data);

                                System.out.println("ID: "+id+" Brand: "+brand+" Model: "+model+ "Year: "+year+ " Color: "+color+ " RentalRatePerDay: "+rentalrateperday);
                            }


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }

                }


            });

            btn_generate.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_generate.setStyle("-fx-background-color: White");
                }
            });




            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));
            btn_generate.setFont(Font.font(null, FontWeight.BOLD, 16));
            menu_title.setTextFill(Color.WHITE);

            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(20,0,16,0));
            this.setMargin(btn_generate, new Insets(20,0,25,0));
            this.getChildren().addAll(menu_title,table,btn_generate);
            this.setMinSize(90,600);
        }
    }


    private class ToolsUpdatePane extends VBox{
        Button btn_submit;
        Label menu_title, lbl_toolID,lbl_brand, lbl_model, lbl_rentalrateperday, lbl_color, lbl_year;
        TextField txt_brand, txt_toolID, txt_model, txt_rentalrateperday, txt_color, txt_year;

        public ToolsUpdatePane (){

            lbl_toolID = new Label("Tool ID");
            txt_toolID = new TextField();

            lbl_brand = new Label("Brand");
            txt_brand = new TextField();

            lbl_model = new Label("Model");
            txt_model = new TextField();

            lbl_year = new Label("Year");
            txt_year = new TextField();

            lbl_color = new Label("Color");
            txt_color = new TextField();

            lbl_rentalrateperday = new Label("Rental Rate Per Day");
            txt_rentalrateperday = new TextField();


            menu_title = new Label("Tool Update");

            btn_submit = new Button("Submit");


            //Setting all nodes

            txt_toolID.setMaxWidth(280);
            txt_toolID.setMinHeight(40);

            txt_brand.setMaxWidth(280);
            txt_brand.setMinHeight(40);

            txt_model.setMaxWidth(280);
            txt_model.setMinHeight(40);

            txt_rentalrateperday.setMaxWidth(280);
            txt_rentalrateperday.setMinHeight(40);

            txt_color.setMaxWidth(280);
            txt_color.setMinHeight(40);

            txt_year.setMaxWidth(280);
            txt_year.setMinHeight(40);

            btn_submit.setMaxWidth(80);
            btn_submit.setMinHeight(40);



            txt_toolID.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_toolID.setMaxWidth(340);

                }
            });


            txt_toolID.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_toolID.setMaxWidth(280);
                }
            });


            txt_brand.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_brand.setMaxWidth(340);

                }
            });


            txt_brand.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_brand.setMaxWidth(280);
                }
            });


            txt_model.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_model.setMaxWidth(340);

                }
            });


            txt_model.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_model.setMaxWidth(280);
                }
            });


            txt_rentalrateperday.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_rentalrateperday.setMaxWidth(340);

                }
            });


            txt_rentalrateperday.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_rentalrateperday.setMaxWidth(280);
                }
            });



            txt_color.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_color.setMaxWidth(340);

                }
            });


            txt_color.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_color.setMaxWidth(280);
                }
            });



            txt_year.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_year.setMaxWidth(340);

                }
            });


            txt_year.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_year.setMaxWidth(280);
                }
            });


            btn_submit.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    String toolid = txt_toolID.getText();
                    String brand = txt_brand.getText();
                    String model = txt_model.getText();
                    String year = txt_year.getText();
                    String color = txt_color.getText();
                    String rentalrateperday = txt_rentalrateperday.getText();

                    if (!brand.equals("") & !model.equals("") & !year.equals("") &!color.equals("") & !color.equals("")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Brand: " +brand+" was updated successfully.");
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.isPresent() && result.get() == ButtonType.OK){
                            final String UPDATE_CARS_SQL = "UPDATE tools SET Brand = ?, Model = ?, Year = ?, Color = ?, RentalRatePerDay = ? WHERE ToolID = ?;";


                            try (Connection connection = getConnection();
                                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CARS_SQL)) {

                                // For example, employee ID
                                preparedStatement.setString(1, brand);   // Employee name
                                preparedStatement.setString(2, model);
                                preparedStatement.setString(3, year);
                                preparedStatement.setString(4, color);
                                preparedStatement.setString(5, rentalrateperday);
                                preparedStatement.setInt(6, Integer.parseInt(toolid));
                                preparedStatement.executeUpdate();

                                System.out.println("Records update successfully.");

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            pne_toolds.getChildren().clear();
                            pne_toolds.getChildren().add(pne_management);
                        }
                    }else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Please make sure all text field are filled." );
                        Optional<ButtonType> result = alert.showAndWait();
                    }

                }
            });

            btn_submit.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: White");
                }
            });


            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));

            lbl_toolID.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_toolID.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_brand.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_brand.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_model.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_model.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_rentalrateperday.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_rentalrateperday.setFont(Font.font(null, FontWeight.BOLD, 14));


            lbl_color.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_color.setFont(Font.font(null, FontWeight.BOLD, 14));


            lbl_year.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_year.setFont(Font.font(null, FontWeight.BOLD, 14));


            btn_submit.setFont(Font.font(null, FontWeight.BOLD, 14));

            menu_title.setTextFill(Color.WHITE);

            lbl_toolID.setTextFill(Color.WHITE);
            lbl_brand.setTextFill(Color.WHITE);
            lbl_model.setTextFill(Color.WHITE);
            lbl_rentalrateperday.setTextFill(Color.WHITE);
            lbl_color.setTextFill(Color.WHITE);


            lbl_year.setTextFill(Color.WHITE);

            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(20,0,16,0));
            this.setMargin(btn_submit, new Insets(20,0,25,0));
            this.getChildren().addAll(menu_title,lbl_toolID, txt_toolID, lbl_brand,txt_brand,lbl_model,txt_model,lbl_color,txt_color,lbl_year,txt_year,lbl_rentalrateperday,txt_rentalrateperday,btn_submit);
            this.setMinSize(90,600);
        }
    }


    private class ToolsDeletePane extends VBox{
        Button btn_submit;
        Label menu_title, lbl_toolid;
        TextField txt_toolid;

        public ToolsDeletePane (){

            lbl_toolid = new Label("Tool ID");
            txt_toolid = new TextField();

            menu_title = new Label("Tool Delete");

            btn_submit = new Button("Submit");


            //Setting all nodes

            txt_toolid.setMaxWidth(280);
            txt_toolid.setMinHeight(40);

            btn_submit.setMaxWidth(80);
            btn_submit.setMinHeight(40);


            txt_toolid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_toolid.setMaxWidth(340);

                }
            });


            txt_toolid.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_toolid.setMaxWidth(280);
                }
            });



            btn_submit.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String toolid = txt_toolid.getText();

                    if (!toolid.equals("")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You are about to delete tool: "+toolid);
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.isPresent() && result.get() == ButtonType.OK){


                            final String DELETE_CARS_SQL = "DELETE FROM tools WHERE ToolID = ?;";


                            try (Connection connection = getConnection();
                                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARS_SQL)) {

                                preparedStatement.setInt(1, Integer.parseInt(toolid));        // For example, employee ID


                                preparedStatement.executeUpdate();

                                System.out.println("Records delete successfully.");

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            pne_toolds.getChildren().clear();
                            pne_toolds.getChildren().add(pne_management);
                        }
                    }else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Please make sure all text field are filled." );
                        Optional<ButtonType> result = alert.showAndWait();
                    }

                }
            });

            btn_submit.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: White");
                }
            });


            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));

            lbl_toolid.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_toolid.setFont(Font.font(null, FontWeight.BOLD, 14));



            btn_submit.setFont(Font.font(null, FontWeight.BOLD, 14));

            menu_title.setTextFill(Color.WHITE);

            lbl_toolid.setTextFill(Color.WHITE);


            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(20,0,16,0));
            this.setMargin(btn_submit, new Insets(20,0,25,0));
            this.getChildren().addAll(menu_title, lbl_toolid,txt_toolid,btn_submit);
            this.setMinSize(90,600);
        }
    }


    private class RentalsCreatePane extends VBox{
        Button btn_submit;
        Label menu_title, lbl_customerid, lbl_toolid, lbl_totalcost, lbl_rentalenddate, lbl_rentalstartdate;
        TextField txt_customerid, txt_toolid, txt_totalcost;
        DatePicker rentalstartdate, rentalenddate ;
        String rentalstartDate,rentalendDate;

        public RentalsCreatePane (){

            lbl_customerid = new Label("Customer ID");
            txt_customerid = new TextField();

            lbl_toolid = new Label("Tool ID");
            txt_toolid = new TextField();



            lbl_rentalstartdate = new Label("Rental Start Date");
            rentalstartdate = new DatePicker();
            rentalstartdate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    rentalstartDate = String.valueOf(rentalstartdate.getValue());

                }
            });

            lbl_rentalenddate = new Label("Rental End Date");
            rentalenddate = new DatePicker();
            rentalenddate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    rentalendDate = String.valueOf(rentalenddate.getValue());

                }
            });




            lbl_totalcost = new Label("Total Cost");
            txt_totalcost = new TextField();





            menu_title = new Label("Rental Create");

            btn_submit = new Button("Submit");


            //Setting all nodes

            txt_customerid.setMaxWidth(280);
            txt_customerid.setMinHeight(40);

            txt_toolid.setMaxWidth(280);
            txt_toolid.setMinHeight(40);

            txt_totalcost.setMaxWidth(280);
            txt_totalcost.setMinHeight(40);

            rentalenddate.setMaxWidth(280);
            rentalenddate.setMinHeight(40);

            rentalstartdate.setMaxWidth(280);
            rentalstartdate.setMinHeight(40);

            btn_submit.setMaxWidth(80);
            btn_submit.setMinHeight(40);


            txt_customerid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_customerid.setMaxWidth(340);

                }
            });


            txt_customerid.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_customerid.setMaxWidth(280);
                }
            });


            txt_toolid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_toolid.setMaxWidth(340);

                }
            });


            txt_toolid.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_toolid.setMaxWidth(280);
                }
            });


            txt_totalcost.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_totalcost.setMaxWidth(340);

                }
            });


            txt_totalcost.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_totalcost.setMaxWidth(280);
                }
            });



            btn_submit.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String customerid = txt_customerid.getText();
                    String toolid = txt_toolid.getText();
                    String totalcost = txt_totalcost.getText();
                    String rentalenddate = rentalendDate;
                    String rentalstartdate = rentalstartDate;

                    if (!customerid.equals("") & !toolid.equals("") & !totalcost.equals("") & !rentalenddate.equals("") & !rentalstartdate.equals("")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Rental from: " +rentalstartdate+" to: " +rentalenddate+" was created successfully.");
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.isPresent() && result.get() == ButtonType.OK){
                            Rentals rentals = new Rentals();
                            rentals.setCustomerId(customerid);
                            rentals.setToolId(toolid);
                            rentals.setRentalsStartDate(rentalstartdate);
                            rentals.setRentalsEndDate(rentalenddate);
                            rentals.setTotalCost(totalcost);

                            double Cost = Double.parseDouble(rentals.getTotalCost());
                            double totalCost = 0;

                            DataOutputStream toServer;
                            DataInputStream fromServer;

                            try
                            {
                                //Create a socket to connect to the server.
                                Socket socket = new Socket("localhost",12345);

                                //Create an input stream to receive data the server
                                fromServer = new DataInputStream(socket.getInputStream());

                                //Create an output stream to send data to the server.
                                toServer= new DataOutputStream(socket.getOutputStream());

                                //Send the radius to the server
                                toServer.writeDouble(Cost);
                                toServer.flush();

                                totalCost = fromServer.readDouble();

                                System.out.println(totalCost);


                            }catch(IOException ex)
                            {
                                System.out.println(ex.toString() + '\n'+ "in here");
                            }



                            final String INSERT_CUSTOMERS_SQL = "INSERT INTO rentals (CustomerID, ToolID, RentalStartDate, RentalEndDate, TotalCost) VALUES (?,?,?,?,?);";


                            try (Connection connection = getConnection();
                                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_SQL)) {


                                // For example, employee ID
                                preparedStatement.setString(1, rentals.getCustomerId());   // Employee name
                                preparedStatement.setString(2, rentals.getToolId());
                                preparedStatement.setString(3, rentals.getRentalsStartDate());   // Employee name
                                preparedStatement.setString(4, rentals.getRentalsEndDate());
                                preparedStatement.setString(5, String.valueOf(totalCost));   // Employee name


                                preparedStatement.executeUpdate();

                                System.out.println("Records inserted successfully.");


                            } catch (SQLException e) {
                                e.printStackTrace();
                            }


                            final String UPDATE_CUSTOMERS_SQL = "UPDATE tools SET Availability = ? WHERE ToolID = ?;";


                            try (Connection connection = getConnection();
                                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMERS_SQL)) {

                                // For example, employee ID
                                preparedStatement.setInt(1,0);
                                preparedStatement.setInt(2, Integer.parseInt(toolid));
                                preparedStatement.executeUpdate();
                                System.out.println("Records update successfully.");

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            pne_toolds.getChildren().clear();
                            pne_toolds.getChildren().add(pne_management);
                        }
                    }else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Please make sure all text field are filled." );
                        Optional<ButtonType> result = alert.showAndWait();
                    }

                }
            });

            btn_submit.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: White");
                }
            });


            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));

            lbl_customerid.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_customerid.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_toolid.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_toolid.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_totalcost.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_totalcost.setFont(Font.font(null, FontWeight.BOLD, 14));


            lbl_rentalenddate.setFont(Font.font(null, FontWeight.BOLD, 16));


            lbl_rentalstartdate.setFont(Font.font(null, FontWeight.BOLD, 16));


            btn_submit.setFont(Font.font(null, FontWeight.BOLD, 14));

            menu_title.setTextFill(Color.WHITE);

            lbl_customerid.setTextFill(Color.WHITE);
            lbl_toolid.setTextFill(Color.WHITE);
            lbl_totalcost.setTextFill(Color.WHITE);
            lbl_rentalenddate.setTextFill(Color.WHITE);


            lbl_rentalstartdate.setTextFill(Color.WHITE);

            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(20,0,16,0));
            this.setMargin(btn_submit, new Insets(20,0,25,0));
            this.getChildren().addAll(menu_title, lbl_customerid,txt_customerid,lbl_toolid,txt_toolid,lbl_rentalstartdate,rentalstartdate,lbl_rentalenddate,rentalenddate,lbl_totalcost,txt_totalcost,btn_submit);
            this.setMinSize(90,600);
        }
    }


    private class RentalsUpdatePane extends VBox{
        Button btn_submit;
        Label menu_title, lbl_rentalID ,lbl_customerid, lbl_toolid, lbl_totalcost, lbl_rentalenddate, lbl_rentalstartdate;
        TextField txt_rentalID, txt_customerid, txt_toolid, txt_totalcost;
        DatePicker rentalstartdate, rentalenddate ;
        String rentalstartDate,rentalendDate;
        public RentalsUpdatePane (){

            lbl_rentalID = new Label("Rental ID");
            txt_rentalID = new TextField();

            lbl_customerid = new Label("Customer ID");
            txt_customerid = new TextField();

            lbl_toolid = new Label("Tool ID");
            txt_toolid = new TextField();



            lbl_rentalstartdate = new Label("Rental Start Date");
            rentalstartdate = new DatePicker();
            rentalstartdate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    rentalstartDate = String.valueOf(rentalstartdate.getValue());

                }
            });

            lbl_rentalenddate = new Label("Rental End Date");
            rentalenddate = new DatePicker();
            rentalenddate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    rentalendDate = String.valueOf(rentalenddate.getValue());

                }
            });

            lbl_totalcost = new Label("Total Cost");
            txt_totalcost = new TextField();





            menu_title = new Label("Rental Update");

            btn_submit = new Button("Submit");


            //Setting all nodes

            txt_rentalID.setMaxWidth(280);
            txt_rentalID.setMinHeight(40);


            txt_customerid.setMaxWidth(280);
            txt_customerid.setMinHeight(40);

            txt_toolid.setMaxWidth(280);
            txt_toolid.setMinHeight(40);

            txt_totalcost.setMaxWidth(280);
            txt_totalcost.setMinHeight(40);

            rentalenddate.setMaxWidth(280);
            rentalenddate.setMinHeight(40);

            rentalstartdate.setMaxWidth(280);
            rentalstartdate.setMinHeight(40);

            btn_submit.setMaxWidth(80);
            btn_submit.setMinHeight(40);


            txt_rentalID.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_rentalID.setMaxWidth(340);

                }
            });


            txt_rentalID.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_rentalID.setMaxWidth(280);
                }
            });


            txt_customerid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_customerid.setMaxWidth(340);

                }
            });


            txt_customerid.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_customerid.setMaxWidth(280);
                }
            });


            txt_toolid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_toolid.setMaxWidth(340);

                }
            });


            txt_toolid.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_toolid.setMaxWidth(280);
                }
            });


            txt_totalcost.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_totalcost.setMaxWidth(340);

                }
            });


            txt_totalcost.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_totalcost.setMaxWidth(280);
                }
            });


            btn_submit.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String rentalid = txt_rentalID.getText();
                    String customerid = txt_customerid.getText();
                    String toolid = txt_toolid.getText();
                    String totalcost = txt_totalcost.getText();
                    String rentalenddate = rentalendDate;
                    String rentalstartdate = rentalstartDate;

                    if (!customerid.equals("") & !toolid.equals("") & !totalcost.equals("") & !rentalenddate.equals("") & !rentalstartdate.equals("")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Rental from: " +rentalstartdate+" to: " +rentalenddate+" was update successfully.");
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.isPresent() && result.get() == ButtonType.OK){


                            double Cost = Double.parseDouble(totalcost);
                            double totalCost = 0;

                            DataOutputStream toServer;
                            DataInputStream fromServer;

                            try
                            {
                                //Create a socket to connect to the server.
                                Socket socket = new Socket("localhost",12345);

                                //Create an input stream to receive data the server
                                fromServer = new DataInputStream(socket.getInputStream());

                                //Create an output stream to send data to the server.
                                toServer= new DataOutputStream(socket.getOutputStream());

                                //Send the radius to the server
                                toServer.writeDouble(Cost);
                                toServer.flush();

                                totalCost = fromServer.readDouble();

                                System.out.println(totalCost);


                            }catch(IOException ex)
                            {
                                System.out.println(ex.toString() + '\n'+ "in here");
                            }



                            final String UPDATE_RENTALS_SQL = "UPDATE rentals SET CustomerID = ?, ToolID = ?, RentalStartDate = ?, RentalEndDate = ?, TotalCost = ? WHERE RentalID = ?;";


                            try (Connection connection = getConnection();
                                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RENTALS_SQL)) {


                                preparedStatement.setString(1, customerid);   // Employee name
                                preparedStatement.setString(2, toolid);
                                preparedStatement.setString(3, rentalstartdate);
                                preparedStatement.setString(4, rentalenddate);
                                preparedStatement.setString(5, String.valueOf(totalCost));
                                preparedStatement.setInt(6, Integer.parseInt(rentalid));

                                preparedStatement.executeUpdate();

                                System.out.println("Records update successfully.");

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            pne_toolds.getChildren().clear();
                            pne_toolds.getChildren().add(pne_management);
                        }
                    }else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Please make sure all text field are filled." );
                        Optional<ButtonType> result = alert.showAndWait();
                    }

                }
            });

            btn_submit.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: White");
                }
            });


            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));


            lbl_rentalID.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_rentalID.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_customerid.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_customerid.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_toolid.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_toolid.setFont(Font.font(null, FontWeight.BOLD, 14));

            lbl_totalcost.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_totalcost.setFont(Font.font(null, FontWeight.BOLD, 14));


            lbl_rentalenddate.setFont(Font.font(null, FontWeight.BOLD, 16));



            lbl_rentalstartdate.setFont(Font.font(null, FontWeight.BOLD, 16));



            btn_submit.setFont(Font.font(null, FontWeight.BOLD, 14));

            menu_title.setTextFill(Color.WHITE);

            lbl_rentalID.setTextFill(Color.WHITE);
            lbl_customerid.setTextFill(Color.WHITE);
            lbl_toolid.setTextFill(Color.WHITE);
            lbl_totalcost.setTextFill(Color.WHITE);
            lbl_rentalenddate.setTextFill(Color.WHITE);


            lbl_rentalstartdate.setTextFill(Color.WHITE);

            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(20,0,16,0));
            this.setMargin(btn_submit, new Insets(20,0,25,0));
            this.getChildren().addAll(menu_title, lbl_rentalID, txt_rentalID, lbl_customerid,txt_customerid,lbl_toolid,txt_toolid,lbl_rentalenddate,rentalenddate,lbl_rentalstartdate,rentalstartdate,lbl_totalcost,txt_totalcost,btn_submit);
            this.setMinSize(90,600);
        }
    }


    private class RentalsDeletePane extends VBox{
        Button btn_submit;
        Label menu_title, lbl_rentalid;
        TextField txt_rentalid;

        public RentalsDeletePane (){

            lbl_rentalid = new Label("Rental ID");
            txt_rentalid = new TextField();


            menu_title = new Label("Rental Delete");

            btn_submit = new Button("Submit");


            //Setting all nodes

            txt_rentalid.setMaxWidth(280);
            txt_rentalid.setMinHeight(40);


            btn_submit.setMaxWidth(80);
            btn_submit.setMinHeight(40);


            txt_rentalid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_rentalid.setMaxWidth(340);

                }
            });


            txt_rentalid.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    txt_rentalid.setMaxWidth(280);
                }
            });


            btn_submit.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String rentalid = txt_rentalid.getText();

                    if (!rentalid.equals("")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You are about to delete rental: "+rentalid);
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.isPresent() && result.get() == ButtonType.OK){


                            final String DELETE_RENTALS_SQL = "DELETE FROM rentals WHERE RentalID = ?;";


                            try (Connection connection = getConnection();
                                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RENTALS_SQL)) {

                                preparedStatement.setInt(1, Integer.parseInt(rentalid));        // For example, employee ID


                                preparedStatement.executeUpdate();

                                System.out.println("Records delete successfully.");

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            pne_toolds.getChildren().clear();
                            pne_toolds.getChildren().add(pne_management);
                        }
                    }else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Please make sure all text field are filled." );
                        Optional<ButtonType> result = alert.showAndWait();
                    }

                }
            });

            btn_submit.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_submit.setStyle("-fx-background-color: White");
                }
            });


            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));

            lbl_rentalid.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_rentalid.setFont(Font.font(null, FontWeight.BOLD, 14));



            btn_submit.setFont(Font.font(null, FontWeight.BOLD, 14));

            menu_title.setTextFill(Color.WHITE);

            lbl_rentalid.setTextFill(Color.WHITE);


            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(20,0,16,0));
            this.setMargin(btn_submit, new Insets(20,0,25,0));
            this.getChildren().addAll(menu_title, lbl_rentalid,txt_rentalid,btn_submit);
            this.setMinSize(90,600);
        }
    }


    public class RentalsReadPane extends VBox{
        Button btn_generate;
        Label menu_title;
        public RentalsReadPane (){




            menu_title = new Label("Rental Read");


            btn_generate = new Button("Generate");


            //Setting all nodes

            btn_generate.setMaxWidth(120);
            btn_generate.setMinHeight(40);


            TableView <RentalsView> table = new TableView();
            table.setEditable(true);


            table.setMaxHeight(400);
            table.setMaxWidth(960);


            TableColumn rentalIdCol = new TableColumn("Rental ID");
            rentalIdCol.setMinWidth(160);

            TableColumn customeridCol = new TableColumn("Customer Id");
            customeridCol.setMinWidth(160);

            TableColumn toolidCol = new TableColumn("Tool Id");
            toolidCol.setMinWidth(160);


            TableColumn rentalstartdateCol = new TableColumn("Rental Start Date");
            rentalstartdateCol.setMinWidth(160);


            TableColumn rentalenddateCol = new TableColumn("Rental End Date");
            rentalenddateCol.setMinWidth(160);

            TableColumn totalcostCol = new TableColumn("Total Cost");
            totalcostCol.setMinWidth(160);




            rentalIdCol.setCellValueFactory(
                    new PropertyValueFactory<RentalsView, String>("rentalid")
            );

            customeridCol.setCellValueFactory(
                    new PropertyValueFactory<RentalsView, String>("customerid")
            );


            toolidCol.setCellValueFactory(
                    new PropertyValueFactory<RentalsView, String>("toolid")
            );


            rentalstartdateCol.setCellValueFactory(
                    new PropertyValueFactory<RentalsView, String>("rentalstartdate")
            );

            rentalenddateCol.setCellValueFactory(
                    new PropertyValueFactory<RentalsView, String>("rentalenddate")
            );


            totalcostCol.setCellValueFactory(
                    new PropertyValueFactory<RentalsView, String>("totalcost")
            );

            table.getColumns().addAll(rentalIdCol,customeridCol,toolidCol, rentalstartdateCol,rentalenddateCol, totalcostCol);



            btn_generate.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_generate.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_generate.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_generate.setStyle("-fx-background-color: White");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You are about to generate all the rentals information." );
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.isPresent() && result.get() == ButtonType.OK) {

                        final String SELECT_ALL_RENTALS_SQL = "SELECT * FROM rentals;";


                        try (Connection connection = getConnection();
                             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RENTALS_SQL)) {
                            ResultSet resultSet = preparedStatement.executeQuery();

                            ObservableList<RentalsView> data = FXCollections.observableArrayList();

                            while(resultSet.next()){
                                int id = resultSet.getInt("RentalID");
                                String customerid = resultSet.getString("CustomerID");
                                String toolid = resultSet.getString("ToolID");
                                String rentalstartdate = resultSet.getString("RentalStartDate");
                                String rentalenddate = resultSet.getString("RentalEndDate");
                                String totalcost = resultSet.getString("TotalCost");


                                Rentals rentals = new Rentals();
                                RentalsView rentalsview = new RentalsView();
                                RentalsController view = new RentalsController(rentals, rentalsview);

                                view.getView().setRentalid(id);
                                view.getView().setCustomerid(customerid);
                                view.getView().setToolid(toolid);
                                view.getView().setRentalstartdate(rentalstartdate);
                                view.getView().setRentalenddate(rentalenddate);
                                view.getView().setTotalcost(totalcost);

                                data.add(new RentalsView(view.getView().getRentalid(),view.getView().getCustomerid(),view.getView().getToolid(),view.getView().getRentalstartdate(),view.getView().getRentalenddate(),view.getView().getTotalcost()));

                                table.setItems(data);

                                System.out.println("ID: "+id+" Customer ID: "+customerid+" Tool ID: "+toolid+ " Rental Start Date: "+rentalstartdate+ " Rental End Date: "+rentalenddate+ " Total Cost: "+totalcost);

                            }


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }

                }


            });

            btn_generate.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_generate.setStyle("-fx-background-color: White");
                }
            });




            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));
            btn_generate.setFont(Font.font(null, FontWeight.BOLD, 16));
            menu_title.setTextFill(Color.WHITE);

            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(20,0,16,0));
            this.setMargin(btn_generate, new Insets(20,0,25,0));
            this.getChildren().addAll(menu_title,table,btn_generate);
            this.setMinSize(90,600);
        }
    }


    public class AvailabilityPane extends VBox{
        Button btn_generate;
        Label menu_title;
        public AvailabilityPane (){




            menu_title = new Label("Available Tools");


            btn_generate = new Button("Generate");


            //Setting all nodes

            btn_generate.setMaxWidth(120);
            btn_generate.setMinHeight(40);


            TableView <AvailabilityView> table = new TableView();
            table.setEditable(true);


            table.setMaxHeight(400);
            table.setMaxWidth(960);


            TableColumn toolidCol = new TableColumn("Tool ID");
            toolidCol.setMinWidth(160);

            TableColumn brandCol = new TableColumn("Brand");
            brandCol.setMinWidth(160);

            TableColumn modelCol = new TableColumn("Model");
            modelCol.setMinWidth(160);


            TableColumn yearCol = new TableColumn("Year");
            yearCol.setMinWidth(160);

            TableColumn colorCol = new TableColumn("Color");
            colorCol.setMinWidth(160);


            TableColumn rentalratederdayCol = new TableColumn("RentalRatePerday");
            rentalratederdayCol.setMinWidth(160);



            toolidCol.setCellValueFactory(
                    new PropertyValueFactory<ToolsView, String>("toolid")
            );


            brandCol.setCellValueFactory(
                    new PropertyValueFactory<ToolsView, String>("brand")
            );

            modelCol.setCellValueFactory(
                    new PropertyValueFactory<ToolsView, String>("model")
            );


            yearCol.setCellValueFactory(
                    new PropertyValueFactory<ToolsView, String>("year")
            );


            colorCol.setCellValueFactory(
                    new PropertyValueFactory<ToolsView, String>("color")
            );

            rentalratederdayCol.setCellValueFactory(
                    new PropertyValueFactory<ToolsView, String>("rentalrateperday")
            );



            table.getColumns().addAll(toolidCol,brandCol, modelCol,yearCol, colorCol,rentalratederdayCol);



            btn_generate.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_generate.setStyle("-fx-background-color: LightBlue");

                }
            });

            btn_generate.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_generate.setStyle("-fx-background-color: White");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You are about to generate all available tools." );
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.isPresent() && result.get() == ButtonType.OK) {

                        final String SELECT_ALL_CARS_SQL = "SELECT * FROM tools WHERE Availability = 1;";


                        try (Connection connection = getConnection();
                             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CARS_SQL)) {
                            ResultSet resultSet = preparedStatement.executeQuery();

                            ObservableList<AvailabilityView> data = FXCollections.observableArrayList();

                            while(resultSet.next()){
                                int id = resultSet.getInt("ToolID");
                                String brand = resultSet.getString("Brand");
                                String model = resultSet.getString("Model");
                                String year = resultSet.getString("Year");
                                String color = resultSet.getString("Color");
                                String rentalrateperday = resultSet.getString("RentalRatePerDay");

                                AvailabilityView availabilityView = new AvailabilityView();
                                availabilityView.setToolid(id);
                                availabilityView.setBrand(brand);
                                availabilityView.setModel(model);
                                availabilityView.setYear(year);
                                availabilityView.setColor(color);
                                availabilityView.setRentalrateperday(rentalrateperday);

                                data.add(new AvailabilityView(availabilityView.getToolid(),availabilityView.getBrand(),availabilityView.getModel(),availabilityView.getYear(),availabilityView.getColor(),availabilityView.getRentalrateperday()));

                                table.setItems(data);

                                System.out.println("ID: "+id+" Brand: "+brand+" Model: "+model+ "Year: "+year+ " Color: "+color+ " RentalRatePerDay: "+rentalrateperday);

                            }


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }

                }


            });

            btn_generate.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    btn_generate.setStyle("-fx-background-color: White");
                }
            });




            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));
            btn_generate.setFont(Font.font(null, FontWeight.BOLD, 16));
            menu_title.setTextFill(Color.WHITE);

            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(20,0,16,0));
            this.setMargin(btn_generate, new Insets(20,0,25,0));
            this.getChildren().addAll(menu_title,table,btn_generate);
            this.setMinSize(90,600);
        }
    }


    private class HelpPane extends VBox{
        Label menu_title;
        TitledPane tp1,tp2,tp3, tp4;


        public HelpPane (){

            tp1 = new TitledPane();
            tp2 = new TitledPane();
            tp3 = new TitledPane();
            tp4 = new TitledPane();



            // Setting all nodes
            tp1.setText("How to create a customer account?");
            tp2.setText("How to add new tools?");
            tp3.setText("How to create a new rental for a customer?");
            tp4.setText("How to display available tools?");

            tp1.setContent(new Text("You can create a customer account and manage accounts, \n by selecting the customers button in the management menu."));

            tp2.setContent(new Text("You can add new tools and manage inventories, \n by selecting the tools button in the management menu."));

            tp3.setContent(new Text("You can create a new rental, \n by selecting the rentals button in the management menu."));

            tp4.setContent(new Text("You can display available tools, \n by selecting the availability button in the management menu."));

            tp1.setMinHeight(86);
            tp1.setMaxWidth(500);
            tp1.setMinWidth(500);
            tp2.setMinHeight(86);
            tp2.setMaxWidth(500);
            tp2.setMinWidth(500);
            tp3.setMinHeight(86);
            tp3.setMaxWidth(500);
            tp3.setMinWidth(500);
            tp4.setMinHeight(86);
            tp4.setMaxWidth(500);
            tp4.setMinWidth(500);



            menu_title = new Label("Help");

            tp1.setFont(Font.font(null, FontWeight.BOLD, 14));
            tp2.setFont(Font.font(null, FontWeight.BOLD, 14));
            tp3.setFont(Font.font(null, FontWeight.BOLD, 14));
            tp4.setFont(Font.font(null, FontWeight.BOLD, 14));
            menu_title.setFont(Font.font(null, FontWeight.BOLD, 30));
            menu_title.setTextFill(Color.WHITE);


            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-background-color: #4682b4");
            this.setMargin(menu_title, new Insets(0,0,30,0));
            this.getChildren().addAll(menu_title,tp1,tp2,tp3,tp4);
            this.setMinSize(90,600);
        }
    }


    private class FooterPane extends VBox{
        Label txt_footer;


        public FooterPane (){


            //Setting all nodes
            txt_footer = new Label("Rent & Use. All Rights Reserved 2024");
            txt_footer.setFont(Font.font(null, FontWeight.BOLD, 16));
            txt_footer.setTextFill(Color.GRAY);



            //Arranging all nodes
            this.setAlignment(Pos.CENTER);
            this.setMargin(txt_footer, new Insets(10,0,10,0));
            this.getChildren().addAll(txt_footer);
            this.setMinSize(90,20);
        }
    }




    /**
     *
     * @param args the command line arguments<br>
     *             Developed: 27/11/2024<br>
     *             Name: Rent and Use<br>
     *             Developer: Kerry Mannette<br>
     *             Version: 1.0<br>
     *
     *             <h1>Program Description</h1>
     *             <p>Rent & Use is an application that allows the user to store
     *             customers' and tool information and create rentals.
     *             </p>
     *
     *
     *
     */
    public static void main(String[] args) {
        databaseConnection();
        launch();
    }
}

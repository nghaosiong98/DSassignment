/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxgui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author End User
 */
public class FrontEnd extends Application {

    int numofcustomer;
    Scene scene, scene2, scene3;
    int totalcompleteiontime = 162;
    String results = "";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image img = new Image("film.png");
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(100);
        imgView.setFitWidth(900);
        imgView.setTranslateY(150);
        Image wakanda = new Image("wakanda3.png");
        ImageView waimg = new ImageView(wakanda);
        waimg.setFitHeight(100);
        waimg.setFitWidth(100);
        waimg.setTranslateX(580);
        waimg.setTranslateY(-20);
        Image clock = new Image("clock.png");
        ImageView clcview = new ImageView(clock);
        clcview.setFitHeight(100);
        clcview.setFitWidth(100);
        clcview.setTranslateX(400);
        clcview.setTranslateY(-40);
        // clcview.setTranslateY(50);
        Stage window = new Stage();
        window.setTitle("The counter");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 10, 0));
        //put the title for the label 
        Label title = new Label("Welcome to Wakanda Cinema");
        title.setFont(new Font("Arial", 30));
        title.setTranslateX(100);
        title.setTextFill(Color.web("#FFD700"));
        //GridPane.setConstraints(title,50,20);

        //Create a textbox for them to enter the number of tickets  
        Label customer = new Label("Enter Number of customer: ");
        //customer.setStyle("-fx-border-color: red");
        customer.setId("nocust");
        customer.getStylesheets().add("Viper.css");

        customer.setWrapText(true);
        TextField nocustomer = new TextField();
        nocustomer.setPromptText("1");
        Button confirm = new Button("Confirm");
        confirm.setStyle("-fx-border-color:#ff0000");
        confirm.setStyle("-fx-background-color:#B22222");

        Button proceed = new Button("Proceed");
        proceed.setTranslateX(30);
        proceed.setStyle("-fx-background-colour:#B22222");

        Button c3 = new Button("go to scene3");

        c3.setOnAction(e -> {
            window.setScene(scene3);
        });

        HBox first = new HBox();
        first.setTranslateX(50);
        first.setTranslateY(100);
        first.setSpacing(10);
        first.getChildren().addAll(customer, nocustomer, confirm, proceed);

        //Button done
        Button done = new Button("Done");
        done.setId("donebtn");
        done.getStylesheets().add("Viper.css");
        done.setTranslateX(430);
        done.setTranslateY(0);
        done.setOnAction(eo -> {
            window.setScene(scene3);
        });

        //Button proceed
        confirm.setOnAction(e -> {
            numofcustomer = Integer.parseInt(nocustomer.getText());
            TextField[] tfarrival = new TextField[numofcustomer];
            TextField[] tfstatus = new TextField[numofcustomer];
            TextField[] tfticket = new TextField[numofcustomer];

            VBox root = new VBox();
            root.setTranslateY(50);

            Label detail = new Label("Please key in the Customer Details");
            detail.setFont(new Font("Arial", 30));
            //  detail.setTranslateY();

            for (int i = 1; i <= numofcustomer; i++) {

                Label arrival = new Label("Customer " + i + " Arrival Time:");
                arrival.setFont(new Font("Arial", 15));
                arrival.setTextFill(Color.web("#B22222"));
                TextField tf = new TextField();
                String name = "Customer " + i;
                tf.setOnAction(onef -> {
                    System.out.println("Action on " + name + " :is arrived on " + tf.getText());
                });
                Label status = new Label("Customer " + i + " Status: ");
                status.setFont(new Font("Arial", 15));
                status.setTextFill(Color.web("#B22222"));
                TextField tf1 = new TextField();
                String statusline = "Customer" + i;
                tf1.setOnAction(twof -> {
                    System.out.println("Action on " + statusline + " :status is " + tf1.getText());
                });
                Label ticket = new Label("Customer " + i + " Number of Tickets: ");
                ticket.setFont(new Font("Arial", 15));
                ticket.setTextFill(Color.web("#B22222"));
                TextField tf2 = new TextField();
                String ticketnumber = "Customer " + i;
                tf2.setOnAction(threef -> {
                    System.out.println("Action on " + ticketnumber + " :number of ticket is " + tf2.getText());
                });

                root.getChildren().addAll(arrival, tf, status, tf1, ticket, tf2);
                tfarrival[i - 1] = tf;
                tfstatus[i - 1] = tf1;
                tfticket[i - 1] = tf2;
            }
            BorderPane s2 = new BorderPane();
            s2.setTop(detail);
            s2.setCenter(root);
            s2.setBottom(done);
//      s2.getChildren().addAll(root,detail,done);
            s2.setId("vbox");
            scene2 = new Scene(s2, 500, 700);
            scene2.getStylesheets().add("Viper.css");

        });

        proceed.setOnAction(ef -> {
            window.setScene(scene2);
            window.show();
        });
        //set scene 3
        GridPane s3 = new GridPane();
        s3.setPadding(new Insets(0, 0, 0, 0));

        //Label title for scene3 
        Label title3 = new Label("System TimeLine");
        title3.setTranslateX(140);
        title3.setTranslateY(-50);
        title3.setId("title3");
        title3.getStylesheets().add("Viper.css");

        Label completetime = new Label("Total Completion Time: " + totalcompleteiontime);
        completetime.setId("time");
        completetime.getStylesheets().add("Viper.css");
        completetime.setTranslateY(250);

        TextArea output = new TextArea();
        output.setTranslateX(20);
        output.setPrefHeight(200);
        output.setPrefWidth(550);
        // this is important
//        output.setText("|Arrival   |Start Processing    |End Processing |Processing Time     |Waiting Time   |Queue   |Counter |");
        output.setText(results);
        output.setTranslateY(130);

        Button close = new Button("Close");
        close.setStyle("-fx-border-color:#ff0000");
        close.setStyle("-fx-background-color:#B22222");
        close.setTranslateY(340);
        close.setOnAction(finish -> {
            window.close();
        });

        s3.getChildren().addAll(title3, output, close, completetime, clcview);
        s3.setId("scenethree");
        s3.getStylesheets().add("Viper.css");
        scene3 = new Scene(s3, 600, 500);

        grid.getChildren().addAll(title, first, imgView, waimg);
        Scene scene = new Scene(grid, 700, 300);
        scene.getStylesheets().add("Viper.css");

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

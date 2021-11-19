package baseline;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class InventoryManagementApplication extends Application{
    @Override
    public void start(Stage stage) throws Exception{
        //create a setOnCloseRequest
        stage.setOnCloseRequest(e -> {
            //create instance of popup
            PopupMessage popup = new PopupMessage();
            //check the returned value of popup
            if(popup.confirmCloseList()){
                //quit program based on value
                Platform.exit();
                System.exit(0);
            }else e.consume(); //else consume event
        });

        //create a parent root
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InventoryManagement.fxml")));
        //attach scene graph to scene
        Scene scene = new Scene(root);
        //display in window's title bar
        stage.setTitle("Your Inventory");
        //attach scene to stage
        stage.setScene(scene);
        //display the stage
        stage.show();
    }

    public static void main(String[] args){
        //create an object and call its start method
        launch(args);
    }
}

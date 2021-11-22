package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class PopupMessage {
    //create a method that will display error for invalid name
    public void invalidName(){
        //create a new Alert with error alert type
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //set the title
        alert.setTitle("Invalid Item Name");
        //set the content text
        alert.setContentText("The current item name is not within 2 and 256 characters. Please enter a valid item");
        //use showAndWait to wait for user input
        alert.showAndWait();
    }

    //create a method that will display error for invalid serial number
    public void invalidSerialNumber(){
        //create a new Alert with error alert type
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //set the title
        alert.setTitle("Invalid Item Serial Number");
        //set the content text
        alert.setContentText("The current item is not a valid serial number. Make sure it is in the format of A-XXX-XXX-XXX, " +
                "where A MUST be a letter and X can either be a letter or digit");
        //use showAndWait to wait for user input
        alert.showAndWait();
    }

    //create a method that will display error for invalid value
    public void invalidValue(){
        //create a new Alert with error alert type
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //set the title
        alert.setTitle("Invalid Item Value");
        //set the content text
        alert.setContentText("The current item value is incorrect. The value must an number which has to be equal to or greater than 0.00");
        //use showAndWait to wait for user input
        alert.showAndWait();
    }

    //create a method that will display error for nonUniqueData
    public void nonUniqueData(){
        //create a new Alert with error alert type
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //set the title
        alert.setTitle("Non Unique Serial Number");
        //set the content text
        alert.setContentText("The current item's serial number already exists within the program. " +
                "Please enter a unique serial number for the corresponding item you are trying to add");
        //use showAndWait to wait for user input
        alert.showAndWait();
    }

    public boolean confirmClearList(){
        //create a new Alert with confirmation alert type
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //set the title
        alert.setTitle("Confirmation");
        //set the content text
        alert.setContentText("Make sure to save your list before clearing. Once cleared there is not a way to recover the list. Press cancel if you don't wish to continue");
        //create a result button from the show and wait
        Optional<ButtonType> result = alert.showAndWait();
        //return the result to determine to continue or not
        return result.isPresent() && result.get() != ButtonType.CANCEL;
    }

    public boolean confirmCloseList(){
        //create a new Alert with confirmation alert type
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //set the title
        alert.setTitle("Confirmation");
        //set the content text
        alert.setContentText("Once exited any unsaved changes will be lost. Press okay to exit the program.");
        //create a result button from the show and wait
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isEmpty()){
            return false;
        } else return result.get() == ButtonType.OK;
    }

}

package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class ValidateTextFields {

    public boolean validateName(String name){
        //create a boolean to return
        //create an if statement to check if description is null or if description is longer than 256 or if description is less than 2
            //return false if name is equal to any of these
            //call popup for invalid data
        //else return true
        return false;
    }

    public boolean validateSerialNumber(String serialNumber){
        //create a boolean to return

        //check if the first letter is a number followed by a dash
            //if not then return false
            //call popup for invalid data
        //check if next set of three numbers is either letters or numbers
            //if not then return false
            //call popup for invalid data
        //check if the last set of is numbers or letters
            //if not then return false
            //call popup for invalid data
        //check if the value is inside the observableList already
            //if it is return false call popup
        return false;
    }

    public boolean validateValue(double value){
        //create a boolean to return

        //check if the value is greater than or equal to zero
            //if not then return false
            //call the popup for invalid data
        return false;
    }
}

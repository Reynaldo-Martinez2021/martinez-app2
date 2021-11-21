package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class ValidateTextFields {

    //create string numRegex and alphaRegex
    String numRegex = ".*[0-9].*";
    String alphaRegex = ".*[A-Za-z].*";

    public boolean validateName(String name){
        //trim the result to avoid empty names
        String trimmedName = name.trim();
        //create an if statement to check if description is null or if description is longer than 256 or if description is less than 2
        if(trimmedName.length() > 256 || trimmedName.length() < 2 || trimmedName.isBlank()) {
            //create instance of popup
            PopupMessage popup = new PopupMessage();
            //call the corresponding popUp message for invalid name
            popup.invalidName();
            return false;
        }else{
            return true;
        }
    }

    public boolean validateSerialNumber(String serialNumber){
        //create an instance of popUp
        PopupMessage popup = new PopupMessage();
        //create array for string parts
        String[] serialNumberParts = serialNumber.split("(-)");
         //check if the number of parts is equal to 4 if not set actualResult to false
        if(serialNumberParts.length != 4){
            //call the corresponding popUp message for invalid name
            popup.invalidSerialNumber();
            return false;
        }else{
            if(
                    (serialNumberParts[0].matches(alphaRegex))
                    && (serialNumberParts[1].matches(numRegex) || serialNumberParts[1].matches(alphaRegex))
                    && (serialNumberParts[2].matches(numRegex) || serialNumberParts[2].matches(alphaRegex))
                    && (serialNumberParts[3].matches(numRegex) || serialNumberParts[3].matches(alphaRegex))
            ){
                return true;
            }else{
                //call the corresponding popup message
                popup.invalidSerialNumber();
                return false;
            }
        }
    }

    public boolean validateValue(Double value){
        //check if the value is greater than 0.00
        if(value >= 0.00)
            return true;
        else{
            //create instance of popup
            PopupMessage popup = new PopupMessage();
            //call the corresponding popUp message for invalid name
            popup.invalidValue();
            //return false
            return false;
        }
    }
}

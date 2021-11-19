package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidateTextFieldsTest {

    @Test
    void validateName() {
        //create an actual boolean to set values
        boolean actualResult;
        //create a string to test
        String input = "PS5 Ultimate Edition";
        //strip the whitespace to determine legal input
        String trimmedInput = input.trim();
        //create an if statement to check if description is null or if description is longer than 256 or if description is less than 2
        if(trimmedInput == null || trimmedInput.length() > 256 || trimmedInput.length() < 2 || trimmedInput.isBlank())
            //set actual to false if name is equal to any of these
            actualResult = false;
        else
            actualResult = true;
        //assertTrue/assertFalse for string name
        assertTrue(actualResult);
    }

    //the serial Number must have 3 dashes if it doesn't set return false
    //will split serial number into 4 parts
    //first part must contain a letter if not return false
    //second part can either be letter or digit and 3 letters only if not return false
    //third part same thing
    //fourth part same thing
    @Test
    void validateSerialNumber() {
        //create string numRegex and alphaRegex
        String numRegex = ".*[0-9].*";
        String alphaRegex = ".*[A-Za-z].*";
        //create an actual boolean to set values
        boolean actualResult;
        //create an actual string serialNumber
        String inputSerialNumber = "A-xb1-244-Xy3";
        //create array for string parts
        String[] parts = inputSerialNumber.split("(-)");
        //check if the number of parts is equal to 4 if not set actualResult to false
        if(parts.length != 4){
            //call the popUp menu
            //set actual result to false
            actualResult = false;
        }else{
            //check if each part contains either numbers or digits
            if(
                    (parts[0].matches(alphaRegex))
                    && (parts[1].matches(numRegex) || parts[1].matches(alphaRegex))
                    && (parts[2].matches(numRegex) || parts[2].matches(alphaRegex))
                    && (parts[3].matches(numRegex) || parts[3].matches(alphaRegex))
            ){
                actualResult = true;
            }else{
                //call the corresponding popup message
               //set the actual result to false
                actualResult = false;
            }
        }
        //assertTrue/assertFalse for string name
        assertTrue(actualResult);
    }

    @Test
    void validateValue() {
        //create an actual boolean to set values
        boolean actualResult = false;
        //create an actual double value
        double actualValue = 1499.99;
        //check if the value is greater than or equal to zero
        if(actualValue >= 0.00)
            //if not then set actual to false
            actualResult = true;
        //assertTrue/assertFalse for string name
        assertTrue(actualResult);
    }
}
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
        //create an actual string name
        //create an if statement to check if description is null or if description is longer than 256 or if description is less than 2
            //set actual to false if name is equal to any of these
        //assertTrue/assertFalse for string name
    }

    @Test
    void validateSerialNumber() {
        //create an actual boolean to set values
        //create an actual string serialNumber

        //check if the first letter is a number followed by a dash
            //if not then set actual to false
        //check if next set of three numbers is either letters or numbers
            //if not then set actual to false
        //check if the last set of is numbers or letters
            //if not then set actual to false
        //check if the value is inside the observableList already
            //if it is then set actual to false
        //assertTrue/assertFalse for string name
    }

    @Test
    void validateValue() {
        //create an actual boolean to set values
        //create an actual double value
        //check if the value is greater than or equal to zero
            //if not then set actual to false
        //assertTrue/assertFalse for string name
    }
}
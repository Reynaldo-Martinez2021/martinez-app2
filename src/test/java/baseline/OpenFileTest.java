package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenFileTest {

    @Test
    void openListFromTxtFile() throws Exception{
        //create an actual list
        ObservableList<InventoryItem> actualList = FXCollections.observableArrayList();
        //create the items to fill actualList
        InventoryItem first = new InventoryItem();
        InventoryItem second = new InventoryItem();
        InventoryItem third = new InventoryItem();

        //set the values for all items
        first.setName("Xbox Series X");
        first.setSerialNumber("A-XB1-24A-XY3");
        first.setValue(1499.99);

        second.setName("Samsung TV");
        second.setSerialNumber("S-40A-ZBD-E47");
        second.setValue(599.99);

        third.setName("Playstation 5");
        third.setSerialNumber("X-40A-ZBD-E47");
        third.setValue(1399.99);


        //create the second item
        //set the second item
        //add to list

        //create the third item
        //set the third item
        //add to list

        //create an observable list to store the contents of file list
        //create string for bufferedReader
        //create a try block with bufferedReader opening up file
            //create awhile loop to parse txt file
            //create a new item
            //parse each line into individual strings
            //convert first string to boolean
            //set each token into each item variable
            //add the item to the openFile list

        //assert the actualList is equal to openFileList
    }

    @Test
    void openListFromTsvFile() throws Exception{
        //create an actual list

        //create the items to fill actualList
        //set the first item
        //add to list


        //create the second item
        //set the second item
        //add to list

        //create the third item
        //set the third item
        //add to list

        //create an observable list to store the contents of file list
        //create string for bufferedReader
        //create a try block with bufferedReader opening up file
            //create awhile loop to parse txt file
                //create a new item
                //parse each line into individual strings
                //convert first string to boolean
                //set each token into each item variable
                //add the item to the openFile list

        //assert the actualList is equal to openFileList
    }

    @Test
    void openListFromJsonFile() throws Exception{
        //create an actual list

        //create the items to fill actualList
        //set the first item
        //add to list


        //create the second item
        //set the second item
        //add to list

        //create the third item
        //set the third item
        //add to list

        //create an observable list to store the contents of file list
        //create string for bufferedReader
        //create a try block with bufferedReader opening up file
            //create awhile loop to parse txt file
                //create a new item
                //parse each line into individual strings
                //convert first string to boolean
                //set each token into each item variable
                //add the item to the openFile list

        //assert the actualList is equal to openFileList
    }
}
package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

class SaveFileTest {

    @Test
    void saveListAsTxtFile() throws Exception{
        //create an actual string
        String actual = """
                A-XB1-24A-XY3,Xbox Series X,$1499.99,
                S-40A-ZBD-E47,Samsung TV,$599.99,
                X-40A-ZBD-E47,Playstation 5,$1399.99,
                """;
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
        actualList.add(first);

        second.setName("Samsung TV");
        second.setSerialNumber("S-40A-ZBD-E47");
        second.setValue(599.99);
        actualList.add(second);

        third.setName("Playstation 5");
        third.setSerialNumber("X-40A-ZBD-E47");
        third.setValue(1399.99);
        actualList.add(third);

        //create a stringBuilder to test
        StringBuilder testingText = new StringBuilder();
        //create a try block with buffered writer
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("testingFiles\\saveListAsTxtFile.txt"))) {
            //create for loop to loop through items and build the string
            for(InventoryItem item: actualList){
                //create a string for text file
                testingText.append(item.getSerialNumber()).append(",").append(item.getName()).append(",$").append(item.getValue()).append(",\n");
                //create a string to add to text file
                String text = item.getSerialNumber() + "," + item.getName() + ",$" + item.getValue() + ",\n";
                //would call the write method to write to file
                writer.write(text);
            }
        }
        //assertEquals the actualFile string and the string created
        assertEquals(actual, testingText.toString());
    }

    @Test
    void saveListAsTsvFile() throws Exception{
        //create an actual string

        //create an observableList

        //create the items to fill actualList
        //set the first item
        //add to list

        //create the second item
        //set the second item
        //add to list

        //create the third item
        //set the third item
        //add to list

        //create a stringBuilder to test
        //create a try block with buffered writer
            //create for loop to loop through items and build the string
                //create a string for text file
                //create a string to add to text file
                //would call the write method to write to file
        //assertEquals the actualFile string and the string created
    }

    @Test
    void saveListAsJsonFile() throws Exception{
        //create an actual string

        //create an observableList

        //create the items to fill actualList
        //set the first item
        //add to list

        //create the second item
        //set the second item
        //add to list

        //create the third item
        //set the third item
        //add to list

        //create a stringBuilder to test
        //create a try block with buffered writer
            //create for loop to loop through items and build the string
                //create a string for text file
                //create a string to add to text file
                //would call the write method to write to file
        //assertEquals the actualFile string and the string created
    }
}
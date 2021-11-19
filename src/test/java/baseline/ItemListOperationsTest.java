package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemListOperationsTest {
    //create an actual observableList
    ObservableList<InventoryItem> actualList = FXCollections.observableArrayList();

    @Test
    void addToList() {
        //create two items
        InventoryItem first = new InventoryItem();
        InventoryItem second = new InventoryItem();
        //set the values for all items
        first.setName("Xbox Series X");
        first.setSerialNumber("A-XB1-24A-XY3");
        first.setValue(1499.99);

        second.setName("Samsung TV");
        second.setSerialNumber("S-40A-ZBD-E47");
        second.setValue(599.99);

        //add the new items to list
        actualList.add(first);
        actualList.add(second);

        //create a new item
        InventoryItem testing = new InventoryItem();
        //set the variables
        testing.setName("Playstation 5");
        testing.setSerialNumber("X-40A-ZBD-E47");
        testing.setValue(1399.99);

        //boolean result
        boolean result = false;
        //for loop to check each item in actualList
        for(InventoryItem inventoryItem : actualList){
            if(inventoryItem.getSerialNumber().equals(testing.getSerialNumber())) {
                //set the result equal to zero
                result = true;
            }
        }
        if (!result) {
            //will return true/false to calling method based on result
            actualList.add(testing);
        }
        //assertFalse
        assertFalse(result);
    }

    @Test
    void deleteFromList() {
        //create an testingList
        ObservableList<InventoryItem> testing = FXCollections.observableArrayList();
        testing.setAll(actualList);
        //create a new item to delete
        InventoryItem newItem =  new InventoryItem();
        //set the variables for item
        newItem.setName("Star Wars Lego Set");
        newItem.setSerialNumber("T-908-P24-XYZ");
        newItem.setValue(99.99);
        //add new item to observable list
        testing.add(newItem);
        //now delete the new item
        testing.remove(newItem);
        //assertEquals testing with observable
        assertEquals(actualList, testing);
    }
}
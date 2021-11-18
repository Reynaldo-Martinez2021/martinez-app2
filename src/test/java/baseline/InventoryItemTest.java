package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryItemTest {

    @Test
    void testName(){
        //create an actual string
        SimpleStringProperty actual = new SimpleStringProperty("PS5");
        //create a new item
        InventoryItem testing = new InventoryItem();
        //set the name
        testing.setName("PS5");
        //assertEquals actual with testing.getName
        assertEquals(actual.get(), testing.getName());
    }

    @Test
    void testSerialNumber(){
        //create an actual string
        SimpleStringProperty actual = new SimpleStringProperty("A-XB1-24A-XY3");
        //create a new item
        InventoryItem testing = new InventoryItem();
        //set the serialNumber
        testing.setSerialNumber("A-XB1-24A-XY3");
        //assertEquals actual with testing.getSerialNumber
        assertEquals(actual.get(), testing.getSerialNumber());
    }

    @Test
    void testValue(){
        //create an actual double
        SimpleDoubleProperty actual = new SimpleDoubleProperty(1499.99);
        //create a new item
        InventoryItem testing = new InventoryItem();
        //set the value
        testing.setValue(1499.99);
        //assertEquals actual with testing.getValue
        assertEquals(actual.get(), testing.getValue());
    }
}
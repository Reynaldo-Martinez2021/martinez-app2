package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateItemInventoryTest {

    @Test
    void startProcess() {
        //create an actual item
        InventoryItem actual = new InventoryItem();
        //set the values for actual item
        actual.setName("PS5");
        actual.setSerialNumber("A-XB1-24A-XY3");
        actual.setValue(1499.00);
        //create an instance of item
        InventoryItem testing = new InventoryItem();
        //set the values for instance of item
        testing.setName("PS5");
        testing.setSerialNumber("A-XB1-24A-XY3");
        testing.setValue(1499.00);
        //assertEquals if the creation of the item is correct
        Assertions.assertEquals(actual.getName(), testing.getName());
        Assertions.assertEquals(actual.getSerialNumber(), testing.getSerialNumber());
        Assertions.assertEquals(actual.getValue(), testing.getValue());
    }
}
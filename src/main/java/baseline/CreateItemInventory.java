package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class CreateItemInventory {

    public InventoryItem startProcess(String name, String serialNumber, double value){
        //create an instance of inventoryItem
        InventoryItem newItem = new InventoryItem();
        //create an instance of ValidateTextFields
        ValidateTextFields validate = new ValidateTextFields();
        //check if each parameter either passes or not
        if((validate.validateName(name) && validate.validateSerialNumber(serialNumber) && validate.validateValue(value))){
            //set the new item variables
            newItem.setName(name);
            newItem.setSerialNumber(serialNumber);
            newItem.setValue(value);
            //return the new item
            return newItem;
        }else{
            return null;
        }
    }
}

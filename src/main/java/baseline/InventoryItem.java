package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class InventoryItem {
    private final SimpleStringProperty serialNumber = new SimpleStringProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleDoubleProperty value = new SimpleDoubleProperty();

    public SimpleStringProperty nameProperty(){
        return name;
    }

    public SimpleStringProperty serialNumberProperty(){
        return serialNumber;
    }

    public SimpleDoubleProperty valueProperty(){
        return value;
    }

    public final String getName(){
        return name.get();
    }

    public final void setName(String itemName){
        name.set(itemName);
    }

    public final String getSerialNumber(){
        return serialNumber.get();
    }

    public final void setSerialNumber(String itemSerialNumber){
        serialNumber.set(itemSerialNumber);
    }

    //check to see if "double" or "Double" works better
    public final double getValue(){
        return value.get();
    }

    public final void setValue(double itemValue){
        value.set(itemValue);
    }
}

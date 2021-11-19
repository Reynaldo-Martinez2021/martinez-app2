package baseline;

import javafx.collections.ObservableList;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class ItemListOperations {

    //create a function that will add new items to the list with item as parameter
    public boolean addToList(ObservableList<InventoryItem> controllerList, InventoryItem newItem) {
        //boolean result
        boolean result = false;
        //create an instance of popup
        for(InventoryItem item: controllerList){
            if(item.getSerialNumber().equals(newItem.getSerialNumber())){
                //create an instance of popup
                PopupMessage popup = new PopupMessage();
                popup.nonUniqueData();
                //set result to true
                result = true;
            }
        }
        //if result is false add newItem to controllerList
        if(!result){
            controllerList.add(newItem);
            return true;
        }else{
            return false;
        }
    }

    //this function will delete items from the list with Item index as parameter
    public void deleteFromList(ObservableList<InventoryItem> controllerList, InventoryItem item){
        //takes the item as parameter
        controllerList.remove(item);
    }
}

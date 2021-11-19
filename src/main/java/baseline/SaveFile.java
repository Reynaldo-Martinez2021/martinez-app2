package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveFile {

    public void saveListAsJsonFile(ObservableList<InventoryItem> inventoryItemList, File file){
        //create a list for JsonItem
        List<JsonItem> jsonItemList = new ArrayList<>();
        //create a loop for each inventory item in actual list
        for (InventoryItem inventoryItem : inventoryItemList) {
            //create a new JsonItem
            JsonItem newItem = new JsonItem();
            //set the name for serial number and name
            newItem.setName(inventoryItem.getName());
            newItem.setSerialNumber(inventoryItem.getSerialNumber());
            newItem.setValue(inventoryItem.getValue());
            //add the new item to the new list
            jsonItemList.add(newItem);
        }
        //create a json object
        JsonObject jo = new JsonObject();
        //create a json array
        JsonArray ja = new Gson().toJsonTree(jsonItemList).getAsJsonArray();
        //create a new gson for pretty printing
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //add Inventory list to json object
        jo.add("Inventory List", ja);
        //convert the json object to a string
        String print = gson.toJson(jo);

        //create a try block with buffered writer
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveListAsTsvFile(ObservableList<InventoryItem> inventoryItemList, File file){
        //create a try block with buffered writer
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            //create a string for the header of the file
            String header = "Serial Number\tName\tValue\n";
            //write the header to the tsv file
            writer.write(header);
            //create for loop to loop through items and build the string
            for(InventoryItem item: inventoryItemList){
                //create a string to add to text file
                String text = item.getSerialNumber() + "\t" + item.getName() + "\t$" + item.getValue() + "\n";
                //would call the write method to write to file
                writer.write(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveListAsHtmlFile(ObservableList<InventoryItem> inventoryItemList, File file){
        //create a try block with buffered writer
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            //create a string for the header
            String header = """
                    <style type="text/css">
                    .tg  {border-collapse:collapse;border-spacing:0;}
                    .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
                      overflow:hidden;padding:10px 5px;word-break:normal;}
                    .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
                      font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
                    .tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}
                    </style>
                    <table class="tg">
                    <thead>
                      <tr>
                        <th class="tg-0pky">Serial Number</th>
                        <th class="tg-0pky">Name</th>
                        <th class="tg-0pky">Value</th>
                      </tr>
                    </thead>
                    <tbody>
                    """;
            writer.write(header);
            //create for loop to loop through items and build the string
            for (InventoryItem item : inventoryItemList) {
                //write the tr
                writer.write("  <tr>\n");
                //create a string to add to text file
                String text = "    <td class=\"tg-0pky\">" + item.getSerialNumber() + "</td>\n    <td class=\"tg-0pky\">" + item.getName() + "</td>\n    <td class=\"tg-0pky\">" + item.getValue() + "</td>\n  </tr>\n";
                //would call the write method to write to file
                writer.write(text);
            }
            writer.write("</tbody>\n</table>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

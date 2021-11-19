package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaveFileTest {

    @Test
    void saveListAsJsonFile() throws Exception{
        //create an actual string
        String actual = """
                {
                  "Inventory List": [
                    {
                      "serialNumber": "A-XB1-24A-XY3",
                      "name": "Xbox Series X",
                      "value": "1499.99"
                    },
                    {
                      "serialNumber": "S-40A-ZBD-E47",
                      "name": "Samsung TV",
                      "value": "599.99"
                    },
                    {
                      "serialNumber": "X-40A-ZBD-E47",
                      "name": "Playstation 5",
                      "value": "1399.99"
                    }
                  ]
                }""";

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

        //create a list for JsonItem
        List<JsonItem> jsonItemList = new ArrayList<>();
        //create a loop for each inventory item in actual list
        for (InventoryItem inventoryItem : actualList) {
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
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("testingFiles\\saveListAsJsonFile.json"))) {
            writer.write(print);
        }
        //assertEquals the actualFile string and the string created
        assertEquals(actual, print);
    }

    @Test
    void saveListAsTsvFile() throws Exception{
        //create an actual string
        String actual = """
                Serial Number\tName\tValue
                A-XB1-24A-XY3\tXbox Series X\t$1499.99
                S-40A-ZBD-E47\tSamsung TV\t$599.99
                X-40A-ZBD-E47\tPlaystation 5\t$1399.99
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
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("testingFiles\\saveListAsTsvFile.txt"))) {
            //create a string for the header of the file
            String header = "Serial Number\tName\tValue\n";
            //write the header to the tsv file
            writer.write(header);
            //append the header to testing text
            testingText.append(header);
            //create for loop to loop through items and build the string
            for(InventoryItem item: actualList){
                //create a string for text file
                testingText.append(item.getSerialNumber()).append("\t").append(item.getName()).append("\t$").append(item.getValue()).append("\n");
                //create a string to add to text file
                String text = item.getSerialNumber() + "\t" + item.getName() + "\t$" + item.getValue() + "\n";
                //would call the write method to write to file
                writer.write(text);
            }
        }
        //assertEquals the actualFile string and the string created
        assertEquals(actual, testingText.toString());
    }

    @Test
    void saveListAsHtmlFile() throws Exception{
        //create an actual string
        String actual = """
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
                  <tr>
                    <td class="tg-0pky">A-XB1-24A-XY3</td>
                    <td class="tg-0pky">Xbox Series X</td>
                    <td class="tg-0pky">$1499.99</td>
                  </tr>
                  <tr>
                    <td class="tg-0pky">S-40A-ZBD-E47</td>
                    <td class="tg-0pky">Samsung TV</td>
                    <td class="tg-0pky">$599.99</td>
                  </tr>
                  <tr>
                    <td class="tg-0pky">X-40A-ZBD-E47</td>
                    <td class="tg-0pky">Playstation 5</td>
                    <td class="tg-0pky">$1399.99</td>
                  </tr>
                </tbody>
                </table>
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
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("testingFiles\\saveListAsHtmlFile.html"))) {
            //create a string for the header
            String header = "<style type=\"text/css\">\n" +
                    ".tg  {border-collapse:collapse;border-spacing:0;}\n" +
                    ".tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                    "  overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
                    ".tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                    "  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
                    ".tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}\n" +
                    "</style>\n<table class=\"tg\">\n<thead>\n  <tr>\n    <th class=\"tg-0pky\">Serial Number</th>\n    <th class=\"tg-0pky\">Name</th>\n    <th class=\"tg-0pky\">Value</th>\n  </tr>\n</thead>\n<tbody>\n";
            writer.write(header);
            //append the header to testing text
            testingText.append(header);
            //create for loop to loop through items and build the string
            for (InventoryItem item : actualList) {
                //write the tr
                writer.write("  <tr>\n");
                //append the tr to testingText
                testingText.append("  <tr>\n");
                //write <th> and item serial number
                testingText.append("    <td class=\"tg-0pky\">").append(item.getSerialNumber()).append("</td>\n");
                //write <th> and item name
                testingText.append("    <td class=\"tg-0pky\">").append(item.getName()).append("</td>\n");
                //write <th> and item value
                testingText.append("    <td class=\"tg-0pky\">$").append(item.getValue()).append("</td>\n");
                //create a string to add to text file
                String text = "    <td class=\"tg-0pky\">" + item.getSerialNumber() + "</td>\n    <td class=\"tg-0pky\">" + item.getName() + "</td>\n    <td class=\"tg-0pky\">" + item.getValue() + "</td>\n  </tr>\n";
                //would call the write method to write to file
                writer.write(text);
                testingText.append("  </tr>\n");
            }
            writer.write("</tbody>\n</table>\n");
            testingText.append("</tbody>\n</table>\n");
        }
        //assertEquals the actualFile string and the string created
        assertEquals(actual, testingText.toString());
    }
}
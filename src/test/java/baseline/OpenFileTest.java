package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class OpenFileTest {

    @Test
    void openListFromHtmlFile() throws Exception{
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

        //add the values
        actualList.add(first);
        actualList.add(second);
        actualList.add(third);

        //create an observable list to store the contents of file list
        ObservableList<InventoryItem> openFileList = FXCollections.observableArrayList();
        //create a try block with bufferedReader opening up file
        Document htmlDoc;
        //parse the html file
        htmlDoc = Jsoup.parse(new File("testingFiles\\saveListAsHtmlFile.html"), "ISO-8859-1");
        //set the table to the first table in the html file
        Element table = htmlDoc.selectFirst("table");
        //set row to the first tr
        assert table != null;
        Iterator<Element> row = table.select("tr").iterator();
        //skipping the <th>
        row.next();
        while(row.hasNext()){
            //create a new Item
            InventoryItem item = new InventoryItem();
            //set the Iterator ite to the "td"
            Iterator<Element> ite = row.next().select("td").iterator();
            //set the values for the new item based on the ite
            item.setSerialNumber(ite.next().text());
            item.setName(ite.next().text());
            item.setValue(Double.parseDouble(ite.next().text()));
            //add the new item to the new List
            openFileList.add(item);
        }
        //assert the actualList is equal to openFileList
        assertEquals(actualList.get(2).getSerialNumber(), openFileList.get(2).getSerialNumber());
        assertEquals(actualList.get(2).getName(), openFileList.get(2).getName());
        assertEquals(actualList.get(2).getValue(), openFileList.get(2).getValue());
    }

    @Test
    void openListFromTsvFile() throws Exception{
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

        //add the values
        actualList.add(first);
        actualList.add(second);
        actualList.add(third);

        //create an observable list to store the contents of file list
        ObservableList<InventoryItem> openFileList = FXCollections.observableArrayList();
        //create a try block with bufferedReader opening up file
        try(BufferedReader br = new BufferedReader(new FileReader("testingFiles\\saveListAsTsvFile.txt"))) {
            String line = br.readLine();
            //create awhile loop to parse txt file
            while((line = br.readLine()) != null) {
                //create a new item
                InventoryItem item = new InventoryItem();
                //parse each line into individual strings
                String[] tokens = line.split("\t");
                //set the values of item from tokens
                item.setSerialNumber(tokens[0]);
                item.setName(tokens[1]);
                item.setValue(Double.parseDouble(tokens[2].replace("$","")));
                //add the item to the openFile list
                openFileList.add(item);
            }
        }
        //assert the actualList is equal to openFileList
        assertEquals(actualList.get(2).getSerialNumber(), openFileList.get(2).getSerialNumber());
        assertEquals(actualList.get(2).getName(), openFileList.get(2).getName());
        assertEquals(actualList.get(2).getValue(), openFileList.get(2).getValue());
    }

    @Test
    void openListFromJsonFile() throws Exception{
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

        //add the values
        actualList.add(first);
        actualList.add(second);
        actualList.add(third);

        //create an instance of gson
        Gson gson = new Gson();
        //create try block with reader that opens json file
        try(Reader reader = new FileReader("testingFiles\\saveListAsJsonFile.json")) {
            //create json element
            JsonElement json = gson.fromJson(reader, JsonElement.class);
            //create a json string toJson
            String jsonInString = gson.toJson(json);
            //create a jsonObject
            JsonObject jsonObject = gson.fromJson(jsonInString, JsonObject.class);
            //parse the jsonObject to create an array
            JsonArray inventoryJsonArray = jsonObject.get("Inventory List").getAsJsonArray();
            //create observableList
            ObservableList<InventoryItem> itemList = FXCollections.observableArrayList();
            //call the fillArrayList method
            fillArrayList(inventoryJsonArray, itemList);
            //assert the actualList is equal to openFileList
            assertEquals(actualList.get(2).getSerialNumber(), itemList.get(2).getSerialNumber());
            assertEquals(actualList.get(2).getName(), itemList.get(2).getName());
            assertEquals(actualList.get(2).getValue(), itemList.get(2).getValue());
        }
    }

    void fillArrayList(JsonArray inventoryArray, ObservableList<InventoryItem> list) {
        //create a for loop to split the array
        for (int i = 0; i < inventoryArray.size(); i++) {
            //create an inventory item
            InventoryItem item = new InventoryItem();
            //create an object
            JsonObject object = inventoryArray.get(i).getAsJsonObject();
            //set the values from jsonArray
            item.setSerialNumber(object.get("serialNumber").getAsString());
            item.setName(object.get("name").getAsString());
            item.setValue(object.get("value").getAsDouble());
            //add the new item to the list
            list.add(item);
        }
    }
}
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
import javafx.scene.control.TableView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.Iterator;

public class OpenFile {

    public void openListFromHtmlFile(TableView<InventoryItem> tableView, ObservableList<InventoryItem> inventoryItemObservableList, File file){
        //create an observable list to store the contents of file list
        ObservableList<InventoryItem> htmlList = FXCollections.observableArrayList();
        //create a try block with bufferedReader opening up file
        Document htmlDoc;
        //parse the html file
        try {
            htmlDoc = Jsoup.parse(file, "ISO-8859-1");
            Element table = htmlDoc.selectFirst("table");
            Iterator<Element> row = null;
            if (table != null) {
                row = table.select("tr").iterator();
            }
            //skipping the <th>
            if (row != null) {
                row.next();
            }
            if (row != null) {
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
                    htmlList.add(item);
                }
            }
            //clear the table view of items
            tableView.getItems().clear();
            //set the new list to the table view
            tableView.setItems(htmlList);
            //set the values to the itemObservableList
            inventoryItemObservableList.setAll(htmlList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openListFromTsvFile(TableView<InventoryItem> tableView, ObservableList<InventoryItem> inventoryItemObservableList, File file){
        //create an observable list to store the contents of file list
        ObservableList<InventoryItem> tsvList = FXCollections.observableArrayList();
        //create a try block with bufferedReader opening up file
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
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
                tsvList.add(item);
            }
            //clear the table view of items
            tableView.getItems().clear();
            //set the new list to the table view
            tableView.setItems(tsvList);
            //set the values to the itemObservableList
            inventoryItemObservableList.setAll(tsvList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openListFromJsonFile(TableView<InventoryItem> tableView, ObservableList<InventoryItem> inventoryItemObservableList, File file){
        //create an observable list to store the contents of file list
        ObservableList<InventoryItem> jsonList = FXCollections.observableArrayList();
        //create an instance of json
        Gson gson = new Gson();
        //create a try block with bufferedReader opening up file
        try(Reader reader = new FileReader(file)){
            //create json element from the reader
            JsonElement json = gson.fromJson(reader, JsonElement.class);
            //create a json string to toJson
            String jsonInString = gson.toJson(json);
            //create a jsonObject for products from file
            JsonObject jsonObject = gson.fromJson(jsonInString, JsonObject.class);
            //parse the jsonObject to create an array
            JsonArray inventoryJsonArray = jsonObject.get("Inventory List").getAsJsonArray();
            //call the fillArrayList function
            fillArrayList(inventoryJsonArray, jsonList);

            //clear the table view of items
            tableView.getItems().clear();
            //set the new list to the table view
            tableView.setItems(jsonList);
            //set the values to the itemObservableList
            inventoryItemObservableList.setAll(jsonList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void fillArrayList(JsonArray inventoryArray, ObservableList<InventoryItem> list){
        //create a for loop to split the array
        for(int i = 0; i < inventoryArray.size(); i++){
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

package baseline;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class InventoryManagementController {

    //create an itemObservableList
    ObservableList<InventoryItem> inventoryItemObservableList = FXCollections.observableArrayList();

    //create a file chooser
    FileChooser fileChooser;

    //create a stage
    Stage stage;

    //enable table menu
    TableViewUtils tableUtils;

    //create an instance of popup
    PopupMessage popup;

    @FXML // fx:id="vBox"
    private VBox vBox;

    @FXML // fx:id="taskTitle"
    private TextField taskTitle;

    @FXML // fx:id="openList"
    private MenuItem openInventoryList;

    @FXML // fx:id="saveList"
    private MenuItem saveInventoryList;

    @FXML // fx:id="searchButton"
    private Button searchButton;

    @FXML // fx:id="searchTextField"
    private TextField searchTextField;

    @FXML // fx:id="cancelSearch"
    private Button cancelSearch;

    @FXML // fx:id="tableView"
    private TableView<InventoryItem> tableView;

    @FXML // fx:id="valueColumn"
    private TableColumn<InventoryItem, Double> itemValueColumn;

    @FXML // fx:id="itemNameColumn"
    private TableColumn<InventoryItem, String> itemNameColumn;

    @FXML // fx:id="serialNumberColumn"
    private TableColumn<InventoryItem, String> itemSerialNumberColumn;

    @FXML // fx:id="serialNumberTextField"
    private TextField itemSerialNumberTextField;

    @FXML // fx:id="itemNameTextField"
    private TextField itemNameTextField;

    @FXML // fx:id="itemValueTextField"
    private TextField itemValueTextField;

    @FXML // fx:id="addNewItemButton"
    private Button addNewItemButton;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL location, ResourceBundle resources) {
        //enable the table menu
        tableUtils = new TableViewUtils(tableView, inventoryItemObservableList);
        //set the tableView selection model
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //set the table row factory
        tableView.setRowFactory(param -> {
            //create a table row
            final TableRow<InventoryItem> row = new TableRow<>();
            //create a context menu
            final ContextMenu contextMenu = new ContextMenu();
            //add a menu option called remove
            final MenuItem removeMenuItem = new MenuItem("Remove");
            //have a set Action for the right click on the mouse
            removeMenuItem.setOnAction(event -> {
                //create an instance of itemListOperations
                ItemListOperations operations = new ItemListOperations();
                //delete the row from itemObservableList
                operations.deleteFromList(inventoryItemObservableList, row.getItem());
                //call the tableViewLoad function to repopulate the tableView
                tableViewLoad();
            });

            //add the removeMenu item
            contextMenu.getItems().add(removeMenuItem);
            //bind the row and context menu
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty()).then((ContextMenu)null).otherwise(contextMenu)
            );
            return row;
        });

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        //initialize the valueColumn and add cell factory
        //set table cell factory
        //set edit commit to the item's completed Variable

        //initialize the itemNameColumn and add cell factory
        //set the cell factory for editable cell
        //set the text alignment to center
        //set the editOnStart to call validate edit

        //initialize the itemSerialNumberColumn and add cell factory
        //set the cell factory for editable cell
        //set the text alignment to center
        //set the editOnStart to call validate edit

        //create new FileChooser() for fileChooser
    }

    //method to load tableView
    private void tableViewLoad(){
        //set the items to the tableView
    }

    @FXML //This method is called when the addNewItemButton is clicked
    void addNewItem() {
        //call create item passing in the textFields
        createItem(itemValueTextField, itemNameTextField, itemSerialNumberTextField);
    }

    //this method is called to create a new item for the inventory
    void createItem(TextField value, TextField name, TextField serialNumber){
        //create a new item
        //create an instance of CreateItem
        //pass the parameters to method inside create Item
    }

    //this is called to add a new item to the table view
    void addToTableView(InventoryItem newInventoryItem){
        //create an instance of ItemListOperations
        //call the addToList with observableList and newItem
            //if it does get added to the list clear the textField
        //call the tableViewLoad with the new updated ObservableList
    }

    @FXML //this method is called when the user wants to open a new file
    void openFiles() {
        //will delegate task to another method

    }

    //void this method will be called from openFiles to set up the file chooser
    void setUpFileChooserOpen(){
        //create an instance of openFile
        //create a stage from vBox and pass the stage to menuOptions
        //show the open dialog
        //check if file is not null
            //if not then call menus openList
            //check if fileName ends with .txt
                //subtract the .txt from taskTitle
            //else if fileName ends with .json
                //subtract the .json from taskTitle
            //else
                //subtract the .tsv from taskTitle
    }

    @FXML //this method is called when the user wants to save the current list
    void saveFiles() {
        //will delegate task to another method

    }

    //this method wil be called from saveFiles to set up the file chooser
    void setUpFileChooserSave(){
        //create an instance of saveFile
        //create a stage from vBox and pass the stage to menuOptions
        //set the name of file name
        //set extension filter for txt files, json files, or tsv files
        //add the extension
        //show the save dialog
        //if statement checking file is not null
    }

}

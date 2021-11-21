package baseline;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class InventoryManagementController {

    //create an itemObservableList
    ObservableList<InventoryItem> itemObservableList = FXCollections.observableArrayList();

    //create a file chooser
    FileChooser fileChooser;

    //create a stage
    Stage stage;

    //enable table menu
    TableViewUtils tableUtils;

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
        tableUtils = new TableViewUtils(tableView, itemObservableList);

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
                operations.deleteFromList(itemObservableList, row.getItem());
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


        //initialize the itemNameColumn and add cell factory
        itemNameColumn.setCellValueFactory(cd -> cd.getValue().nameProperty());
        //set the cell factory for editable cell
        itemNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //set the text alignment to center
        itemNameColumn.setStyle("-fx-alignment: CENTER;");
        //set the editOnStart to call validate edit
        itemNameColumn.setOnEditCommit((TableColumn.CellEditEvent<InventoryItem, String> t) -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));

        //initialize the itemSerialNumberColumn and add cell factory
        itemSerialNumberColumn.setCellValueFactory(cd -> cd.getValue().serialNumberProperty());
        //set the cell factory for editable cell
        itemSerialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //set the text alignment to center
        itemSerialNumberColumn.setStyle("-fx-alignment: CENTER;");
        //set the editOnStart to call validate edit
        itemSerialNumberColumn.setOnEditCommit((TableColumn.CellEditEvent<InventoryItem, String> t) -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setSerialNumber(t.getNewValue()));

        //initialize the valueColumn and add cell factory
        itemValueColumn.setCellValueFactory(cd -> cd.getValue().valueProperty().asObject());
//        //set table cell factory
//        itemValueColumn.setCellFactory(TextFieldTableCell.forTableColumn(itemValueColumn));
//        //set the text alignment to center
//        itemValueColumn.setStyle("-fx-alignment: CENTER;");
//        //set the editOnStart
//        itemValueColumn.setOnEditCommit((TableColumn.CellEditEvent<InventoryItem, String> t) -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));

        //create new FileChooser() for fileChooser
        fileChooser = new FileChooser();
    }

    //method to load tableView
    private void tableViewLoad(){
        //set the items to the tableView
        tableView.setItems(itemObservableList);
    }

    @FXML //This method is called when the addNewItemButton is clicked
    void addNewItem() {
        //call create item passing in the textFields
        createItem(itemValueTextField, itemNameTextField, itemSerialNumberTextField);
    }

    //this method is called to create a new item for the inventory
    void createItem(TextField value, TextField name, TextField serialNumber){
        //create a new item
        InventoryItem item = null;
        //create an instance of CreateItem
        CreateItemInventory create = new CreateItemInventory();
        if(value.getText().isBlank()){
            //create a popup message
            PopupMessage popup = new PopupMessage();
            popup.invalidValue();
        }else{
            //pass the parameters to method inside create Item
            item = create.startProcess(name.getText(), serialNumber.getText(), Double.parseDouble(value.getText()));
        }
        if(item != null){
            addToTableView(item);
        }
    }

    //this is called to add a new item to the table view
    void addToTableView(InventoryItem newInventoryItem){
        //create an instance of ItemListOperations
        ItemListOperations listOperations = new ItemListOperations();
        //call the addToList with observableList and newItem
        if(listOperations.addToList(itemObservableList, newInventoryItem)) {
            //if it does get added to the list clear the textField
            itemNameTextField.clear();
            itemSerialNumberTextField.clear();
            itemValueTextField.clear();
        }
        //call the tableViewLoad with the new updated ObservableList
        tableViewLoad();
    }

    @FXML //this method is called when the user wants to open a new file
    void openFiles() {
        //will delegate task to another method
        setUpFileChooserOpen();
    }

    //void this method will be called from openFiles to set up the file chooser
    void setUpFileChooserOpen(){
        //create an instance of openFile
        OpenFile open = new OpenFile();
        //create a stage from vBox and pass the stage to menuOptions
        stage = (Stage) vBox.getScene().getWindow();
        //show the open dialog
        File file = fileChooser.showOpenDialog(stage);
        //check if file is not null
        if(file != null) {
            //if not then call menus openList
            //check if fileName ends with .txt
            //subtract the .txt from taskTitle
            //else if fileName ends with .json
            //subtract the .json from taskTitle
            //else
            //subtract the .tsv from taskTitle
        }
    }

    @FXML //this method is called when the user wants to save the current list
    void saveFiles() {
        //will delegate task to another method
        setUpFileChooserSave();
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

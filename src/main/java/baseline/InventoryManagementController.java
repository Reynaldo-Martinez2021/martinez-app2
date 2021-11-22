package baseline;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class InventoryManagementController implements Initializable {

    //create an itemObservableList
    ObservableList<InventoryItem> itemObservableList = FXCollections.observableArrayList();

    //create a filteredList
    FilteredList<InventoryItem> filteredList;

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

    @FXML // fx:id="searchTextField"
    private TextField searchTextField;

    @FXML
    private ChoiceBox<String> searchChoiceBox;

    @FXML // fx:id="tableView"
    private TableView<InventoryItem> tableView;

    @FXML // fx:id="valueColumn"
    private TableColumn<InventoryItem, String> itemValueColumn;

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
        itemNameColumn.setOnEditCommit(event -> {
            //create a new item to hold the row
            InventoryItem item = event.getTableView().getItems().get(event.getTablePosition().getRow());
            //create an instance of validate
            ValidateTextFields validate = new ValidateTextFields();
            //create a try block
            try{
                //create a string for newValue
                String newValue = event.getNewValue();
                //if validate name returns true set the newValue
                if(validate.validateName(newValue)){
                    item.setName(newValue);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            //refresh the tableview
            tableView.refresh();
        });

        //initialize the itemSerialNumberColumn and add cell factory
        itemSerialNumberColumn.setCellValueFactory(cd -> cd.getValue().serialNumberProperty());
        //set the cell factory for editable cell
        itemSerialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //set the text alignment to center
        itemSerialNumberColumn.setStyle("-fx-alignment: CENTER;");
        //set the editOnStart to call validate edit
        itemSerialNumberColumn.setOnEditCommit(event -> {
            //create a new item to hold the row
            InventoryItem item = event.getTableView().getItems().get(event.getTablePosition().getRow());
            //create an instance of validate
            ValidateTextFields validate = new ValidateTextFields();
            //create a try block
            try{
                //create a string for newValue
                String newValue = event.getNewValue();
                //if validate returns true set the item
                if(validate.validateSerialNumber(newValue)){
                    item.setSerialNumber(newValue);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            //refresh the tableView
            tableView.refresh();
        });

        DecimalFormat currency = new DecimalFormat("$0.00");

        //initialize the valueColumn and add cell factory
        itemValueColumn.setCellValueFactory(cd -> cd.getValue().valueProperty());
        itemValueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //set table cell factory
//        itemValueColumn.setCellFactory(c -> new TableCell<>(){
//            @Override
//            public void updateItem(Double price, boolean empty){
//                super.updateItem(String.valueOf(price), empty);
//                if(empty){
//                    setText(null);
//                }else{
//                    setText(String.format("$%.2f", price));
//                }
//            }
//        });
        //set the text alignment to center
        itemValueColumn.setStyle("-fx-alignment: CENTER;");
        //set the editOnStart to call validate edit
        itemValueColumn.setOnEditCommit(event -> {
            //create a new item to hold the row
            InventoryItem item = event.getTableView().getItems().get(event.getTablePosition().getRow());
            //create an instance of validate
            ValidateTextFields validate = new ValidateTextFields();
            //create a try block
            try{
                //create a string for newValue
                String newValue = event.getNewValue();
                if(newValue == null){
                    PopupMessage popup = new PopupMessage();
                    popup.invalidValue();
                }else{
                    //if validate returns true set the item
                    if(validate.validateValue(Double.valueOf(newValue))){
                        item.setValue(Double.parseDouble(newValue));
                    }
                }
            }catch(Exception e){
                PopupMessage popup = new PopupMessage();
                popup.invalidValue();
            }
            //refresh the tableView
            tableView.refresh();
        });

        searchChoiceBox.getItems().addAll("Serial Number", "Name");
        searchChoiceBox.setValue("Serial Number");

        //create a filteredList<InventoryItem>
        filteredList = new FilteredList<>(itemObservableList, p -> true);

        searchTextField.textProperty().addListener((obs, oldValue, newValue) -> {
            //set the table items using the filtered list
            tableView.setItems(filteredList);
            switch(searchChoiceBox.getValue()){
                case "Serial Number":
                    filteredList.setPredicate(p-> p.getSerialNumber().toLowerCase(Locale.ROOT).contains(newValue.toLowerCase(Locale.ROOT).trim()));
                    break;
                case "Name":
                    filteredList.setPredicate(p-> p.getName().toLowerCase(Locale.ROOT).contains(newValue.toLowerCase(Locale.ROOT).trim()));
                    break;
            }
        });

        searchChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)->{
            if(newVal != null){
                searchTextField.setText("");
            }
        });

        //create new FileChooser() for fileChooser
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter txtExtension = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(txtExtension);
        FileChooser.ExtensionFilter jsonExtension = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(jsonExtension);
        FileChooser.ExtensionFilter htmlExtension = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(htmlExtension);
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
        if(value.getText().isBlank() || value.getText().matches("[a-zA-z]+")){
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
        //show the stage
        File file = fileChooser.showOpenDialog(stage);
        //check if file is not null
        if(file != null) {
            //check if file name contains html
            if(file.getName().contains(".html")) {
                //call html method
                open.openListFromHtmlFile(tableView,itemObservableList, file);
                //set the title and subtract the .html extension
                taskTitle.setText(file.getName().substring(0, file.getName().length()-5));
            }
            //else check if it is json
            else if(file.getName().contains(".json")){
                //call json method
                open.openListFromJsonFile(tableView, itemObservableList, file);
                //set the title and subtract the .json extension
                taskTitle.setText(file.getName().substring(0, file.getName().length()-5));
            }
            //check if fileName ends with .txt
            else if(file.getName().contains(".txt")){
                //call the tsv method
                open.openListFromTsvFile(tableView, itemObservableList, file);
                //set the title and subtract the .txt extension
                taskTitle.setText(file.getName().substring(0, file.getName().length()-4));
            }
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
        SaveFile save = new SaveFile();
        //create a stage from vBox and pass the stage to menuOptions
        stage = (Stage) vBox.getScene().getWindow();
        //set the name of file name
        fileChooser.setInitialFileName(taskTitle.getText());
        //show the save dialog
        File file = fileChooser.showSaveDialog(stage);
        //if statement checking file is not null
        if(file != null){
            if(file.getName().contains(".html")) save.saveListAsHtmlFile(itemObservableList, file);
            else if(file.getName().contains(".json")) save.saveListAsJsonFile(itemObservableList, file);
            else if(file.getName().contains(".txt")) save.saveListAsTsvFile(itemObservableList, file);
        }
    }


}

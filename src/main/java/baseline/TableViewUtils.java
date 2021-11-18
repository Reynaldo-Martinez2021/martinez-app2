package baseline;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableView;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class TableViewUtils {

    //create a final TableView variable
    //create a itemObservable list to set values
    //create a private contextMenu
    //create additionalMenuItems list


    public TableViewUtils(TableView<InventoryItem> tableView, ObservableList<InventoryItem> list) {
        super();
        //set table view to passed tableview
        //set the itemObservableList to the passed list

        //adding event and then implementing the event handler assignment
        //add a listener to tableMenuButton
        //if tableView button is visible register the listeners
    }

    private void registerListeners() {
        //create a node
        // replace mouse listener on "+" node
        //calling method to show context menu when buttonNode is pressed
        //show context menu and consume
    }

    protected void showContextMenu() {
        //create a node button
        //call the fixedHeader height

        // When the menu is already shown clicking the + button hides it.
        // Show the menu
        // Repositioning the menu to be aligned by its right side (keeping inside the table view)
    }

    private void setFixedHeader() {
        //setting the height of header
        //setting the default height
        //setting preferred height
    }

    private Node findButtonNode() {
        //get the tableHeaderRow from getTableHeaderRow()
        //if tableHeader == null return it

        //for loop to get each tableHeaderRow child
            //the child is identified as cornerRegion in TableHeaderRow.java
        return null;
    }

    private Node getTableHeaderRow() {
        //create a tableViewSkin
        //if skin is null then return it

        // get all children of the skin

        // find the TableHeaderRow child
        //if there is one return node
        return null;
    }

    private ContextMenu createContextMenu() {
        //create a new context menu

        // create new context menu

        //if showAllColumnsOperators is true start creating them
        if (isShowAllColumnsOperators()) {
            // select all item
            //add eventHandler and call doSelectAll
            //create customMenuItem
            //on click call doSelect all
            //set hideOnClick to false
            //add the cmi to tableView menu

            // deselect all item
            //add an eventHandler and call doDeselectAll
            //create customerMenuItem
            //on click call the doDeselectAll
            //set hideOnClick to false
            //add the cmi to tableView menu

            // separator
        }

        //create checkBox to clear the whole list
            //check to see if the checkBox is selected
                //create an instance of Popup
                    //returns true clear items
        //create a new customMenuItem
        //set HidOnClick to false
        //add the cmi to menu option

        return null;
    }

    //create a function to deselect each column
    protected void doDeselectAll(Event e) {
        //create a for loop to get each column
        //set each column to not visible
    }

    //create a function to select each column
    protected void doSelectAll(Event e) {
        //create a for loop to get each column
        //set each column to visible

    }

    public boolean isShowAllColumnsOperators() {
        //create boolean showColumns operators and set that value to true
        return false;
    }
}

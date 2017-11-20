package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class editItemController {
	
private Main main;
@FXML private Button select;
@FXML private Button saveItem;
@FXML private TextField itemNameBox;
@FXML private TextField itemCostBox;
@FXML private TextField itemNumberBox;
@FXML private TextField itemStockBox;
@FXML private ListView<String> listView=new ListView<String>();
@FXML private ListView<Integer> numberView=new ListView<Integer>();
@FXML private Button back;

public void setMain(Main main) {
	this.main=main;
	displayItems();
}
public void displayItems() {
	ObservableList<String> items=FXCollections.observableArrayList();
	ArrayList<Item>itemList=Main.itemList.getItemList();
	for(int i=0;i<Main.itemList.size();i++) {
		items.add(itemList.get(i).getItemName());
		
	}
	listView.setItems(items);

}
public void saveItem() throws IOException, ClassNotFoundException {
	if(!(itemNameBox==null))
	System.out.println(itemNameBox.getText());
	else
		System.out.println("Null");
	
	Item item=new Item(Integer.parseInt(itemNumberBox.getText()),
			itemNameBox.getText(),Double.parseDouble(itemCostBox.getText()),
			Integer.parseInt(itemStockBox.getText()));
	if(!(Main.itemList.exists(item.getItemNumber()))) {
	Main.itemList.addNewItem(item);}
	else {
		Main.itemList.updateItem(item);
	}
	itemNumberBox.clear();
	itemNameBox.clear();
	itemCostBox.clear();
	itemStockBox.clear();
	
	displayItems();

} 	
public void editItem() throws ClassNotFoundException, IOException {
	String itemName=listView.getSelectionModel().getSelectedItem();
	Item item=Main.itemList.findByName(itemName);
	itemNameBox.setText(item.getItemName());
	itemCostBox.setText(Double.toString(item.getCost()));
	itemNumberBox.setText(Integer.toString(item.getItemNumber()));
	itemStockBox.setText(Integer.toString(item.getStock()));
	Main.itemList.updateItem(item);
	
}
public void removeItem()
{
	String itemName=listView.getSelectionModel().getSelectedItem();
	Item item=Main.itemList.findByName(itemName);
	Main.itemList.removeItem(item);
	displayItems();

}

public void backHome() throws IOException {
	main.POSHome();}
}

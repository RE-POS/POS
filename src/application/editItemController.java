package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class editItemController {
	
private Main main;
@FXML private Label alreadyExists;
@FXML private Button select;
@FXML private Button saveItem;
@FXML private TextField itemNameBox;
@FXML private TextField itemCostBox;
@FXML private TextField itemNumberBox;
@FXML private TextField itemStockBox;
@FXML private ListView<String> listView=new ListView<String>();
@FXML private ListView<Integer> numberView=new ListView<Integer>();
@FXML private Button back;
@FXML private TableView<Item> itemView;
@FXML private TableColumn itemNameCol;
@FXML private TableColumn itemNumberCol;
private Boolean edit=false;

public void setMain(Main main) {
	this.main=main;
	displayItems();
}
public void displayItems() {
	ObservableList<String> items=FXCollections.observableArrayList();
	ObservableList<Item> data=FXCollections.observableArrayList();
	ArrayList<Item>itemList=Main.itemList.getItemList();
	for(int i=0;i<Main.itemList.size();i++) {
		items.add(itemList.get(i).getItemName());
		data.add(itemList.get(i));
		itemNameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("itemName"));
		itemNumberCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("itemNumber"));
		
	}
	itemView.setItems(data);
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
	if(!(Main.itemList.exists(item.getItemNumber())))
	{
		Main.itemList.addNewItem(item);
		edit=false;
		alreadyExists.setText("");
		itemNumberBox.clear();
		itemNameBox.clear();
		itemCostBox.clear();
		itemStockBox.clear();
		displayItems();
	}
	else if(edit==true) {
		Main.itemList.updateItem(item);
		edit=false;
		alreadyExists.setText("");
		itemNumberBox.clear();
		itemNameBox.clear();
		itemCostBox.clear();
		itemStockBox.clear();
		displayItems();
	}
	else {
		alreadyExists.setText("Item Number Already Exists");
		
	}
	

} 	
public void editItem() throws ClassNotFoundException, IOException {
	String itemName=itemView.getSelectionModel().getSelectedItem().getItemName();
	Item item=Main.itemList.findByName(itemName);
	itemNameBox.setText(item.getItemName());
	itemCostBox.setText(Double.toString(item.getCost()));
	itemNumberBox.setText(Integer.toString(item.getItemNumber()));
	itemStockBox.setText(Integer.toString(item.getStock()));
	Main.itemList.updateItem(item);
	edit=true;
	
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

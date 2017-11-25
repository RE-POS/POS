package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class POSMainController {
	private Main main;
	@FXML private TableView<cartList> cartTable;
	@FXML private Button home;
	@FXML private TableColumn quantity;
	@FXML private TableColumn itemName;
	@FXML private TableColumn totalCost;
	@FXML private TableColumn itemNameList;
	@FXML private TableColumn stock;
	@FXML private TableView<Item> allItems;
	@FXML private Label subTotal;

	
	public void setMain(Main main) {
		this.main=main;
		showAllItems();
		fillCart();
		getTotal();
	}
	public void goHome() throws IOException {
		main.POSHome();
	}
	public void showAllItems() {
		allItems.getItems().clear();
		ObservableList<Item> data=FXCollections.observableArrayList();
		ArrayList<Item> items=Main.itemList.getItemList();

		for(int i=0;i<main.itemList.size();i++) {
			
			data.add(items.get(i));
			itemNameList.setCellValueFactory(new PropertyValueFactory<Item,String>("itemName"));
			stock.setCellValueFactory(new PropertyValueFactory<Item,Integer>("stock"));
		}
		allItems.setItems(data);
	
	}
	public void fillCart() {
		cartTable.getItems().clear();
		ObservableList<cartList> data =FXCollections.observableArrayList();
		Cart cart=Main.cart;
		ArrayList<cartList> cartList=Main.cart.getCartList();
		for (int i=0;i<cart.size();i++) {
			data.add(cartList.get(i));
			quantity.setCellValueFactory(new PropertyValueFactory<cartList,String>("quantity"));
			itemName.setCellValueFactory(new PropertyValueFactory<cartList,String>("itemName"));
			totalCost.setCellValueFactory(new PropertyValueFactory<cartList,String>("totalCost"));
			
		}
		main.cart.printCart();
		cartTable.setItems(data);
		getTotal();

	}
	public void addToCart() {
		Item item=allItems.getSelectionModel().getSelectedItem();
		Main.cart.addItemCart(item, 1);
		Main.itemList.getItem(item.getItemNumber()).removeStock(1);
		fillCart();
		getTotal();
		showAllItems();
	}
	public void emptyCart() {
		Main.cart.emptyCart();
	}
	public void getTotal() {
		subTotal.setText("Subtotal: $"+Double.toString(Main.cart.getCartTotal())+"0");
		
	}
	public void removeFromCart(){
		if(!(cartTable.getSelectionModel().getSelectedItem()==null)){
		int itemNumber=cartTable.getSelectionModel().getSelectedItem().getItemNumber();
		Item item=Main.cart.findItem(itemNumber);
		Main.cart.removeFromCart(item.getItemNumber());
		Main.itemList.getItem(item.getItemNumber()).addStock(1);
		fillCart();
		showAllItems();
		getTotal();
		}
		}
		
}

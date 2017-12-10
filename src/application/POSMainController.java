package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class    POSMainController {
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
	@FXML private Button checkoutButton;
	@FXML private TextField searchBar=new TextField();
	@FXML
    Button quickAddOne = new Button();
    @FXML
    Button quickAddTwo = new Button();
    @FXML
    Button quickAddThree = new Button();
    @FXML
    Button quickAddFour = new Button();
    @FXML
    Button quickAddFive = new Button();
    @FXML
    Button quickAddSix = new Button();
    @FXML
    Button quickAddSeven = new Button();
    @FXML
    Button quickAddEight = new Button();
    @FXML
    Button quickAddNine = new Button();

	
	public void setMain(Main main) {
		this.main=main;
		showAllItems();
		fillCart();
		//getTotal();
	}
	public void goHome() throws IOException {
		main.POSHome();
	}
	public void showAllItems() {
		allItems.getItems().clear();
		ObservableList<Item> data=FXCollections.observableArrayList();
		ArrayList<Item> items=Main.itemList.getItemList();

		for(int i=0;i<Main.itemList.size();i++) {
			
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
		cartTable.setItems(data);
		getTotal();

	}
	@FXML
    public void initialize()
    {
        searchBarEventHandler();
        addToCart();
    }
	public void addToCart() {
      /*  if(!(allItems.getSelectionModel().isEmpty())){
        Item item=allItems.getSelectionModel().getSelectedItem();
        Main.cart.addItemCart(item, 1);
        Main.itemList.getItem(item.getItemNumber()).removeStock(1);
        fillCart();
        getTotal();
        showAllItems();
        }*/
        allItems.setRowFactory(tv -> {
            TableRow<Item> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                int count = event.getClickCount();
                if(count == 1 && event.isControlDown()) {
                    Item item = row.getItem();
                    updateQuickAdd(item);
                }
                else if(count == 2)
                {
                    Item item = row.getItem();
                    Main.cart.addItemCart(item, 1);
                    Main.itemList.getItem(item.getItemNumber()).removeStock(1);
                    fillCart();
                    getTotal();
                    showAllItems();
                }
            });
            return row;
        });
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
	public void checkOut() throws IOException, ClassNotFoundException{
		main.subtotal = Main.cart.getCartTotal();
		ItemList items = Main.itemList;
		ArrayList<cartList> cartList=Main.cart.getCartList();
		Item remove = new Item();
		for(int i = 0; i<cartList.size(); i++)
		{
			remove = items.getItem(cartList.get(i).getItemNumber());
			items.updateItem(remove);
		}

		//cartList.clear();
		main.checkoutTab();
	}
	public void searchBarEventHandler()
    {
		Search search = new Search();
        searchBar.setOnKeyTyped(event ->  {
            String search1 = searchBar.getText() + event.getCharacter();
            ArrayList<Item> results;
			try {
				if(searchBar.getText()==""){
					showAllItems();
				}
				else {
				results = search.search(search1);
	            ObservableList<Item> resultsReturned = (ObservableList<Item>) new TableViewPopulator().getObjectList(results);
	            allItems.setItems(resultsReturned);}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        });
    }

    private QuickAddMatrix quickAddMatrix = new QuickAddMatrix();

    public TreeMap<Integer, Button> createQuickAddList()
    {
        TreeMap<Integer, Button> quickAddMap = new TreeMap<>();
        quickAddMap.put(1, quickAddOne);
        quickAddMap.put(2, quickAddTwo);
        quickAddMap.put(3, quickAddThree);
        quickAddMap.put(4, quickAddFour);
        quickAddMap.put(5, quickAddFive);
        quickAddMap.put(6, quickAddSix);
        quickAddMap.put(7, quickAddSeven);
        quickAddMap.put(8, quickAddEight);
        quickAddMap.put(9, quickAddNine);
        return quickAddMap;
    }
public void populateQuickAddMatrix()
    {
        ArrayList<Item> quickAddValues = new ArrayList(quickAddMatrix.getQuickAddMatrix().values());
        for (int i = 0; i < createQuickAddList().size(); i++) {
            TreeMap<Integer, Button> quickAddMap = createQuickAddList();
            if(i < quickAddValues.size()) {
                quickAddMap.get(i + 1).setText(quickAddValues.get(i).getItemName());
                quickAddMap.get(i + 1).setUserData(quickAddValues.get(i).getItemNumber());
                quickAddMap.get(i + 1).setOnAction(event -> {
                    Button button = (Button)event.getSource();
                    int id = (int)button.getUserData();
                    Item item = Main.itemList.getItem(id);
                    Main.cart.addItemCart(item, 1);
                    Main.itemList.getItem(item.getItemNumber()).removeStock(1);
                    fillCart();
                    showAllItems();

                });
            }
            else if(i >= quickAddValues.size())
            {
                quickAddMap.get(i + 1).setText("");
                quickAddMap.get(i + 1).setUserData(0);
            }
        }
    }
public void updateQuickAdd(Item item)
    {
        quickAddMatrix.updateQuickAdd(item);
        populateQuickAddMatrix();
    }
		
}

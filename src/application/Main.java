package application;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	public static Cart cart;
	public static ItemList itemList;
	private Stage primaryStage;
	public static void main(String[] args) throws ClassNotFoundException, IOException 
	{

		File file=new File("Items");
		if(!(file.isDirectory()))
				{
			file.mkdirs();
				}
		itemList=new ItemList(file);
		itemList.populate();
		cart= new Cart(file, itemList);
		//cart.addItemCart(itemList.getItem(1), 2);
		//cart.addItemCart(itemList.getItem(4), 5);
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage=primaryStage;
		POSHome();
	}
	public void editItemWindow() throws IOException {
		FXMLLoader loader= new FXMLLoader(Main.class.getResource("editItemPOS.fxml"));
		Pane pane= loader.load();
		editItemController editItemConstroller= loader.getController();
		editItemConstroller.setMain(this);
		Scene scene= new Scene(pane);
		this.primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void POSHome() throws IOException {
		FXMLLoader loader2= new FXMLLoader(Main.class.getResource("POSHome.fxml"));
		Pane pane= loader2.load();	
		POSHomeController POSHomeController= loader2.getController();
		POSHomeController.setMain(this);
		Scene scene= new Scene(pane);
		this.primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void POSMain() throws IOException {
		FXMLLoader loader3= new FXMLLoader(Main.class.getResource("posMain.fxml"));
		VBox pane= loader3.load();	
		POSMainController posMainController= loader3.getController();
		posMainController.setMain(this);
		Scene scene= new Scene(pane);
		this.primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	

}

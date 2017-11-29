package application;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	public static Cart cart;
	public static ItemList itemList;
	private Stage primaryStage;
	private BorderPane borderPane;
	public static UserList users;
	public static User currentUser = new User();
	public double subtotal;
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
		File userFiles=new File("Users");
		if(!(userFiles.isDirectory()))
		{
			userFiles.mkdirs();
		}
		users=new UserList(userFiles);
		users.populate();

		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage=primaryStage;
		LogIn();
		
	}
	public void editItemWindow() throws IOException {
		FXMLLoader loader= new FXMLLoader(Main.class.getResource("editItemPOS.fxml"));
		Pane pane= loader.load();
		editItemController editItemConstroller= loader.getController();
		Parent rightSide=(Parent) pane;
		editItemConstroller.setMain(this);
		borderPane.setRight(rightSide);
	}
	public void POSHome() throws IOException {
		FXMLLoader loader2= new FXMLLoader(Main.class.getResource("newHome.fxml"));
		borderPane= loader2.load();	
		POSHomeController POSHomeController= loader2.getController();
		POSHomeController.setMain(this);
		Scene scene= new Scene(borderPane);
		this.primaryStage.setScene(scene);
		primaryStage.show();
		POSHomeController.start();
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
	public void LogIn()throws IOException{
		FXMLLoader loader4 = new FXMLLoader(Main.class.getResource("LogIN.fxml"));
		Pane pane = loader4.load();
		LogInController logInController = loader4.getController();
		logInController.setMain(this);
		Scene scene = new Scene(pane);
		this.primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void employeeTab() throws IOException{
		FXMLLoader loader5 = new FXMLLoader(Main.class.getResource("newEmployeeTab.fxml"));
		Pane pane = loader5.load();
		employeeTabController eController = loader5.getController();
		eController.setMain(this);
		Parent rightSide=(Parent) pane;
		borderPane.setRight(rightSide);
	}

	public void checkoutTab() throws IOException{
		FXMLLoader loader6 = new FXMLLoader(Main.class.getResource("Checkout.fxml"));
		Pane pane = loader6.load();
		checkoutController checkController = loader6.getController();
		checkController.setMain(this);
		Scene scene = new Scene(pane);
		this.primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	

}

package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

public class POSHomeController {

	private Main main;
	
	@FXML private Button editAddButton;
	@FXML private Button pOS;
public void setMain(Main main) {
	this.main=main;
}
	
	public void goToEditAddItem() throws IOException {
		main.editItemWindow();
	}
	public void goToPOS() throws IOException {
		main.POSMain();
		
	}

}

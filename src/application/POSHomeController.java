package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class POSHomeController {

	private Main main;
	@FXML private Button editAddButton;
	@FXML private Button pOS;
	@FXML private Button employeeButton;
	@FXML private Button logOutButton;
	@FXML private Label currentEmployee;
	@FXML private Pane pane;

public void setMain(Main main) {
	this.main=main;
}

	public void start() throws IOException {
		if(main.currentUser != null) {
			if (main.currentUser.getRole() == 'C') {
				editAddButton.setDisable(true);
				employeeButton.setDisable(true);
			}
			currentEmployee.setText("Welcome "+ Main.currentUser.getName());
		}
	}
	
	public void goToEditAddItem() throws IOException {
		main.editItemWindow();
	}
	public void goToPOS() throws IOException {
		main.POSMain();
	}

	public void goToEmployeeTab() throws IOException{
		main.employeeTab();
	}

	public void logOut() throws IOException{
		main.LogIn();
	}

}

package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class POSMainController {
	private Main main;
	@FXML private Button home;
	
	public void setMain(Main main) {
		this.main=main;
	}
	public void goHome() throws IOException {
		main.POSHome();
	}

}

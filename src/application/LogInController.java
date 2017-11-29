package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LogInController {

    private Main main;

    @FXML private Button LogInButton;
    @FXML private TextField LogInTextField;
    @FXML private Label loginFailed;

    public void setMain(Main main) {
        this.main=main;
    }

    public void enterLogIn() throws IOException
    {
        String employeeID = " ";
        if(LogInTextField != null)
            employeeID = LogInTextField.getText();
        else {
            System.out.println("Null");
            return;
        }

        UserList users = main.users;

        if(users.exists(employeeID)) {
            Main.currentUser = users.findById(employeeID);
            System.out.println(main.currentUser);
            main.POSHome();
            loginFailed.setText("");
        }
        else
            loginFailed.setText("LOGIN FAILED");
    }
}

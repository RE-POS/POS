package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class employeeTabController {
    private Main main;

    @FXML Button addButton;
    @FXML Button removeButton;
    @FXML Button backButton;
    @FXML TextField idAdd;
    @FXML TextField roleAdd;
    @FXML TextField nameAdd;
   // @FXML TextField statusField;
    @FXML TableView <User>employeeList;
    @FXML TableColumn ID;
    @FXML TableColumn employeeName;
    @FXML TableColumn role;
    @FXML Label statusField;
    @FXML Button editRoleButton;
    private Boolean editRole=false;
    private String employeeID;
    private char roleField;
    private String name;
    public void setMain(Main main) {
        this.main=main;
        getEmployees();
    }

    private UserList users = main.users;
    public void addNewEmployee() throws IOException{   
        if(idAdd != null){
            if(idAdd.getText().length() == 6 && !(users.exists(idAdd.getText())))
                employeeID = idAdd.getText();
            	
            else {
                addFailure();
                return;
            }
        }
        else {
            addFailure();
            return;
        }

        if(roleAdd != null) {
            if(roleAdd.getText().charAt(0) == 'M' || roleAdd.getText().charAt(0) == 'C')
                roleField = roleAdd.getText().charAt(0);
            else {
                addFailure();
                return;
            }
        }
        else {
            addFailure();
            return;
        }
        if(nameAdd != null && nameAdd.getText().length() <= 50)
            name = nameAdd.getText();
        else {
            addFailure();
            return;
        }
        User newUser = new User(employeeID, roleField, name);
        users.addUser(newUser);
        idAdd.clear();
        roleAdd.clear();
        nameAdd.clear();
        statusField.setText("ADDED EMPLOYEE");
        editRole=true;
        blockFields();
        getEmployees();
    }

    public void addFailure(){
        idAdd.clear();
        roleAdd.clear();
        nameAdd.clear();
        statusField.setText("ADD EMPLOYEE FAILED");
    }
    
    public void removeEmployee() throws IOException{
        if(employeeList.getSelectionModel() != null && users.exists(employeeList.getSelectionModel().getSelectedItem().getEmployeeID())){
            users.removeUser(employeeList.getSelectionModel().getSelectedItem().getEmployeeID());
            getEmployees();
            statusField.setText("REMOVED EMPLOYEE");
        }else
            statusField.setText("REMOVE EMPLOYEE FAILED");
    }
    public void blockFields(){
    	idAdd.setEditable(editRole);
    	nameAdd.setEditable(editRole);  	
    	
    }
    public void goBack() throws IOException
    {
        main.POSHome();
    }

	public void editRole() {
		User user =employeeList.getSelectionModel().getSelectedItem();
		editRole=false;
		blockFields();
		idAdd.setText(user.getEmployeeID());
		nameAdd.setText(user.getName());
		roleAdd.setText(Character.toString(user.getRole()));
		users.removeUser(user.getEmployeeID());
	}
	public void getEmployees(){
		ObservableList<User> data =FXCollections.observableArrayList();
		for(int i=0;i<users.size();i++) {
			User user=users.getUserType(i);
			data.add(user);
			ID.setCellValueFactory(new PropertyValueFactory<User,String>("employeeID"));
			employeeName.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
			role.setCellValueFactory(new PropertyValueFactory<User,String>("role"));

		}
		employeeList.setItems(data);

	}
}

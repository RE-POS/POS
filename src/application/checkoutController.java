package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class checkoutController {

    private Main main;
    private double salesTax = .06;

    @FXML private TextField subTotal_TextField;
    @FXML private TextField salesTax_TextField;
    @FXML private TextField total_TextField;
    @FXML private Button back;
    @FXML private TextField cash_TextField;
    @FXML private TextField change_TextField;
    @FXML private TextField cardNumber_TextField;
    @FXML private TextField name_TextField;
    @FXML private TextField date_TextField;
    @FXML private TextField securityCode_TextField;
    @FXML private TextField address_TextField;

    @FXML private Button payCash_Button;
    @FXML private Button payCard_Button;
    @FXML private Button done_Button;
    private Cart cart;

    public void setMain(Main main) {
        this.main=main;
        this.cart=Main.cart;
        done_Button.setDisable(true);
        setText();
    }

    public void setText ()
    {
        subTotal_TextField.setText("SubTotal: $" + main.subtotal + "0");
        salesTax_TextField.setText("SalesTax: $" + (main.subtotal*salesTax) + "0");
        total_TextField.setText("Total: $" + (main.subtotal+(main.subtotal*salesTax))+ "0");
    }

    public void done() throws IOException, MessageNotSentException
    {
    	Receipt receipt = new Receipt(cart);
        Email email = new Email();
        email.openCredentials();
        email.setMessage(receipt.getReceipt());
       email.sendEmail();
		Main.cart.emptyCart();

        main.POSHome();
    }

    public void payCash(){
        double total = main.subtotal+(main.subtotal*salesTax);
        String cashInputS = cash_TextField.getText();
        double cashInputD = Double.parseDouble(cashInputS);
        double changeDue;

        if(cashInputD >= total)
        {
            changeDue = cashInputD-total;
            change_TextField.setText("Change Due: $" + changeDue + "0");
            done_Button.setDisable(false);
        }else{
            change_TextField.setPromptText("Inadequate Payment");
            cash_TextField.setText("Inadequate Payment");
        }
    }

    public void payCard(){
    	done_Button.setDisable(false);

    }
    public void back() throws IOException {
    	main.POSMain();
    }

}

package application;

import application.*;
import application.MessageNotSentException;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {


    private Cart cart;
    private double total;

    public Receipt(Cart cart) {
        this.cart = cart;
        double total = cart.getCartTotal() * 1.07;
        this.total = Double.parseDouble(new DecimalFormat("0.00").format(total));
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getReceipt()
    {

        String message = "<h1>Order Submitted</a></h1>\n";
        message += "<br><b>Invoice:</b> " + new SimpleDateFormat().format(new Date());
        message += "<br><table style='border:1px solid black;border-collapse:collapse'>";
        message += "<br><tr>";
        message += "<br><th>Name</th>";
        message += "<br><th>Qty</th>";
        message += "<br><th>Unit Price</th>";
        message += "<br><th>Total Price</th>";
        message += "<br></tr>";
        DecimalFormat format = new DecimalFormat("0.00");
        for (cartList item: cart.getCartList()) {
            message += "<tr>";
            message += "<br><td>" + item.getItemName()+ "</td>";
            message += "<br><td>" + item.getQuantity() + "</td>";
            message += "<br><td>" + format.format(cart.findItem(item.getItemNumber()).getCost()) + "</td>";
            message += "<br><td>" + format.format(item.getTotalCost()) + "</td>";
            message += "</tr>";
        }
        message += "</table>";
        message += "<br><b>Subtotal:</b> $" + format.format(cart.getCartTotal());
        message += "<br><b>Tax:</b> $" + format.format(cart.getCartTotal() * .07);
        message += "<br><b>Total:</b> $" + format.format(getTotal());


        message += "<br><br><br>Thank you for shopping!";


        return message;
    }


}

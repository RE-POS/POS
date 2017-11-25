package application;

public class cartList {
private String itemName;
private double totalCost;
private int quantity;
private int itemNumber;

	public cartList() {
		itemName=null;
		totalCost=0.0;
		quantity=0;
		itemNumber=0;
	}
	public cartList(int itemNumber, String itemName,double totalCost,int quantity) {
		this.itemName=itemName;
		this.totalCost=totalCost;
		this.quantity=quantity;
		this.itemNumber=itemNumber;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}

package application;

import javafx.scene.image.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class Item extends Object implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private int itemNumber;
	private String itemName;
	private double cost;
	private int stock;
	private Image itemImage;
	private Item item;
	
	public Item() {
		itemNumber=0;
		itemName=null;
		cost=0;
		stock=0;
		itemImage=null;
	}
	public Item(int itemNumber,String itemName,double cost, int stock) {
		this.itemNumber=itemNumber;
		this.itemName=itemName;
		this.cost=cost;
		this.stock=stock;
	}
	public Item(int itemNumber,String itemName,double cost, int stock, Image itemImage) {
		this.itemNumber=itemNumber;
		this.itemName=itemName;
		this.cost=cost;
		this.stock=stock;
		this.itemImage=itemImage;
		item=new Item(itemNumber,itemName,cost,stock, itemImage);

		
	}
	public int getItemNumber()
	{
		return this.itemNumber;
	}
	public double getCost()
	{
		return this.cost;
	}
	public int getStock()
	{
		return this.stock;
	}
	public String getItemName() {
		return this.itemName;
	}
	public Image getImage()
	{
		return this.itemImage;
	}
	public void setItemNumber(int itemNumber)
	{
		this.itemNumber=itemNumber;
	}
	public void setItemName(String itemName)
	{
		this.itemName=itemName;
	}
	public void setCost(double cost) {
		this.cost=cost;
	}
	public void setImage(Image image) {
		this.itemImage=image;
	}
	public void setStock(int stock) {
		this.stock=stock;
	}
	public void removeStock(int sold) {
		this.stock-=sold;
	}
	public void addStock(int added) {
		this.stock+=added;
	}
	public String toString() {
		return "Item Name: "+ this.itemName+ "\nItem Cost: "+ this.cost;
	}
}

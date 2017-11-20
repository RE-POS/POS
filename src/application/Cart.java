package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Cart {
	private ItemList itemList;
	private ArrayList<ArrayList<Integer>>itemArray=new ArrayList<ArrayList<Integer>>();
	private double cartTotal;
	private File file;
	
	public Cart() {
		
		cartTotal=0.0;
	}
	public Cart(File file, ItemList itemList) {
		this.cartTotal=0.0;
		this.file=file;
		this.itemList=itemList;
		ArrayList<Integer>itemNumber=new ArrayList<Integer>();
		ArrayList<Integer>itemQuantity=new ArrayList<Integer>();
		itemArray.add(itemNumber);
		itemArray.add(itemQuantity);
		
	}
	public void addItemCart(Item item, int amount) {

		if (item.getStock()>0) {
			for(int i=0;i<itemArray.get(0).size();i++) {
				if(itemArray.get(0).get(i).equals(item.getItemNumber())){
					itemArray.get(1).set(i, amount+itemArray.get(1).get(i));

					cartTotal+=item.getCost()*amount;
					break;
				}
			}
		
				itemArray.get(0).add(item.getItemNumber());
				itemArray.get(1).add(amount);
				cartTotal+=item.getCost()*amount;
			
		}
		else {
			System.out.print("Out of Stock");
		}

		}
	public Item findItem(int itemNumber) {
		Item itemFound= itemList.getItem(itemNumber);
		return itemFound;
	}
	public void removeFromCart(int itemNumber) {
		if(itemArray.get(0).contains(itemNumber)) {
			int i=itemArray.get(0).indexOf(itemNumber);
			itemArray.get(0).remove(i);
			Item item=findItem(itemNumber);
			cartTotal-=item.getCost()*itemArray.get(1).get(i);
			itemArray.get(1).remove(i);
		}
	}
	public void emptyCart() {
		itemArray.clear();
		cartTotal=0.0;
	}
	public String printCart() {
		Item item;
		
		for (int i=0;i<itemArray.get(0).size();i++)
		{
			item=findItem(itemArray.get(0).get(i));
			System.out.println(item.toString());
		}
			
		
		return Double.toString(cartTotal);
	}


}

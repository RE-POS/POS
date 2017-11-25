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
	private ArrayList<cartList> cartList=new ArrayList<cartList>();
	
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
	public ArrayList<Integer> getItemNumbers(){
		return itemArray.get(0);
	}
	public ArrayList<Integer> getQuantities(){
		return itemArray.get(1);
	}
	public void addItemCart(Item item, int amount) {
		if (item.getStock()>0){
			if(itemArray.get(0).isEmpty()){	
				itemArray.get(0).add(item.getItemNumber());
				itemArray.get(1).add(amount);
				cartTotal+=item.getCost()*amount;
				cartList.add(new cartList(item.getItemNumber(),item.getItemName(),item.getCost()*amount,amount));
				
				}
			else {
				if(itemArray.get(0).contains(item.getItemNumber())) {
					for(int i=0;i<itemArray.get(0).size();i++) {
						if(itemArray.get(0).get(i)==item.getItemNumber()) {
						itemArray.get(1).set(i, amount+itemArray.get(1).get(i));
						cartTotal+=item.getCost()*amount;
						cartList.get(i).setQuantity(cartList.get(i).getQuantity()+amount);
						cartList.get(i).setTotalCost(cartList.get(i).getQuantity()*item.getCost());
					}}
					}
					else{
						itemArray.get(0).add(item.getItemNumber());
						itemArray.get(1).add(amount);
						cartTotal+=item.getCost()*amount;
						cartList.add(new cartList(item.getItemNumber(),item.getItemName(),item.getCost()*amount,amount));					
					}
				
			}
					
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
		for(int i=0;i<cartList.size();i++) {
			if(itemArray.get(0).get(i)==itemNumber) {
				if(itemArray.get(1).get(i)>1) {
					int amount=itemArray.get(1).get(i)-1;
					this.itemArray.get(1).remove(i);
					this.itemArray.get(1).add(i, amount);
					System.out.println(amount);
					Item item=findItem(itemNumber);
					cartTotal-=item.getCost();
					cartList.get(i).setQuantity(amount);
					
					}
				else {
					itemArray.get(0).remove(i);
					Item item=findItem(itemNumber);
					cartTotal-=item.getCost()*itemArray.get(1).get(i);
					itemArray.get(1).remove(i);
					cartList.remove(i);
				}
			}
			
		}
	}
	public ArrayList<cartList> getCartList() {
		return cartList;
	}
	public void setCartList(ArrayList<cartList> cartList) {
		this.cartList = cartList;
	}
	public void emptyCart() {
		itemArray.clear();
		cartList=new ArrayList<cartList>();
		cartTotal=0.0;
	}
	public double getCartTotal() {
		return cartTotal;
	}
	public void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
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
	public int size() {
		return itemArray.get(0).size();
	}


}

package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ItemList {
	private ArrayList<Item>itemList=new ArrayList<Item>();
	private File file;
	public ItemList(File file) {

		this.file=file;
	}
	public Item getItem(int itemNumber) {
		Item item=new Item();
		for(int i=0;i<itemList.size();i++) {
			if(itemList.get(i).getItemNumber()==itemNumber) {
				item=itemList.get(i);
				break;
			}
		}
		return item;
	}
	public void populate() throws ClassNotFoundException, IOException 
	{

		if (!(file.isDirectory())) {
			System.out.println("No Directory Found");
		}
		else {
			File[] itemFiles=file.listFiles();
			if(itemFiles.length>0) {
			ObjectInputStream ois=null;
			for(File file: itemFiles) {
			FileInputStream fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			this.itemList.add((Item)ois.readObject());
			System.out.println("Item added");
			ois.close();
			}
			}
			else {System.out.println("No Files Found");}
	
		

		
		
		}
	}
	public void addNewItem(Item item) throws IOException {
		itemList.add(item);
		FileOutputStream fos = new FileOutputStream(file+"/"+Integer.toString(item.getItemNumber()));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(item);
		oos.close();	
	}
	public int size() {
		return itemList.size();
	}
	public void print() {
		for(int i=0;i<itemList.size();i++) {
			System.out.println(this.itemList.get(i).toString());
			;
		}

	}
	public ArrayList<Item> getItemList(){ 
		return itemList;
	}
	public void updateItem(Item item) throws IOException, ClassNotFoundException {
		if (!(file.isDirectory())) {
			System.out.println("No Directory Found");
		}
		else {
			File[] itemFiles=file.listFiles();
			int index=0;
			for(int i=0;i<itemList.size();i++) {
				if(itemList.get(i).getItemNumber()==item.getItemNumber()) {
					itemList.remove(i);
					index=i;
				}
			}
			for (File file: itemFiles) {
				if(file.getName().contains(Integer.toString(item.getItemNumber()))) {
					file.delete();
					itemList.add(index,item);
					FileOutputStream fos = new FileOutputStream(this.file+"/"+Integer.toString(item.getItemNumber()));
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(item);
					oos.close();	
					break;
				}
			}

			
			}
		
	}
	public Item findByName(String itemName) {
		Item item=new Item();
		for(int i=0;i<itemList.size();i++) {
			if(itemList.get(i).getItemName()==itemName) {
				item=itemList.get(i);
				break;
			}
		}
		return item;
	}
	public boolean exists(int itemNumber) {
		for(int i=0;i<itemList.size();i++) {
			if(itemList.get(i).getItemNumber()==itemNumber) {
			return true;
			}
		}
		return false;
		
	}
	public void removeItem(Item item) {
		itemList.remove(item);
		if (!(file.isDirectory())) {
			System.out.println("No Directory Found");
		}
		else {
			File[] itemFiles=file.listFiles();
			for (File file: itemFiles) {
				if(file.getName().contains(Integer.toString(item.getItemNumber()))) {
					file.delete();}
			}
		}
	}

}


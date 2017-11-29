package application;

import application.ObjectIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Myles on 7/24/17.
 */
public class Search {

    public ArrayList<Item> search(String search) throws IOException, ClassNotFoundException {
        ObjectIO objectIO = new ObjectIO(new File("Items"));
        ItemList itemList = new ItemList(objectIO.getObjectFile());
        itemList.populate();
        ArrayList<Item> list = itemList.getItemList();
        ArrayList<Item> results = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Item student = contains(search, list.get(i));
            if(student != null)
            {
                results.add(student);
            }
        }
        if(!results.isEmpty()) {
            return results;
        }
        else {
            return list;
        }
    }

    public Item contains(String search, Item item)
    {
        String id = String.valueOf(item.getItemNumber());
        search = search.toLowerCase();
        if (id.contains(search)) {
            return item;
        } else if (item.getItemName().toLowerCase().contains(search)) {
            return item;
        }
        return null;
    }

    /*Pre-deprecate*/
    public String[] split(String search)
    {
        String[] searches = {""};
        if(search.contains(" "))
        {
            searches = search.split(" ");
        }
        else{
            searches[0] = search;
        }
        return searches;
    }


}

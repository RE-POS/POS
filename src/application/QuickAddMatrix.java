package application;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class QuickAddMatrix implements Serializable {

    private TreeMap<Integer, Item> quickAddMatrix = new TreeMap<>();
    private ObjectIO quickAddFile = new ObjectIO(new File("quickAdd.qad"));

    public void addToMatrix(Item item)
    {
        if(quickAddMatrix.size() < 9)
        {
            quickAddMatrix.put(item.getItemNumber(), item);
        }
        else if(quickAddMatrix.size() == 9)
        {
            quickAddMatrix.remove(quickAddMatrix.lastKey());
            quickAddMatrix.put(item.getItemNumber(), item);
        }
    }

    public void removeFromMatrix(Item item) {
        quickAddMatrix.remove(item.getItemNumber());
    }

    public boolean checkIfItemisInQuickAdd(Item item)
    {
        return quickAddMatrix.containsKey(item.getItemNumber());
    }

    public void writeQuickAdd()
    {
        quickAddFile.writeObject(this.quickAddMatrix);
    }

    public void readQuickAdd()
    {
        this.quickAddMatrix = (TreeMap<Integer, Item>) quickAddFile.readObject();
    }

    public void updateQuickAdd(Item item)
    {
        if(!checkIfItemisInQuickAdd(item))
        {
            addToMatrix(item);
        }
        else if(checkIfItemisInQuickAdd(item))
        {
            removeFromMatrix(item);
        }
        writeQuickAdd();
    }


    public Item get(Long id)
    {
        return quickAddMatrix.get(id);
    }

    public TreeMap<Integer, Item> getQuickAddMatrix() {
        readQuickAdd();
        return quickAddMatrix;
    }

    
}

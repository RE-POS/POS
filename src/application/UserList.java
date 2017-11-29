package application;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserList {

    private ArrayList<User> users = new ArrayList<User>();
    private File file;

    public UserList(File file)
    {
        this.file = file;
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public User getUser(String employeeID) {
        User user=new User();
        for(int i=0;i<users.size();i++) {
            if(users.get(i).getEmployeeID()==employeeID) {
                user=users.get(i);
                break;
            }
        }
        return user;
    }

    public void populate() throws ClassNotFoundException, IOException
    {

        if (!(file.isDirectory())) {
            System.out.println("No Directory Found");
        }
        else {
            File[] userFiles=file.listFiles();
            if(userFiles.length>0) {
                ObjectInputStream ois=null;
                for(File file: userFiles) {
                    FileInputStream fis = new FileInputStream(file);
                    ois = new ObjectInputStream(fis);
                    this.users.add((User)ois.readObject());
                    System.out.println("User added");
                    ois.close();
                }
            }
            else {System.out.println("No Files Found");}
        }
    }

    public void addUser(User newUser) throws IOException {
        users.add(newUser);
        FileOutputStream fos = new FileOutputStream(file+"/"+newUser.getEmployeeID());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(newUser);
        oos.close();
    }

    public User findById(String id) {
        User foundUser=new User();

        for(User user:users) {
            if(user.getEmployeeID().equals(id)) {
                foundUser = user;
                break;
            }
        }

        return foundUser;
    }

    public void removeUser(String employeeID) {
        User toRemove =  findById(employeeID);
        users.remove(toRemove);
        if (!(file.isDirectory())) {
            System.out.println("No Directory Found");
        }
        else {
            File[] userFiles=file.listFiles();
            for (File file: userFiles) {
                if(file.getName().contains(employeeID)) {
                    file.delete();}
            }
        }
    }

    public boolean exists(String id) {
        boolean found = false;

        for(User user:users) {
            if(user.getEmployeeID().equals(id)) {
                found = true;
                break;
            }
        }

        return found;
    }
    public int size() {
    	return users.size();
    }
    public User getUserType(int i) {
    	return users.get(i);
    }
}

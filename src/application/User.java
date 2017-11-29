package application;

import java.io.Serializable;

public class User extends Object implements Serializable {

    private static final long serialVersionUID = 42L;
    private String employeeID;
    private char role;
    private String name;

    public User()
    {
        employeeID = null;
        role = ' ';
        name = null;
    }

    public User(String employeeNumber, char role, String name)
    {
        this.employeeID = employeeNumber;
        this.role = role;
        this.name = name;
    }

    public String getEmployeeID(){return this.employeeID;}
    public void setEmployeeID(String newID){employeeID = newID;}
    public char getRole(){return this.role;}
    public void setRole(char newRole){role = newRole;}
    public String getName(){return this.name;}
    public void setName (String newName){name = newName;}
    public String toString(){return employeeID+"/"+role+"/"+name+"/";}
}

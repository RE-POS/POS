package application;

import java.io.*;
import java.util.TreeMap;

/**
 * Created by Myles on 3/29/17.
 */
public class ObjectIO {

    private File objectFile;

    public ObjectIO(File objectFile) {
        this.objectFile = new File(getApplicationDataFolder().getAbsoluteFile() + File.separator + objectFile.getName());
    }

    public ObjectIO() {
    }

    public File getObjectFile() {
        return objectFile;
    }

    public void setObjectFile(File objectFile) {
        this.objectFile = new File(getApplicationDataFolder().getAbsoluteFile() + File.separator + objectFile.getName());
    }

    public void writeObject(Object object) {
        OutputStream file = null;
        try {
            file = new FileOutputStream(getObjectFile());
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            try {
                output.writeObject(object);
            } finally {
                output.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readObject() {
        //use buffering
        Object object = new Object();
        try {
            InputStream file = new FileInputStream(getObjectFile());
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(getObjectFile())));

            //deserialize the List
            try {
                object = input.readObject();
            } finally {
                input.close();
            }
        }
        catch (FileNotFoundException ex)
        {
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public File getApplicationDataFolder()
    {
        File documentsFolder = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Java Workplace");
        File emailDataFolder = null;
        documentsFolder.mkdirs();
        if(documentsFolder.isDirectory())
        {
            emailDataFolder = new File(documentsFolder.getAbsolutePath() + File.separator + "Pos fx");
            emailDataFolder.mkdirs();
        }
        return emailDataFolder;

    }

  
}

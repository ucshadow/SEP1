import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author Nikolay D Nikolav, Yusuf A Farah, Radu G Orleanu, Catalin Udrea
 * @version 1.0
 */


public class MyFileIO {
    /**
     * Write object to file.
     * @param fileName takes file name.
     * @param obj takes specific object.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void writeToFile(String fileName, Object obj) throws FileNotFoundException, IOException {
        ObjectOutputStream writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName);
            writeToFile = new ObjectOutputStream(fileOutStream);

            writeToFile.writeObject(obj);
        } finally {
            if (writeToFile != null) {
                try {
                    writeToFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }
    }

    /**
     * Write multiple objects to file
     * @param fileName takes file name.
     * @param objs takes multiple objects.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void writeToFile(String fileName, Object[] objs) throws FileNotFoundException, IOException {
        ObjectOutputStream writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName);
            writeToFile = new ObjectOutputStream(fileOutStream);

            for (int i = 0; i < objs.length; i++) {
                writeToFile.writeObject(objs[i]);
            }
        } finally {
            if (writeToFile != null) {
                try {
                    writeToFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }
    }

    /**
     * Read object from file.
     * @param fileName takes file name.
     * @return object
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object readObjectFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        Object obj = null;
        ObjectInputStream readFromFile = null;
        try {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new ObjectInputStream(fileInStream);
            try {
                obj = readFromFile.readObject();
            } catch (EOFException eof) {
                //Done reading
            }
        } finally {
            if (readFromFile != null) {
                try {
                    readFromFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }

        return obj;
    }

    /**
     * Read array from file
     * @param fileName takes file name
     * @return ArrayList returns the whole list as an array list.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object[] readArrayFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Object> objs = new ArrayList<Object>();

        ObjectInputStream readFromFile = null;
        try {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new ObjectInputStream(fileInStream);
            while (true) {
                try {
                    objs.add(readFromFile.readObject());
                } catch (EOFException eof) {
                    //Done reading
                    break;
                }
            }
        } finally {
            if (readFromFile != null) {
                try {
                    readFromFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }

        return objs.toArray();
    }
}

import java.io.IOException;
import java.util.ArrayList;

public class FileAdapter {
    private MyFileIO fileIO;
    private String fileName;

    public FileAdapter(String fileName) {
        this.fileName = fileName;
    }

    public void writeToFile(Guest guest) {
        try {
            fileIO.writeToFile(fileName, guest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(Guest[] guests) {
        try {
            fileIO.writeToFile(fileName, guests);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFileObj(Object object) {
        try {
            fileIO.writeToFile(fileName, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Object> readFromFileObj(String fileName) {
        ArrayList<Object> read = new ArrayList<Object>();
        try {
            read.add(fileIO.readArrayFromFile(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return read;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Guest> getAllGuests(String fileName) {
        ArrayList<Guest> guests = new ArrayList<Guest>();
        Object[] fg = null;
        try {
            fg = fileIO.readArrayFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < fg.length; i++) {
            guests.add((Guest) fg[i]);
        }
        return guests;
    }
}

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileAdapter {
    private MyFileIO fileIO;
    private String fileName;

    public FileAdapter(String fileName) {
        this.fileName = fileName;
    }

    public void writeToFile(String fileName, Reservation reservation) {
        try {
            fileIO.writeToFile(fileName, reservation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void writeToFile(Reservation[] reservations) {
//        try {
//            fileIO.writeToFile(fileName, reservations);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public void writeToFileObj(String fileName, Object object) {
        try 0 {
            fileIO.writeToFile(fileName, object);
        } catch(IOException e){
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

//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }

    public ArrayList<Reservation> getAllGuests(String fileName) {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        Object[] fg = null;
        try {
            fg = fileIO.readArrayFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < fg.length; i++) {
            reservations.add((Reservation) fg[i]);
        }
        return reservations;
    }

    public void appendToFile(String fileName, Reservation reservation) {
        Object[] read = null;
        try {
            read = fileIO.readArrayFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object[] newValues = new Object[read.length + 1];
        for (int i = 0; i < read.length; i++) {
            newValues[i] = read[i];
        }
        newValues[newValues.length] = reservation;
        try {
            fileIO.writeToFile(fileName, newValues);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeSingleObjectFromFile(String fileName, Reservation reservation){
        Object[] read = null;
        try {
            read = fileIO.readArrayFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Object> lessValues = new ArrayList<Object>();
        for(int i = 0; i < read.length; i++){
            if(!(read[i].equals(reservation))){
                lessValues.add(read[i]);
            }
        }
        Object[] temp = new Object[lessValues.size()];
        lessValues.toArray(temp);
        try {
            fileIO.writeToFile(fileName, temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

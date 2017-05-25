import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class  which later on will be used to save a object from the HotelManager with the help of MyFileIO.
 * @author Nikolay D Nikolav, Yusuf A Farah, Radu G Orleanu, Catalin Udrea
 * @version 1.0
 */
public class FileAdapter implements Serializable {
    private MyFileIO fileIO = new MyFileIO();

    /**
     * Writing reservation to file.
     *
     * @param fileName    takes filename.
     * @param reservation takes specific reservation to write
     */
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

    /**
     * Write object to file.
     *
     * @param fileName takes file name.
     * @param object   takes object.
     */

    public void writeToFileObj(String fileName, Object object) {
        try {
            fileIO.writeToFile(fileName, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reading object from file.
     *
     * @param fileName takes file name.
     * @return object
     */
    public Object readFromFileObj(String fileName) {
        Object read = null;
        try {
            read = fileIO.readObjectFromFile(fileName);
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

    /**
     * Reading from file.
     *
     * @param fileName takes file name.
     * @return ArrayList<Reservtion> AllGuests returns all guests.
     */
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

    /**
     * Append to file.
     *
     * @param fileName    takes file name.
     * @param reservation takes specific reservation
     */
    public void appendToFile(String fileName, Reservation reservation) {

        Object[] read = null;
        try {
            read = fileIO.readArrayFromFile(fileName);
            //System.out.println(Arrays.toString(read));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object[] newValues = new Object[read.length + 1];
        for (int i = 0; i < read.length; i++) {
            newValues[i] = read[i];
        }
        newValues[newValues.length - 1] = reservation;
        try {
            fileIO.writeToFile(fileName, newValues);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove single reservation from file
     *
     * @param fileName    takes file name.
     * @param reservation takes specific reservation.
     */
    public void removeSingleObjectFromFile(String fileName, Reservation reservation) {
        Object[] read = null;
        try {
            read = fileIO.readArrayFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Object> lessValues = new ArrayList<Object>();
        for (int i = 0; i < read.length; i++) {
            if (!(read[i].equals(reservation))) {
                lessValues.add(read[i]);
            }
        }
        Object[] temp = new Object[lessValues.size()];
        lessValues.toArray(temp);
        //System.out.println(Arrays.toString(temp));
        try {
            fileIO.writeToFile(fileName, temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package modules.functional;

import main.MainClass;
import model.Country;
import model.Holiday;
import model.Tradition;
import sun.awt.image.ImageWatched;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Deprecated
public class FileWorking {

    public static void writeHoliday(LinkedList<Holiday> holidays, OutputStream outputStream) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(outputStream);
        output.writeObject(holidays);
        output.close();
    }

    public static LinkedList<Holiday> readHoliday(InputStream in) throws IOException, ClassNotFoundException {

        ObjectInputStream input = new ObjectInputStream(in);
        LinkedList<Holiday> holidays = (LinkedList<Holiday>) input.readObject();
        input.close();
        return holidays;
    }

    public static void writeCountry(LinkedList<Country> countries, OutputStream stream) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(stream);
        output.writeObject(countries);
        output.close();
    }

    public static LinkedList<Country> readCountry(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(in);
            LinkedList<Country> countries = (LinkedList<Country>) input.readObject();
            input.close();
        return countries;
    }

    public static void writeTradition(ArrayList<Tradition> traditions, OutputStream stream) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(stream);
        output.writeObject(traditions);
        output.close();
    }

    public static ArrayList<Tradition> readTradition(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(in);
        ArrayList<Tradition> traditions = (ArrayList<Tradition>) input.readObject();
            input.close();
        return traditions;
    }
}
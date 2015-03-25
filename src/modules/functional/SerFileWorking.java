package modules.functional;

import main.Resources;
import model.Country;
import model.Holiday;
import model.Tradition;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Михаил on 10.03.2015.
 */
public class SerFileWorking implements DataSaveLoad {

    private Country country = new Country("Россия");
    private Holiday holiday = new Holiday("Парампампам", 0);
    private Tradition tradition = new Tradition(holiday, country);
    private DataSaveLoad serSaveLoad = new SerFileWorking();


    @Override
    public void saveTradition(ArrayList<Tradition> traditions, String direct) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(direct);
        ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
        output.writeObject(traditions);
        output.close();

    }

    @Override
    public void saveHolidays(LinkedList<Holiday> holidays, String direct) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(direct);
        ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
        output.writeObject(holidays);
        output.close();

    }

    @Override
    public void saveCountry(LinkedList<Country> countries, String direct) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(direct);
        ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
        output.writeObject(countries);
        output.close();
    }

    @Override
    public ArrayList<Tradition> loadTradition(String direct) throws IOException, ClassNotFoundException {
        if ((new File(direct)).exists()) {
            FileInputStream stream = new FileInputStream(direct);
            ObjectInputStream input = new ObjectInputStream(stream);
            ArrayList<Tradition> traditions = (ArrayList<Tradition>) input.readObject();
            input.close();

            return traditions;
        } else {
            ArrayList<Tradition> traditionArrayList = new ArrayList<Tradition>();
            traditionArrayList.add(tradition);
            return traditionArrayList;
        }
    }

    @Override
    public LinkedList<Holiday> loadHoliday(String direct) throws IOException, ClassNotFoundException {
        if ((new File(direct)).exists()) {
            FileInputStream stream = new FileInputStream(direct);
            ObjectInputStream input = new ObjectInputStream(stream);
            LinkedList<Holiday> holidays = (LinkedList<Holiday>) input.readObject();
            input.close();
            return holidays;
        } else {
            LinkedList<Holiday> holidayLinkedList = new LinkedList<Holiday>();
            holidayLinkedList.add(holiday);
            return holidayLinkedList;
        }


    }

    @Override
    public LinkedList<Country> loadCountry(String direct) throws IOException, ClassNotFoundException {
        if ((new File(direct)).exists()) {
            FileInputStream stream = new FileInputStream(direct);
            ObjectInputStream input = new ObjectInputStream(stream);
            LinkedList<Country> countries = (LinkedList<Country>) input.readObject();
            input.close();
            return countries;
        } else {
            LinkedList<Country> countryLinkedList = new LinkedList<Country>();
            countryLinkedList.add(country);
            return countryLinkedList;
        }
    }


    public void loadAllEN() throws ClassNotFoundException, IOException, JDOMException, ParseException, SAXException {
        Resources.holidays = serSaveLoad.loadHoliday(HOLIDAY_PATCH_EN);
        Resources.countries = serSaveLoad.loadCountry(COUNTRY_PATCH_EN);
        Resources.traditions = serSaveLoad.loadTradition(TRADITION_PATCH_EN);

    }

    public void loadAllRU() throws ClassNotFoundException, IOException, JDOMException, ParseException, SAXException {
        Resources.holidays = serSaveLoad.loadHoliday(HOLIDAY_PATCH_RU);
        Resources.countries = serSaveLoad.loadCountry(COUNTRY_PATCH_RU);
        Resources.traditions = serSaveLoad.loadTradition(TRADITION_PATCH_RU);
    }

    public void saveAllEN() throws IOException {
        serSaveLoad.saveHolidays(Resources.holidays, HOLIDAY_PATCH_EN);
        serSaveLoad.saveCountry(Resources.countries, COUNTRY_PATCH_EN);
        serSaveLoad.saveTradition(Resources.traditions, TRADITION_PATCH_EN);
    }

    public void saveAllRU() throws IOException {
        serSaveLoad.saveHolidays(Resources.holidays, HOLIDAY_PATCH_RU);
        serSaveLoad.saveCountry(Resources.countries, COUNTRY_PATCH_RU);
        serSaveLoad.saveTradition(Resources.traditions, TRADITION_PATCH_RU);
    }
}
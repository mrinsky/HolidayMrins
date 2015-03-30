package modules.functional;

import model.Country;
import model.Holiday;
import model.Tradition;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;

/*
 * Created by Михаил on 09.03.2015.
 */

public  interface DataSaveLoad {
    public final static String XML_TRADITION_DEFAULT_RU = "resources/xml/default_save/traditionSave_ru.xml";
    public final static String XML_HOLIDAY_DEFAULT_RU = "resources/xml/default_save/holidaySave_ru.xml";
    public final static String XML_COUNTRY_DEFAULT_RU = "resources/xml/default_save/countrySave_ru.xml";
    public final static String XML_TRADITION_DEFAULT_EN = "resources/xml/default_save/traditionSave_en.xml";
    public final static String XML_HOLIDAY_DEFAULT_EN = "resources/xml/default_save/holidaySave_en.xml";
    public final static String XML_COUNTRY_DEFAULT_EN = "resources/xml/default_save/countrySave_en.xml";


    public final static String XML_TRADITION_PATCH_RU = "resources/xml/traditionSave_ru.xml";
    public final static String XML_HOLIDAY_PATCH_RU = "resources/xml/holidaySave_ru.xml";
    public final static String XML_COUNTRY_PATCH_RU = "resources/xml/countrySave_ru.xml";
    public final static String XML_TRADITION_PATCH_EN = "resources/xml/traditionSave_en.xml";
    public final static String XML_HOLIDAY_PATCH_EN = "resources/xml/holidaySave_en.xml";
    public final static String XML_COUNTRY_PATCH_EN = "resources/xml/countrySave_en.xml";

    public final static String XML_USERS = "resources/userList/user.xml";

    public final static String HOLIDAY_PATCH_EN = "resources/bin/holidays_en.bin";
    public final static String COUNTRY_PATCH_EN = "resources/bin/country_en.bin";
    public final static String TRADITION_PATCH_EN = "resources/bin/tradition_en.bin";
    public final static String HOLIDAY_PATCH_RU = "resources/bin/holidays_ru.bin";
    public final static String COUNTRY_PATCH_RU = "resources/bin/country_ru.bin";
    public final static String TRADITION_PATCH_RU = "resources/bin/tradition_ru.bin";

    public final static String TRADITION_XSD = "resources/XSD_Schems/TraditionXSD.xsd";
    public final static String HOLIDAY_XSD = "resources/XSD_Schems/HolidayXSD.xsd";
    public final static String COUNTRY_XSD = "resources/XSD_Schems/CountryXSD.xsd";
    public final static String USERS_XSD = "resources/XSD_Schems/UsersXSD.xsd";
    DataSaveLoad xmlSaveLoad = new XmlFileWorking();
//    DataSaveLoad serSaveLoad = new SerFileWorking();


    public void saveTradition(ArrayList<Tradition> traditions,String direct) throws IOException;
    public void saveHolidays(LinkedList<Holiday> holidays,String direct) throws IOException;
    public void saveCountry(LinkedList<Country> countries,String direct) throws IOException;
    public ArrayList<Tradition> loadTradition(String direct) throws IOException, JDOMException, ClassNotFoundException, ParseException, SAXException;
    public LinkedList<Holiday> loadHoliday(String direct) throws IOException, JDOMException, ClassNotFoundException, ParseException, SAXException;
    public LinkedList<Country> loadCountry(String direct) throws IOException, JDOMException, ClassNotFoundException, SAXException;
    public void loadAllEN() throws ClassNotFoundException, IOException, JDOMException, ParseException, SAXException;
    public void loadAllRU() throws ClassNotFoundException, IOException, JDOMException, ParseException, SAXException;
    public void saveAllEN() throws IOException;
    public void saveAllRU() throws IOException;


}

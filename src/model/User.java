package model;

import main.Resources;
import modules.functional.DataSaveLoad;
//import modules.functional.SerFileWorking;
import modules.functional.XmlFileWorking;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by root on 12.03.15.
 */
public class User {
    private String login;
    private String pass;
    private BigInteger _pass;
    private BigInteger key;
    private BigInteger modules;
    private int count = 0;
    private RSA rsa;
    private static LinkedList<Country> c_list = new LinkedList<Country>();
    private static LinkedList<Holiday> h_list = new LinkedList<Holiday>();
    private static ArrayList<Tradition> t_list = new ArrayList<Tradition>();
    protected static DataSaveLoad xmlFiles = new XmlFileWorking();
//    protected static DataSaveLoad serFiles = new SerFileWorking();

    /*************************
     * Constructor
     *************************/
    public User(String login, BigInteger pass, BigInteger key, BigInteger modules){
        this.login = login;
        rsa = new RSA();

        rsa.setModulus(modules);
        rsa.setPublicKey(key);

        _pass = rsa.encrypt(pass);

        //final String TRADITION_ADRESS = "resources/" + this.login + "traditionSave_ru.xml";
        //final String HOLIDAY_ADRESS = "resources/" + this.login + "holidaySave_ru.xml";
        //final String COUNTRY_ADRESS = "resources/" + this.login + "countrySave_ru.xml";


        File folder = new File("resources/" + login);
        if(folder.mkdir()) {


            File tradition = new File("resources/" + login + "/traditionSave_ru.xml");
            File country = new File("resources/" + login + "/countrySave_ru.xml");
            File holiday = new File("resources/" + login + "/holidaySave_ru.xml");
            //System.out.println("resources/" + login + "/traditionSave_ru.xml");
            tradition.getParentFile().mkdirs();
            country.getParentFile().mkdirs();
            holiday.getParentFile().mkdirs();
            try {
                tradition.createNewFile();
                country.createNewFile(); // Handle the returned value!
                holiday.createNewFile();
            } catch (IOException e) {
                e.printStackTrace(); // FIX!!!
            }
        }
    }

    public User() {

    }


    /*************************
     * Methods
     *************************/
    public LinkedList<Country> getCountryList() {
        return c_list;
    }
    public LinkedList<Holiday> getHolidayList() {
        return h_list;
    }
    public static void setTraditionList(ArrayList<Tradition> list){
        t_list = list;
    }
    public static void setCountryList(LinkedList<Country> list){
        c_list = list;
    }
    public static void setHolidayList(LinkedList<Holiday> list){
        h_list = list;
    }
    public static ArrayList<Tradition> getTraditionList(){
        return t_list;
    }
    void setPublicKey(BigInteger value){
        rsa.setPublicKey(value);
    }
    void setModules(BigInteger value){
        rsa.setModulus(value);
    }
    public void setPass(String pass){
        this._pass = new BigInteger(pass);
    }
    public BigInteger getPass(){
        return _pass;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public String getLogin(){
        return login;
    }
    //private String TRADITION_ADRESS = "resources/" + login + "/traditionSave_ru.xml";
    //private final String HOLIDAY_ADRESS = "resources/" + this.login + "/holidaySave_ru.xml";
    //private final String COUNTRY_ADRESS = "resources/" + this.login + "/countrySave_ru.xml";
    public void saveAllRU(){
        try {
            xmlFiles.saveTradition(t_list, "resources/" + login + "/traditionSave_ru.xml");
            xmlFiles.saveHolidays(h_list, "resources/" + this.login + "/holidaySave_ru.xml");
            xmlFiles.saveCountry(c_list, "resources/" + this.login + "/countrySave_ru.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadAllRU(){
        try {
            for (Tradition item : xmlFiles.loadTradition("resources/" + login + "/traditionSave_ru.xml")) {
                Resources.traditions.add(item);// = users.get(logins.indexOf(login)).getTraditionList();
                t_list.add(item);
            }
            for (Country item : xmlFiles.loadCountry("resources/" + this.login + "/countrySave_ru.xml")) {
                Resources.countries.add(item);// = users.get(logins.indexOf(login)).getTraditionList();
                c_list.add(item);
            }
            for (Holiday item : xmlFiles.loadHoliday("resources/" + this.login + "/holidaySave_ru.xml")) {
                Resources.holidays.add(item);// = users.get(logins.indexOf(login)).getTraditionList();
                h_list.add(item);
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    public boolean isAdmin(){
        return "admin".equals(login);
//
//
//        boolean result = false;
//        if (this.login.equals("admin")){
//            result = true;
//        }
//        return result;
    }
}

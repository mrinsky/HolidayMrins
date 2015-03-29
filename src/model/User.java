package model;

import main.MainClass;
import main.Resources;
import modules.functional.DataSaveLoad;
import modules.functional.XmlFileWorking;
import modules.user_interface.MainMenu;
import modules.user_interface.UserHandler;
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
    private final String ROOT = "resources/users/";
    private final String TRADITION_FILE_RU = "/traditionSave_ru.xml";
    private final String HOLIDAY_FILE_RU = "/holidaySave_ru.xml";
    private final String COUNTRY_FILE_RU = "/countrySave_ru.xml";
    private final String TRADITION_FILE_EN = "/traditionSave_en.xml";
    private final String HOLIDAY_FILE_EN = "/holidaySave_en.xml";
    private final String COUNTRY_FILE_EN = "/countrySave_en.xml";
    private String login;
    private BigInteger pass;
    private BigInteger modules;
    //private RSA rsa;
    private static LinkedList<Country> countries = new LinkedList<Country>();
    private static LinkedList<Holiday> holidays = new LinkedList<Holiday>();
    private static ArrayList<Tradition> traditions = new ArrayList<Tradition>();
    protected static DataSaveLoad xmlFiles = new XmlFileWorking();

    /**
     * **********************
     * Constructor
     * ***********************
     */
    public User(String login, BigInteger pass, BigInteger key, BigInteger modules) {
        this.login = login;
        //rsa = new RSA();

        this.modules = modules;
        UserHandler.rsa.setModulus(modules);
        UserHandler.rsa.setPublicKey(key);

        this.pass = UserHandler.rsa.encrypt(pass);

        File folder = new File(ROOT + this.login);
        if (folder.mkdir()) {

            File tradition = new File(ROOT + this.login + TRADITION_FILE_RU);
            File country = new File(ROOT + this.login + COUNTRY_FILE_RU);
            File holiday = new File(ROOT + this.login + HOLIDAY_FILE_RU);

            try {
                if (!(tradition.createNewFile() & country.createNewFile() & holiday.createNewFile())) {
                    throw new IOException(Resources.language.getFILES_BUILD_ERROR());
                }
            } catch (IOException e) {
                Resources.out.println(e.toString());
            }
        }
    }

    public User(String login, String pass) {
        this.login = login;

        this.modules = new BigInteger("114300212443049308755638385038607092399228059171843074638659728066396329731870812301666900170326603999649607364454783561463395729169397992550553334308251756497995161575531048559625701582012129417669546314420880750128408561569822198960212709010390091463374475374736305384151906473683969549684741213893356703077");
        UserHandler.rsa.setModulus(this.modules);
        UserHandler.rsa.setPublicKey(UserHandler.rsa.getPublicKey());
        BigInteger pass_value = new BigInteger(pass);
        this.pass = pass_value;
        //this.pass = rsa.encrypt(pass);
    }

    /**
     * **********************
     * Methods
     * ***********************
     */
    public LinkedList<Country> getCountryList() {
        return countries;
    }

    public LinkedList<Holiday> getHolidayList() {
        return holidays;
    }

    public static ArrayList<Tradition> getTraditionList() {
        return traditions;
    }

    public static void setCountryList(LinkedList<Country> list) {
        countries = list;
    }

    public static void setHolidayList(LinkedList<Holiday> list) {
        holidays = list;
    }

    public static void setTraditionList(ArrayList<Tradition> list) {
        traditions = list;
    }

    public void setModules(String value) {
        UserHandler.rsa.setModulus(new BigInteger(value));
    }

    public void setPass(String pass) {
        this.pass = new BigInteger(pass);
    }

    public BigInteger getPass() {
        return pass;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public BigInteger getModules() {
        return this.modules;
    }

    public void saveAllRU() {
        try {
            xmlFiles.saveTradition(traditions, ROOT + login + TRADITION_FILE_RU);
            xmlFiles.saveHolidays(holidays, ROOT + this.login + HOLIDAY_FILE_RU);
            xmlFiles.saveCountry(countries, ROOT + this.login + COUNTRY_FILE_RU);
        } catch (IOException e) {
            Resources.language.getIO_ERROR();
        }
    }

    public void loadAllRU() {
        try {
            for (Tradition item : xmlFiles.loadTradition(ROOT + login + TRADITION_FILE_RU)) {
                Resources.traditions.add(item);
                traditions.add(item);
            }
            for (Country item : xmlFiles.loadCountry(ROOT + this.login + COUNTRY_FILE_RU)) {
                Resources.countries.add(item);
                countries.add(item);
            }
            for (Holiday item : xmlFiles.loadHoliday(ROOT + this.login + HOLIDAY_FILE_RU)) {
                Resources.holidays.add(item);
                holidays.add(item);
            }
        } catch (ParseException e) {
            Resources.language.getPARSE_ERROR();
        } catch (IOException e) {
            Resources.language.getIO_ERROR();
        } catch (JDOMException e) {
            Resources.language.getXML_ERROR();
        } catch (ClassNotFoundException e) {
            Resources.language.getCLASS_NOT_FOUND_ERROR();
        } catch (SAXException e) {
            Resources.language.getXML_ERROR();
        }
    }

    public void saveAllEN() {
        try {
            xmlFiles.saveTradition(traditions, ROOT + login + TRADITION_FILE_EN);
            xmlFiles.saveHolidays(holidays, ROOT + this.login + HOLIDAY_FILE_EN);
            xmlFiles.saveCountry(countries, ROOT + this.login + COUNTRY_FILE_EN);
        } catch (IOException e) {
            Resources.language.getIO_ERROR();
        }
    }

    public void loadAllEN() {
        try {
            for (Tradition item : xmlFiles.loadTradition(ROOT + login + TRADITION_FILE_EN)) {
                Resources.traditions.add(item);
                traditions.add(item);
            }
            for (Country item : xmlFiles.loadCountry(ROOT + this.login + COUNTRY_FILE_EN)) {
                Resources.countries.add(item);
                countries.add(item);
            }
            for (Holiday item : xmlFiles.loadHoliday(ROOT + this.login + HOLIDAY_FILE_EN)) {
                Resources.holidays.add(item);
                holidays.add(item);
            }
        } catch (ParseException e) {
            Resources.language.getPARSE_ERROR();
        } catch (IOException e) {
            Resources.language.getIO_ERROR();
        } catch (JDOMException e) {
            Resources.language.getXML_ERROR();
        } catch (ClassNotFoundException e) {
            Resources.language.getCLASS_NOT_FOUND_ERROR();
        } catch (SAXException e) {
            Resources.language.getXML_ERROR();
        }
    }

    public boolean isAdmin() {
        return "admin".equals(login);
    }

}

package modules.functional;

import languages.Strings_EN;
import main.Resources;
import model.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by root on 05.04.15.
 */
public class UserData {
    public static int traditionCount = 0;
    public static int countryCount = 0;
    public static int holidayCount = 0;
    public static RSA rsa = new RSA();
    public static User currentUser;

    static ArrayList<User> users = new ArrayList<User>();

    public static String loadData(String login, String pass) {
        if (authentication(login, pass)) {
            traditionCount = Resources.traditions.size();
            countryCount = Resources.countries.size();
            holidayCount = Resources.holidays.size();

            currentUser = users.get(Search.searchIndex(users, login));

            if (Resources.language.getClass() == Strings_EN.class) {
                currentUser.loadAllEN();
            } else {
                currentUser.loadAllRU();
            }
            return "";
        }
        else return Resources.language.getLOGIN_OR_PASS_EXCEPTION();
    }

    public static void saveData() {
        ArrayList<Tradition> traditions = new ArrayList<Tradition>();
        for (int i = traditionCount; i < Resources.traditions.size(); i++) {
            traditions.add(Resources.traditions.get(i));
        }
        currentUser.setTraditionList(traditions);
        Remove.removeListTradition(traditions);
        LinkedList<Country> countries = new LinkedList<Country>();
        for (int i = countryCount; i < Resources.countries.size(); i++) {
            countries.add(Resources.countries.get(i));
        }
        currentUser.setCountryList(countries);
        Remove.removeListCountry(countries);
        LinkedList<Holiday> holidays = new LinkedList<Holiday>();
        for (int i = holidayCount; i < Resources.holidays.size(); i++) {
            holidays.add(Resources.holidays.get(i));
        }
        currentUser.setHolidayList(holidays);
        Remove.removeListHoliday(holidays);
        if (Resources.language.getClass() == Strings_EN.class) {
            currentUser.saveAllEN();
        } else {
            currentUser.saveAllRU();
        }
        traditionCount = 0;
        countryCount = 0;
        holidayCount = 0;
    }

    static boolean authentication(String login, String pass) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(login)) {
                index = i;
            }
        }

        BigInteger message = new BigInteger(pass.getBytes());
        BigInteger encrypt = UserData.rsa.encrypt(message);
        return (index > -1) ? encrypt.equals(users.get(index).getPass()) : false;
    }
}

package modules.user_interface;

import languages.Strings_EN;
import main.MainClass;
import main.Resources;
import model.*;
//import modules.functional.DataSaveLoad;
import modules.functional.Remove;
import modules.functional.Search;
//import modules.functional.SerFileWorking;
//import modules.functional.XmlFileWorking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by root on 15.03.15.
 */
public class UserHandler {
    protected static PrintWriter out = new PrintWriter(System.out, true);
    protected static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static int traditionCount = 0;
    public static int countryCount = 0;
    public static int holidayCount = 0;
    public static RSA rsa = new RSA();
    static User currentUser;

    public static ArrayList<User> users = new ArrayList<User>();

    private static boolean authorizate(String login, String pass){
        int index = 0;
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getLogin().equals(login)){
                index = i;
            }
        }

        BigInteger message = new BigInteger(pass.getBytes());
        BigInteger encrypt = rsa.encrypt(message);
        return encrypt.equals(users.get(index).getPass());
    }
    private static boolean checkLogin(String login){
        boolean result = false;
        for (int i = 0; i < users.size(); i++) {
            if (login.equals(users.get(i).getLogin())) {
                result = true;
            }
        }
        return result;
    }
    public static void registration(String login, String pass1, String pass2) {
        if (pass1.equals(pass2)) {

            BigInteger pass = new BigInteger(pass1.getBytes());
            User user = new User(login, pass, rsa.getPublicKey(),
                    rsa.getModulus());
            users.add(user);
            loadUserData(login, pass1);
        } else {
            throw new IllegalArgumentException(Resources.language.getPASS_EXCEPTION());
        }
    }
    private static void registration(){
        String login,
                pass1,
                pass2;

        try {
            while (true) {
                out.println(Resources.language.getLOGIN());
                login = reader.readLine();
                if (checkLogin(login)) {
                    throw new IllegalArgumentException(Resources.language.getLOGIN_EXCEPTION());
                }
                out.println(Resources.language.getPASS());
                pass1 = reader.readLine();
                out.println(Resources.language.getPASS());
                pass2 = reader.readLine();
                registration(login, pass1, pass2);
                break;
            }
        }catch (IllegalArgumentException e){
            out.println(e.getMessage());
            registration();
        }
        catch (IOException e){
            out.println(Resources.language.getIO_ERROR());
            MainMenu.mainMenu();
        }
    }
    private static void authorization() {
        String login,
                pass;
        while (true) {
            out.println(Resources.language.getLOGIN());
            try {
                login = reader.readLine();
                out.println(Resources.language.getPASS());
                pass = reader.readLine();
                loadUserData(login, pass);
            }
            catch (IOException exc){
                out.println(Resources.language.getIO_ERROR());
            }
            MainMenu.mainMenu();
        }
    }
    private static void loadUserData(String login, String pass){
        if (authorizate(login, pass)){
            out.println(Resources.language.getHELLO_USER() + login + "!");

            traditionCount = Resources.traditions.size();
            countryCount = Resources.countries.size();
            holidayCount = Resources.holidays.size();

            currentUser = users.get(Search.searchIndex(users, login));

            if (Resources.language.getClass() == Strings_EN.class) {
                currentUser.loadAllEN();
            }
            else {
                currentUser.loadAllRU();
            }
        }
        else {
            out.println(Resources.language.getLOGIN_OR_PASS_EXCEPTION());
            authorization();
        }
    }
    protected static void logIn(){
        out.println(Resources.language.getENTER_MESSAGE());
        int choice;
        try{
            choice = Integer.parseInt(reader.readLine());

            switch(choice){
                case 1:
                    authorization();
                    MainMenu.mainMenu();
                    break;
                case 2:
                    registration();
                    MainMenu.mainMenu();
                    break;
                case 3:
                    MainMenu.mainMenu();
                case 4:
                    MainMenu.exit();
                default:
                    out.println(Resources.language.getWRONG_CHOICE());
                    logIn();
                    break;
            }
        } catch (IOException e) {
            out.println(Resources.language.getIO_ERROR());
            logIn();
        }
    }
    protected static void logOut(){
        ArrayList<Tradition> traditions = new ArrayList<Tradition>();
        for (int i = traditionCount; i < Resources.traditions.size(); i++){
            traditions.add(Resources.traditions.get(i));
        }
        currentUser.setTraditionList(traditions);
        Remove.removeListTradition(traditions);
        LinkedList<Country> countries = new LinkedList<Country>();
        for (int i = countryCount; i < Resources.countries.size(); i++){
            countries.add(Resources.countries.get(i));
        }
        currentUser.setCountryList(countries);
        Remove.removeListCountry(countries);
        LinkedList<Holiday> holidays = new LinkedList<Holiday>();
        for (int i = holidayCount; i < Resources.holidays.size(); i++){
            holidays.add(Resources.holidays.get(i));
        }
        currentUser.setHolidayList(holidays);
        Remove.removeListHolidy(holidays);
        if (Resources.language.getClass() == Strings_EN.class) {
            currentUser.saveAllEN();
        }
        else{
            currentUser.saveAllRU();
        }
    }

}




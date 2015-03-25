package modules.user_interface;

import main.Resources;
import model.*;
//import modules.functional.DataSaveLoad;
import modules.functional.Remove;
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
    //protected static Language language = MainMenu.language;
    protected static PrintWriter out = new PrintWriter(System.out, true);
    protected static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    //protected static DataSaveLoad xmlFiles = new XmlFileWorking();
    //protected static DataSaveLoad serFiles = new SerFileWorking();
    public static int traditionCount = 0;
    public static int countryCount = 0;
    public static int holidayCount = 0;
    public static RSA rsa = new RSA();
    static User currentUser;
    public static ArrayList<String> logins = new ArrayList<String>();
    //private static ArrayList<BigInteger> passwords = new ArrayList<BigInteger>();
    public static ArrayList<User> users = new ArrayList<User>();

    public static boolean authorizate(String login, String pass){
        int index = 0;
        for (String value : logins){
            //out.println(value);
            if (value.equals(login))
                //out.println("yes");
                index = logins.indexOf(value);
        }
        //out.println(index);
        BigInteger message = new BigInteger(pass.getBytes());
        //out.println(users.get(index).getPass());
        BigInteger decrypt = rsa.decrypt(users.get(index).getPass());
        //out.print();
        //out.println(decrypt.toString());
        //out.println(message);
        if (decrypt.toString().equals(message.toString())){
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean checkLogin(String login){
        boolean result = false;
        for (String value : logins){
            if (login.equals(value)) {
                out.println("Пользователь с таким именем уже существует");
                result = true;
            }
        }
        return result;
    }

    private static void registration(){
        String login = "";
        String pass1 = "";
        String pass2 = "";

        try {
            out.println(Resources.language.getLOGIN());
            login = reader.readLine();
            out.println(Resources.language.getPASSWORD());
            pass1 = reader.readLine();
            out.println(Resources.language.getPASSWORD());
            pass2 = reader.readLine();
            registration(login, pass1, pass2);
        }catch (IOException e){
            //out.println(MainMenu.language.getIO_ERROR());
            out.println("Пароль не совпадает");
            MainMenu.mainMenu();
        }
    }

    public static void registration(String login, String pass1, String pass2)throws IOException {
        while (true) {
            if (UserHandler.checkLogin(login)){
                break;
            }
            if (pass1.equals(pass2)) {
                int N = 1024;

                UserHandler.rsa.init(N);

                BigInteger pass = new BigInteger(pass1.getBytes());
                User user = new User(login, pass, UserHandler.rsa.getPublicKey(),
                        UserHandler.rsa.getModulus());
                UserHandler.users.add(user);
            }
            else {
                throw new IOException();
            }
            break;
        }
    }

    private static void authorization() throws IOException {
        String login = "";
        String pass = "";
        while (true) {
            out.println(Resources.language.getLOGIN());
            login = reader.readLine();
            out.println(Resources.language.getPASSWORD());
            pass = reader.readLine();
            if (authorizate(login, pass)){
                out.println("Приветствую вас, " + login + "!");

                traditionCount = Resources.traditions.size();
                countryCount = Resources.countries.size();
                holidayCount = Resources.holidays.size();

                currentUser = users.get(logins.indexOf(login));

                currentUser.loadAllRU();
            }
            else {
                out.println("Неправильные логин и(или) пароль!");
                authorization();
               // break;
            }
            MainMenu.mainMenu();
        }
    }
    protected static void logIn(){
        out.println("Вход:\n1 - Авторизация\n2 - Регистрация\n3 - Гостевой режим");
        int choice = 0;
        try{
            choice = Integer.parseInt(reader.readLine());

            switch(choice){
                case 1:
                    authorization();
                    MainMenu.mainMenu();
                    break;
                case 2:
                    registration();
                    logIn();
                    MainMenu.mainMenu();
                    break;
                case 3:
                    MainMenu.mainMenu();
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

    protected static void logOut(User user){
        ArrayList<Tradition> t_list = new ArrayList<Tradition>();
        for (int i = traditionCount; i < Resources.traditions.size(); i++){
            t_list.add(Resources.traditions.get(i));
        }
        user.setTraditionList(t_list);
        Remove.removeListTradition(t_list);
        LinkedList<Country> c_list = new LinkedList<Country>();
        for (int i = countryCount; i < Resources.countries.size(); i++){
            c_list.add(Resources.countries.get(i));
        }
        user.setCountryList(c_list);
        Remove.removeListCountry(c_list);
        LinkedList<Holiday> h_list = new LinkedList<Holiday>();
        for (int i = holidayCount; i < Resources.holidays.size(); i++){
            h_list.add(Resources.holidays.get(i));
        }
        user.setHolidayList(h_list);
        Remove.removeListHolidy(h_list);
       // user.saveAllRU();
    }

}

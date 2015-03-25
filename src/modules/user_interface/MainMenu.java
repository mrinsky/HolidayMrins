package modules.user_interface;

import languages.Strings_EN;
import languages.Strings_RU;
//import main.MainClass;
import main.Resources;
import model.*;
import modules.functional.DataSaveLoad;
//import modules.functional.Remove;
//import modules.functional.SerFileWorking;
import modules.functional.XmlFileWorking;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import java.io.*;
import java.math.BigInteger;
import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.LinkedList;
// import java.util.List;

public class MainMenu {

    protected static PrintWriter out = new PrintWriter(System.out, true);
    protected static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    protected static DataSaveLoad xmlFiles = new XmlFileWorking();
    //protected static DataSaveLoad serFiles = new SerFileWorking();
    //public static int traditionCount = 0;
    //public static int countryCount = 0;
    //public static int holidayCount = 0;
    //private static RSA rsa = new RSA();
    //private static User currentUser;

    //region Completed
    public static void init() {
        chooseLocale();
    }

    private static void chooseLocale() {
        int N = 1024;

        UserHandler.rsa.init(N);
        //User user = new User(login, pass1);
        String login = "user";
        String pass1 = "12345";
        BigInteger pass = new BigInteger(pass1.getBytes());
        User user = new User(login, pass, UserHandler.rsa.getPublicKey(),
                UserHandler.rsa.getModulus());
        //passwords.add(pass);
        //user.setCount();
        UserHandler.users.add(user);
        UserHandler.logins.add(login);
        int choice = 0;
        while (true) {
            out.println(Resources.language.getSTART_CHOICE());
            try {
                choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    case 1:
                        Resources.language = new Strings_RU();
                        readArrays();
                        UserHandler.logIn();
                        //mainMenu();
                        break;
                    case 2:
                        Resources.language = new Strings_EN();
                        readArrays();
                        UserHandler.logIn();
                        //mainMenu();
                        break;
                    default:
                        out.println(Resources.language.getWRONG_CHOICE());
                }
            } catch (NumberFormatException ex) {
                out.println(Resources.language.getWRONG_CHOICE());
            } catch (IOException ex) {
                out.println(Resources.language.getIO_ERROR());
            }
        }
    }

    protected static void mainMenu() {
        out.println(Resources.language.getMAIN_MENU());
        int choice = 0;
        try {
            choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    AddHandler.addMenu();
                    break;
                case 2:
                    SearchHandler.searchMenu();
                    break;
                case 3:
                    PrintHandler.showMenu();
                    break;
                case 4:
                    if (Resources.language.getClass() == Strings_EN.class) help("./resources/help_en.txt");
                    else help("./resources/help_ru.txt");
                    break;
                case 5:
                    exit();
                    break;
                default:
                    out.println(Resources.language.getWRONG_CHOICE());
                    mainMenu();
                    break;
            }
        } catch (NumberFormatException ex) {
            out.println(Resources.language.getWRONG_CHOICE());
            mainMenu();
        } catch (IOException e) {
            out.println(Resources.language.getIO_ERROR());
            mainMenu();
        }
    }

    private static void help(String path) {

        File helpFile = new File(path);
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(helpFile));
            while (fileReader.ready()) out.println(fileReader.readLine());

            out.println(Resources.language.getHELP_MENU());
            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1:
                    mainMenu();
                    break;
                case 2:
                    exit();
                    break;
                default:
                    out.println(Resources.language.getWRONG_CHOICE());
                    mainMenu();
            }
        } catch (FileNotFoundException e) {
            out.println(Resources.language.getHELP_FILE_ERROR());
            mainMenu();
        } catch (IOException e) {
            out.println(Resources.language.getIO_ERROR());
            mainMenu();
        } catch (NumberFormatException ex) {
            out.println(Resources.language.getWRONG_CHOICE());
            mainMenu();
        }
    }

    protected static void exit() { //Куча Исключений, нужны try и catch
        try {
            //UserHandler.logOut(UserHandler.currentUser);
            writeArrays();

            reader.close();
            out.close();
            System.exit(0);
        } catch (IOException e) {  // прописать сюда ошибкииииииии

        } catch (JDOMException e) {

        } catch (ParseException e) {

        } catch (ClassNotFoundException e) {

        }
    }

    private static void readArrays() throws IOException {
        try {
            if (Resources.language.getClass() == Strings_EN.class) xmlFiles.loadAllEN();
            else xmlFiles.loadAllRU();

        } catch (ClassNotFoundException ex) {
            MainMenu.out.println(Resources.language.getNO_CLASS());
        } catch (JDOMException e) {  //подписать сюда какие нибудь ошибки

        } catch (ParseException e) {// И СЮДААААААААА

        } catch (SAXException e) {
                    }
    }

    private static void writeArrays() throws IOException, JDOMException, ParseException, ClassNotFoundException {

        if (Resources.language.getClass() == Strings_EN.class) {
            xmlFiles.saveAllEN();
           // serFiles.saveAllEN();
        } else {
            xmlFiles.saveAllRU();
         //   serFiles.saveAllRU();
        }
    }
    //endregion
}

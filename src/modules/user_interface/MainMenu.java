package modules.user_interface;

import languages.Strings_EN;
import languages.Strings_RU;
import main.Resources;
import modules.functional.DataSaveLoad;
import modules.functional.XmlFileWorking;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import java.io.*;
import java.text.ParseException;

public class MainMenu {
    protected static PrintWriter out = new PrintWriter(System.out, true);
    protected static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    protected static DataSaveLoad xmlFiles = new XmlFileWorking();

    public static void init() {
        chooseLocale();
    }

    private static void chooseLocale() {
        int N = 1024;
        UserHandler.rsa.init(N);
        int choice;
        while (true) {
            out.println(Resources.language.getSTART_CHOICE());
            try {
                choice = Integer.parseInt(reader.readLine());
                switch (choice) {
                    case 1:
                        Resources.language = new Strings_RU();
                        readArrays();
                        if (UserHandler.currentUser == null) {
                            UserHandler.logIn();
                        } else {
                            mainMenu();
                        }
                        break;
                    case 2:
                        Resources.language = new Strings_EN();
                        readArrays();
                        if (UserHandler.currentUser == null) {
                            UserHandler.logIn();
                        } else {
                            mainMenu();
                        }
                        break;
                    case 3:
                        System.exit(0);
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
        if (UserHandler.currentUser != null) {
            out.println(Resources.language.getMAIN_MENU());
        } else {
            out.println(Resources.language.getGUEST_MAIN_MENU());
        }
        int choice;
        boolean guest_flag = false;
        try {
            choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1:
                    if (UserHandler.currentUser != null) {
                        AddHandler.addMenu();
                        break;
                    } else {
                        guest_flag = true;
                    }
                case 2:
                    if (UserHandler.currentUser != null || guest_flag) {
                        SearchHandler.searchMenu();
                        break;
                    } else {
                        guest_flag = true;
                    }
                case 3:
                    if (UserHandler.currentUser != null || guest_flag) {
                        PrintHandler.showMenu();
                        break;
                    } else {
                        guest_flag = true;
                    }
                case 4:
                    if (UserHandler.currentUser != null || guest_flag) {
                        if (Resources.language.getClass() == Strings_EN.class) help("./resources/helps/help_en.txt");
                        else help("./resources/helps/help_ru.txt");
                        break;
                    } else {
                        guest_flag = true;
                    }
                case 5:
                    if (UserHandler.currentUser != null || guest_flag) {
                        chooseLocale();
                        break;
                    }
                case 6:
                    if (UserHandler.currentUser != null) {
                        UserHandler.logOut();
                        UserHandler.logIn();
                        break;
                    }
                case 7:
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
            if (UserHandler.currentUser != null) {
                UserHandler.logOut();
            }
            writeArrays();
            reader.close();
            out.close();
            System.exit(0);
        } catch (IOException e) { // прописать сюда ошибкииииииии
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
        } catch (JDOMException e) { //подписать сюда какие нибудь ошибки
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
// serFiles.saveAllRU();
        }
    }
//endregion
}

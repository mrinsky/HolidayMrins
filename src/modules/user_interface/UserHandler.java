package modules.user_interface;

import languages.Strings_EN;
import main.MainClass;
import main.Resources;
import model.*;
//import modules.functional.DataSaveLoad;
import modules.functional.Registration;
import modules.functional.Remove;
import modules.functional.Search;
import modules.functional.UserData;
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

    /*******************
     * Constructors
     *******************/
    private UserHandler() {}

    /******************
     * Methods
     ******************/
    private static void registration() {
        String login,
                pass1,
                pass2;

        try {
            while (true) {
                out.println(Resources.language.getLOGIN());
                login = reader.readLine();
                Registration.readLogin(login);
                out.println(Resources.language.getPASS());
                pass1 = reader.readLine();
                out.println(Resources.language.getPASS());
                pass2 = reader.readLine();
                Registration.registration(login, pass1, pass2);
                break;
            }
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage());
            registration();
        } catch (IOException e) {
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
            } catch (IOException exc) {
                out.println(Resources.language.getIO_ERROR());
            }
            MainMenu.mainMenu();
        }
    }

    protected static void loadUserData(String login, String pass) {

        String message = UserData.loadData(login, pass);
        if (!message.isEmpty()) {
            out.println(Resources.language.getLOGIN_OR_PASS_EXCEPTION());
            authorization();
        }
        else out.println(Resources.language.getHELLO_USER() + login);
    }

    protected static void logIn() {
        out.println(Resources.language.getENTER_MESSAGE());
        int choice;
        try {
            choice = Integer.parseInt(reader.readLine());

            switch (choice) {
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
}




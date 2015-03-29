package main;

import languages.Language;
import languages.Strings_EN;
import model.Country;
import model.Holiday;
import model.Tradition;
import model.User;
import modules.functional.XmlFileWorking;
import modules.gui_interface.LoginWindow;
import modules.gui_interface.MainWindow;
import modules.user_interface.MainMenu;
import modules.user_interface.UserHandler;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainClass {


    public static void main(String[] args) {
        start();
    }

    private static void start() {
            try {
                Resources.out.println(Resources.language.getCONSOLE_CHOICE());
                int choice = Integer.parseInt(Resources.reader.readLine());
                switch (choice) {
                    case 1:
                        MainMenu.init();
                        break;
                    case 2:
                        LoginWindow.main();
                        /*XmlFileWorking xml = new XmlFileWorking();
                        try {
                        UserHandler.users = xml.loadUsers(XmlFileWorking.XML_USERS);}
                        catch (JDOMException e) {
                            Resources.out.println(Resources.language.getXML_ERROR());
                            start();
                        } catch (SAXException e) {
                            Resources.out.println(Resources.language.getXML_ERROR());
                            start();
                        }*/
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        Resources.out.println(Resources.language.getWRONG_CHOICE());
                        break;
                }

            } catch (IOException e) {
                Resources.out.println(Resources.language.getWRONG_CHOICE());
                start();
            } catch (NumberFormatException ex) {
                Resources.out.println(Resources.language.getWRONG_CHOICE());
                start();
            }
        }
    }

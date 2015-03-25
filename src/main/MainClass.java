package main;

import model.Country;
import model.Holiday;
import model.Tradition;
import modules.gui_interface.LoginWindow;
import modules.gui_interface.MainWindow;
import modules.user_interface.MainMenu;

import java.io.IOException;
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

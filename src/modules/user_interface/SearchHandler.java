package modules.user_interface;

import main.Resources;
import modules.functional.Search;

import java.io.IOException;
import java.util.regex.PatternSyntaxException;

public class SearchHandler {

    protected static void searchMenu() {
        MainMenu.out.println(Resources.language.getSEARCH_MENU());
        int choice = 0;
        try {
            choice = Integer.parseInt(MainMenu.reader.readLine());

            switch (choice) {
                case 1:
                    searchVoid();
                    break;
                case 2:
                    maskSearchVoid();
                    break;
                case 3:
                    regularSearchVoid();
                case 4:
                    MainMenu.mainMenu();
                    break;
                default:
                    MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                    searchMenu();
                    break;
            }
        } catch (NumberFormatException ex) {
            MainMenu.out.println(Resources.language.getWRONG_CHOICE());
            searchMenu();
        } catch (IOException e) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
            searchMenu();
        }
    }

    private static void searchVoid() {
        try {
            MainMenu.out.println(Resources.language.getSEARCH_REQUEST());
            String request = MainMenu.reader.readLine();
            if (Search.search(request, Resources.traditions).size() != 0) {
                PrintHandler.printArrayTraditions(Search.search(request, Resources.traditions));
                TraditionHandler.traditionMenu();
            }
            else MainMenu.out.println(Resources.language.getNOT_FOUND());
        }
        catch (IOException ex) {MainMenu.out.println(Resources.language.getIO_ERROR());}
        searchMenu();
    }

    private static void maskSearchVoid() {
        try {
            String country, holiday, description = "";
            MainMenu.out.println(Resources.language.getCOUNTRY_REQUEST());
            country = MainMenu.reader.readLine();
            MainMenu.out.println(Resources.language.getHOLIDAY_REQUEST());
            holiday = MainMenu.reader.readLine();
            if (Search.maskSearch(holiday,country,"", Resources.traditions).size() != 0) {
                PrintHandler.printArrayTraditions(Search.maskSearch(holiday, country, "", Resources.traditions));
                TraditionHandler.traditionMenu();
            }
            else MainMenu.out.println(Resources.language.getNOT_FOUND());
        }
        catch (IOException ex) {MainMenu.out.println(Resources.language.getIO_ERROR());}
        searchMenu();
    }

    private static void regularSearchVoid() {
        try {
            MainMenu.out.println(Resources.language.getSEARCH_REQUEST());
            String request = MainMenu.reader.readLine();
            if (Search.regularSearch(request, Resources.traditions).size() != 0) {
                PrintHandler.printArrayTraditions(Search.regularSearch(request, Resources.traditions));
                TraditionHandler.traditionMenu();
            }
            else MainMenu.out.println(Resources.language.getNOT_FOUND());
        }
        catch (IOException ex) {MainMenu.out.println(Resources.language.getIO_ERROR());}
        catch (PatternSyntaxException ex) {MainMenu.out.println(Resources.language.getWRONG_MASK());}
        searchMenu();
    }
}
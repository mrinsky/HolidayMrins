package modules.user_interface;

import main.Resources;
import model.Country;
import model.Holiday;
import model.Tradition;
import modules.functional.Add;

import java.io.IOException;
import java.util.ArrayList;

public class TraditionHandler {

    public static void traditionMenu() {
        if (UserHandler.currentUser != null) {
            MainMenu.out.println(Resources.language.getTRADITION_MENU());
        }
        else{
            MainMenu.out.println(Resources.language.getGUEST_TRADITION_MENU());
        }
        int choice;
        try {
            choice = Integer.parseInt(MainMenu.reader.readLine());
            switch (choice) {
                case 1:
                    printDescription();
                    break;
                case 2:
                    if (UserHandler.currentUser != null) {
                        ChangeHandler.changeTradition();
                        break;
                    }
                case 3:
                    if (UserHandler.currentUser != null) {
                        RemoveHandler.removeTradition();
                        break;
                    }
                case 4:
                    PrintHandler.showMenu();
                    break;
                default:
                    MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                    break;
            }
        } catch (IOException ex) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
        }
    }

    private static void printDescription() {
        MainMenu.out.println(Resources.language.getID_REQUEST());
        try {
            int id = Integer.parseInt(MainMenu.reader.readLine());
            Tradition tradition = UserHandler.currentUser.getTraditionList().get(id);
            if (!tradition.getDescription().equals("")) MainMenu.out.println(Resources.traditions.get(id).getDescription());
            else {
                MainMenu.out.println(Resources.language.getNO_DESCRIPT());

                int choice = Integer.parseInt(MainMenu.reader.readLine());
                switch (choice) {
                    case 1:
                        Resources.traditions.get(id).setDescription(MainMenu.reader.readLine());
                    case 2: break;
                }
            }
            MainMenu.out.println(Resources.language.getHELP_MENU());
            int choice = Integer.parseInt(MainMenu.reader.readLine());
            switch (choice) {
                case 1:
                    MainMenu.mainMenu();
                    break;
                case 2:
                    MainMenu.exit();
                    break;
                default:
                    MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                    MainMenu.mainMenu();
                    break;
            }
        }
        catch (IOException e) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
        }
    }

    public static void noTraditionMenu(Holiday holiday) {
        int choice = 0;
        try {
            choice = Integer.parseInt(MainMenu.reader.readLine());

            switch (choice) {
                case 1:
                    addTradition(holiday);
                    break;
                case 2:
                    PrintHandler.showMenu();
                    break;
                default:
                    MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                    noTraditionMenu(holiday);
                    return;
            }
        } catch (NumberFormatException ex) {
            MainMenu.out.println(Resources.language.getWRONG_CHOICE());
            noTraditionMenu(holiday);
        } catch (IOException e) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
            noTraditionMenu(holiday);
        }
    }

    public static void noTraditionMenu(Country country) {
        int choice = 0;
        try {
            choice = Integer.parseInt(MainMenu.reader.readLine());

            switch (choice) {
                case 1:
                    addTradition(country);
                    break;
                case 2:
                    PrintHandler.showMenu();
                    break;
                default:
                    MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                    noTraditionMenu(country);
                    break;
            }
        } catch (NumberFormatException ex) {
            MainMenu.out.println(Resources.language.getWRONG_CHOICE());
            noTraditionMenu(country);
        } catch (IOException e) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
            noTraditionMenu(country);
        }
    }

    private static void addTradition(Holiday holiday) {
        MainMenu.out.println(Resources.language.getCOUNTRY_CHOICE());
        PrintHandler.printArrayCountries(Resources.countries);
        try {
            int choice = Integer.parseInt(MainMenu.reader.readLine());
            Country country = Resources.countries.get(choice);
            MainMenu.out.println(Resources.language.getDESCRIPT_REQUEST());
            choice = Integer.parseInt(MainMenu.reader.readLine());
            String description = "";

            if (choice == 1) {description = MainMenu.reader.readLine();
                Resources.traditions = (ArrayList<Tradition>)Add.addTradition(holiday, country, description, Resources.traditions);
                MainMenu.out.println(Resources.language.getREADY());
                PrintHandler.showMenu();}
            else if (choice == 2) {
                Resources.traditions = (ArrayList<Tradition>)Add.addTradition(holiday, country, Resources.traditions);
                MainMenu.out.println(Resources.language.getREADY());
                PrintHandler.showMenu();}
            else MainMenu.out.println(Resources.language.getWRONG_CHOICE());

        } catch (IOException e) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
        }
    }

    private static void addTradition(Country country) {
        MainMenu.out.println(Resources.language.getHOLIDAY_REQUEST());
        PrintHandler.printArrayHolidays(Resources.holidays,0);
        try {
            int choice = Integer.parseInt(MainMenu.reader.readLine());
            Holiday holiday = Resources.holidays.get(choice);
            MainMenu.out.println(Resources.language.getDESCRIPT_REQUEST());
            choice = Integer.parseInt(MainMenu.reader.readLine());
            String description = "";

            if (choice == 1) {description = MainMenu.reader.readLine();
                Resources.traditions = (ArrayList<Tradition>)Add.addTradition(holiday, country, description, Resources.traditions);
                MainMenu.out.println(Resources.language.getREADY());
                PrintHandler.showMenu();}
            else if (choice == 2) {
                Resources.traditions = (ArrayList<Tradition>)Add.addTradition(holiday, country, Resources.traditions);
                MainMenu.out.println(Resources.language.getREADY());
                PrintHandler.showMenu();}
            else MainMenu.out.println(Resources.language.getWRONG_CHOICE());

        } catch (IOException e) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
        }
    }
}

package modules.user_interface;

import main.MainClass;
import main.Resources;
import model.Country;
import model.Holiday;
import model.HolidayType;
import model.Tradition;
import modules.functional.Search;
import modules.functional.UserData;
import sun.awt.image.ImageWatched;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class PrintHandler {

    //region Completed
    protected static void showMenu() {
        if (UserData.currentUser != null && !UserData.currentUser.isAdmin()) {
            MainMenu.out.println(Resources.language.getSHOW_USER_MENU());
        }
        else {
            MainMenu.out.println(Resources.language.getSHOW_MENU());
        }
        int choice;
        try {
            choice = Integer.parseInt(MainMenu.reader.readLine());

            switch (choice) {
                case 1:
                    printByToday(UserData.currentUser != null);
                    break;
                case 2:
                    printByDate(UserData.currentUser != null);
                    break;
                case 3:
                    printByType(UserData.currentUser != null);
                    break;
                case 4:
                    printByCountry();
                    break;
                case 5:
                    printAll(UserData.currentUser != null);
                    break;
                case 6:
                    printArrayCountries(Resources.countries);
                    printCountryMenu();
                    break;
                case 7:
                    if (UserData.currentUser != null && !UserData.currentUser.isAdmin()){
                        printOwnHolidays();
                        break;
                    }
                case 8:
                    //if (UserHandler.currentUser != null && !UserHandler.currentUser.isAdmin()) {
                    MainMenu.mainMenu();
                    break;
                //}
                default:
                    MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                    showMenu();
                    break;
            }
        } catch (NumberFormatException ex) {
            MainMenu.out.println(Resources.language.getWRONG_CHOICE());
            showMenu();
        } catch (IOException e) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
            showMenu();
        }
    }

    private static void printHolidayMenu(boolean validate) {
        if (!validate) {
            MainMenu.out.println(Resources.language.getPRINT_SHORT_MENU());
        }
        else {
            MainMenu.out.println(Resources.language.getPRINT_MENU());
        }
        int choice;
        try {
            choice = Integer.parseInt(MainMenu.reader.readLine());
            switch (choice) {
                case 1:
                    chooseHoliday();
                    break;
                case 2:
                    if (validate) {
                        ChangeHandler.holidayChanger(UserData.currentUser.isAdmin());
                        break;
                    }
                case 3:
                    if (validate) {
                        RemoveHandler.holidayRemover();
                        break;
                    }
                case 4:
                    showMenu();
                    break;
                default:
                    MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                    showMenu();
                    break;
            }
        } catch (IOException ex) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
            showMenu();
        }

    }


    private static void printCountryMenu() {
        if (UserData.currentUser != null) {
            MainMenu.out.println(Resources.language.getPRINT_MENU());
        }
        else{
            MainMenu.out.println(Resources.language.getPRINT_SHORT_MENU());
        }
        int choice;
        try {
            choice = Integer.parseInt(MainMenu.reader.readLine());
            switch (choice) {
                case 1:
                    chooseCountry();
                    break;
                case 2:
                    if (UserData.currentUser == null) throw new NumberFormatException();
                    else {
                        ChangeHandler.countryChanger(UserData.currentUser.isAdmin());
                    }
                    break;
                case 3:
                    RemoveHandler.countryRemover();
                    break;
                case 4:
                    showMenu();
                    break;
                default:
                    MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                    showMenu();
                    break;
            }
        } catch (IOException ex) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
            showMenu();
        }
    }

    private static void chooseHoliday() {
        MainMenu.out.println(Resources.language.getID_REQUEST());
        try {
            int id = Integer.parseInt(MainMenu.reader.readLine());
            Holiday holiday = Resources.holidays.get(id);
            printArrayTraditions(Search.getTraditions(holiday));
            TraditionHandler.traditionMenu();
        } catch (IOException ex) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
        } catch (IndexOutOfBoundsException ex) {
            MainMenu.out.println(Resources.language.getWRONG_CHOICE());
        }
    }

    private static void chooseCountry() {
        MainMenu.out.println(Resources.language.getID_REQUEST());
        try {
            int id = Integer.parseInt(MainMenu.reader.readLine());
            if (Search.getCountryTraditions(id).size() != 0) {
                printTraditionTable();
                int count = 0;
                for (Tradition tradition : Resources.traditions) {
                    if (tradition.getCountry().getName().equals(Resources.countries.get(id).getName()))
                        MainMenu.out.printf("%5d%s\n", count, tradition);
                    count++;
                }
                TraditionHandler.traditionMenu();
            } else {
                MainMenu.out.println(Resources.language.getNO_TRADITION());
                TraditionHandler.noTraditionMenu(Resources.holidays.get(id));
            }
        } catch (IOException ex) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
            showMenu();
        } catch (IndexOutOfBoundsException ex) {
            MainMenu.out.println(Resources.language.getWRONG_CHOICE());
            showMenu();
        }
    }

    private static void printAll(boolean validate) {
        if (!Resources.holidays.isEmpty()) {
            printArrayHolidays(Resources.holidays);
            printHolidayMenu(validate);
        } else {
            MainMenu.out.println(Resources.language.getNOT_FOUND());
            MainMenu.mainMenu();
        }
    }

    private static void printOwnHolidays(){
        if (UserData.currentUser != null && !UserData.currentUser.isAdmin()){
            if (!UserData.currentUser.getHolidayList().isEmpty()){
                ArrayList<Tradition> traditions = new ArrayList<Tradition>();
                for (int i = UserData.traditionCount; i < Resources.traditions.size(); i++){
                    traditions.add(Resources.traditions.get(i));
                }
                UserData.currentUser.setTraditionList(traditions);
                LinkedList<Country> countries = new LinkedList<Country>();
                for (int i = UserData.countryCount; i < Resources.countries.size(); i++){
                    countries.add(Resources.countries.get(i));
                }
                UserData.currentUser.setCountryList(countries);
                LinkedList<Holiday> holidays = new LinkedList<Holiday>();
                for (int i = UserData.holidayCount; i < Resources.holidays.size(); i++){
                    holidays.add(Resources.holidays.get(i));
                }
                UserData.currentUser.setHolidayList(holidays);
                printArrayHolidays(UserData.currentUser.getHolidayList(), 0);
                printHolidayMenu(true);
            }
            else {
                MainMenu.out.println(Resources.language.getNOT_FOUND());
                MainMenu.mainMenu();
            }
        }
    }

    private static void printByCountry() {
        MainMenu.out.println(Resources.language.getID_REQUEST());
        printArrayCountries(Resources.countries);
        try {
            int choice = Integer.parseInt(MainMenu.reader.readLine());

            if (Search.getCountryTraditions(choice).size() != 0) {
                printArrayTraditions(Search.getCountryTraditions(choice));
                TraditionHandler.traditionMenu();
            } else MainMenu.out.println(Resources.language.getNOT_FOUND());

        } catch (IOException ex) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
        } catch (IndexOutOfBoundsException ex) {
            MainMenu.out.println(Resources.language.getWRONG_CHOICE());
        }
        showMenu();
    }

    private static void printByType(boolean validate) {
        MainMenu.out.println(Resources.language.getTYPE_CHOICE());
        printAllTypes();
        while (true) {
            try {
                int choice = Integer.parseInt(MainMenu.reader.readLine());
                if (Search.getTypeHolidays(choice).size() != 0) {
                    printArrayHolidays(Search.getTypeHolidays(choice));
                    printHolidayMenu(validate);
                } else {
                    MainMenu.out.println(Resources.language.getNOT_FOUND());
                    showMenu();
                }
            } catch (IOException ex) {
                MainMenu.out.println(Resources.language.getIO_ERROR());
            } catch (ArrayIndexOutOfBoundsException ex) {
                MainMenu.out.println(Resources.language.getWRONG_CHOICE());
            }
        }
    }

    private static void printByToday(boolean validate) {
        Date date = new Date();

        if (Search.getDateHolidays(date).size() != 0) {
            printArrayHolidays(Search.getDateHolidays(date));
            printHolidayMenu(validate);
        } else {
            MainMenu.out.println(Resources.language.getNOT_FOUND());
            showMenu();
        }
    }

    private static void printByDate(boolean validate) {
        int day, month;
        try {
            MainMenu.out.println(Resources.language.getDAY());
            day = Integer.parseInt(MainMenu.reader.readLine());
            MainMenu.out.println(Resources.language.getMONTH());
            month = Integer.parseInt(MainMenu.reader.readLine());
            Date date = Holiday.dateFormat.parse(day + "." + month);

            if (Search.getDateHolidays(date).size() != 0) {
                printArrayHolidays(Search.getDateHolidays(date));
                printHolidayMenu(validate);
            } else {
                MainMenu.out.println(Resources.language.getNOT_FOUND());
                showMenu();
            }
        } catch (IOException ex) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
        } catch (ParseException ex) {
            MainMenu.out.println(Resources.language.getPARSE_ERROR());
        }
    }

    //region Printing arrays
    public static void printAllTypes() {
        for (int i = 0; i < HolidayType.values().length; i++)
            MainMenu.out.printf("%d - %s\n", i, HolidayType.values()[i]);
    }

    protected static void printTraditionTable() {
        MainMenu.out.printf("%5s%30s%20s\n", Resources.language.getTABLE_ID(), Resources.language.getTABLE_HOLIDAY(), Resources.language.getTABLE_COUNTRY());
    }

    protected static void printHolidayTable() {
        MainMenu.out.printf("%3s%30s%15s%15s\n", Resources.language.getTABLE_ID(),
                Resources.language.getTABLE_HOLIDAY(), Resources.language.getTABLE_DATE(), Resources.language.getTABLE_TYPE());
    }

    public static void printArrayCountries(LinkedList<Country> countries) {
        MainMenu.out.printf("%3s%20s\n", Resources.language.getTABLE_ID(),
                Resources.language.getTABLE_COUNTRY());
        for (int i = 0; i < countries.size(); i++) {
            MainMenu.out.printf("%3d%20s\n", i, countries.get(i).getName());
        }
    }

    public static void printArrayTraditions(ArrayList<Tradition> traditions) {
        printTraditionTable();
        int count = 0;
        for (Tradition tradition : Resources.traditions) {
            for (int i = 0; i < traditions.size(); i++) {
                if (tradition.toString().equals(traditions.get(i).toString()))
                    MainMenu.out.printf("%5d%30s\n", count, traditions.get(i));
            }
            count++;
        }
    }

    public static void printArrayHolidays(LinkedList<Holiday> holidays) {
        printHolidayTable();
        int count = 0;
        for (Holiday holiday : Resources.holidays) {
            for (int i = 0; i < holidays.size(); i++) {
                if (holiday.equals(holidays.get(i)))
                    MainMenu.out.printf("%5d%30s\n", count, holidays.get(i));
            }
            count++;
        }
    }

    public static void printArrayHolidays(LinkedList<Holiday> holidays, int start) {
        printHolidayTable();
        int count = start;
        for (Holiday holiday : holidays) {
            //for (int i = 0; i < holidays.size(); i++) {
                //if (holiday.toString().equals(holidays.get(i).toString()))

                    MainMenu.out.printf("%5d%30s\n", count, holiday);
            //}
            count++;
        }
    }


}

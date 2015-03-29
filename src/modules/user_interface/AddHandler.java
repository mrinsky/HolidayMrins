package modules.user_interface;

import main.Resources;
import model.Country;
import model.Holiday;
import model.Tradition;
import modules.functional.Add;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class AddHandler {

    protected static void addMenu() {
        int choice;
        while (true) {
            try {
                MainMenu.out.println(Resources.language.getADD_MENU());
                choice = Integer.parseInt(MainMenu.reader.readLine());

                switch (choice) {
                    case 1:
                        addTradition();
                        break;
                    case 2:
                        MainMenu.mainMenu();
                        break;
                    default:
                        MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                }
            } catch (NumberFormatException ex) {
                MainMenu.out.println(Resources.language.getWRONG_CHOICE());
            } catch (IOException e) {
                MainMenu.out.println(Resources.language.getIO_ERROR());
            }
        }
    }

    private static void addTradition() {
        while (true) {
            try {
                if (UserHandler.currentUser.isAdmin()) {
                    Resources.traditions = (ArrayList<Tradition>) Add.addTradition(holidayMenu(), countryMenu(), Resources.traditions);
                }
                else if (UserHandler.currentUser != null){
                    UserHandler.currentUser.setTraditionList((ArrayList<Tradition>) Add.addTradition(holidayMenu(), countryMenu(), Resources.traditions));
                }
                MainMenu.out.println(Resources.language.getDESCRIPT_REQUEST());
                int choice = Integer.parseInt(MainMenu.reader.readLine());
                switch (choice) {
                    case 1:
                        Resources.traditions.get(Resources.traditions.size() - 1).setDescription(MainMenu.reader.readLine());
                        //Add.checkTraditionList(Resources.traditions.get(Resources.traditions.size() - 1));
                        MainMenu.out.println(Resources.language.getREADY());
                        addMenu();
                        break;
                    case 2:
                        //Add.checkTraditionList(Resources.traditions.get(Resources.traditions.size() - 1));
                        MainMenu.out.println(Resources.language.getREADY());
                        addMenu();
                        break;
                    default:
                        MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                }
            } catch (IOException ex) {
                MainMenu.out.println(Resources.language.getIO_ERROR());
            }
        }
    }

    private static Country countryMenu() {
        while (true) {
            try {
                MainMenu.out.println(Resources.language.getTRADITION_COUNTRY());
                int choice = Integer.parseInt(MainMenu.reader.readLine());
                switch (choice) {
                    case 1:
                        MainMenu.out.println(Resources.language.getID_REQUEST());
                        PrintHandler.printArrayCountries(Resources.countries);
                        choice = Integer.parseInt(MainMenu.reader.readLine());
                        return Resources.countries.get(choice);
                    case 2:
                        return addCountry();
                    default:
                        MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                }
            } catch (IOException ex) {
                MainMenu.out.println(Resources.language.getIO_ERROR());
            }
        }
    }

    private static Holiday holidayMenu() {
        Holiday holiday;
        while (true) {
            try {
                MainMenu.out.println(Resources.language.getTRADITION_HOLIDAY());
                int choice = Integer.parseInt(MainMenu.reader.readLine());
                switch (choice) {
                    case 1:
                        PrintHandler.printArrayHolidays(Resources.holidays, 0);
                        MainMenu.out.println(Resources.language.getID_REQUEST());
                        choice = Integer.parseInt(MainMenu.reader.readLine());
                        holiday = Resources.holidays.get(choice);
                        return holiday;
                    case 2:
                        holiday = addHoliday();
                        return holiday;
                    default:
                        MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                }
            } catch (IOException ex) {
                MainMenu.out.println(Resources.language.getIO_ERROR());
                addMenu();
            } catch (IndexOutOfBoundsException ex) {
                MainMenu.out.println(Resources.language.getWRONG_CHOICE());
            }
        }
    }

    protected static Holiday addHoliday() {
        while (true) {
            try {
                MainMenu.out.println(Resources.language.getHOLIDAY_REQUEST());
                String name = MainMenu.reader.readLine();

                MainMenu.out.println((Resources.language.getTYPE_REQUEST()));
                PrintHandler.printAllTypes();
                int type = Integer.parseInt(MainMenu.reader.readLine());

                MainMenu.out.println(Resources.language.getDATE_REQUEST());
                int dateChoice = Integer.parseInt(MainMenu.reader.readLine());

                switch (dateChoice) {
                    case 1:
                        Resources.holidays = (LinkedList<Holiday>)Add.addHoliday(name, type, Resources.holidays);
                        if (Add.uniqueFlag) {
                        return new Holiday(name, type);
                        }
                        else {
                            Resources.out.println(Resources.language.getNOT_UNIQUE());
                            return holidayMenu();
                        }
                    case 2:
                        Date start = createDate();
                        Date end;
                        MainMenu.out.println(Resources.language.getEND_DATE_REQUEST());
                        int endChoice = Integer.parseInt(MainMenu.reader.readLine());
                        if (endChoice == 2) {
                            end = createDate();
                            Resources.holidays = (LinkedList<Holiday>)Add.addHoliday(name, start, end, type, Resources.holidays);
                            if (Add.uniqueFlag) {
                                return new Holiday(name, start, end, type);
                            }
                            else {
                                Resources.out.println(Resources.language.getNOT_UNIQUE());
                                return holidayMenu();
                            }
                        } else if (endChoice == 1) {
                            Resources.holidays = (LinkedList<Holiday>)Add.addHoliday(name, start, type, Resources.holidays);
                            if (Add.uniqueFlag) {
                                return new Holiday(name, start, type);
                            }
                            else {
                                Resources.out.println(Resources.language.getNOT_UNIQUE());
                                return holidayMenu();
                            }
                        } else {
                            MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                            Resources.holidays = (LinkedList<Holiday>)Add.addHoliday(name, start, type, Resources.holidays);
                            if (Add.uniqueFlag) {
                                return new Holiday(name, start, type);
                            }
                            else {
                                Resources.out.println(Resources.language.getNOT_UNIQUE());
                                return holidayMenu();
                            }
                        }
                    default:
                        MainMenu.out.println(Resources.language.getWRONG_CHOICE());
                }
            } catch (IOException ex) {
                MainMenu.out.println(Resources.language.getIO_ERROR());
            } catch (ParseException ex) {
                Resources.language.getPARSE_ERROR();
            }
        }
    }

    private static Country addCountry() {
        Country country = null;
        try {
            MainMenu.out.println(Resources.language.getCOUNTRY_REQUEST());
            country = new Country(MainMenu.reader.readLine());
            Resources.countries = (LinkedList<Country>)Add.addCountry(country, Resources.countries);
            if (!Add.uniqueFlag) {
                Resources.out.println(Resources.language.getNOT_UNIQUE());
                country = countryMenu();
            }
            MainMenu.out.println(Resources.language.getREADY());
        } catch (IOException ex) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
            addMenu();
        }
        return country;
    }

    private static Date createDate() throws ParseException, IOException {
        MainMenu.out.println(Resources.language.getDAY());
        int day = Integer.parseInt(MainMenu.reader.readLine());
        MainMenu.out.println(Resources.language.getMONTH());
        int month = Integer.parseInt(MainMenu.reader.readLine());
        Date date = Holiday.dateFormat.parse(day + "." + month);
        return date;
    }
}

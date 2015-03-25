package modules.user_interface;

import main.Resources;
import modules.functional.Remove;

import java.io.IOException;

public class RemoveHandler {

    protected static void holidayRemover() {
        try {
            MainMenu.out.println(Resources.language.getID_REQUEST());
            int id = Integer.parseInt(MainMenu.reader.readLine());
            if (id >= UserHandler.holidayCount && id < Resources.holidays.size()) {
                removeHoliday(id);
            }
            else {
                throw new IndexOutOfBoundsException();
            }
            PrintHandler.showMenu();
        } catch (IOException e) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
        }
        catch (IndexOutOfBoundsException e) {
            Resources.language.getWRONG_CHOICE();
        }
    }

    protected static void countryRemover() {
        try {
            MainMenu.out.println(Resources.language.getID_REQUEST());
            int id = Integer.parseInt(MainMenu.reader.readLine());
            if (id >= UserHandler.countryCount && id < Resources.countries.size()) {
                removeCountry(id);
            }
            else {
                throw new IndexOutOfBoundsException();
            }
            PrintHandler.showMenu();
        } catch (IOException e) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
        } catch (IndexOutOfBoundsException e) {
            Resources.language.getWRONG_CHOICE();
            countryRemover();
        }
    }

    private static void removeCountry(int id) {
        Remove.removeCountry(id, Resources.countries);
    }

    private static void removeHoliday(int id) {
        Remove.removeHoliday(id, Resources.holidays);
    }

    protected static void removeTradition() {
        try {
            MainMenu.out.println(Resources.language.getID_REQUEST());
            int choice = Integer.parseInt(MainMenu.reader.readLine());
            if (choice >= UserHandler.countryCount && choice < Resources.countries.size()) {
                Remove.removeTradition(choice, Resources.traditions);
            }
            else {
                throw new IndexOutOfBoundsException();
            }
            MainMenu.out.println(Resources.language.getREADY());
        } catch (IOException e) {
            MainMenu.out.println(Resources.language.getIO_ERROR());
        }
        catch (IndexOutOfBoundsException ex) {
            MainMenu.out.println(Resources.language.getWRONG_CHOICE());}
        removeTradition();
    }
}

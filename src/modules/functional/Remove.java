package modules.functional;

import main.Resources;
import model.Country;
import model.Holiday;
import model.Tradition;
import modules.user_interface.UserHandler;

import javax.swing.*;
import java.util.List;

public class Remove {

    public static void removeListTradition(List<Tradition> traditionList){
        if (traditionList != null){
            for(Tradition item : traditionList) {
                Resources.traditions.remove(item);
            }
        }
    }

    public static void removeListCountry(List<Country> countryList){
        if (countryList != null){
            for(Country item : countryList) {
                Resources.countries.remove(item);
            }
        }
    }

    public static void removeListHoliday(List<Holiday> holidayList){
        if (holidayList != null){
            for(Holiday item : holidayList) {
                Resources.holidays.remove(item);
            }
        }
    }

    public static void removeTradition(int id, List<Tradition> traditions) {
        if (id >= UserHandler.traditionCount && id < Resources.traditions.size()) {
            traditions.remove(id);
        }
        else throw new IndexOutOfBoundsException();
    }
    public static void removeTraditionGui(int id, List<Tradition> traditions) {
        if (id < Resources.traditions.size()) {
            traditions.remove(id);
        }
        else throw new IndexOutOfBoundsException();
    }

    public static void removeCountry(int country, List<Country> c_list){
        //поиск традиций с этой страной
        removeListTradition(Search.getCountryTraditions(country));
        c_list.remove(country);
    }

    public static void removeHoliday(int holiday, List<Holiday> h_list){
        // поиск и удаление традиций с этим праздником
        removeListTradition(Search.getTraditions(h_list.get(holiday)));
        h_list.remove(holiday);
    }
}
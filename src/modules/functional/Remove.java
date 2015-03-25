package modules.functional;

import main.Resources;
import model.Country;
import model.Holiday;
import model.Tradition;

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

    public static void removeListHolidy(List<Holiday> holidayList){
        if (holidayList != null){
            for(Holiday item : holidayList) {
                Resources.holidays.remove(item);
            }
        }
    }

    public static void removeTradition(int id, List<Tradition> traditions) {
        traditions.remove(id);
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



    // public static void remove(Tradition tradition) {
    //int indexHoliday = Add.searchIndex(tradition.getHoliday(), MainClass.holidays);
    //int indexCountry = Add.searchIndex(tradition.getCountry(), MainClass.countries);
    // ArrayList<Tradition> holidayTraditions = Search.getTraditions(tradition.getHoliday());
    //ArrayList<Tradition> countryTraditions = Search.getCountryTraditions(indexCountry);
    // if (holidayTraditions.size() == 1) {
    //MainClass.holidays.remove(indexHoliday);
    //}
    //if (countryTraditions.size() == 1) {
    //MainClass.countries.remove(indexCountry);
    //}
    //}
}
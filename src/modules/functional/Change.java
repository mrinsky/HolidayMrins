package modules.functional;

import main.MainClass;
import model.Country;
import model.Holiday;
import model.HolidayType;
import model.Tradition;
import modules.user_interface.MainMenu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Change {
    //Правка страны.
    public static List<Country> editCountry(Country country, String newName, List<Country> list) {
        //int index = Search.searchIndex(country, list);
        int index = list.indexOf(country);
        country.setName(newName);
        //for (int i = 0; i < index.length; i++){
        list.set(index, country);
        //}
        return list;
    }

    public static List<Holiday> editHoliday(int id, Holiday newHoliday,
                                            List<Holiday> list) {
        list.set(id, newHoliday);
        return list;
    }

    public static List<Tradition> editTradition(String newStr, int id, int param, List<Tradition> list) {
        Tradition tradition = list.get(id);
        switch (param) {
            case 1:
                tradition.setDescription(newStr);
                break;
            case 2:
                tradition.getCountry().setName(newStr);
                break;
            case 3:
                tradition.getHoliday().setName(newStr);
                break;
            case 4:
                ArrayList<Tradition> traditions = Search.getCountryTraditions(id);
                //int count = 0;
                for (Tradition trad : list) {
                    for (int i = 0; i < traditions.size(); i++) {
                        if (trad.equals(traditions.get(i)))
                            list.set(list.indexOf(
                                            traditions.get(i)),
                                    new Tradition(tradition.getHoliday(),
                                            new Country(newStr), tradition.getDescription()));
                    }
                    //count++;
                }
                break;
            default:
                break;
        }
        list.set(id, tradition);
        return list;
    }

    public static List<Tradition> editTradition(Holiday holiday, Holiday newHoliday, List<Tradition> list) {
        ArrayList<Tradition> traditions = Search.getTraditions(holiday);
        //int count = 0;
        for (Tradition tradition : list) {
            for (int i = 0; i < traditions.size(); i++) {
                if (tradition.equals(traditions.get(i)))
                    list.set(list.indexOf(traditions.get(i)), new Tradition(newHoliday, tradition.getCountry(), tradition.getDescription()));
            }
            //count++;
        }
        return list;
    }

    public static List<Tradition> editTradition(int id, Holiday newHoliday, Country newCountry, List<Tradition> list) {
        //int count = 0;
        list.get(id).setHoliday(newHoliday);
        list.get(id).setCountry(newCountry);
        return list;
    }
}

package modules.functional;

import main.Resources;
import model.Country;
import model.Holiday;
import model.Tradition;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Add {
    public static boolean uniqueFlag = true;
    /*************************
     * Constructor
     *************************/
    private Add(){

    }
    /************************
     * Methods
     ************************/
    //Проверка на уникальность.
    private static boolean isUnique(Object value, List<?> list){
        boolean result = true;
        for (Object item : list){
            if (value.toString().equals(item.toString())) {
                result = false;
            }
        }
        return result;
    }
    //Добавить страну.
    public static List<Country> addCountry(String name, List<Country> list){
        Country country = new Country(name);
        if (isUnique(country, list)) {
            list.add(country);
        }
        else uniqueFlag = false;
        return list;
    }
    public static List<Country> addCountry(Country country, List<Country> list){
        if (isUnique(country, list)) {
            list.add(country);
        }
        else uniqueFlag = false;
        return list;
    }
    //Добавить праздник.
    public static List<Holiday> addHoliday(String name, List<Holiday> list){
        Holiday holiday = new Holiday(name);
        if (isUnique(holiday, list)) {
            list.add(holiday);
        }
        else uniqueFlag = false;
        return list;
    }
    public static List<Holiday> addHoliday(String name, int typeNum, List<Holiday> list){
        Holiday holiday = new Holiday(name, typeNum);
        if (isUnique(holiday, list)) {
            list.add(holiday);
        }
        else uniqueFlag = false;
        return list;
    }
    public static List<Holiday> addHoliday(String name, Date start, int typeNum, List<Holiday> list){
        Holiday holiday = new Holiday(name, start, typeNum);
        if (isUnique(holiday, list)) {
            list.add(holiday);
        }
        else uniqueFlag = false;
        return list;
    }
    public static List<Holiday> addHoliday(String name, Date start, Date end, int typeNum,
                                           List<Holiday> list){
        Holiday holiday = new Holiday(name, start, end, typeNum);
        if (isUnique(holiday, list)) {
            list.add(holiday);
        }
        else uniqueFlag = false;
        return list;
    }
    //Добавить новую традицию.
    public static List<Tradition> addTradition(Holiday holiday, Country country, List<Tradition> list) {
        Tradition tradition = new Tradition(holiday, country);
        //if (isUnique(tradition, list)) {
        list.add(tradition);
        //}
        //else uniqueFlag = false;
        return list;
    }
    public static List<Tradition> addTradition(Holiday holiday, Country country, String description, List<Tradition> list) {
        Tradition tradition = new Tradition(holiday, country);
        tradition.setDescription(description);
        //if (isUnique(tradition, list)) {
        list.add(tradition);
        //}
        //else Resources.language.getNOT_UNIQUE();
        return list;
    }

    public static void checkTraditionList(Tradition tradition){
        if (!isUnique(tradition, Resources.traditions)){
            Resources.traditions.remove(tradition);
            Resources.out.println(Resources.language.getNOT_UNIQUE());
        }
    }
}

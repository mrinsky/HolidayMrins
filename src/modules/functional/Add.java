package modules.functional;

import model.Country;
import model.Holiday;
import model.Tradition;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Add {
    /*************************
     * Constructor
     *************************/
    private Add(){

    }
    /************************
     * Methods
     ************************/
    //Добавить страну.
    public static List<Country> newCountry(String name, List<Country> list){
        Country country = new Country(name);
        list.add(country);
        return list;
    }
    public static List<Country> newCountry(Country country, List<Country> list){
        list.add(country);
        return list;
    }
    //Добавить праздник.
    public static List<Holiday> newHoliday(String name, List<Holiday> list){
        Holiday holiday = new Holiday(name);
        list.add(holiday);
        return list;
    }
    public static List<Holiday> newHoliday(String name, int typeNum, List<Holiday> list){
        Holiday holiday = new Holiday(name, typeNum);
        list.add(holiday);
        return list;
    }
    public static List<Holiday> newHoliday(String name, Date start, int typeNum, List<Holiday> list){
        Holiday holiday = new Holiday(name, start, typeNum);
        list.add(holiday);
        return list;
    }
    public static List<Holiday> newHoliday(String name, Date start, Date end, int typeNum,
                                           List<Holiday> list){
        Holiday holiday = new Holiday(name, start, end, typeNum);
        list.add(holiday);
        return list;
    }
    //Добавить новую традицию.
    public static List<Tradition> newTradition(Holiday holiday, Country country, List<Tradition> list) {
        Tradition tradition = new Tradition(holiday, country);
        list.add(tradition);
        return list;
    }
    public static List<Tradition> newTradition(Holiday holiday, Country country, String description, List<Tradition> list) {
        Tradition tradition = new Tradition(holiday, country);
        tradition.setDescription(description);
        list.add(tradition);
        return list;
    }
}

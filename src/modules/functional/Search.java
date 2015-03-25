package modules.functional;

import main.Resources;
import model.Holiday;
import model.HolidayType;
import model.Tradition;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Search {

    public static ArrayList<Tradition> search(String request, ArrayList<Tradition> traditions) {
        ArrayList<Tradition> searchResult = new ArrayList<Tradition>();
        String countryName;
        String holidayName;
        String description;

        for (int i = 0; i < traditions.size(); i++) {
            countryName = traditions.get(i).getCountry().getName();
            holidayName = traditions.get(i).getHoliday().getName();
            description = traditions.get(i).getDescription();

            if (countryName.contains(request)) {
              /*  System.out.print(traditions.get(i).getCountry().getName() + "   ");
                System.out.print(traditions.get(i).getHoliday().getName() + "   ");
                System.out.println(traditions.get(i).getDescription());*/
                searchResult.add(traditions.get(i));
                continue;
            }
            if (holidayName.contains(request)) {
                searchResult.add(traditions.get(i));
               /* System.out.print(traditions.get(i).getCountry().getName() + "   ");
                System.out.print(traditions.get(i).getHoliday().getName() + "   ");
                System.out.println(traditions.get(i).getDescription());*/
                continue;
            }
            if (description.contains(request)) {
               /* System.out.print(traditions.get(i).getCountry().getName() + "   ");
                System.out.print(traditions.get(i).getHoliday().getName() + "   ");
                System.out.println(traditions.get(i).getDescription());*/
                searchResult.add(traditions.get(i));
                continue;
            }
        }

        return searchResult;
    }


    public static ArrayList<Tradition> maskSearch(String holidayName, String countryName, String description, ArrayList<Tradition> traditions) { // Введите название -> Enter Введите страну -> Enter итд Если перенесем на форму будет удобнее
        ArrayList<Tradition> searchResult = new ArrayList<Tradition>();
        boolean holidayFound = false;
        boolean countryFound = false;
        ArrayList<Tradition> selectByHoliday = new ArrayList<Tradition>();
        ArrayList<Tradition> selectByCountry = new ArrayList<Tradition>();


        for (Tradition tradition : traditions) {
            if (holidayName.compareToIgnoreCase(tradition.getHoliday().getName()) == 0) {
                selectByHoliday.add(tradition);
                holidayFound = true;

            }
        }
        if (holidayFound == false) selectByHoliday = traditions;
        for (Tradition aSelectByHoliday : selectByHoliday) {
            if ((countryName.compareToIgnoreCase(aSelectByHoliday.getCountry().getName()) == 0)|(countryName=="")) {
                selectByCountry.add(aSelectByHoliday);
                countryFound = true;

            }
        }
        if (countryFound == true) {
            selectByHoliday = selectByCountry;
        }
        for (Tradition aSelectByHoliday : selectByHoliday) {
            if ((aSelectByHoliday.getDescription().contains(description) |(description==""))) {
                searchResult.add(aSelectByHoliday);
            }
        }
        //  for (int i = 0; i < searchResult.size(); i++) {
        //    System.out.println(searchResult.get(i).getHoliday().getName() + " " + searchResult.get(i).getCountry().getName() + " " + searchResult.get(i).getDescription());
        //}
        //Проверка поиска, вывод на консоль


        return searchResult;
    }

    public static ArrayList<Tradition> regularSearch(String request, ArrayList<Tradition> traditions) throws PatternSyntaxException{
        ArrayList<Tradition> searchResult = new ArrayList<Tradition>();
        Matcher matcher;
        boolean found;
        Pattern pattern = Pattern.compile(request);

        for (int i = 0; i < traditions.size(); i++) {
            found = false;
            matcher = pattern.matcher(traditions.get(i).getHoliday().getName());
            found = matcher.matches();
            if (found) {
                searchResult.add(traditions.get(i));
                // System.out.println("Found in holiday name");

                continue;
            }
            matcher = pattern.matcher(traditions.get(i).getCountry().getName());
            found = matcher.matches();
            if (found) {
                searchResult.add(traditions.get(i));
                // System.out.println("Found in country name");
                continue;
            }
            matcher = pattern.matcher(traditions.get(i).getDescription());
            found = matcher.matches();
            if (found) {
                searchResult.add(traditions.get(i));
                // System.out.println("Found in description");
            }

        }
        for (int i = 0; i < searchResult.size(); i++) {
            System.out.println(searchResult.get(i).getHoliday().getName() + " " + searchResult.get(i).getCountry().getName() + " " + searchResult.get(i).getDescription());
        }


        return searchResult;
    }

    public static ArrayList<Tradition> getCountryTraditions(int country) {
        ArrayList<Tradition> traditions = new ArrayList<Tradition>();
        for (Tradition tradition : Resources.traditions) {
            if (tradition.getCountry().getName().equals(Resources.countries.get(country).getName()))
                traditions.add(tradition);
        }
        return traditions;
    }

    public static LinkedList<Holiday> getTypeHolidays(int type) {
        LinkedList<Holiday> holidays = new LinkedList<Holiday>();
        for (Holiday holiday : Resources.holidays) {
            if (holiday.getType().equals(HolidayType.values()[type])) holidays.add(holiday);
        }
        return holidays;
    }

    public static LinkedList<Holiday> getDateHolidays(Date date) {
        LinkedList<Holiday> holidays = new LinkedList<Holiday>();
        for (Holiday holiday : Resources.holidays) {
            if (holiday.getStartDate().equals(Holiday.dateFormat.format(date))) holidays.add(holiday);}
        return holidays;
    }

    public static ArrayList<Tradition> getTraditions(Holiday holiday) {
        ArrayList<Tradition> traditions = new ArrayList<Tradition>();
        for (Tradition tradition : Resources.traditions) {
            if ((tradition.getHoliday().getName().equals(holiday.getName()))&&
                    (tradition.getHoliday().getStartDate().equals(holiday.getStartDate())))
                traditions.add(tradition);}
        return traditions;
    }

}

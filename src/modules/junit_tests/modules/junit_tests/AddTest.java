
package modules.junit_tests;

import junit.framework.TestCase;
import junit.framework.TestSuite;
        import model.Country;
        import model.Holiday;
        import model.Tradition;
import modules.functional.Add;

import java.util.ArrayList;

//import static org.junit.Assert.assertTrue;

public class AddTest extends TestCase {
    static ArrayList<Country> countryList = new ArrayList<Country>();
    static ArrayList<Holiday> holidayList = new ArrayList<Holiday>();
    static ArrayList<Tradition> traditionList = new ArrayList<Tradition>();

    public AddTest(String testName){
        super(testName);
        countryList.add(new Country("Russia"));
        countryList.add(new Country("Россия"));

        holidayList.add(new Holiday("Адын"));
        holidayList.add(new Holiday("Два"));

        traditionList.add(new Tradition(holidayList.get(0), countryList.get(0)));
        traditionList.add(new Tradition(holidayList.get(1), countryList.get(1)));
    }

    public static void testAddEquals() {
        Country lastCountry = countryList.get(countryList.size() - 1);
        countryList = (ArrayList<Country>)Add.addCountry(new Country("Russia"), countryList);
        assertTrue(lastCountry.equals(countryList.get(countryList.size() - 1)));

        Holiday lastHoliday = holidayList.get(holidayList.size() - 1);
        holidayList = (ArrayList<Holiday>) Add.addHoliday("Адын", holidayList);
        assertTrue(lastHoliday.equals(holidayList.get(holidayList.size() - 1)));

        Tradition lastTradition = traditionList.get(traditionList.size() - 1);
        traditionList = (ArrayList<Tradition>)Add.addTradition(lastHoliday, lastCountry, traditionList);
        assertTrue(lastTradition.equals(traditionList.get(traditionList.size() - 1)));
    }

    public void testAddValue() {
        countryList = (ArrayList<Country>)Add.addCountry(new Country("Argentina"), countryList);
        assertTrue("Argentina".equals(countryList.get(countryList.size() - 1).getName()));

        holidayList = (ArrayList<Holiday>)Add.addHoliday("Праздник", holidayList);
        assertTrue("Праздник".equals(holidayList.get(holidayList.size() - 1).getName()));

        Tradition tradition = new Tradition(new Holiday("Holiday"), new Country("Country"));
        traditionList = (ArrayList<Tradition>)Add.addTradition(new Holiday("Holiday"), new Country("Country"), traditionList);
        assertTrue(tradition.equals(traditionList.get(traditionList.size() - 1)));
    }

    public static void main(String[] args) {
        junit.textui.TestRunner runner = new junit.textui.TestRunner();
        TestSuite suite = new TestSuite();
        suite.addTest(new AddTest("testAddEquals"));
        suite.addTest(new AddTest("testAddValue"));
        runner.doRun(suite);
    }
}

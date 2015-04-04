package modules.junit_tests;

import junit.framework.TestCase;
import main.Resources;
import model.Country;
import model.Holiday;
import model.Tradition;
import modules.functional.Add;
import modules.functional.Change;
import modules.functional.Search;
import org.junit.Test;

import java.util.ArrayList;

public class TestVally extends TestCase {


    public TestVally(String testName) {
        super(testName);
    }
    @Test
    public void testSearchIndex() {
        ArrayList<Tradition> traditions = new ArrayList<Tradition>();
        int expected = 0;
        Holiday holiday = new Holiday("День Ибна Нехура", 0);
        Country country = new Country("Египет");
        Add.addTradition(holiday, country, traditions);

        assertEquals(expected, Search.searchIndex(traditions, country.getName(), holiday.getName()));
    }
    @Test
    public void testChangeTradition() {

        ArrayList<Tradition> traditions = new ArrayList<Tradition>();
        Holiday holiday = new Holiday("День Ибна Нехура", 0);
        Country country = new Country("Египет");
        Add.addTradition(holiday, country, traditions);

        String description = "НЕВЕРОЯТНЫЕ ВАКХАНАЛИИ!";

        Tradition expected = traditions.get(0);
        expected.setDescription(description);

        Change.editTradition(0,expected.getHoliday(),expected.getCountry(),description, traditions);
        assertSame(expected,traditions.get(0));
    }
}

/*package modules.junit_tests;

import junit.framework.TestCase;
import main.Resources;
import model.Country;
import model.Holiday;
import model.Tradition;
import modules.functional.Change;
import modules.functional.Search;
import org.junit.Test;

public class TestVally extends TestCase {

    public TestVally(String testName) {
        super(testName);
    }
    @Test
    public void testSearchIndex() {
        int expected = 1;
        int actual = Search.searchIndex(Resources.traditions, "День программиста", "Россия");
        assertEquals(expected, actual);
    }
    @Test
    public void testChangeTradition() {
        Country country = Resources.countries.get(0);
        Holiday holiday = Resources.holidays.get(0);
        String description = "НЕВЕРОЯТНЫЕ ВАКХАНАЛИИ!";
        int id = 0;
        Tradition expected = new Tradition(holiday,country,description);
        Change.editTradition(id,holiday,country,description,Resources.traditions);
        Tradition actual = Resources.traditions.get(id);
        assertSame(expected,actual);
    }
}
*/
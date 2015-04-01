package modules.junit_tests;

import model.Country;
import modules.functional.Add;

import java.util.LinkedList;

public class AddTest {
    //@Test
    public void testAdd() {
        LinkedList<Country> list = new LinkedList<Country>();

        list = (LinkedList<Country>)Add.addCountry(new Country("Test1"), list);
    }
}

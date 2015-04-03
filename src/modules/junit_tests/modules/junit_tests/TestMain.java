package modules.junit_tests;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class TestMain extends TestCase {

    public static void main(String[] args) {
        TestRunner runner = new TestRunner();
        TestSuite suite = new TestSuite();
        suite.addTest(new TestVally("testSearchIndex"));
        suite.addTest(new TestVally("testChangeTradition"));
        runner.doRun(suite);
    }
}
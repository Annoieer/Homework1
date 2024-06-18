package homework_1;

import homework_1.annotation.*;

public class TestClass {
    @BeforeSuite
    public static void beforeSuite() {
        System.out.println("BEFORE SUITE");
    }

    @AfterSuite
    public static void afterSuite() {
        System.out.println("AFTER SUITE");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("BEFORE EACH");
    }

    @BeforeEach
    public void beforeEach2() {
        System.out.println("ANOTHER BEFORE EACH");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("AFTER EACH");
    }

    @Test(priority = 1)
    public void Test1() {
        System.out.println("TEST1");
    }

    @Test(priority = 3)
    public void Test2() {
        System.out.println("TEST2");
    }

    @Test(priority = 2)
    public void Test3() {
        System.out.println("TEST3");
    }

    @Test(priority = 4)
    public void Test4() {
        System.out.println("TEST4");
    }

    @CsvSource(info = "BOOLEAN STRING, true, 4, REPEATED TEXT ")
    public void TestCsvSource(String booleanString, Boolean isNeeded, int repeats, String info) {
        if (isNeeded) {
            System.out.println(booleanString);
        }

        for (int i = 0; i < repeats; i++) {
            System.out.println(info + i);
        }
    }
}

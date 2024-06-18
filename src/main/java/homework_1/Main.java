package homework_1;

import homework_1.processor.ArgumentsRunner;
import homework_1.processor.TestRunner;

public class Main {
    public static void main(String[] args) throws Exception {
        TestRunner.runTests(TestClass.class);
        ArgumentsRunner.runMethodsWithArg(TestClass.class);
    }
}

package homework_1.exception;

import java.lang.reflect.Method;

public class UncorrectModifierMethod extends Exception {
    public UncorrectModifierMethod(Method m, String annotationClass, String modifier) {
        System.out.println("Аннотация " + annotationClass + " применима только к " + modifier
                + " методам. Функция " + m.getName());
    }
}

package homework_1.exception;

import java.lang.reflect.Method;

public class OutOfIntervalException extends Exception {
    public OutOfIntervalException(Method m) {
        System.out.println("Значение приоритета должно быть от 1 до 10. Функция " + m.getName());
    }
}

package homework_1.exception;

import java.lang.reflect.Method;

public class IllegalAnnotationParametersException extends Exception{
    public IllegalAnnotationParametersException(Method m) {
        System.out.println("Типы данных аннотации не совпадают с типом данных параметров функции. Функция "
                + m.getName());
    }
}

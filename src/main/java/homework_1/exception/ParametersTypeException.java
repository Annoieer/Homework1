package homework_1.exception;

import java.lang.reflect.Method;

public class ParametersTypeException extends Exception{
    public ParametersTypeException(Method m) {
        System.out.println("В функции присутствуют необрабатываемые типы. Функция " + m.getName());
    }
}

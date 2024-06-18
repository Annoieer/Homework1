package homework_1.exception;

import java.lang.reflect.Method;

public class IllegalArgsCountException extends Exception {
    public IllegalArgsCountException(Method m) {
        System.out.println("Количество аргументов аннотации не совпадает с количеством аргументов функции. Функция "
                + m.getName());
    }
}

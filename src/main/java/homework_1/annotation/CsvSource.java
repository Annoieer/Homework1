package homework_1.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Разделитель аргументов функции ', '
 * Поддерживаемые типы boolean, Boolean, int, Integer, Long, String
 */
@Target(value= ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface CsvSource {
    String info() default "";
}

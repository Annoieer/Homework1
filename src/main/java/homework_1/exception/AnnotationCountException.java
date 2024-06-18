package homework_1.exception;

import java.lang.reflect.Method;

public class AnnotationCountException extends Exception{
    public AnnotationCountException(Method m, String annotationClass) {
        System.out.println("В классе более одного метода " + annotationClass + ". Функция " + m.getName());
    }
}

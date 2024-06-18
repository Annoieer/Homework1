package homework_1.processor;

import homework_1.annotation.CsvSource;
import homework_1.exception.IllegalAnnotationParametersException;
import homework_1.exception.IllegalArgsCountException;
import homework_1.exception.ParametersTypeException;

import java.lang.reflect.Method;

public class ArgumentsRunner {
    public static <T> void runMethodsWithArg(Class<T> c) throws Exception {
        Object o = c.getConstructor().newInstance();
        Method[] methods = c.getDeclaredMethods();

        for (Method method : methods) {
            if (!method.isAnnotationPresent(CsvSource.class)) {
                continue;
            }

            Class<?>[] params = method.getParameterTypes();
            String arguments = method.getDeclaredAnnotation(CsvSource.class).info();
            String[] allArgs = arguments.split(", ");

            if (params.length != allArgs.length) {
                throw new IllegalArgsCountException(method);
            }

            Object[] args = new Object[params.length];
            try {
                for (int i = 0; i < params.length; i++) {
                    if (String.class.equals(params[i])) {
                        args[i] = (allArgs[i]);
                    } else if (int.class.equals(params[i]) || Integer.class.equals(params[i])) {
                        args[i] = (Integer.parseInt(allArgs[i]));
                    } else if (boolean.class.equals(params[i]) || Boolean.class.equals(params[i])) {
                        args[i] = (Boolean.parseBoolean(allArgs[i]));
                    } else if (Long.class.equals(params[i])) {
                        args[i] = (Long.parseLong(allArgs[i]));
                    } else {
                        throw new ParametersTypeException(method);
                    }
                }
            } catch (NumberFormatException e) {
                throw new IllegalAnnotationParametersException(method);
            }

            method.invoke(o, args);
        }
    }
}

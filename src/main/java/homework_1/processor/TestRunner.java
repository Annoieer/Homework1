package homework_1.processor;

import homework_1.annotation.*;
import homework_1.exception.AnnotationCountException;
import homework_1.exception.OutOfIntervalException;
import homework_1.exception.UncorrectModifierMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class TestRunner {
    public static <T> void runTests(Class<T> c) throws Exception {
        final String staticMethod = "статическим";
        final String nonStaticMethod = "нестатическим";

        Object o = c.getConstructor().newInstance();
        Method[] methods = c.getDeclaredMethods();

        Queue<Method> queueBeforeSuite = new PriorityQueue<>();
        Queue<Method> queueAfterSuite = new PriorityQueue<>();
        Queue<Method> queueBeforeEach = new PriorityQueue<>(
                Comparator.comparingInt(m -> m.getDeclaredAnnotation(BeforeEach.class).priority())
        );
        Queue<Method> queueAfterEach = new PriorityQueue<>(
                Comparator.comparingInt(m -> m.getDeclaredAnnotation(AfterEach.class).priority())
        );
        Queue<Method> queueTests = new PriorityQueue<>(
                Comparator.comparingInt(m -> m.getDeclaredAnnotation(Test.class).priority()));

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (!queueBeforeSuite.isEmpty()) {
                    throw new AnnotationCountException(method, BeforeSuite.class.getName());
                }
                if (!Modifier.isStatic(method.getModifiers())) {
                    throw new UncorrectModifierMethod(method, BeforeSuite.class.getName(), staticMethod);
                }
                queueBeforeSuite.add(method);
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (!queueAfterSuite.isEmpty()) {
                    throw new AnnotationCountException(method, AfterSuite.class.getName());
                }
                if (!Modifier.isStatic(method.getModifiers())) {
                    throw new UncorrectModifierMethod(method, AfterSuite.class.getName(), staticMethod);
                }
                queueAfterSuite.add(method);
            }
            if (method.isAnnotationPresent(AfterEach.class)) {
                if (Modifier.isStatic(method.getModifiers())) {
                    throw new UncorrectModifierMethod(method, AfterEach.class.getName(), nonStaticMethod);
                }
                queueAfterEach.add(method);
            }
            if (method.isAnnotationPresent(BeforeEach.class)) {
                if (Modifier.isStatic(method.getModifiers())) {
                    throw new UncorrectModifierMethod(method, BeforeEach.class.getName(), nonStaticMethod);
                }
                queueBeforeEach.add(method);
            }
            if (method.isAnnotationPresent(Test.class)) {
                int priority = method.getAnnotation(Test.class).priority();
                if (priority > 10 || priority < 1) {
                    throw new OutOfIntervalException(method);
                }
                queueTests.add(method);
            }
        }

        Method method;
        while ((method = queueBeforeSuite.poll()) != null) {
            method.invoke(o);
        }
        while ((method = queueTests.poll()) != null) {
            Method m;
            for (Method beforeEach : queueBeforeEach) {
                m = beforeEach;
                m.invoke(o);
            }

            method.invoke(o);

            for (Method afterEach : queueAfterEach) {
                m = afterEach;
                m.invoke(o);
            }
        }
        while ((method = queueAfterSuite.poll()) != null) {
            method.invoke(o);
        }
    }
}

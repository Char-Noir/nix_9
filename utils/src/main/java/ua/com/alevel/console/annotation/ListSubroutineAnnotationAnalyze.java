package ua.com.alevel.console.annotation;

import ua.com.alevel.console.Subroutine;

import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ListSubroutineAnnotationAnalyze {

    public static void runFirstSubroutine(Class<? extends Subroutine>[] subroutines, BufferedReader bf) {
        for (Class<?> cass :
                subroutines) {
            if (cass.isAnnotationPresent(ListSubroutine.class)) {
                try {
                    Method method = cass.getMethod("run", BufferedReader.class);
                    method.invoke(cass.getConstructor().newInstance(), bf);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void runFirstSubroutine(Subroutine[] subroutines, BufferedReader bf) {
        List<Class<? extends Subroutine>> classes = new ArrayList<>();
        for (Subroutine subroutine : subroutines) {
            classes.add(subroutine.getClass());
        }
        runFirstSubroutine(classes.toArray(new Class[0]), bf);
    }
}

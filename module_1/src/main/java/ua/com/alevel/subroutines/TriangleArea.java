package ua.com.alevel.subroutines;

import ua.com.alevel.console.ConsoleSubroutine;
import ua.com.alevel.level1.GeometryHelper;
import ua.com.alevel.level1.entity.Point2D;

import java.io.BufferedReader;

public class TriangleArea extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Считает площадь треугольника, если такой существует. Используем формулу для просчета площи по координатам трех вершин.";
    public final String SHORT_DESCRIPTION = "Площадь треугольника.";

    public final String EXPECTED_INPUT = "Три строки, где указаны координаты вершин через зяпятую. 1,2 \\n 2,4 \\n 3,6\\n";

    @Override
    public String getShortDescription() {
        return SHORT_DESCRIPTION;
    }

    @Override
    public String getLongDescription() {
        return LONG_DESCRIPTION;
    }

    @Override
    public String getExpectedInput() {
        return EXPECTED_INPUT;
    }

    @Override
    public void run(BufferedReader bufferedReader) {
        Point2D A, B, C;
        double area = 0;
        try {
            String first_pos = bufferedReader.readLine();
            String[] sPoses = first_pos.split(",");
            if (sPoses.length != 2) {
                throw new Exception();
            }
            A = new Point2D(Integer.parseInt(sPoses[0]), Integer.parseInt(sPoses[1]));
            String second_pos = bufferedReader.readLine();
            sPoses = second_pos.split(",");
            if (sPoses.length != 2) {
                throw new Exception();
            }
            B = new Point2D(Integer.parseInt(sPoses[0]), Integer.parseInt(sPoses[1]));
            String third_pos = bufferedReader.readLine();
            sPoses = third_pos.split(",");
            if (sPoses.length != 2) {
                throw new Exception();
            }
            C = new Point2D(Integer.parseInt(sPoses[0]), Integer.parseInt(sPoses[1]));
            area = GeometryHelper.calculateTriangleSquare(A, B, C);
        } catch (IllegalArgumentException e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            System.out.println(EXPECTED_INPUT);
        }
        System.out.println("Результат задания: " + (area == 0 ? "треугольник не существует" : "площа равна " + area));
    }
}

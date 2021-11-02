package ua.com.alevel.level1;

import ua.com.alevel.level1.entity.Point2D;

public class GeometryHelper {
    public static double calculateTriangleSquare(Point2D A, Point2D B, Point2D C) {
        double AB = distance(A, B);
        double BC = distance(B, C);
        double AC = distance(A, C);
        if (!isTriangleExist(AB, BC, AC)) {
            throw new IllegalArgumentException("Triangle dont exists");
        }
        double semiPerimeter = (AB + BC + AC) / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - AB) * (semiPerimeter - BC) * (semiPerimeter - AC));
    }

    public static double distance(Point2D A, Point2D B) {
        return Math.sqrt(Math.pow((B.getX() - A.getX()), 2) + Math.pow((B.getY() - A.getY()), 2));
    }

    public static boolean isTriangleExist(double first, double second, double third) {
        if (first + second >= third) {
            if (second + third >= first)
                return first + third >= second;
        }
        return false;
    }
}

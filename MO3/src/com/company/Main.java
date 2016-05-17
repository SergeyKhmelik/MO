package com.company;

import java.util.Vector;

public class Main {

    private static class Point {
        double x1;
        double x2;

        Point(double x1, double x2) {
            this.x1 = x1;
            this.x2 = x2;
        }

        Point minus(Point p) {
            return new Point(this.x1 - p.x1, this.x2 - p.x2);
        }

        @Override
        public String toString() {
            return "x1 = " + x1 + " x2 = " + x2;
        }
    }

    private static double f(Point p) {
        return Math.pow(p.x1, 2) - p.x2;
    }

    private static double dfdx1(Point p) {
        return 2 * p.x1;
    }

    private static double dfdx2(Point p) {
        return -1;
    }

    private static Point grad(Point p) {
        double x1 = dfdx1(p);
        double x2 = dfdx2(p);
        return new Point(x1, x2);
    }

    private static Point projection(Point p, double a, double b, double r) {
        double x1 = a + (p.x1 - a) * r / Math.sqrt(Math.pow(p.x1 - a, 2) + Math.pow(p.x2 - b, 2));
        double x2 = b + (p.x2 - b) * r / Math.sqrt(Math.pow(p.x1 - a, 2) + Math.pow(p.x2 - b, 2));
        return new Point(x1, x2);
    }

    private static Vector<Point> gradientProjectionMethod(Point p, double a, double b, double r, double e) {
        Vector<Point> steps = new Vector<>();
        steps.add(p);
        do {
            p = steps.lastElement();
            steps.add(projection(p.minus(grad(p)), a, b, r));
        } while (Math.abs(f(p) - f(steps.lastElement())) > e);
        return steps;
    }

    public static void main(String[] args) {
        Point startingPoint = new Point(20, 20);
        double a = 2;
        double b = 7;
        double r = 2;
        double e = 0.000001;
        Vector<Point> results = gradientProjectionMethod(startingPoint, a, b, r, e);
        for (Point p : results) {
            System.out.println(p + " f(x1, x2) = " + f(p));
        }
        Point pMin = results.lastElement();
        System.out.println("Minimum: " + pMin + " f(x1, x2) = " + f(pMin));
    }
}

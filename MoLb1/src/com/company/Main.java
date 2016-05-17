package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static double f(double x) {
        return Math.pow(x, 4) + 8  * Math.pow(x, 2) - 12 * x + 5;
    }

    private static double grad(double x) {
        return 4 * Math.pow(x, 3) + 16 * x - 12;
    }

    private static double hessian(double x) {
        return 12 * Math.pow(x, 2) + 16;
    }

    private static List<Double> dichotomy(double a, double b, double e) {
        List<Double> results = new ArrayList<>();
        while (Math.abs(a - b) > e) {
            double middle = (a + b) / 2;
            double left = f(middle - e / 2);
            double right = f(middle + e / 2);
            if (left < right) {
                b = middle;
            } else {
                a = middle;
            }
            results.add(middle);
        }
        return results;
    }

    private static List<Double> goldenRatio(double a, double b, double e) {
        List<Double> results = new ArrayList<>();
        double t = (1 + Math.sqrt(5)) / 2;
        double x1 = b + (a - b) / t;
        double x2 = a + (b - a) / t;
        double left = f(x1);
        double right = f(x2);
        results.add((a + b) / 2);
        while (Math.abs(a - b) > e) {
            if (left < right) {
                b = x2;
                x2 = x1;
                right = left;
                x1 = b + (a - b) / t;
                left = f(x1);
            } else {
                a = x1;
                x1 = x2;
                left = right;
                x2 = a + (b - a) / t;
                right = f(x2);
            }
            results.add((a + b) / 2);
        }
        return results;
    }

    public static void main(String[] args) {
        double a = 0;
        double b = 1;
        double e = 0.0001;

        List<Double> results = dichotomy(a, b, e);
        for (Double x : results) {
            System.out.println("x = " + x + " f(x) = " + f(x));
        }
        System.out.println("Function calculated " + (results.size() * 2 + 2) + " times");
        double xMin = results.get(results.size() - 1);
        System.out.println("x* = " + xMin);
        System.out.println("grad(x*) = " + grad(xMin));
        System.out.println("H(x*) = " + hessian(xMin) + "\n");

        results = goldenRatio(a, b, e);
        for (Double x : results) {
            System.out.println("x = " + x + " f(x) = " + f(x));
        }
        System.out.println("Function calculated " + (results.size() + 2) + " times");
        xMin = results.get(results.size() - 1);
        System.out.println("x* = " + xMin);
        System.out.println("grad(x*) = " + grad(xMin));
        System.out.println("H(x*) = " + hessian(xMin));
    }

}

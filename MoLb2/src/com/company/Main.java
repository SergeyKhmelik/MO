package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<IterationDto<Double>> results = GradientDescentService.getMinimum(
                (x1, x2) -> Math.pow(x1, 3) + x1 * Math.pow(x2, 2) + 6 * x1 * x2,
                (x1, x2) -> 3 * Math.pow(x1, 2) + Math.pow(x2, 2) + 6 * x2,
                (x1, x2) -> Math.pow(x1, 3) + 2 * x1 * x2 + 6 * x1,
                0.001);

        results.forEach(iteration ->
                System.out.println(iteration.getFirst() + " " + iteration.getSecond() + " " + iteration.getResult()));


        System.out.println(function(1.5, -3));

    }

    private static double function(double x1, double x2) {
       return Math.pow(x1, 3) + x1 * Math.pow(x2, 2) + 6 * x1 * x2;
    }
}

package com.company;

import java.util.List;
import java.util.function.BiFunction;

public class Main {

    public static void main(String[] args) {

        BiFunction<Double, Double, Double> function = (x1, x2) -> Math.pow(x1, 3) + x1 * Math.pow(x2, 2) + 6 * x1 * x2;
        BiFunction<Double, Double, Double> x1Derivative = (x1, x2) -> 3 * Math.pow(x1, 2) + Math.pow(x2, 2) + 6 * x2;
        BiFunction<Double, Double, Double> x2Derivative = (x1, x2) -> 2 * x1 * x2 + 6 * x1;
        BiFunction<Double, Double, Double> x1SecondDerivative = (x1, x2) -> 6 * x1;
        BiFunction<Double, Double, Double> x2SecondDerivative = (x1, x2) -> 2 * x1;


        double firstApproximationX1 = 1.5;
        double firstApproximationX2 = -2d;
        double e = 0.01;

        List<IterationDto> gradientDescentResults = GradientDescentService.getMinimum(function,
                x1Derivative, x2Derivative,
                firstApproximationX1, firstApproximationX2,
                e);

        List<IterationDto> coordinateDescentResults = CoordinateDescentService.getMinimum(function,
                x1Derivative, x2Derivative,
                firstApproximationX1, firstApproximationX2,
                x1SecondDerivative, x2SecondDerivative,
                e);

        List<IterationDto> newtonResults = NewtonService.getMinimum(function, x1Derivative, x2Derivative,
                firstApproximationX1, firstApproximationX2, e);

        System.out.println("Gradient descent");
        printResults(gradientDescentResults);

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Coordinate descent");
        printResults(coordinateDescentResults);

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Newton");
//        printResults(newtonResults);
    }

    private static void printResults(List<IterationDto> gradientDescentResults) {
        gradientDescentResults.forEach(iteration -> System.out.println("x1: " + iteration.getFirst() +
                ",\t x2: " + iteration.getSecond() +
                ",\t step: " + iteration.getStep() +
                ",\t f: " + iteration.getResult()));
        System.out.println("Iterations made: " + gradientDescentResults.size());
    }

    public static double fullDerivative(BiFunction<Double, Double, Double> x1Derivative, double x1,
                                         BiFunction<Double, Double, Double> x2Derivative, double x2) {
        return Math.sqrt(Math.pow(x1Derivative.apply(x1, x2), 2) + Math.pow(x2Derivative.apply(x1, x2), 2));
    }

}

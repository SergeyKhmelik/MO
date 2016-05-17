package com.company;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.BiFunction;

public class Main {

    public static void main(String[] args) {

        BiFunction<Double, Double, Double> function = (x1, x2) -> Math.pow(x1, 3) + Math.pow(x2, 2) - 6 * x1 * x2 - 39 * x1 + 18 * x2 + 20;
        BiFunction<Double, Double, Double> x1Derivative = (x1, x2) -> 3 * Math.pow(x1, 2) - 6 * x2 - 39;
        BiFunction<Double, Double, Double> x2Derivative = (x1, x2) -> 2 * x2 - 6 * x1 + 18;
            BiFunction<Double, Double, Double> x1SecondDerivative = (x1, x2) -> 6 * x1;
        BiFunction<Double, Double, Double> x2SecondDerivative = (x1, x2) -> 2 + 0*x1;
        BiFunction<Double, Double, Double> x1x2Directive = (x1, x2) -> 0 * x1 - 6;
        BiFunction<Double, Double, Double> x2x1Directive = (x1, x2) ->  0 * x1 - 6;

        double firstApproximationX1 = 4;
        double firstApproximationX2 = 5;
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

        List<IterationDto> newtonResults = NewtonService.getMinimum(function,
                x1Derivative, x2Derivative,
                x1SecondDerivative, x2SecondDerivative,
                x1x2Directive, x2x1Directive,
                firstApproximationX1, firstApproximationX2,
                e);

        System.out.println("Gradient descent");
        printResults(gradientDescentResults);
        System.out.println("Full derivative: " +
                fullDerivative(
                        x1Derivative, gradientDescentResults.get(gradientDescentResults.size() - 1).getFirst(),
                        x2Derivative, gradientDescentResults.get(gradientDescentResults.size() - 1).getSecond()));

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Coordinate descent");
        printResults(coordinateDescentResults);
        System.out.println("Full derivative: " +
                fullDerivative(
                        x1Derivative, gradientDescentResults.get(gradientDescentResults.size() - 1).getFirst(),
                        x2Derivative, gradientDescentResults.get(gradientDescentResults.size() - 1).getSecond()));

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Newton");
        printResults(newtonResults);
        System.out.println("Full derivative: " +
                fullDerivative(
                        x1Derivative, gradientDescentResults.get(gradientDescentResults.size() - 1).getFirst(),
                        x2Derivative, gradientDescentResults.get(gradientDescentResults.size() - 1).getSecond()));
    }

    private static void printResults(List<IterationDto> gradientDescentResults) {
        gradientDescentResults.forEach(iteration -> System.out.println("x1: " + iteration.getFirst() +
                ",\t x2: " + iteration.getSecond() +
                ",\t step: " + iteration.getStep() +
                ",\t f: " + iteration.getResult() +
                ",\t fullDerivative: " + iteration.getFullDerivative()));
        System.out.println("Iterations made: " + gradientDescentResults.size());
    }

    public static double fullDerivative(BiFunction<Double, Double, Double> x1Derivative, double x1,
                                        BiFunction<Double, Double, Double> x2Derivative, double x2) {
        return Math.sqrt(Math.pow(x1Derivative.apply(x1, x2), 2) + Math.pow(x2Derivative.apply(x1, x2), 2));
    }

}

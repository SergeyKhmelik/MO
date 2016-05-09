package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class NewtonService {

    public static List<IterationDto> getMinimum(
            BiFunction<Double, Double, Double> function,
            BiFunction<Double, Double, Double> x1Derivative, BiFunction<Double, Double, Double> x2Derivative,
            BiFunction<Double, Double, Double> x1SecondDerivative, BiFunction<Double, Double, Double> x2SecondDerivative,
            BiFunction<Double, Double, Double> x1x2Derivative, BiFunction<Double, Double, Double> x2x1Derivative,
            double firstApproximationX1, double firstApproximationX2,
            double e) {

        List<IterationDto> iterations = new ArrayList<>();

        double x1 = firstApproximationX1;
        double x2 = firstApproximationX2;

        do {
            double oneDivDet = oneDivDet(x1, x2, x1SecondDerivative, x2SecondDerivative, x1x2Derivative, x2x1Derivative);

            double newX1 = x1 - x1Approximate(x1, x2, x2SecondDerivative, x1Derivative, x1x2Derivative, x2Derivative, oneDivDet);
            double newX2 = x2 - x2Approximate(x1, x2, x1SecondDerivative, x1Derivative, x2x1Derivative, x2Derivative, oneDivDet);

            x1 = newX1;
            x2 = newX2;

            iterations.add(new IterationDto(x1, x2, function.apply(x1, x2), 0));
        } while (!(Math.abs(x1Derivative.apply(x1, x2)) <= e && Math.abs(x2Derivative.apply(x1, x2)) <= e));

        return iterations;
    }

    private static double x1Approximate(double x1, double x2,
                                        BiFunction<Double, Double, Double> x2SecondDerivative,
                                        BiFunction<Double, Double, Double> x1Derivative,
                                        BiFunction<Double, Double, Double> x1x2Derivative,
                                        BiFunction<Double, Double, Double> x2Derivative,
                                        double oneDivDev) {
        return oneDivDev * (x2SecondDerivative.apply(x1, x2) * x1Derivative.apply(x1, x2) -
                x1x2Derivative.apply(x1, x2) * x2Derivative.apply(x1, x2));
    }

    private static double x2Approximate(double x1, double x2,
                                        BiFunction<Double, Double, Double> x1SecondDerivative,
                                        BiFunction<Double, Double, Double> x1Derivative,
                                        BiFunction<Double, Double, Double> x2x1Derivative,
                                        BiFunction<Double, Double, Double> x2Derivative,
                                        double oneDivDev) {
        return oneDivDev * (x1SecondDerivative.apply(x1, x2) * x2Derivative.apply(x1, x2) -
                x2x1Derivative.apply(x1, x2) * x1Derivative.apply(x1, x2));
    }

    private static double oneDivDet(double x1, double x2,
                                    BiFunction<Double, Double, Double> x1SecondDerivative,
                                    BiFunction<Double, Double, Double> x2SecondDerivative,
                                    BiFunction<Double, Double, Double> x1x2Derivative,
                                    BiFunction<Double, Double, Double> x2x1Derivative) {
        return 1 / (x1SecondDerivative.apply(x1, x2) * x2SecondDerivative.apply(x1, x2) -
                x1x2Derivative.apply(x1, x2) * x2x1Derivative.apply(x1, x2));
    }

}

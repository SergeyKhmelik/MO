package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static com.company.Main.fullDerivative;

public class CoordinateDescentService {

    public static List<IterationDto> getMinimum(
            BiFunction<Double, Double, Double> function,
            BiFunction<Double, Double, Double> x1Derivative, BiFunction<Double, Double, Double> x2Derivative,
            double x1Val, double x2Val,
            BiFunction<Double, Double, Double> x1SecondDerivative, BiFunction<Double, Double, Double> x2SecondDerivative,
            double e) {

        List<IterationDto> iterations = new ArrayList<>();

        double x1 = x1Val;
        double x2 = x2Val;

        do {
            if (Math.abs(x1Derivative.apply(x1, x2)) > e) {
                x1 = x1 - x1Derivative.apply(x1, x2) / x1SecondDerivative.apply(x1, x2);
                iterations.add(new IterationDto(x1, x2, function.apply(x1, x2), 0,
                        fullDerivative(x1Derivative, x1, x2Derivative, x2)));
            }

            if (Math.abs(x2Derivative.apply(x1, x2)) > e) {
                x2 = x2 - x2Derivative.apply(x1, x2) / x2SecondDerivative.apply(x1, x2);
                iterations.add(new IterationDto(x1, x2, function.apply(x1, x2), 0,
                        fullDerivative(x1Derivative, x1, x2Derivative, x2)));
            }

        } while (!(Math.abs(x1Derivative.apply(x1, x2)) <= e && Math.abs(x2Derivative.apply(x1, x2)) <= e));

        return iterations;
    }

}

package com.company;

import java.util.List;
import java.util.function.BiFunction;

public class NewtonService {

    public static List<IterationDto> getMinimum(
            BiFunction<Double, Double, Double> function,
            BiFunction<Double, Double, Double> x1Derivative,
            BiFunction<Double, Double, Double> x2Derivative,
            double firstApproximationX1, double firstApproximationX2,
            double e) {
        return null;
    }

}

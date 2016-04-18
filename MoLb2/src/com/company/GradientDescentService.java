package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GradientDescentService {

    public static List<IterationDto<Double>> getMinimum(BiFunction<Double, Double, Double> function,
                                       BiFunction<Double, Double, Double> x1Derivative,
                                       BiFunction<Double, Double, Double> x2Derivative,
                                       double e) {
        List<IterationDto<Double>> iterations = new ArrayList<>();

        int k = 0;          //iteration counter
        double a = 1d;      //step size

        double x1 = 1.5;
        double x2 = -2d;
        double result = function.apply(x1, x2);
        double nextResult;

        IterationDto<Double> iterationData = new IterationDto(x1, x2, result);
        iterations.add(iterationData);

        do{
            // 1. Change x1 & x2 by step
            double newX1, newX2;
            newX1 = x1 - a * x1Derivative.apply(x1, x2);
            newX2 = x2 - a * x2Derivative.apply(x1, x2);

            // 2. Find function result for new x1 & x2
            nextResult = function.apply(newX1, newX2);

            // 3. Update step if needed
            if (nextResult >= result) {
                a = a / 2;
            }

            x1 = newX1;
            x2 = newX2;
            result = nextResult;
            //Add iteration data to result dto
            iterations.add(new IterationDto(x1, x2, result));
        } while(fullDerivative(x1Derivative, x1, x2Derivative, x2) >= e);

            return iterations;
    }

    private static double fullDerivative(BiFunction<Double, Double, Double> x1Derivative, double x1,
                                         BiFunction<Double, Double, Double> x2Derivative, double x2) {
        return x1Derivative.apply(x1, x2) + x2Derivative.apply(x1, x2);
    }

}
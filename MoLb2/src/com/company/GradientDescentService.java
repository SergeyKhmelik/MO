package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static com.company.Main.fullDerivative;

public class GradientDescentService {

    public static List<IterationDto> getMinimum(
            BiFunction<Double, Double, Double> function,
            BiFunction<Double, Double, Double> x1Derivative,
            BiFunction<Double, Double, Double> x2Derivative,
            double x1,
            double x2,
            double e) {

        List<IterationDto> iterations = new ArrayList<>();

        double a = 0.5;     //step size
        double result = function.apply(x1, x2);
        double nextResult;

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
            } else {
                x1 = newX1;
                x2 = newX2;
                result = nextResult;
            }

            //Add iteration data to result dto
            iterations.add(new IterationDto(x1, x2, result, a, fullDerivative(x1Derivative, x1, x2Derivative, x2)));

        } while(fullDerivative(x1Derivative, x1, x2Derivative, x2) >= e);

        return iterations;
    }
}


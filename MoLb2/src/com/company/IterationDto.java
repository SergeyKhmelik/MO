package com.company;

public class IterationDto {

    private double first;
    private double second;
    private double result;
    private double step;
    private double fullDerivative;

    public IterationDto(double first, double second, double result, double step, double fullDerivative) {
        this.first = first;
        this.second = second;
        this.result = result;
        this.step = step;
        this.fullDerivative = fullDerivative;
    }

    public double getFirst() {
        return first;
    }

    public void setFirst(double first) {
        this.first = first;
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getFullDerivative() {
        return fullDerivative;
    }

    public void setFullDerivative(double fullDerivative) {
        this.fullDerivative = fullDerivative;
    }
}

package com.company;

public class IterationDto<T> {

    private T first;
    private T second;
    private T result;

    public IterationDto(T first, T second, T result) {
        this.first = first;
        this.second = second;
        this.result = result;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

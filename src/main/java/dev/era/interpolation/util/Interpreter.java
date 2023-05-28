package dev.era.interpolation.util;

public abstract class Interpreter<T> {


    protected T start, end;


    public Interpreter(T start, T end) {
        this.start = start;
        this.end = end;
    }

    public Interpreter() {
        start = null;
        end = null;
    }

    public abstract T interpolate(double elapsedTime);


    public T getStart() {
        return this.start;
    }

    public T getEnd() {
        return this.end;
    }

    public Interpreter<T> start(T value) {
        this.start = value;
        return this;
    }

    public Interpreter<T> end(T value) {
        this.end = value;
        return this;
    }
}

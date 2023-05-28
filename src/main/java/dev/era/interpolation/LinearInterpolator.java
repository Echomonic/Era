package dev.era.interpolation;


import java.util.function.Supplier;

public class LinearInterpolator<T> {


    public T interpret(Supplier<T> i) {
        return i.get();
    }


}

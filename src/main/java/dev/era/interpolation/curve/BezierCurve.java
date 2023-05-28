package dev.era.interpolation.curve;


import dev.era.interpolation.sub.EulerAngleInterpreter;
import dev.era.interpolation.sub.FloatInterpreter;
import dev.era.interpolation.sub.LocationInterpreter;
import dev.era.interpolation.sub.VectorInterpreter;

public abstract class BezierCurve<T> {


    protected LocationInterpreter startLocationInterpreter, middleEndLocationInterpreter,middleToMiddleLocationInterpreter;
    protected EulerAngleInterpreter startEulerInterpreter, middleEndEulerInterpreter,middleToMiddleEulerAngleInterpreter;
    protected FloatInterpreter startFloatInterpreter, middleEndFloatInterpreter, middleToMiddleFloatInterpreter;
    protected VectorInterpreter startVectorInterpreter, middleEndVectorInterpreter, middleToMiddleVectorInterpreter;

    protected final T[] supplies = (T[]) new Object[3];

    public abstract T curve(double elapsedTime);

}

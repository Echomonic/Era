package dev.era.interpolation.curve;

import dev.era.interpolation.sub.EulerAngleInterpreter;
import dev.era.interpolation.sub.FloatInterpreter;
import dev.era.interpolation.sub.LocationInterpreter;
import dev.era.interpolation.sub.VectorInterpreter;
import org.bukkit.Location;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.function.Supplier;

public class Cubic<T> extends BezierCurve<T> {

    private final T[] supplies = (T[]) new Object[4];

    public Cubic<T> init(Supplier<T[]> supplier) {
        if (supplier.get().length != 4) return this;

        supplies[0] = supplier.get()[0];
        supplies[1] = supplier.get()[1];
        supplies[2] = supplier.get()[2];
        supplies[3] = supplier.get()[3];

        T start = supplies[0];
        T middleFirst = supplies[1];
        T middleSecond = supplies[2];
        T end = supplies[3];

        if (start instanceof Location) {
            startLocationInterpreter = new LocationInterpreter().start((Location) start).end((Location) middleFirst);
            middleToMiddleLocationInterpreter = new LocationInterpreter().start((Location) middleFirst).end((Location) middleSecond);
            middleEndLocationInterpreter = new LocationInterpreter().start((Location) middleSecond).end((Location) end);
            return this;
        } else if (start instanceof EulerAngle) {
            startEulerInterpreter = new EulerAngleInterpreter().start((EulerAngle) start).end((EulerAngle) middleFirst);
            middleToMiddleEulerAngleInterpreter = new EulerAngleInterpreter().start((EulerAngle) middleFirst).end((EulerAngle) middleSecond);
            middleEndEulerInterpreter = new EulerAngleInterpreter().start((EulerAngle) middleSecond).end((EulerAngle) end);
            return this;
        } else if (start instanceof Vector) {
            startVectorInterpreter = new VectorInterpreter().start((Vector) start).end((Vector) middleFirst);
            middleToMiddleVectorInterpreter = new VectorInterpreter().start((Vector) middleFirst).end((Vector) middleSecond);
            middleEndVectorInterpreter = new VectorInterpreter().start((Vector) middleSecond).end((Vector) end);
            return this;
        }
        startFloatInterpreter = new FloatInterpreter().start((Float) start).end((Float) middleFirst);
        middleToMiddleFloatInterpreter = new FloatInterpreter().start((Float) middleFirst).end((Float) middleFirst);
        middleEndFloatInterpreter = new FloatInterpreter().start((Float) middleSecond).end((Float) end);

        return this;
    }

    public T curve(double elapsedTime, boolean rounded) {

        T start = supplies[0];
        //start = p0
        //middle = p1
        //middle2 = p2
        //end = p3

        //l1 = p0 + p1
        //l2 = p1 + p2
        //l3 = p2 + p3
        //quadA = l1 + l2
        //quadB = l2 + l3

        if (start instanceof Location) {


            LocationInterpreter l1 = new LocationInterpreter().start(startLocationInterpreter.interpolate(elapsedTime)).end(middleToMiddleLocationInterpreter.interpolate(elapsedTime));
            LocationInterpreter l2 = new LocationInterpreter().start(middleToMiddleLocationInterpreter.interpolate(elapsedTime)).end(middleEndLocationInterpreter.interpolate(elapsedTime));

            LocationInterpreter quadA = new LocationInterpreter();
            quadA.start(l1.interpolate(elapsedTime, rounded)).end(l2.interpolate(elapsedTime, rounded));

            Location quadOutput = quadA.interpolate(elapsedTime, rounded);

            return (T) quadOutput;
        } else if (start instanceof EulerAngle) {
            EulerAngleInterpreter quadInterpreter = new EulerAngleInterpreter().start(middleToMiddleEulerAngleInterpreter.interpolate(elapsedTime)).end(middleEndEulerInterpreter.interpolate(elapsedTime));
            EulerAngle quadOutput = quadInterpreter.interpolate(elapsedTime);
            return (T) quadOutput;
        } else if (start instanceof Vector) {
            VectorInterpreter quadInterpreter = new VectorInterpreter().start(middleToMiddleVectorInterpreter.interpolate(elapsedTime)).end(middleEndVectorInterpreter.interpolate(elapsedTime));
            Vector quadOutput = quadInterpreter.interpolate(elapsedTime);
            return (T) quadOutput;
        }

        FloatInterpreter quad = new FloatInterpreter().start(middleToMiddleFloatInterpreter.interpolate(elapsedTime)).end(middleEndFloatInterpreter.interpolate(elapsedTime));
        return (T) quad.interpolate(elapsedTime);
    }

    @Override
    public T curve(double elapsedTime) {
        return curve(elapsedTime, false);
    }

    public T curveDistance(double elapsedTime, double totalDistance) {

        double currentDistance = totalDistance * elapsedTime;
        double progress = currentDistance / totalDistance;

        return curve(progress, false);
    }

}

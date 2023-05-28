package dev.era.interpolation.curve;

import dev.era.interpolation.sub.EulerAngleInterpreter;
import dev.era.interpolation.sub.FloatInterpreter;
import dev.era.interpolation.sub.LocationInterpreter;
import dev.era.interpolation.sub.VectorInterpreter;
import org.bukkit.Location;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.function.Supplier;

public class Quadratic<T> extends BezierCurve<T> {



    public Quadratic<T> init(Supplier<T[]> supplier) {
        if (supplier.get().length != 3) return this;

        supplies[0] = supplier.get()[0];
        supplies[1] = supplier.get()[1];
        supplies[2] = supplier.get()[2];

        T start = supplies[0];
        T middle = supplies[1];
        T end = supplies[2];

        if (start instanceof Location) {
            startLocationInterpreter = new LocationInterpreter().start((Location) start).end((Location) middle);
            middleEndLocationInterpreter = new LocationInterpreter().start((Location) middle).end((Location) end);
            return this;
        } else if (start instanceof EulerAngle) {
            startEulerInterpreter = new EulerAngleInterpreter().start((EulerAngle) start).end((EulerAngle) middle);
            middleEndEulerInterpreter = new EulerAngleInterpreter().start((EulerAngle) middle).end((EulerAngle) end);
            return this;
        } else if (start instanceof Vector) {
            startVectorInterpreter = new VectorInterpreter().start((Vector) start).end((Vector) middle);
            middleEndVectorInterpreter = new VectorInterpreter().start((Vector) middle).end((Vector) end);
            return this;
        }

        startFloatInterpreter = new FloatInterpreter().start((Float) start).end((Float) middle);
        middleEndFloatInterpreter = new FloatInterpreter().start((Float) middle).end((Float) end);

        return this;
    }

    public T curve(double elapsedTime,boolean rounded) {

        T start = supplies[0];

        if (start instanceof Location) {
            LocationInterpreter quadInterpreter = new LocationInterpreter().start(startLocationInterpreter.interpolate(elapsedTime)).end(middleEndLocationInterpreter.interpolate(elapsedTime));
            Location quadOutput = quadInterpreter.interpolate(elapsedTime,rounded);
            return (T) quadOutput;
        } else if (start instanceof EulerAngle) {
            EulerAngleInterpreter quadInterpreter = new EulerAngleInterpreter().start(startEulerInterpreter.interpolate(elapsedTime)).end(middleEndEulerInterpreter.interpolate(elapsedTime));
            EulerAngle quadOutput = quadInterpreter.interpolate(elapsedTime);
            return (T) quadOutput;
        } else if (start instanceof Vector) {
            VectorInterpreter quadInterpreter = new VectorInterpreter().start(startVectorInterpreter.interpolate(elapsedTime)).end(middleEndVectorInterpreter.interpolate(elapsedTime));
            Vector quadOutput = quadInterpreter.interpolate(elapsedTime);
            return (T) quadOutput;
        }

        FloatInterpreter quad = new FloatInterpreter().start(startFloatInterpreter.interpolate(elapsedTime)).end(middleEndFloatInterpreter.interpolate(elapsedTime));

        return (T) quad.interpolate(elapsedTime);
    }
    @Override
    public T curve(double elapsedTime) {
        return curve(elapsedTime,false);
    }

}


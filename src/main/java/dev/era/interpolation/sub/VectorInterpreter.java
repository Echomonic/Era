package dev.era.interpolation.sub;

import dev.era.interpolation.util.Interpreter;
import org.bukkit.util.Vector;

public class VectorInterpreter extends Interpreter<Vector> {


    @Override
    public VectorInterpreter start(Vector value) {
        return (VectorInterpreter) super.start(value);
    }

    @Override
    public VectorInterpreter end(Vector value) {
        return (VectorInterpreter) super.end(value);
    }

    @Override
    public Vector interpolate(double elapsedTime) {
        DoubleInterpreter x = new DoubleInterpreter().start(start.getX()).end(end.getX());
        DoubleInterpreter y = new DoubleInterpreter().start(start.getY()).end(end.getY());
        DoubleInterpreter z = new DoubleInterpreter().start(start.getZ()).end(end.getZ());

        return new Vector(x.interpolate(elapsedTime,false),y.interpolate(elapsedTime,false),z.interpolate(elapsedTime,false));
    }
}

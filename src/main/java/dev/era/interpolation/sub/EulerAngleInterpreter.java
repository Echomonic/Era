package dev.era.interpolation.sub;

import dev.era.interpolation.util.Interpreter;
import org.bukkit.util.EulerAngle;

public class EulerAngleInterpreter extends Interpreter<EulerAngle> {


    @Override
    public EulerAngleInterpreter start(EulerAngle value) {
        return (EulerAngleInterpreter) super.start(value);
    }

    @Override
    public EulerAngleInterpreter end(EulerAngle value) {
        return (EulerAngleInterpreter) super.end(value);
    }

    @Override
    public EulerAngle interpolate(double elapsedTime) {

        DoubleInterpreter x = new DoubleInterpreter().start(start.getX()).end(end.getX());
        DoubleInterpreter y = new DoubleInterpreter().start(start.getY()).end(end.getY());
        DoubleInterpreter z = new DoubleInterpreter().start(start.getZ()).end(end.getZ());
        boolean round = false;
        return new EulerAngle(x.interpolate(elapsedTime,round),y.interpolate(elapsedTime,round),z.interpolate(elapsedTime,round));
    }
}

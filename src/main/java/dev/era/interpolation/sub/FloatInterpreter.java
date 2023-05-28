package dev.era.interpolation.sub;


import dev.era.interpolation.util.Interpreter;
import dev.era.utils.MathUtil;

public class FloatInterpreter extends Interpreter<Float> {

    @Override
    public FloatInterpreter start(Float value) {
        return (FloatInterpreter) super.start(value);
    }

    @Override
    public FloatInterpreter end(Float value) {
        return (FloatInterpreter) super.end(value);
    }

    @Override
    public Float interpolate(double elapsedTime) {
        if (Float.compare(start, end) == 0) return start;
        return (float) MathUtil.lerp(start, end, elapsedTime);
    }
}

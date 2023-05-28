package dev.era.interpolation.sub;

import dev.era.interpolation.util.Interpreter;
import dev.era.utils.MathUtil;
import org.decimal4j.util.DoubleRounder;


public class DoubleInterpreter extends Interpreter<Double> {


    @Override
    public DoubleInterpreter start(Double value) {
        return (DoubleInterpreter) super.start(value);
    }

    @Override
    public DoubleInterpreter end(Double value) {
        return (DoubleInterpreter) super.end(value);
    }

    @Override
    public Double interpolate(double elapsedTime) {
        return interpolate(elapsedTime,true);
    }
    //

    public double calculateDuration(){
        return this.end + 0.1;
    }
    public Double interpolate(double elapsedTime, boolean round){
        if (start.equals(end)) return start;

        double interpolated = MathUtil.lerp(start, end, elapsedTime);

        if(!round) return interpolated;

        return DoubleRounder.round(interpolated,MathUtil.getPrecision(end));
    }
}

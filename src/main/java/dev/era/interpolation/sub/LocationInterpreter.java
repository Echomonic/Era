package dev.era.interpolation.sub;

import dev.era.interpolation.util.Interpreter;
import org.bukkit.Location;

public class LocationInterpreter extends Interpreter<Location> {

    private final DoubleInterpreter x = new DoubleInterpreter();
    private final DoubleInterpreter y = new DoubleInterpreter();
    private final DoubleInterpreter z = new DoubleInterpreter();
    private final FloatInterpreter yaw = new FloatInterpreter();
    private final FloatInterpreter pitch = new FloatInterpreter();

    @Override
    public LocationInterpreter end(Location value) {
        this.x.end(value.getX());
        this.y.end(value.getY());
        this.z.end(value.getZ());
        this.yaw.end(value.getYaw());
        this.pitch.end(value.getPitch());

        return (LocationInterpreter) super.end(value);
    }

    @Override
    public LocationInterpreter start(Location value) {
        this.x.start(value.getX());
        this.y.start(value.getY());
        this.z.start(value.getZ());
        this.yaw.start(value.getYaw());
        this.pitch.start(value.getPitch());
        return (LocationInterpreter) super.start(value);
    }

    @Override
    public Location interpolate(double elapsed) {
        return interpolate(elapsed, false);
    }

    public Location interpolate(double elapsed, boolean round) {

        if (start.getWorld() != end.getWorld()) return null;

        Location clonedLocation = start.clone();
        clonedLocation.setX(x.interpolate(elapsed, round));
        clonedLocation.setY(y.interpolate(elapsed, round));
        clonedLocation.setZ(z.interpolate(elapsed, round));
        clonedLocation.setYaw(yaw.interpolate(elapsed));
        clonedLocation.setPitch(pitch.interpolate(elapsed));

        return clonedLocation;
    }


}

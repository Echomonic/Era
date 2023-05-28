package dev.era.utils;


import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.concurrent.CompletableFuture;

public class MathUtil {


    public static double lerp(double start, double end, double elapsedTime) {
        return (start + (end - start) * elapsedTime);
    }

    public static double flip(double elapsedTime) {
        return 1 - elapsedTime;
    }

    public static double easeIn(double elapsedTime) {
        return elapsedTime * elapsedTime;
    }

    public static double easeInOut(double elapsedTime) {
        return lerp(easeIn(elapsedTime), easeOut(elapsedTime), elapsedTime);
    }

    public static double easeOut(double elapsedTime) {
        return elapsedTime * elapsedTime;
    }

    public static Vector rotateAroundY(Vector base, double angle) {
        angle = -angle;
        angle = Math.toRadians(angle);
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = (base.getX() * cos) - (base.getZ() * sin);
        z = (base.getX() * -sin) + (base.getZ() * cos);

        return base.setX(x).setZ(z);
    }

    public static Location getMiddleLocation(Location start, Location end) {
        double x, y, z;

        x = (start.getX() + end.getX()) / 2;
        y = (start.getY() + end.getY()) / 2;
        z = (start.getZ() + end.getZ()) / 2;

        return new Location(start.getWorld(), x, y, z);
    }

    public static CompletableFuture<Location> getMiddleLocationAsync(Location start, Location end) {
        return CompletableFuture.supplyAsync(() -> getMiddleLocation(start, end));
    }

    public static int getPrecision(double decimal) {
        String doubleString = Double.toString(decimal);

        int index = doubleString.indexOf('.');

        return doubleString.length() - index - 1;
    }

}

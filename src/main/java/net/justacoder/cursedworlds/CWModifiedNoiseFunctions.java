package net.justacoder.cursedworlds;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

public abstract class CWModifiedNoiseFunctions { // mess with anything in this class.

    public static double lerp(double delta, double start, double end) {
        int type = CWMain.getCursedWorldType();

        return switch(type) {
            case 1: // Tall Cliffs
                yield start / 2.0 + delta * 2.0 * (end + 0.2 - start) - 0.1;
            case 2: // High Mountains
                yield 2.0 + (start - 1.0 + delta * (end - start)) * 2.0;
            case 3: // Blox/Islands
                yield start * (end + start) - delta;
            case 4: // Broken Amplified
                // clamping is really just here because... no idea, but it crashes sometimes without it
                yield Math.min(Math.max(end * delta - (start - end) + (start * end) - delta, 0), 192);
            case 5: // Bumpy/Spiky
                double pulseWidth = 0.2; // my friend asked chatGPT to generate some of these. this is, apparently, a pulse wave.
                if (delta >= 0.5 - pulseWidth / 2 && delta <= 0.5 + pulseWidth / 2) {
                    yield end;
                } else {
                    yield start;
                }
            case 6: // Spiky/Cliffs
                yield end + delta * (start - end);
            case 7: // Holes/Spiky
                yield start;
            case 8: // Mangrove Stripes
                yield delta * delta + delta;
            case 9: // Mountains/Walls
                yield end - start * 2.0 - delta / 2.0 * (end + 0.1 * start - start) / 2.0;
            case 10: // Brutalist Stone Architecture
                yield MathHelper.clamp(MathHelper.nextBetween(Random.create(), (int) Math.min(start * 2, end * 2),
                        (int) Math.max(start * 2, end * 2)) * (delta + 1.5), 0, 191);
            case 11: // Islands/Shrooms
                yield start + (MathHelper.sin((float) delta + 0.3f) / MathHelper.cos((float) delta + 0.3f)) * (end - start);
            default: // None
                yield start + delta * (end - start);
        }
    }


    public static double lerp2(double deltaX, double deltaY, double x0y0, double x1y0, double x0y1, double x1y1) {

        return lerp(deltaY, lerp(deltaX, x0y0, x1y0), lerp(deltaX, x0y1, x1y1));

    }


    public static double lerp3(double deltaX, double deltaY, double deltaZ, double x0y0z0, double x1y0z0, double x0y1z0, double x1y1z0, double x0y0z1, double x1y0z1, double x0y1z1, double x1y1z1) {

        int type = CWMain.getCursedWorldType();

        if (type == 3) {
            return lerp(deltaZ, lerp2(deltaY, deltaX, x0y0z0, x1y0z0, x0y1z0, x1y1z0), lerp2(deltaY, deltaX, x0y0z1, x1y0z1, x0y1z1, x1y1z1));
        }

        return lerp(deltaZ, lerp2(deltaX, deltaY, x0y0z0, x1y0z0, x0y1z0, x1y1z0), lerp2(deltaX, deltaY, x0y0z1, x1y0z1, x0y1z1, x1y1z1));
    }


    public static double dot(int[] gradient, double x, double y, double z) {
        return (double) gradient[0] * x + (double) gradient[1] * y + (double) gradient[2] * z;
    }
}
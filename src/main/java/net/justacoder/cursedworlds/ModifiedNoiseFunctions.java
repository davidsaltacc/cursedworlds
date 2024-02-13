package net.justacoder.cursedworlds;

public abstract class ModifiedNoiseFunctions { // mess with anything in this class.


    public static double lerp(double delta, double start, double end) {

        int type = CWMain.getCursedWorldType();

        if (type == 1) {
            return start / 2.0 + delta * 2.0 * (end + 0.2 - start) - 0.1;
        } else if (type == 2) {
            return 2.0 + (start - 1.0 + delta * (end - start)) * 2.0;
        } else if (type == 3) {
            return start * (end + start) - delta;
        } else if (type == 4) {
            return Math.min(Math.max(end * delta - (start - end) + (start * end) - delta, 0), 192); // clamping is really just here because... no idea, but it crashes sometimes without it
        } else if (type == 5) {
            double pulseWidth = 0.2; // my friend asked chatGPT to generate some of these. this is, apparently, a pulse wave.
            if (delta >= 0.5 - pulseWidth / 2 && delta <= 0.5 + pulseWidth / 2) {
                return end;
            } else {
                return start;
            }
        } else if (type == 6) {
            return end + delta * (start - end);
        }
        return start + delta * (end - start);
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


    public static double grad(int hash, double x, double y, double z, double distance, int[][] GRADIENTS) {
        double e;
        double d = distance - x * x - y * y - z * z;
        if (d < 0.0) {
            e = 0.0;
        } else {
            d *= d;
            e = d * d * dot(GRADIENTS[hash], x, y, z);
        }
        return e;
    }
}

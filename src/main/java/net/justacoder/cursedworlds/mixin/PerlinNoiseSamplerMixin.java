package net.justacoder.cursedworlds.mixin;

import net.justacoder.cursedworlds.ModifiedNoiseFunctions;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PerlinNoiseSampler.class)
public abstract class PerlinNoiseSamplerMixin {
    @Redirect(method = "sample(IIIDDDD)D", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;lerp3(DDDDDDDDDDD)D"))
    private double redirectLerp3(double deltaX, double deltaY, double deltaZ, double x0y0z0, double x1y0z0, double x0y1z0, double x1y1z0, double x0y0z1, double x1y0z1, double x0y1z1, double x1y1z1) {
        return ModifiedNoiseFunctions.lerp3(deltaX, deltaY, deltaZ, x0y0z0, x1y0z0, x0y1z0, x1y1z0, x0y0z1, x1y0z1, x0y1z1, x1y1z1);
    }
}

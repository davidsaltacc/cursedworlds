package net.justacoder.cursedworlds.mixin;

import net.justacoder.cursedworlds.CWModifiedNoiseFunctions;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimplexNoiseSampler.class)
public abstract class SimplexNoiseSamplerMixin {

    @Inject(method = "dot", at = @At("HEAD"), cancellable = true)
    private static void modifyDot(int[] gradient, double x, double y, double z, CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(CWModifiedNoiseFunctions.dot(gradient, x, y, z));
    }

}

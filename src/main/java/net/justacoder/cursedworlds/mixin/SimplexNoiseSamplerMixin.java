package net.justacoder.cursedworlds.mixin;

import net.justacoder.cursedworlds.ModifiedNoiseFunctions;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimplexNoiseSampler.class)
public abstract class SimplexNoiseSamplerMixin {

    @Shadow @Final protected static int[][] GRADIENTS;

    @Inject(method = "dot", at = @At("HEAD"), cancellable = true)
    private static void modifyDot(int[] gradient, double x, double y, double z, CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(ModifiedNoiseFunctions.dot(gradient, x, y, z));
    }
    @Inject(method = "grad", at = @At("HEAD"), cancellable = true)
    private static void modifyGrad(int hash, double x, double y, double z, double distance, CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(ModifiedNoiseFunctions.grad(hash, x, y, z, distance, GRADIENTS));
    }

}

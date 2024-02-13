package net.justacoder.cursedworlds.mixin;

import net.justacoder.cursedworlds.CWMain;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.world.SaveProperties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {

    @Shadow @Final protected SaveProperties saveProperties;

    @Inject(method = "createWorlds", at = @At("HEAD"))
    private void setCWId(WorldGenerationProgressListener worldGenerationProgressListener, CallbackInfo ci) {
        CWMain.worldType = saveProperties.getMainWorldProperties().getGameRules().getInt(CWMain.CURSEDWORLD_ID_GAMERULE);
    }

}

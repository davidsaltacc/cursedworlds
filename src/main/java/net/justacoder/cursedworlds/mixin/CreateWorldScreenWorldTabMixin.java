package net.justacoder.cursedworlds.mixin;

import net.justacoder.cursedworlds.CWMain;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.tab.GridScreenTab;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Objects;

@Mixin(targets = "net.minecraft.client.gui.screen.world.CreateWorldScreen$WorldTab")
public abstract class CreateWorldScreenWorldTabMixin extends GridScreenTab {

    public CreateWorldScreenWorldTabMixin(Text title) {
        super(title);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void addCWButtons(CreateWorldScreen createWorldScreen, CallbackInfo ci) {
        CyclingButtonWidget<Integer> cyclingButtonWidget = CyclingButtonWidget.<Integer>builder(v -> {

            String n = CWMain.WORLDS.get(v);
            return Text.of(Objects.requireNonNullElse(n, "None"));

        }).values(CyclingButtonWidget.Values.of(CWMain.WORLDS.keySet())).build(0, 0, 310, 20, Text.of("Cursed World Type"), (b, t) -> createWorldScreen.getWorldCreator().getGameRules().get(CWMain.CURSEDWORLD_ID_GAMERULE).set(t, null));
        grid.add(cyclingButtonWidget, 6, 0);
    }

}

package net.justacoder.cursedworlds;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import java.util.HashMap;
import java.util.Map;

public class CWMain implements ModInitializer {

    public static final GameRules.Key<GameRules.IntRule> CURSEDWORLD_ID_GAMERULE = GameRuleRegistry.register("cursedWorldTypeId", GameRules.Category.MOBS, GameRuleFactory.createIntRule(0));

	public static final Map<Integer, String> WORLDS = new HashMap<>();
	public static void addWorld(Integer id, String name) {
		WORLDS.put(id, name);
	}

	@Override
	public void onInitialize() {
		addWorld(0, "None");
		addWorld(1, "Tall Cliffs");
		addWorld(2, "High Mountains");
		addWorld(3, "Blox/Islands");
		addWorld(4, "Broken Amplified");
		addWorld(5, "Bumpy/Spiky");
		addWorld(6, "Spiky/Cliffs");
		addWorld(7, "Holes/Spiky");
		addWorld(8, "Mangrove Stripes");
		addWorld(9, "Mountains/Walls");
	}

	public static int worldType = 0;
	public static int getCursedWorldType() {
		return worldType;
	}
}
package jeckelsignmod;

import jeckelsignmod.common.InfoModCommand;
import jeckelsignmod.core.Refs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod
(
modid = Refs.ModId,
useMetadata = true,
canBeDeactivated = false,
guiFactory = Refs.ConfigFactoryTypeName
)
public class JeckelSignMod
{
	@Mod.Instance (value = Refs.ModId)
	public static JeckelSignMod INSTANCE;

	public JeckelSignMod() { }

	@Mod.EventHandler
	public void preInitialize(FMLPreInitializationEvent event) { Refs.pre(INSTANCE, event); }

	@Mod.EventHandler
	public void initialize(FMLInitializationEvent event) { Refs.initialize(event); }

	@Mod.EventHandler
	public void postInitialization(FMLPostInitializationEvent event) { Refs.post(event); }

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new InfoModCommand(Refs.getMetadata(), Refs.getUpdateChecker(), "Display info about the mod."));
	}
}

package jeckelsignmod.core;

import jeckelsignmod.api.ISignTool;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerEventHandler
{
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if (e.isCanceled()) { return; }
		if (e.action != Action.RIGHT_CLICK_BLOCK) { return; }
		if (e.world.isRemote) { return; }

		if (e.entityPlayer.isSneaking()) { return; }

		ItemStack stack = e.entityPlayer.inventory.getCurrentItem();
		if (stack == null || !(stack.getItem() instanceof ISignTool)) { return; }

		TileEntity tile = e.world.getTileEntity(e.x, e.y, e.z);
		if (tile == null || !(tile instanceof TileEntitySign)) { return; }

		TileEntitySign sign = (TileEntitySign) tile;
		ISignTool tool = (ISignTool) stack.getItem();

		boolean result = tool.onSignActivated(sign, stack, e.entityPlayer);
		if (result) { e.setCanceled(true); }
	}
}

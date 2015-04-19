package jeckelsignmod.content.items;

import java.util.List;

import jeckelsignmod.api.SignToolType;
import jeckelsignmod.utils.SignUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

public class ItemSignToolErase extends AItemSignTool
{
	public ItemSignToolErase(String modId)
	{
		super(modId, "tool.sign.erase");
	}

    // ##################################################
    //
    // ISignTool - 
    //
    // ##################################################

	@Override public SignToolType getSignToolType(ItemStack stack) { return SignToolType.Erase; }

	@Override public boolean onSignActivated(TileEntitySign sign, ItemStack stack, EntityPlayer player)
	{
		if (!player.worldObj.isRemote)
		{
			boolean result = SignUtil.hasSignText(sign);
			if (result)
			{
				//ISignTool tool = (ISignTool) stack.getItem();
				//tool.dealDurability(player, stack, 1);
				SignUtil.delSignText(sign);
				player.worldObj.markBlockForUpdate(sign.xCoord, sign.yCoord, sign.zCoord);
			}
			addChatMessage(player, "item.tool.sign.erase.result." + result + ".text");
		}
		return true;
	}
	
	@Override public void getTooltipInfo(ItemStack stack, EntityPlayer player, List<String> infoList, boolean verbose)
	{
	}
	
	private static void addChatMessage(EntityPlayer player, String message)
	{
		player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal(message)));
	}
}

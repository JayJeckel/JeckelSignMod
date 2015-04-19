package jeckelsignmod.content.items;

import java.util.List;

import jeckelsignmod.api.SignToolType;
import jeckelsignmod.utils.SignUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;

public class ItemSignToolEdit extends AItemSignTool
{
	public ItemSignToolEdit(String modId)
	{
		super(modId, "tool.sign.edit");
	}

    // ##################################################
    //
    // ISignTool - 
    //
    // ##################################################

	@Override public SignToolType getSignToolType(ItemStack stack) { return SignToolType.Edit; }

	@Override public boolean onSignActivated(TileEntitySign sign, ItemStack stack, EntityPlayer player)
	{
		if (!player.worldObj.isRemote)
		{
			SignUtil.editSign(sign, player);
			//ISignTool tool = (ISignTool) stack.getItem();
			//tool.dealDurability(player, stack, 1);
		}
		return true;
	}
	
	@Override public void getTooltipInfo(ItemStack stack, EntityPlayer player, List<String> infoList, boolean verbose)
	{
	}
}

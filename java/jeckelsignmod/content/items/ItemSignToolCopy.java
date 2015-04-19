package jeckelsignmod.content.items;

import java.util.List;

import jeckelsignmod.api.ISignTool;
import jeckelsignmod.api.SignToolType;
import jeckelsignmod.utils.SignToolUtil;
import jeckelsignmod.utils.SignUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

public class ItemSignToolCopy extends AItemSignTool
{
	public ItemSignToolCopy(String modId)
	{
		super(modId, "tool.sign.copy");
	}

    // ##################################################
    //
    // ISignTool - 
    //
    // ##################################################

	@Override public SignToolType getSignToolType(ItemStack stack) { return SignToolType.Copy; }

	@Override public boolean onSignActivated(TileEntitySign sign, ItemStack stack, EntityPlayer player)
	{
		if (!player.worldObj.isRemote)
		{
			ISignTool tool = (ISignTool) stack.getItem();
			boolean state = tool.hasText(stack);
			boolean result;
			if (state)
			{
				result = !SignUtil.hasEqualText(tool.getText(stack), sign.signText);
				if (result)
				{
					SignToolUtil.pasteSignText(sign, stack);
					player.worldObj.markBlockForUpdate(sign.xCoord, sign.yCoord, sign.zCoord);
					//tool.dealDurability(player, stack, 1);
				}
			}
			else
			{
				result = SignUtil.hasSignText(sign);
				if (result)
				{
					SignToolUtil.copySignText(sign, stack);
					//tool.dealDurability(player, stack, 1);
				}
			}
			addChatMessage(player, "item.tool.sign.copy.state." + state + ".result." + result + ".text");
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

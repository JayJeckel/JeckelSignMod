package jeckelsignmod.utils;

import jeckelsignmod.api.ISignTool;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;

public class SignToolUtil
{
	
	public static void pasteSignText(TileEntitySign sign, ItemStack stack)
	{
		ISignTool tool = (ISignTool) stack.getItem();
		String[] text = tool.getText(stack);
		sign.signText[0] = text[0];
		sign.signText[1] = text[1];
		sign.signText[2] = text[2];
		sign.signText[3] = text[3];
		sign.markDirty();
	}
	
	public static void copySignText(TileEntitySign sign, ItemStack stack)
	{
		ISignTool tool = (ISignTool) stack.getItem();
		tool.setText(stack, sign.signText);
		//sign.markDirty();
	}
	
	/*public static void editSign(TileEntitySign sign, EntityPlayer player, ItemStack stack)
	{
		if (!player.worldObj.isRemote)
		{
			SignUtil.editSign(sign, player);
			//ISignTool tool = (ISignTool) stack.getItem();
			//tool.dealDurability(player, stack, 1);
		}
	}*/
	
	/*public static void eraseSign(TileEntitySign sign, EntityPlayer player, ItemStack stack)
	{
		if (SignUtil.hasSignText(sign))
		{
			//ISignTool tool = (ISignTool) stack.getItem();
			SignUtil.delSignText(sign);
			//tool.dealDurability(player, stack, 1);
			if (!player.worldObj.isRemote) { addChatMessage(player, "Text erased."); }

		}
		else
		{
			if (!player.worldObj.isRemote) { addChatMessage(player, "No text to erase."); }
		}
	}*/
	
	/*public static void copySign(TileEntitySign sign, EntityPlayer player, ItemStack stack)
	{
		ISignTool tool = (ISignTool) stack.getItem();
		if (tool.hasText(stack))
		{
			if (SignUtil.hasEqualText(tool.getText(stack), sign.signText))
			{
				if (!player.worldObj.isRemote) { addChatMessage(player, "Can't paste the same text."); }
			}
			else
			{
				pasteSignText(sign, stack);
				//tool.dealDurability(player, stack, 1);
				if (!player.worldObj.isRemote) { addChatMessage(player, "Text pasted to sign."); }
			}
		}
		else
		{
			if (SignUtil.hasSignText(sign))
			{
				copySignText(sign, stack);
				//tool.dealDurability(player, stack, 1);
				if (!player.worldObj.isRemote) { addChatMessage(player, "Text copied from sign."); }
			}
			else
			{
				if (!player.worldObj.isRemote) { addChatMessage(player, "No text to copy."); }
			}
		}
	}*/
	
	/*public static void cutSign(TileEntitySign sign, EntityPlayer player, ItemStack stack)
	{
		ISignTool tool = (ISignTool) stack.getItem();
		if (tool.hasText(stack))
		{
			if (SignUtil.hasEqualText(tool.getText(stack), sign.signText))
			{
				if (!player.worldObj.isRemote) { addChatMessage(player, "Can't paste the same text."); }
			}
			else
			{
				pasteSignText(sign, stack);
				//tool.dealDurability(player, stack, 1);
				if (!player.worldObj.isRemote) { addChatMessage(player, "Text pasted to sign."); }
			}
		}
		else
		{
			if (SignUtil.hasSignText(sign))
			{
				copySignText(sign, stack);
				SignUtil.delSignText(sign);
				//tool.dealDurability(player, stack, 1);
				if (!player.worldObj.isRemote) { addChatMessage(player, "Text cut from sign."); }
			}
			else
			{
				if (!player.worldObj.isRemote) { addChatMessage(player, "No text to cut."); }
			}
		}
	}*/
	
	/*private static void addChatMessage(EntityPlayer player, String message)
	{
		player.addChatMessage(new ChatComponentText(message));
	}*/
}

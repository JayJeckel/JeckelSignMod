package jeckelsignmod.api;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;

public interface ISignTool
{
	public SignToolType getSignToolType(ItemStack stack);
	
	public void getTooltipInfo(ItemStack stack, EntityPlayer player, List<String> infoList, boolean verbose);
	
	public boolean hasText(ItemStack stack);
	
	public String[] getText(ItemStack stack);
	
	public void delText(ItemStack stack);
	
	public void setText(ItemStack stack, String[] text);
	
	public boolean hasLine(int index, ItemStack stack);
	
	public String getLine(int index, ItemStack stack);
	
	public void delLine(int index, ItemStack stack);
	
	public void setLine(int index, ItemStack stack, String line);
	
	/*public int getMaxDurability(ItemStack stack);
	
	public int getDurability(ItemStack stack);
	
	public int setDurability(ItemStack stack, int amount);
	
	public int changeDurability(ItemStack stack, int amount);
	
	public void dealDurability(EntityPlayer player, ItemStack stack, int amount);*/
	
	public boolean onSignActivated(TileEntitySign sign, ItemStack stack, EntityPlayer player);
}

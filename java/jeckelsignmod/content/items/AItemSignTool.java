package jeckelsignmod.content.items;

import java.util.List;
import java.util.regex.Pattern;

import jeckelsignmod.api.ISignTool;
import jeckelsignmod.api.SignToolType;
import jeckelsignmod.utils.NBTUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class AItemSignTool extends Item implements ISignTool
{
	public AItemSignTool(String modId, String rawName)
	{
		super();
		this._rawName = rawName;
		this.setUnlocalizedName(this.getRawName());
		this.setTextureName(modId + ":" + this.getRawName());
		this.setMaxStackSize(1);
	}
	
	public String getRawName() { return this._rawName; }
	private String  _rawName;
	
	@Override public boolean isItemTool(ItemStack stack) { return true; }

    // ##################################################
    //
    // ISignTool - Text Methods
    //
    // ##################################################
	
	public static final String[] tagPathText = { "signtool", "text" };
	
	@Override public boolean hasText(ItemStack stack)
	{
		NBTTagCompound tagText = NBTUtil.getTag(stack, tagPathText);
		if (tagText == null) { return false; }
        for (int index = 0; index < 4; index++)
        {
        	String line = tagText.getString("" + index);
        	if (!line.isEmpty()) { return true; }
        }
        return false;
	}
    
	@Override public String[] getText(ItemStack stack) { return NBTUtil.getTagStringArray(stack, 4, tagPathText); }
    
	@Override public void delText(ItemStack stack) { this.setText(stack, new String[] { "", "", "", "" }); }

	@Override public void setText(ItemStack stack, String[] text) { NBTUtil.setTagStringArray(stack, text, tagPathText); }
	
	@Override public boolean hasLine(int index, ItemStack stack) { return !this.getText(stack)[index].isEmpty(); }
	
	@Override public String getLine(int index, ItemStack stack) { return this.getText(stack)[index]; }
	
	@Override public void delLine(int index, ItemStack stack)
	{
		this.setLine(index, stack, "");
	}
	
	@Override public void setLine(int index, ItemStack stack, String line)
	{
		String[] text = this.getText(stack);
		text[index] = line;
		this.setText(stack, text);
	}


    // ##################################################
    //
    // Item - Misc Methods
    //
    // ##################################################

    @SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
    @Override public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List infoList, boolean verbose)
    {
		String text = StatCollector.translateToLocal(this.getUnlocalizedName() + ".tooltip");
		for (String tip : text.split(Pattern.quote("\\n"))) { infoList.add(tip); }
    	this.getTooltipInfo(stack, player, infoList, verbose);
    }
	
	@Override public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		SignToolType toolType = this.getSignToolType(stack);
		if (toolType != SignToolType.Copy && toolType != SignToolType.Cut) { return stack; }
		
		if (!world.isRemote)
		{
			if (player.isSneaking())
			{
				if (this.hasText(stack))
				{
					this.delText(stack);
					//ISignTool tool = (ISignTool) stack.getItem();
					//tool.dealDurability(player, stack, (toolType == SignToolType.Copy ? LocalConfig.costDurabilitySignToolCopyClear : LocalConfig.costDurabilitySignToolCutClear));
					if (!world.isRemote) { addChatMessage(player, "Copied text cleared."); }
				}
			}
			else
			{
				if (!world.isRemote)
				{
					if (this.hasText(stack))
					{
						String[] text = this.getText(stack);
						for (int index = 0; index < text.length; index++)
						{
							addChatMessage(player, "" + (index + 1) + ": " + text[index]);
						}
					}
					else
					{
						addChatMessage(player, "No copied text found.");
					}
				}
			}
		}
		return stack;
    }
	
	
    // ##################################################
    //
    // ISignTool - Durability Methods
    //
    // ##################################################
	
	/*public static final String[] tagPathDurability = { "signtool", "durability" };
	
	public boolean hasDurabilityTag(ItemStack stack)
	{
		return NBTUtil.hasTag(stack, tagPathDurability);
	}
	
	@Override public int getMaxDurability(ItemStack stack) { return 50; }
	
	@Override public int getDurability(ItemStack stack)
	{
		if (this.getMaxDamage(stack) <= 0) { return 0; }
		return NBTUtil.getTagInteger(stack, tagPathDurability);
	}
	
	@Override public int setDurability(ItemStack stack, int amount)
	{
		NBTUtil.setTagInteger(stack, amount, tagPathDurability);
		return amount;
	}
	
	@Override public int changeDurability(ItemStack stack, int amount)
	{
		return this.setDurability(stack, this.getDurability(stack) + amount);
	}
	
	@Override public void dealDurability(EntityPlayer player, ItemStack stack, int amount)
	{
		if (player.capabilities.isCreativeMode) { return; }
		if (this.getMaxDamage(stack) <= 0) { return; }

		this.changeDurability(stack, amount);
		int durability = this.getDurability(stack);
		if (durability >= this.getMaxDamage(stack))
		{
			player.setCurrentItemOrArmor(0, null);
		}
	}*/

    // ##################################################
    //
    // Damage Methods
    //
    // ##################################################

    //@Override public float getDamageVsEntity(Entity entity, ItemStack stack) { return damageArray[stack.getItemDamage()]; }
    
    /* Note: Due to a bug in forge, must return a number greater than 0 to make this work. */
    /*@Override public int getMaxDamage() { return (!LocalConfig.enableSignToolDurability ? 0 : 1); }

    @Override public boolean isDamageable() { return LocalConfig.enableSignToolDurability; }
    
    @Override public int getMaxDamage(ItemStack stack) { return (!this.isDamageable() ? 0 : this.getMaxDurability(stack)); }

    @Override public int getDisplayDamage(ItemStack stack) { return (!this.isDamageable() ? 0 : this.getDurability(stack)); }

    @Override public boolean isDamaged(ItemStack stack) { return this.getDisplayDamage(stack) > 0; }

    @Override public void setDamage(ItemStack stack, int damage) { this.setDurability(stack, Math.max(0, damage)); }*/

	
	private static void addChatMessage(EntityPlayer player, String message)
	{
		player.addChatMessage(new ChatComponentText(message));
	}
}

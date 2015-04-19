package jeckelsignmod.utils;

import java.lang.reflect.Field;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntitySign;

public class SignUtil
{
	public static boolean hasText(String[] textArray)
	{
		if (textArray == null || textArray.length == 0) { return false; }
        for (int index = 0; index < textArray.length; index++)
        {
        	if (textArray[index] != null && !textArray[index].isEmpty()) { return true; }
        }
        return false;
	}
	
	public static boolean hasEqualText(String[] a, String[] b)
	{
		if (a == null || b == null) { return false; }
		if (!hasText(a) || !hasText(b)) { return false; }
		if (a.length != 4 || b.length != 4) { return false; }
		for (int index = 0; index < 4; index++)
		{
			if (!a[index].equals(b[index])) { return false; }
		}
		return true;
	}
	
	public static boolean hasSignText(TileEntitySign sign) { return hasText(sign.signText); }
	
	public static boolean hasSignEqualText(TileEntitySign sign, String[] b) { return hasEqualText(sign.signText, b); }
	
	public static String[] getSignText(TileEntitySign sign) { return sign.signText; }
	
	public static void setSignText(TileEntitySign sign, String[] text)
	{
		sign.signText[0] = text[0];
		sign.signText[1] = text[1];
		sign.signText[2] = text[2];
		sign.signText[3] = text[3];
		sign.markDirty();
	}
	
	public static void delSignText(TileEntitySign sign)
	{
		setSignText(sign, new String[] { "", "", "", "" });
	}
	
	//
	//
	//
	
	public static void editSign(TileEntitySign sign, EntityPlayer player)
	{
		if (!player.worldObj.isRemote)
		{
	    	try
	    	{
				Field field = TileEntitySign.class.getDeclaredField("field_145916_j");
				field.setAccessible(true);
				field.setBoolean(sign, true);
			}
	    	catch (NoSuchFieldException e) { e.printStackTrace(); }
	    	catch (IllegalArgumentException e) { e.printStackTrace(); }
	    	catch (IllegalAccessException e) { e.printStackTrace(); }
	    	player.func_146100_a(sign);
		}
	}
}

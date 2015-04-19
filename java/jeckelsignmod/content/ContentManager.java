package jeckelsignmod.content;

import jeckelsignmod.content.items.ItemSignToolCopy;
import jeckelsignmod.content.items.ItemSignToolCut;
import jeckelsignmod.content.items.ItemSignToolEdit;
import jeckelsignmod.content.items.ItemSignToolErase;
import jeckelsignmod.core.Refs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ContentManager
{
	public static class ModItems
	{
		public static ItemSignToolEdit tool_sign_edit;
		public static ItemSignToolErase tool_sign_erase;
		public static ItemSignToolCopy tool_sign_copy;
		public static ItemSignToolCut tool_sign_cut;
	}

	public void pre()
	{
		ModItems.tool_sign_edit = new ItemSignToolEdit(Refs.ModId);
		ModItems.tool_sign_edit.setCreativeTab(CreativeTabs.tabTools);
		GameRegistry.registerItem(ModItems.tool_sign_edit, ModItems.tool_sign_edit.getRawName());

		ModItems.tool_sign_erase = new ItemSignToolErase(Refs.ModId);
		ModItems.tool_sign_erase.setCreativeTab(CreativeTabs.tabTools);
		GameRegistry.registerItem(ModItems.tool_sign_erase, ModItems.tool_sign_erase.getRawName());

		ModItems.tool_sign_copy = new ItemSignToolCopy(Refs.ModId);
		ModItems.tool_sign_copy.setCreativeTab(CreativeTabs.tabTools);
		GameRegistry.registerItem(ModItems.tool_sign_copy, ModItems.tool_sign_copy.getRawName());

		ModItems.tool_sign_cut = new ItemSignToolCut(Refs.ModId);
		ModItems.tool_sign_cut.setCreativeTab(CreativeTabs.tabTools);
		GameRegistry.registerItem(ModItems.tool_sign_cut, ModItems.tool_sign_cut.getRawName());
	}

	public void initialize()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tool_sign_edit, 1, 0),
				"  S",
				" S ",
				"C  ",
				'C', new ItemStack(Items.coal, 1, OreDictionary.WILDCARD_VALUE),
				'S', new ItemStack(Items.stick)));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tool_sign_erase, 1, 0),
				"CSC",
				'C', new ItemStack(Items.clay_ball),
				'S', new ItemStack(Blocks.sand)));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tool_sign_copy, 1, 0),
				"PSP",
				'P', new ItemStack(Items.paper),
				'S', new ItemStack(Items.stick)));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tool_sign_cut, 1, 0),
				"I I",
				" I ",
				"S S",
				'S', new ItemStack(Items.stick),
				'I', new ItemStack(Items.iron_ingot)));
	}

	public void post()
	{
	}
}

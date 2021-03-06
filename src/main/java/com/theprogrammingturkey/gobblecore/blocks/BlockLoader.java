package com.theprogrammingturkey.gobblecore.blocks;

import com.theprogrammingturkey.gobblecore.GobbleCore;
import com.theprogrammingturkey.gobblecore.IModCore;
import com.theprogrammingturkey.gobblecore.items.BaseItemBlock;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockLoader
{
	private IModCore subMod = GobbleCore.instance;

	private CreativeTabs tab = null;

	public BlockLoader(IModCore subMod)
	{
		this.subMod = subMod;
	}

	public void setCreativeTab(CreativeTabs tab)
	{
		this.tab = tab;
	}

	public void registerBlock(BaseBlock block)
	{
		this.registerBlock(block, block.getBlockName());
	}

	public void registerBlock(Block block, String name)
	{
		block.setRegistryName(subMod.getModID(), name);
		block.setCreativeTab(tab);
		GameRegistry.register(block);
		GameRegistry.register(new BaseItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	public void registerBlock(BaseBlock block, Class<? extends TileEntity> tileEntityClass)
	{
		this.registerBlock(block, tileEntityClass, block.getBlockName());
	}

	public void registerBlock(Block block, Class<? extends TileEntity> tileEntityClass, String name)
	{
		registerBlock(block, name);
		GameRegistry.registerTileEntity(tileEntityClass, "tile_" + name);
	}

	public void registerBlockModel(ItemModelMesher mesher, BaseBlock b, int meta)
	{
		this.registerBlockModel(mesher, b, meta, b.getBlockName());
	}

	public void registerBlockModel(ItemModelMesher mesher, Block b, int meta, String name)
	{
		mesher.register(Item.getItemFromBlock(b), meta, new ModelResourceLocation(subMod.getModID() + ":" + name, "inventory"));
	}
}
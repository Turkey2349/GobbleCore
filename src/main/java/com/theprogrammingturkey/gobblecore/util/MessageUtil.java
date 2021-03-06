package com.theprogrammingturkey.gobblecore.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class MessageUtil
{
	public static void sendMessageToPlayer(EntityPlayer player, String message)
	{
		if(player != null && GameUtil.isPlayerOnline(player))
			player.addChatMessage(new TextComponentString(message));
	}

	public static void sendMessageToPlayer(EntityPlayer player, String message, int delay)
	{
		Scheduler.scheduleTask(new Task("Delayed_Message", delay)
		{
			@Override
			public void callback()
			{
				sendMessageToPlayer(player, message);
			}
		});
	}

	public static void sendMessageToNearestPlayer(World world, BlockPos pos, String message)
	{
		EntityPlayer nearest = null;
		double nearestDist = Integer.MAX_VALUE;
		for(int i = 0; i < world.playerEntities.size(); ++i)
		{
			EntityPlayer entityplayer = (EntityPlayer) world.playerEntities.get(i);
			double dist = Math.sqrt(Math.pow(pos.getX() - entityplayer.posX, 2) + Math.pow(pos.getY() - entityplayer.posY, 2) + Math.pow(pos.getZ() - entityplayer.posZ, 2));
			if(dist < nearestDist)
			{
				nearestDist = dist;
				nearest = entityplayer;
			}
		}

		if(nearest != null)
			sendMessageToPlayer(nearest, message);
	}

	public static void sendMessageToNearestPlayer(World world, BlockPos pos, String message, int delay)
	{
		Scheduler.scheduleTask(new Task("Delayed_Message", delay)
		{
			@Override
			public void callback()
			{
				sendMessageToNearestPlayer(world, pos, message);
			}
		});
	}

	public static void sendMessageToNearestPlayerInRange(World world, BlockPos pos, int distance, String message)
	{
		EntityPlayer nearest = null;
		double nearestDist = Integer.MAX_VALUE;
		for(int i = 0; i < world.playerEntities.size(); ++i)
		{
			EntityPlayer entityplayer = (EntityPlayer) world.playerEntities.get(i);
			double dist = Math.sqrt(Math.pow(pos.getX() - entityplayer.posX, 2) + Math.pow(pos.getY() - entityplayer.posY, 2) + Math.pow(pos.getZ() - entityplayer.posZ, 2));
			if(dist < nearestDist && dist <= distance)
			{
				nearestDist = dist;
				nearest = entityplayer;
			}
		}

		if(nearest != null)
			sendMessageToPlayer(nearest, message);
	}

	public static void sendMessageToNearestPlayerInRange(World world, BlockPos pos, int distance, String message, int delay)
	{
		Scheduler.scheduleTask(new Task("Delayed_Message", delay)
		{
			@Override
			public void callback()
			{
				sendMessageToNearestPlayerInRange(world, pos, distance, message);
			}
		});
	}

	public static void sendMessageToNearPlayers(World world, BlockPos pos, int distance, String message)
	{
		for(int i = 0; i < world.playerEntities.size(); ++i)
		{
			EntityPlayer entityplayer = (EntityPlayer) world.playerEntities.get(i);
			double dist = Math.sqrt(Math.pow(pos.getX() - entityplayer.posX, 2) + Math.pow(pos.getY() - entityplayer.posY, 2) + Math.pow(pos.getZ() - entityplayer.posZ, 2));
			if(dist <= distance)
				sendMessageToPlayer(entityplayer, message);
		}
	}

	public static void sendMessageToNearPlayers(World world, BlockPos pos, int distance, String message, int delay)
	{
		Scheduler.scheduleTask(new Task("Delayed_Message", delay)
		{
			@Override
			public void callback()
			{
				sendMessageToNearPlayers(world, pos, distance, message);
			}
		});
	}

	public static void sendMessageToAllPlayers(World world, String message)
	{
		for(int i = 0; i < world.playerEntities.size(); ++i)
			sendMessageToPlayer((EntityPlayer) world.playerEntities.get(i), message);
	}

	public static void sendMessageToAllPlayers(World world, String message, int delay)
	{
		Scheduler.scheduleTask(new Task("Delayed_Message", delay)
		{
			@Override
			public void callback()
			{
				sendMessageToAllPlayers(world, message);
			}
		});
	}

	public void noPermision(EntityPlayer player, String action)
	{
		sendMessageToPlayer(player, TextFormatting.RED + "You do not have permission to " + action + "!");
	}
}

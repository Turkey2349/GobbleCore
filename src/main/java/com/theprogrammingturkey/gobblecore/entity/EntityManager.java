package com.theprogrammingturkey.gobblecore.entity;

import java.util.HashMap;
import java.util.Map;

import com.theprogrammingturkey.gobblecore.IModCore;
import com.theprogrammingturkey.gobblecore.util.CustomEntry;

public class EntityManager
{
	private static Map<IModCore, CustomEntry<IEntityHandler, EntityLoader>> entityHandlers = new HashMap<IModCore, CustomEntry<IEntityHandler, EntityLoader>>();

	public static void registerEntityHandler(IEntityHandler handler, IModCore mod)
	{
		entityHandlers.put(mod, new CustomEntry<IEntityHandler, EntityLoader>(handler, new EntityLoader(mod)));
	}

	public static void registerEntities()
	{
		for(IModCore mod : entityHandlers.keySet())
		{
			CustomEntry<IEntityHandler, EntityLoader> values = entityHandlers.get(mod);
			values.getKey().registerEntities(values.getValue());
		}
	}

	public static void registerRenderings()
	{
		for(IModCore mod : entityHandlers.keySet())
		{
			CustomEntry<IEntityHandler, EntityLoader> values = entityHandlers.get(mod);
			values.getKey().registerRenderings(values.getValue());
		}
	}
}
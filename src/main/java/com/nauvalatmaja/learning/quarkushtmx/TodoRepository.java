package com.nauvalatmaja.learning.quarkushtmx;

import java.util.List;

public interface TodoRepository {
	public List<Item> list();

	public void add(Item build);

	public Item toggleDone(int itemId);
}


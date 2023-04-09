package com.nauvalatmaja.learning.quarkushtmx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InMemoryTodoRepository implements TodoRepository {

	private Map<Integer, Item> todos;

	public InMemoryTodoRepository() {
		if (todos == null) {
			todos = new HashMap<>();
		}
	}

	@Override
	public List<Item> list() {
		return new ArrayList<>(todos.values());
	}

	@Override
	public void add(Item item) {
		todos.put(item.getId(), item);
	}

}


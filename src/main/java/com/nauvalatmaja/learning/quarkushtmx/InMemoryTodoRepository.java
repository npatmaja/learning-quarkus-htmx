package com.nauvalatmaja.learning.quarkushtmx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import lombok.SneakyThrows;

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
		int id = todos.size() + 1;
		if (item.getId() <= 0) {
			item.setId(id);
		}
		todos.put(item.getId(), item);
	}

	@Override
	@SneakyThrows
	public Item toggleDone(int itemId) {
		Item i = todos.get(itemId);
		if (i == null) {
			throw new ItemNotFoundException(itemId);
		}
		if (i.isDone()) {
			i.setDone(false);
		} else {
			i.setDone(true);
		}
		return i;
	}

}


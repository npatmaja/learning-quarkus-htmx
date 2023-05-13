package com.nauvalatmaja.learning.quarkushtmx;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class TodoService {
	private final TodoRepository todoRepository;

    public List<Item> list() {
        return todoRepository.list();
    }

	public void add(String todoText) {
		todoRepository.add(Item.builder().task(todoText).build());
	}

	public Item toggleDone(int itemId) {
		return todoRepository.toggleDone(itemId);
	}

}


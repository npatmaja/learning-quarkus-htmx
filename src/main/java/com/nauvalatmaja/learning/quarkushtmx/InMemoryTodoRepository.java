package com.nauvalatmaja.learning.quarkushtmx;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InMemoryTodoRepository implements TodoRepository {

	@Override
	public List<Item> list() {
        return Arrays.asList(
			Item.builder().id(1).task("Checklist 1").build(),
			Item.builder().id(2).task("Checklist 2").done(true).build()
		);
	}

}


package com.nauvalatmaja.learning.quarkushtmx;

import java.util.Arrays;
import java.util.List;

public class DoubleTodoRepository implements TodoRepository {

	@Override
	public List<Item> list() {
        return Arrays.asList(
			Item.builder().id(1).task("Checklist 1").build(),
			Item.builder().id(2).task("Checklist 2").done(true).build()
		);
	}

}


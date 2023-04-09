package com.nauvalatmaja.learning.quarkushtmx;

import java.util.Arrays;
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

}


package com.nauvalatmaja.learning.quarkushtmx;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class InMemoryTodoRepositoryTest {

	@Test
	public void testListInitialDataShouldReturnEmpty() {
		TodoRepository repo = new InMemoryTodoRepository();
		assertEquals(true, repo.list().isEmpty(), "Initial data should be empty");
	}	

	@Test
	public void testAddItemShouldAppearWhenListed() {
		TodoRepository repo = new InMemoryTodoRepository();
		repo.add(Item.builder().id(1).task("1").build());
		repo.add(Item.builder().id(2).task("2").done(true).build());

		List<Item> actual = repo.list();
		assertEquals(2, actual.size());
		assertEquals(1, actual.get(0).getId());
		assertEquals("1", actual.get(0).getTask());
		assertEquals(false, actual.get(0).isDone());

		assertEquals(2, actual.get(1).getId());
		assertEquals("2", actual.get(1).getTask());
		assertEquals(true, actual.get(1).isDone());
	}

	@Test
	public void testAddItemWithoutIdShouldAddCorrectId() {
		TodoRepository repo = new InMemoryTodoRepository();
		repo.add(Item.builder().task("1").build());
		repo.add(Item.builder().task("2").done(true).build());

		List<Item> actual = repo.list();
		assertEquals(2, actual.size());
		assertEquals(1, actual.get(0).getId());
		assertEquals(2, actual.get(1).getId());
	}
}

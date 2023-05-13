package com.nauvalatmaja.learning.quarkushtmx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class TodoServiceTest {

	private TodoRepository repository;
	private TodoService service;

	@BeforeEach
	public void beforeEach() {
		repository = spy(new InMemoryTodoRepository());
		service = new TodoService(repository);
	} 

	@Test
	public void testListShouldCallTodoRepositoryListMethod() {
		service.list();

		verify(repository).list();
	}

	@Test
	public void testAddShouldCallTodoRepositoryAddMethod() {
		service.add("-");

		ArgumentCaptor<Item> captor = ArgumentCaptor.forClass(Item.class);

		verify(repository).add(captor.capture());
		assertEquals("-", captor.getValue().getTask());
	}

	@Test
	public void testDoneToggleShouldCallRepositoryToggleDone() {
		repository.add(Item.builder().id(1).build());
		service.toggleDone(1);
		verify(repository).toggleDone(1);
	}

}

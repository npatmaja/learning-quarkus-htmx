package com.nauvalatmaja.learning.quarkushtmx;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class TodoServiceTest {

	@Test
	public void testListShouldCallTodoRepositoryListMethod() {
		TodoRepository repository = Mockito.spy(new InMemoryTodoRepository());		
		TodoService service = new TodoService(repository);

		service.list();

		Mockito.verify(repository).list();
	}

	@Test
	public void testAddShouldCallTodoRepositoryAddMethod() {
		TodoRepository repository = Mockito.spy(new InMemoryTodoRepository());		
		TodoService service = new TodoService(repository);
		service.add("-");

		ArgumentCaptor<Item> captor = ArgumentCaptor.forClass(Item.class);

		Mockito.verify(repository).add(captor.capture());
		assertEquals("-", captor.getValue().getTask());
	}

}

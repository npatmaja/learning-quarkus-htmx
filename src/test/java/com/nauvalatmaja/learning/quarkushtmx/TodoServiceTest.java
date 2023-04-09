package com.nauvalatmaja.learning.quarkushtmx;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TodoServiceTest {

	@Test
	public void testServiceCallTodoRepositoryListMethod() {
		TodoRepository repository = Mockito.spy(new InMemoryTodoRepository());		
		TodoService service = new TodoService(repository);

		service.list();

		Mockito.verify(repository).list();
	}
}

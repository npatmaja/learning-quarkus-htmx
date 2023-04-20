package com.nauvalatmaja.learning.quarkushtmx;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.qute.TemplateInstance;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TodoResourceNewTodoTest {
	
	@Inject
	TodoService service;

	TodoService spy;
	TodoResource todoResource;

	@BeforeEach
	public void beforeEach() {
		spy = spy(service);
		todoResource = new TodoResource();
		todoResource.todoService = spy;
	}

	@BeforeAll
	public static void setup() {
		TodoRepository repo = new InMemoryTodoRepository();
		QuarkusMock.installMockForType(repo, TodoRepository.class);
	}

	@Test
	public void testShouldCallTodoServiceNewTodo() {
		todoResource.newTodoPartial("Test");

		verify(spy).add("Test");
	}

	@Test
	public void testShouldCallTodoServiceList() {
		todoResource.newTodoPartial("Test");

		verify(spy).list();
	}

	@Test
	public void testShouldReturnTemplateInstance() {
		assertInstanceOf(TemplateInstance.class, todoResource.newTodoPartial("Test"));
	}
}

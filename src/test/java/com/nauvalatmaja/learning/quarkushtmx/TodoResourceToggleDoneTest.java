package com.nauvalatmaja.learning.quarkushtmx;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
public class TodoResourceToggleDoneTest {

	static Item item = Item.builder().id(1).build();
		
	@Inject
	TodoService service;

	TodoService spy;
	TodoResource todoResource;

	@BeforeEach
	public void beforeEach() {
		spy = spy(service);
		item.setDone(false);
		todoResource = new TodoResource();
		todoResource.todoService = spy;
	}

	@BeforeAll
	public static void setup() {
		TodoRepository repo = new InMemoryTodoRepository();
		repo.add(item);
		QuarkusMock.installMockForType(repo, TodoRepository.class);
	}

	@Test
	public void testShouldCallTodoServiceToggleDone() {
		todoResource.toggleDone(1);
		verify(spy).toggleDone(1);
	}

	@Test
	public void testShouldThrowNotFoundExceptionWhenIdNotFound() {
		assertThrows(
			ItemNotFoundException.class, () -> todoResource.toggleDone(2));
	}

	@Test
	public void testShouldReturnTemplateInstace() {
		assertInstanceOf(TemplateInstance.class, todoResource.toggleDone(1));
	}
}

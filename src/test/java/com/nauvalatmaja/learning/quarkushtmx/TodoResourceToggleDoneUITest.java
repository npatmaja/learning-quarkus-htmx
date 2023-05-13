package com.nauvalatmaja.learning.quarkushtmx;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TodoResourceToggleDoneUITest {

	@Inject
	TodoRepository repository;

	@BeforeAll
	public static void setup() {
		TodoRepository repo = new InMemoryTodoRepository();
		QuarkusMock.installMockForType(repo, TodoRepository.class);
	}

	@Test
	public void testShouldToggleDone() {
		repository.add(Item.builder().id(1).task("some task").build());
		String csrf = "XVNuKyOb8HbeA-TaroMgeg";
		String response = given()
			.params("csrf-token", csrf)
			.cookie("csrf-token", csrf)
			.when().post("/todo/1/toggle-done")
			.then()
			.statusCode(200)
			.extract().asString();
		assertTrue(response.contains("checked"));
	}
}

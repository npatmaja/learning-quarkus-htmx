package com.nauvalatmaja.learning.quarkushtmx;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class TodoResourceNewTodoUITest {

	@BeforeAll
	public static void setup() {
		TodoRepository repo = new InMemoryTodoRepository();
		QuarkusMock.installMockForType(repo, TodoRepository.class);
	}

	@Test
	public void testTodoIndexShouldShowTodoTextInputForm() {
		given().when().get("/todo")
			.then()
			.statusCode(200)
			.contentType(ContentType.HTML)
			.body("html.body.div.form.@id", equalTo("new-todo"))
			.body("html.body.div.form.@hx-post", equalTo("/todo/new"))
			.body("html.body.div.form.input[0].@type", equalTo("text"))
			.body("html.body.div.form.input[0].@name", equalTo("todoText"))
			.body("html.body.div.form.input[1].@type", equalTo("hidden"))
			.body("html.body.div.form.input[2].@type", equalTo("submit"))
			.body("html.body.div.form.input[2].@name", equalTo("submit"));
	}

	@Test
	public void testTodoNewShouldReturnListWithNewTodo() {
		String csrf = "XVNuKyOb8HbeA-TaroMgeg";
		String response = given()
			.params("todoText", "new checklist", "csrf-token", csrf)
			.cookie("csrf-token", csrf)
			.when().post("/todo/new")
			.peek()
			.then()
			.statusCode(200)
			.extract().asString();
		String[] splitted = response.split("\n");
		for (int i = 0; i < splitted.length; i++) {
			splitted[i] = splitted[i].trim();
		}
		assertTrue(response.trim().startsWith("<ul id=\"todo-list\">"), "Start with <ul>");
		assertTrue(response.contains("<li>"), "contains <li>");
		assertTrue(response.contains("<form id=\"toggle-1\""), "contains tag form with id toggle-1");
		assertTrue(response.contains("<input type=\"checkbox\""), "contains input type checkbox");
	}
}

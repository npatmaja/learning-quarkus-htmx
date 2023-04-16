package com.nauvalatmaja.learning.quarkushtmx;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class TodoResourceNewTodoTest {
	
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
			.body("html.body.div.form.@method", equalTo("post"))
			.body("html.body.div.form.@action", equalTo("/todo"))
			.body("html.body.div.form.@id", equalTo("new-todo"))
			.body("html.body.div.form.input[0].@type", equalTo("text"))
			.body("html.body.div.form.input[0].@name", equalTo("todoText"))
			.body("html.body.div.form.input[1].@type", equalTo("submit"))
			.body("html.body.div.form.input[1].@name", equalTo("submit"));
	}

	@Test
	public void testTodoNewShouldShowNewTodo() {
		given()
			.params("todoText", "new checklist")
			.when().post("/todo")
			.then()
			.statusCode(200)
			.contentType(ContentType.HTML)
			.body("html.body.div.ul.li[0].input.@type", equalTo("checkbox"))
			.body("html.body.div.ul.li[0].input.@name", equalTo("id"))
			.body("html.body.div.ul.li[0].input.@value", equalTo("1"))
			.body("html.body.div.ul.li[0].label", equalTo("new checklist"));
	}
}

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
	public void testTodoNewShouldShowNewTodo() {
		given()
			.params("todoText", "new checklist")
			.when().post("/todo/new")
			.then()
			.statusCode(200)
			.contentType(ContentType.HTML)
			.body("html.body.div.ul.li[0].input.@type", equalTo("checkbox"))
			.body("html.body.div.ul.li[0].input.@name", equalTo("id"))
			.body("html.body.div.ul.li[0].input.@value", equalTo("1"))
			.body("html.body.div.ul.li[0].label", equalTo("new checklist"));
	}
}

package com.nauvalatmaja.learning.quarkushtmx;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.enterprise.inject.Produces;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class TodoResourceTest {

	@Produces	
	TodoRepository todoRepository = new DoubleTodoRepository();
	
	@Test
	public void testTodoIndexShouldReturnHtml() {
		given().when().get("/todo")
			.then()
			.statusCode(200)
			.contentType(ContentType.HTML)
			.body("html.head.title", equalTo("Simple Todo App"));
	}

	@Test
	public void testTodoIndexShouldShowTodoItems() {
		given().when().get("/todo")
			.then()
			.statusCode(200)
			.contentType(ContentType.HTML)
			.body("html.body.div.ul.li[0].input.@type", equalTo("checkbox"))
			.body("html.body.div.ul.li[0].input.@name", equalTo("id"))
			.body("html.body.div.ul.li[0].input.@value", equalTo("1"))
			.body("html.body.div.ul.li[0].label", equalTo("Checklist 1"));
	}

	@Test
	public void testCompletedTodoIsMarkedAsDone() {
		given().when().get("/todo")
			.then()
			.statusCode(200)
			.contentType(ContentType.HTML)
			.body("html.body.div.ul.li[1].input.@type", equalTo("checkbox"))
			.body("html.body.div.ul.li[1].input.@name", equalTo("id"))
			.body("html.body.div.ul.li[1].input.@value", equalTo("2"))
			.body("html.body.div.ul.li[1].input.@checked", equalTo("checked"));
	}
}

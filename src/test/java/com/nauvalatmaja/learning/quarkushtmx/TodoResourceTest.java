package com.nauvalatmaja.learning.quarkushtmx;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class TodoResourceTest {

	@BeforeAll
	public static void setup() {
		TodoRepository repo = new InMemoryTodoRepository();
		repo.add(Item.builder().id(1).task("Checklist 1").build());
		repo.add(Item.builder().id(2).task("Checklist 2").done(true).build());
		QuarkusMock.installMockForType(repo, TodoRepository.class);
	}
	
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

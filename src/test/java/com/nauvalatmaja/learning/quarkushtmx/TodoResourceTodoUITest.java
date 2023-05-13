package com.nauvalatmaja.learning.quarkushtmx;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class TodoResourceTodoUITest {

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
			.body("html.body.div.ul.li[0].div.input.@type", equalTo("checkbox"))
			.body("html.body.div.ul.li[0].div.label", equalTo("Checklist 1"));
	}

	@Test
	public void testCompletedTodoIsMarkedAsDone() {
		given().when().get("/todo")
			.then()
			.statusCode(200)
			.contentType(ContentType.HTML)
			.body("html.body.div.ul.li[1].div.input.@type", equalTo("checkbox"))
			.body("html.body.div.ul.li[1].div.input.@checked", equalTo("checked"));
	}
}

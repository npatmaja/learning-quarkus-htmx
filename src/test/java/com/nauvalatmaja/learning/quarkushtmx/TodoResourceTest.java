package com.nauvalatmaja.learning.quarkushtmx;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class TodoResourceTest {
	
	@Test
	public void testTodoIndexShouldReturnHtml() {
		given().when().get("/todo")
			.then()
			.statusCode(200)
			.contentType(ContentType.HTML)
			.body("html.head.title", equalTo("Simple Todo App"));
	}
}

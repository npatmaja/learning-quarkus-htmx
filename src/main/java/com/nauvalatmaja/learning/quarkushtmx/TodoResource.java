package com.nauvalatmaja.learning.quarkushtmx;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

@Path("/todo")
public class TodoResource {

	@CheckedTemplate
	public static class Templates {
		public static native TemplateInstance todo(List<Item> items);
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance index() {
		return Templates.todo(Arrays.asList(
			Item.builder()
				.id(1)
				.task("Checklist 1")
				.build(),
			Item.builder()
				.id(2)
				.done(true)
				.task("Checklist 2")
				.build()
		));
	}
}

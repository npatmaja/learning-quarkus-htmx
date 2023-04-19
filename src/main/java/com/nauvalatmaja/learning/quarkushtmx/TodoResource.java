package com.nauvalatmaja.learning.quarkushtmx;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

@Path("/todo")
public class TodoResource {

	@Inject
	TodoService todoService;

	@CheckedTemplate
	public static class Templates {
		public static native TemplateInstance todo(List<Item> items);
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance index() {
		return Templates.todo(todoService.list());
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	@Path("/new")
	public TemplateInstance newTodoPartial(@FormParam("todoText") String todoText) {
		todoService.add(todoText);
		return Templates.todo(null).getFragment("todo_items").data("items", todoService.list());
	}
}

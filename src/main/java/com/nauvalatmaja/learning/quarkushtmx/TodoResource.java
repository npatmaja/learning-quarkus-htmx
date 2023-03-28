package com.nauvalatmaja.learning.quarkushtmx;

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
		public static native TemplateInstance todo();
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance index() {
		return Templates.todo();
	}
}

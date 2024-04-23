package br.com.jkavdev.quarkusapp.task;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/api/v1/tasks")
public class TaskResource {

    private final TaskService taskService;

    @Inject
    public TaskResource(final TaskService taskService) {
        this.taskService = taskService;
    }

    @GET
    public Uni<List<Task>> get() {
        return taskService.listForUser();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseStatus(201)
    public Uni<Task> create(final Task project) {
        return taskService.create(project);
    }

    @GET
    @Path("{id}")
    public Uni<Task> get(@PathParam("id") long id) {
        return taskService.findById(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Uni<Task> update(@PathParam("id") long id, final Task project) {
        project.id = id;
        return taskService.update(project);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}/complete")
    public Uni<Boolean> setComplete(@PathParam("id") long id, boolean complete) {
        return taskService.setComplete(id, complete);
    }

    @DELETE
    @Path("{id}")
    public Uni<Void> delete(@PathParam("id") long id) {
        return taskService.delete(id);
    }


}

package br.com.jkavdev.quarkusapp.project;

import io.smallrye.mutiny.Uni;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/api/v1/projects")
@RolesAllowed("user")
public class ProjectResource {

    private final ProjectService projectService;

    @Inject
    public ProjectResource(final ProjectService projectService) {
        this.projectService = projectService;
    }

    @GET
    public Uni<List<Project>> get() {
        return projectService.listForUser();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseStatus(201)
    public Uni<Project> create(final Project project) {
        return projectService.create(project);
    }

    @GET
    @Path("{id}")
    public Uni<Project> get(@PathParam("id") long id) {
        return projectService.findById(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Uni<Project> update(@PathParam("id") long id, final Project project) {
        project.id = id;
        return projectService.update(project);
    }

    @DELETE
    @Path("{id}")
    public Uni<Void> delete(@PathParam("id") long id) {
        return projectService.delete(id);
    }


}

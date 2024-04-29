package br.com.jkavdev.quarkusapp.user;

import io.smallrye.mutiny.Uni;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/api/v1/users")
@RolesAllowed("admin")
public class UserResource {

    private final UserService userService;

    @Inject
    public UserResource(final UserService userService) {
        this.userService = userService;
    }

    @GET
    public Uni<List<User>> get() {
        return userService.list();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseStatus(201)
    public Uni<User> create(final User user) {
        return userService.create(user);
    }

    @GET
    @Path("{id}")
    public Uni<User> get(@PathParam("id") long id) {
        return userService.findById(id
        );
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Uni<User> update(@PathParam("id") long id, final User user) {
        user.id = id;
        return userService.update(user);
    }

    @PUT
    @Path("self/password")
    @RolesAllowed("user")
    public Uni<User> changePassword(final PasswordChange passwordChange) {
        return userService.changePassword(passwordChange.currentPassword(), passwordChange.newPassword());
    }

    @DELETE
    @Path("{id}")
    public Uni<Void> delete(@PathParam("id") long id) {
        return userService.delete(id);
    }

    @GET
    @Path("self")
    @RolesAllowed("user")
    public Uni<User> getCurrentUser() {
        return userService.getCurrentUser();
    }

}

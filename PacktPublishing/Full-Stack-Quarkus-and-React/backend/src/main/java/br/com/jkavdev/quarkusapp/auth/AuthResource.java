package br.com.jkavdev.quarkusapp.auth;

import io.smallrye.mutiny.Uni;
import javax.annotation.security.PermitAll;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/api/v1/auth")
public class AuthResource {

    private final AuthService authService;

    public AuthResource(final AuthService authService) {
        this.authService = authService;
    }

    @PermitAll
    @POST
    @Path("/login")
    public Uni<String> login(final AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }

}

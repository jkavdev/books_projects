package br.com.jkavdev.quarkusapp.auth;

import br.com.jkavdev.quarkusapp.user.UserService;
import io.quarkus.security.AuthenticationFailedException;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Duration;
import java.util.HashSet;

@ApplicationScoped
public class AuthService {

    private final String issuer;
    private final UserService userService;

    public AuthService(
            @ConfigProperty(name = "mp.jwt.verify.issuer") final String issuer,
            final UserService userService
    ) {
        this.issuer = issuer;
        this.userService = userService;
    }

    public Uni<String> authenticate(final AuthRequest authRequest) {
        return userService.findByName(authRequest.name())
                .onItem()
                .transform(user -> {
                    if (user == null || !UserService.matches(user, authRequest.password())) {
                        throw new AuthenticationFailedException("Invalid credentials");
                    }
                    return Jwt.issuer(issuer)
                            .upn(user.name)
                            .groups(new HashSet<>(user.roles))
                            .expiresIn(Duration.ofHours(1L))
                            .sign();
                });
    }

}

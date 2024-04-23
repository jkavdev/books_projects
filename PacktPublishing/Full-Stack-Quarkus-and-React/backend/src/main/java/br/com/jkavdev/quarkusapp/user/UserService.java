package br.com.jkavdev.quarkusapp.user;

import br.com.jkavdev.quarkusapp.project.Project;
import br.com.jkavdev.quarkusapp.task.Task;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.hibernate.ObjectNotFoundException;

import java.util.List;

@ApplicationScoped
public class UserService {

    private final JsonWebToken jwt;

    public UserService(final JsonWebToken jwt) {
        this.jwt = jwt;
    }

    @WithTransaction
    public Uni<User> findById(long id) {
        return User.<User>findById(id)
                .onItem()
                .ifNull().failWith(() -> new ObjectNotFoundException(id, "User"));
    }

    @WithTransaction
    public Uni<User> findByName(final String name) {
        return User.find("name", name)
                .firstResult();
    }

    @WithTransaction
    public Uni<List<User>> list() {
        return User.listAll();
    }

    @WithTransaction
    public Uni<User> create(final User user) {
        user.password = BcryptUtil.bcryptHash(user.password);
        return user.persistAndFlush();
    }

    @WithTransaction
    public Uni<User> update(final User user) {
        return findById(user.id)
                .chain(u -> {
                    user.setPassword(u.password);
                    return User.getSession();
                })
                .chain(session -> session.merge(user));
    }

    @WithTransaction
    public Uni<User> changePassword(final String currentPassword, final String newPassword) {
        return getCurrentUser()
                .chain(u -> {
                    if (!matches(u, currentPassword)) {
                        throw new ClientErrorException("Current password does not match", Response.Status.CONFLICT);
                    }
                    u.setPassword(BcryptUtil.bcryptHash(newPassword));
                    return u.persistAndFlush();
                });
    }

    @WithTransaction
    public Uni<Void> delete(long id) {
        return findById(id)
                .chain(u -> Uni.combine().all()
                        .unis(
                                Task.delete("user.id", u.id),
                                Project.delete("user.id", u.id)
                        ).asTuple()
                        .chain(t -> u.delete())
                );
    }

    public Uni<User> getCurrentUser() {
        return findByName(jwt.getName());
    }

    public static boolean matches(final User user, final String password) {
        return BcryptUtil.matches(password, user.password);
    }

}

package br.com.jkavdev.quarkusapp.user;

import br.com.jkavdev.quarkusapp.project.Project;
import br.com.jkavdev.quarkusapp.task.Task;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.hibernate.ObjectNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class UserService {

    private final JsonWebToken jwt;

    public UserService(final JsonWebToken jwt) {
        this.jwt = jwt;
    }

    @ReactiveTransactional
    public Uni<User> findById(long id) {
        return User.<User>findById(id)
                .onItem()
                .ifNull().failWith(() -> new ObjectNotFoundException(id, "User"));
    }

    @ReactiveTransactional
    public Uni<User> findByName(final String name) {
        return User.find("name", name)
                .firstResult();
    }

    @ReactiveTransactional
    public Uni<List<User>> list() {
        return User.listAll();
    }

    @ReactiveTransactional
    public Uni<User> create(final User user) {
        user.password = BcryptUtil.bcryptHash(user.password);
        return user.persistAndFlush();
    }

    @ReactiveTransactional
    public Uni<User> update(final User user) {
        return findById(user.id)
                .chain(u -> {
                    user.setPassword(u.password);
                    return User.getSession();
                })
                .chain(session -> session.merge(user));
    }

    @ReactiveTransactional
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

    @ReactiveTransactional
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

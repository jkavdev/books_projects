package br.com.jkavdev.quarkusapp.task;

import br.com.jkavdev.quarkusapp.user.UserService;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.security.UnauthorizedException;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.ObjectNotFoundException;

import java.time.ZonedDateTime;
import java.util.List;

@ApplicationScoped
public class TaskService {

    private final UserService userService;

    public TaskService(final UserService userService) {
        this.userService = userService;
    }

    public Uni<Task> findById(long id) {
        return userService.getCurrentUser()
                .chain(user -> Task.<Task>findById(id)
                        .onItem().ifNull().failWith(() -> new ObjectNotFoundException(id, "Task"))
                        .onItem().invoke(task -> {
                            if (!user.equals(task.user)) {
                                throw new UnauthorizedException("you are not allowed to update this project");
                            }
                        })
                );
    }

    public Uni<List<Task>> listForUser() {
        return userService.getCurrentUser()
                .chain(user -> Task.find("user", user).list());
    }

    @WithTransaction
    public Uni<Task> create(final Task task) {
        return userService.getCurrentUser()
                .chain(user -> {
                    task.user = user;
                    return task.persistAndFlush();
                });
    }

    @WithTransaction
    public Uni<Task> update(final Task task) {
        return findById(task.id)
                .chain(t -> Task.getSession())
                .chain(s -> s.merge(task));
    }

    @WithTransaction
    public Uni<Void> delete(long id) {
        return findById(id)
                .chain(Task::delete);
    }

    @WithTransaction
    public Uni<Boolean> setComplete(long id, boolean complete) {
        return findById(id)
                .chain(task -> {
                    task.complete = complete ? ZonedDateTime.now() : null;
                    return task.persistAndFlush();
                })
                .chain(task -> Uni.createFrom().item(complete));
    }

}

package br.com.jkavdev.quarkusapp.task;

import br.com.jkavdev.quarkusapp.project.Project;
import br.com.jkavdev.quarkusapp.user.User;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "tasks")
public class Task extends PanacheEntity {

    @Column(nullable = false)
    public String title;

    @Column(length = 100)
    public String description;

    public Integer priority;

    @ManyToOne(optional = false)
    public User user;

    public ZonedDateTime complete;

    @ManyToOne
    public Project project;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    public ZonedDateTime created;

    @Version
    public int version;

}

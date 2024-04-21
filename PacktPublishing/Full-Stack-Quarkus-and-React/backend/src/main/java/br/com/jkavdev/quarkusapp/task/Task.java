package br.com.jkavdev.quarkusapp.task;

import br.com.jkavdev.quarkusapp.user.User;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "tasks")
public class Task extends PanacheEntity {

    @Column(nullable = false)
    String title;

    @Column(length = 100)
    String description;

    Integer priority;

    @ManyToOne(optional = false)
    public User user;

    public ZonedDateTime completed;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    public ZonedDateTime created;

    @Version
    public int version;

}

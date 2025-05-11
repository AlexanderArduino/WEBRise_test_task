package ru.spb.anohin.webrise_test_task.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source;

    @ManyToMany(mappedBy = "subscriptions")
    private List<User> users;

    public Subscription() {
    }

    public Subscription(String source) {
        this.source = source;
    }
}

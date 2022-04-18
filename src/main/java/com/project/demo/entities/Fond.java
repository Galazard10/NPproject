package com.project.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fonds")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fond {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "amount")
    private Integer amount;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> monthlySupport;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Event> events;

    @Column(name = "description")
    private String description;
}

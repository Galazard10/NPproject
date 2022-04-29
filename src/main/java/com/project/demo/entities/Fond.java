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

    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> monthlySupport;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Event> events;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "category")
    private String category;

    @Lob
    @Column(name = "img")
    private String img;
}

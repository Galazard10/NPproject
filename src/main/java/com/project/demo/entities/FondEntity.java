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
public class FondEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "amount")
    private Integer amount;

    @OneToMany(fetch = FetchType.LAZY)
    private List<PostEntity> posts;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<UserEntity> monthlySupport;

    @OneToMany(fetch = FetchType.LAZY)
    private List<EventEntity> events;

    @Column(name = "description")
    private String description;
}

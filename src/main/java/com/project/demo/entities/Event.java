package com.project.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private String status;

    @Column(name = "cashgoal")
    private String cashgoal;

    @ManyToOne
    private User user;

    @ManyToOne
    private Fond fond;
}

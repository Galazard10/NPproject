package com.project.demo.repos;

import com.project.demo.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EventsRepo extends JpaRepository<Event, Long> {
}

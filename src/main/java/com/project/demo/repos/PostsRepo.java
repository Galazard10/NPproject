package com.project.demo.repos;

import com.project.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PostsRepo extends JpaRepository<Post, Long> {
}

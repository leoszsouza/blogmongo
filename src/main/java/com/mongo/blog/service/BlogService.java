package com.mongo.blog.service;

import com.mongo.blog.domain.Blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Blog.
 */
public interface BlogService {

    /**
     * Save a blog.
     *
     * @param blog the entity to save
     * @return the persisted entity
     */
    Blog save(Blog blog);

    /**
     * Get all the blogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Blog> findAll(Pageable pageable);


    /**
     * Get the "id" blog.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Blog> findOne(String id);

    /**
     * Delete the "id" blog.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}

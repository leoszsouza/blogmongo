package com.mongo.blog.service;

import com.mongo.blog.domain.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Post.
 */
public interface PostService {

    /**
     * Save a post.
     *
     * @param post the entity to save
     * @return the persisted entity
     */
    Post save(Post post);

    /**
     * Get all the posts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Post> findAll(Pageable pageable);


    /**
     * Get the "id" post.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Post> findOne(String id);

    /**
     * Delete the "id" post.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}

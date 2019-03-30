package com.mongo.blog.service.impl;

import com.mongo.blog.service.PostService;
import com.mongo.blog.domain.Post;
import com.mongo.blog.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Post.
 */
@Service
public class PostServiceImpl implements PostService {

    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Save a post.
     *
     * @param post the entity to save
     * @return the persisted entity
     */
    @Override
    public Post save(Post post) {
        log.debug("Request to save Post : {}", post);
        return postRepository.save(post);
    }

    /**
     * Get all the posts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Post> findAll(Pageable pageable) {
        log.debug("Request to get all Posts");
        return postRepository.findAll(pageable);
    }


    /**
     * Get one post by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Post> findOne(String id) {
        log.debug("Request to get Post : {}", id);
        return postRepository.findById(id);
    }

    /**
     * Delete the post by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Post : {}", id);
        postRepository.deleteById(id);
    }
}

package com.mongo.blog.service.impl;

import com.mongo.blog.service.BlogService;
import com.mongo.blog.domain.Blog;
import com.mongo.blog.repository.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Blog.
 */
@Service
public class BlogServiceImpl implements BlogService {

    private final Logger log = LoggerFactory.getLogger(BlogServiceImpl.class);

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    /**
     * Save a blog.
     *
     * @param blog the entity to save
     * @return the persisted entity
     */
    @Override
    public Blog save(Blog blog) {
        log.debug("Request to save Blog : {}", blog);
        return blogRepository.save(blog);
    }

    /**
     * Get all the blogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Blog> findAll(Pageable pageable) {
        log.debug("Request to get all Blogs");
        return blogRepository.findAll(pageable);
    }


    /**
     * Get one blog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Blog> findOne(String id) {
        log.debug("Request to get Blog : {}", id);
        return blogRepository.findById(id);
    }

    /**
     * Delete the blog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Blog : {}", id);
        blogRepository.deleteById(id);
    }
}

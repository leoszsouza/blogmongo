package com.mongo.blog.web.rest;

import com.mongo.blog.BlogmongoApp;

import com.mongo.blog.domain.Blog;
import com.mongo.blog.repository.BlogRepository;
import com.mongo.blog.service.BlogService;
import com.mongo.blog.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import java.util.List;


import static com.mongo.blog.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BlogResource REST controller.
 *
 * @see BlogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogmongoApp.class)
public class BlogResourceIntTest {

    private static final String DEFAULT_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogService blogService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restBlogMockMvc;

    private Blog blog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlogResource blogResource = new BlogResource(blogService);
        this.restBlogMockMvc = MockMvcBuilders.standaloneSetup(blogResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Blog createEntity() {
        Blog blog = new Blog()
            .usuario(DEFAULT_USUARIO)
            .descricao(DEFAULT_DESCRICAO);
        return blog;
    }

    @Before
    public void initTest() {
        blogRepository.deleteAll();
        blog = createEntity();
    }

    @Test
    public void createBlog() throws Exception {
        int databaseSizeBeforeCreate = blogRepository.findAll().size();

        // Create the Blog
        restBlogMockMvc.perform(post("/api/blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blog)))
            .andExpect(status().isCreated());

        // Validate the Blog in the database
        List<Blog> blogList = blogRepository.findAll();
        assertThat(blogList).hasSize(databaseSizeBeforeCreate + 1);
        Blog testBlog = blogList.get(blogList.size() - 1);
        assertThat(testBlog.getUsuario()).isEqualTo(DEFAULT_USUARIO);
        assertThat(testBlog.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    public void createBlogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blogRepository.findAll().size();

        // Create the Blog with an existing ID
        blog.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlogMockMvc.perform(post("/api/blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blog)))
            .andExpect(status().isBadRequest());

        // Validate the Blog in the database
        List<Blog> blogList = blogRepository.findAll();
        assertThat(blogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllBlogs() throws Exception {
        // Initialize the database
        blogRepository.save(blog);

        // Get all the blogList
        restBlogMockMvc.perform(get("/api/blogs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blog.getId())))
            .andExpect(jsonPath("$.[*].usuario").value(hasItem(DEFAULT_USUARIO.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }
    
    @Test
    public void getBlog() throws Exception {
        // Initialize the database
        blogRepository.save(blog);

        // Get the blog
        restBlogMockMvc.perform(get("/api/blogs/{id}", blog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(blog.getId()))
            .andExpect(jsonPath("$.usuario").value(DEFAULT_USUARIO.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    public void getNonExistingBlog() throws Exception {
        // Get the blog
        restBlogMockMvc.perform(get("/api/blogs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBlog() throws Exception {
        // Initialize the database
        blogService.save(blog);

        int databaseSizeBeforeUpdate = blogRepository.findAll().size();

        // Update the blog
        Blog updatedBlog = blogRepository.findById(blog.getId()).get();
        updatedBlog
            .usuario(UPDATED_USUARIO)
            .descricao(UPDATED_DESCRICAO);

        restBlogMockMvc.perform(put("/api/blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBlog)))
            .andExpect(status().isOk());

        // Validate the Blog in the database
        List<Blog> blogList = blogRepository.findAll();
        assertThat(blogList).hasSize(databaseSizeBeforeUpdate);
        Blog testBlog = blogList.get(blogList.size() - 1);
        assertThat(testBlog.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testBlog.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    public void updateNonExistingBlog() throws Exception {
        int databaseSizeBeforeUpdate = blogRepository.findAll().size();

        // Create the Blog

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlogMockMvc.perform(put("/api/blogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blog)))
            .andExpect(status().isBadRequest());

        // Validate the Blog in the database
        List<Blog> blogList = blogRepository.findAll();
        assertThat(blogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteBlog() throws Exception {
        // Initialize the database
        blogService.save(blog);

        int databaseSizeBeforeDelete = blogRepository.findAll().size();

        // Delete the blog
        restBlogMockMvc.perform(delete("/api/blogs/{id}", blog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Blog> blogList = blogRepository.findAll();
        assertThat(blogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Blog.class);
        Blog blog1 = new Blog();
        blog1.setId("id1");
        Blog blog2 = new Blog();
        blog2.setId(blog1.getId());
        assertThat(blog1).isEqualTo(blog2);
        blog2.setId("id2");
        assertThat(blog1).isNotEqualTo(blog2);
        blog1.setId(null);
        assertThat(blog1).isNotEqualTo(blog2);
    }
}

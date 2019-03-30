package com.mongo.blog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Blog.
 */
@Document(collection = "blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @Field("usuario")
    private String usuario;

    @Field("descricao")
    private String descricao;

    @DBRef
    @Field("blog")
    private Set<Post> blogs = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public Blog usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public Blog descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Post> getBlogs() {
        return blogs;
    }

    public Blog blogs(Set<Post> posts) {
        this.blogs = posts;
        return this;
    }

    public Blog addBlog(Post post) {
        this.blogs.add(post);
        post.setBlog(this);
        return this;
    }

    public Blog removeBlog(Post post) {
        this.blogs.remove(post);
        post.setBlog(null);
        return this;
    }

    public void setBlogs(Set<Post> posts) {
        this.blogs = posts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Blog blog = (Blog) o;
        if (blog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), blog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Blog{" +
            "id=" + getId() +
            ", usuario='" + getUsuario() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}

package com.springboot.blog.springbootblogapp.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.springbootblogapp.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

    
}

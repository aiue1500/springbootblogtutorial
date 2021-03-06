package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> , JpaSpecificationExecutor<Article> {

}

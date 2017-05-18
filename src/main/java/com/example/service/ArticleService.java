package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.example.model.Article;
import com.example.model.ArticleListForm;
import com.example.repository.ArticleRepository;

import static com.example.specifications.ArticleSpecifications.*;

@Service
@Transactional
public class ArticleService {

  @Autowired
  ArticleRepository repository;
  
  
  //index
  public List<Article> findAll(){
    return repository.findAll(new Sort(Sort.Direction.DESC,"id"));
  }
  
  //Create
  public Article create(Article article){
    return repository.save(article);
  }
  //Read
  public Article findOne(Integer id){
    return repository.findOne(id);
  }
  public List<Article> findArticles(ArticleListForm articleListForm){
    return repository.findAll(
        Specifications
          .where(titleContains(articleListForm.getTitle()))
          .and(user_idContains(articleListForm.getUser_id()))
          .and(updated_atContains(articleListForm.getUpdated_at()))
        ,new Sort(Sort.Direction.DESC,"id")
    );
  }
  //Update
  public Article update(Article article){
    return repository.save(article);
  }
  //Delete
  public void delete(Integer id){
    repository.delete(id);
  }
  
}

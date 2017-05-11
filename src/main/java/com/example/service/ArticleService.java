package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.model.Article;
import com.example.repository.ArticleRepository;

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
  //Update
  public Article update(Article article){
    return repository.save(article);
  }
  //Delete
  public void delete(Integer id){
    repository.delete(id);
  }
}

package com.example.specifications;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.example.model.Article;

public class ArticleSpecifications {

  /**
   * 指定文字をタイトルに含む記事を検索する
   */
  public static Specification<Article> titleContains(String title){
    return StringUtils.isEmpty(title) ? null : new Specification<Article>(){
      @Override
      public Predicate toPredicate(Root<Article> root , CriteriaQuery<?> query , CriteriaBuilder cb){
        return cb.like(root.get("title"), "%" + title + "%");
      }
    };
  }
  
  /**
   * 指定文字をuser_idと一致する記事を検索する
   */
  public static Specification<Article> user_idContains(String user_id){
    return StringUtils.isEmpty(user_id) ? null : new Specification<Article>(){
      @Override
      public Predicate toPredicate(Root<Article> root , CriteriaQuery<?> query , CriteriaBuilder cb){
        return cb.equal(root.get("user_id"),Integer.parseInt(user_id));
      }
    };
  }
  
  
  /**
   * 指定日付以降の記事を検索する
   */
  public static Specification<Article> updated_atContains(String updated_at) {
    return StringUtils.isEmpty(updated_at) ? null : new Specification<Article>(){
      @Override
      public Predicate toPredicate(Root<Article> root , CriteriaQuery<?> query , CriteriaBuilder cb){
        // String -> Date型変換
        Date formatDate;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
          formatDate = sdf.parse(updated_at);
        } catch (ParseException e) {
          formatDate = new Date();
          e.printStackTrace();
        }
        return cb.greaterThanOrEqualTo(root.<Date>get("updated_at"),formatDate);
      }
    };
  }
  
}

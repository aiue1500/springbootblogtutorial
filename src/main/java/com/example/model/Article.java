package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "article")
public class Article {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  
  @Column(name="title")
  @Size(max=255)
  @NotEmpty(message="タイトルを入力してください")
  private String title;
  
  @Column(name="description")
  @Size(max=1000 ,message="文字数が多すぎます。(最大1000）")
  @NotEmpty(message="本文を入力してください")
  private String description;
  
  @Column(name="created_at")
  private Date created_at;
  
  @Column(name="updated_at")
  private Date updated_at;
  
  @NotNull(message="ユーザIDが指定されていません。")
  @Column(name="user_id")
  private int user_id;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }
  
  
  
  
}

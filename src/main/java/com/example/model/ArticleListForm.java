package com.example.model;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ArticleListForm {
  
  @Column(name="title")
  @Size(max=255 , message="文字数が多すぎます（最大２５５）")
  private String title;
  
  @Size(max=10 , message="IDの数が多すぎます（最大10文字）")
  @Pattern(regexp="[0-9]*" , message="入力値のタイプが不正です。")
  private String user_id;
  
  private String updated_at;
  
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getUser_id() {
    return user_id;
  }
  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }
  public String getUpdated_at() {
    return updated_at;
  }
  public void setUpdated_at(String updated_at) {
    this.updated_at = updated_at;
  }
  
  
  
}

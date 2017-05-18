package com.example.model;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ArticleListForm {
  
  @Column(name="title")
  @Size(max=255)
  private String title;
  
  @Size(max=10)
  @Pattern(regexp="[0-9]*")
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
  
  
  /**
   * @return 全ての項目に対して空かどうかチェックし、全て空だった場合、trueを返す
   */
  public boolean isEmpty(){
    if(title == null && user_id == null && updated_at == null){
      return true;
    }
    return false;
    
  }
  
}

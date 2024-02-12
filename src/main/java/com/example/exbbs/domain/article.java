package com.example.exbbs.domain;

/**
 * 記事情報を表すドメイン
 */
public class article {
  // 投稿ID
  Integer id;
  // 投稿者名
  String name;
  // 投稿内容
  String content;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "article [id=" + id + ", name=" + name + ", content=" + content + "]";
  }
}

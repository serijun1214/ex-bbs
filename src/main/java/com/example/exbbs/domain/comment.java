package com.example.exbbs.domain;

/**
 * コメント情報を表すドメイン
 */
public class Comment {
  // コメントID
  Integer id;
  // 投稿者名
  String name;
  // コメント内容
  String content;
  // 投稿ID
  Integer articleId;

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

  public Integer getArticleId() {
    return articleId;
  }

  public void setArticleId(Integer articleId) {
    this.articleId = articleId;
  }

  @Override
  public String toString() {
    return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
  }
}

package com.example.exbbs.form;

/**
 * コメント投稿フォーム
 */
public class CommentForm {
  // 投稿ID
  Integer articleId;
  // 投稿者名
  String name;
  // コメント内容
  String content;

  public Integer getArticleId() {
    return articleId;
  }

  public void setArticleId(Integer articleId) {
    this.articleId = articleId;
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
    return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
  }
}

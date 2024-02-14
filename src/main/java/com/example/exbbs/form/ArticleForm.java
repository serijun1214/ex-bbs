package com.example.exbbs.form;

import jakarta.validation.constraints.NotBlank;

/**
 * 記事投稿フォーム
 */
public class ArticleForm {
  // 投稿者名
  @NotBlank(message = "名前を入力してください")
  private String name;
  // 投稿内容
  @NotBlank(message = "投稿内容を入力してください")
  private String content;

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
    return "ArticleForm [name=" + name + ", content=" + content + "]";
  }
}

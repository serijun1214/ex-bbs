package com.example.exbbs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.exbbs.domain.Article;

/**
 * 記事情報を操作するリポジトリ.
 */
@Repository
public class ArticleRepository {

  // NamedParameterJdbcTemplateをDI
  @Autowired
  NamedParameterJdbcTemplate template;

  // RowMapper
  private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
    Article article = new Article();
    article.setId(rs.getInt("id"));
    article.setName(rs.getString("name"));
    article.setContent(rs.getString("content"));
    return article;
  };

  /**
   * 記事情報一覧を取得する.
   * 
   * @return 記事情報一覧
   */
  public List<Article> findAll() {
    String sql = "SELECT id, name, content FROM articles ORDER BY id DESC";
    List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
    return articleList;
  }
  
  /**
   * 記事情報を挿入する.
   */
  public void insert(Article article) {
    String sql = "INSERT INTO articles (name, content) VALUES (:name, :content)";
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("name", article.getName());
    param.addValue("content", article.getContent());
    template.update(sql, param);
  }
}

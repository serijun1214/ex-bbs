package com.example.exbbs.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.exbbs.domain.Article;
import com.example.exbbs.domain.Comment;

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

  // Extractor
  private static final ResultSetExtractor<List<Article>> ARTICLE_WITH_COMMENTS_EXTRACTOR = (rs) -> {
    List<Article> articles = new ArrayList<>();
    int checkArticleId = -1;
    Article article = null;

    while (rs.next()) {
      if (checkArticleId != rs.getInt("id")) {
        article = new Article();
        article.setId(rs.getInt("id"));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));
        article.setCommentList(new ArrayList<>());
        articles.add(article);
        checkArticleId = rs.getInt("id");
      }

      if (rs.getInt("comments_id") > 0) {
        Comment comment = new Comment();
        comment.setId(rs.getInt("comments_id"));
        comment.setName(rs.getString("comments_name"));
        comment.setContent(rs.getString("comments_content"));
        article.getCommentList().add(comment);
      }
    }
    return articles;
  };

  /**
   * 記事情報一覧を取得する.
   * 
   * @return 記事情報一覧
   */
  public List<Article> findAll() {
    String sql = """
        SELECT
        articles.id AS id,
        articles.name AS name,
        articles.content AS content,
        comments.id AS comments_id,
        comments.name comments_name,
        comments.content AS comments_content
        FROM
        articles
        LEFT JOIN
        comments
        ON articles.id = comments.article_id
        ORDER BY
        articles.id,
        comments.id;
            """;

    List<Article> articleList = template.query(sql, ARTICLE_WITH_COMMENTS_EXTRACTOR);
    return articleList;
  }

  /**
   * 記事情報を挿入する
   * 
   * @param article 記事情報
   */
  public void insert(Article article) {
    String sql = "INSERT INTO articles (name, content) VALUES (:name, :content)";
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("name", article.getName());
    param.addValue("content", article.getContent());
    template.update(sql, param);
  }

  /**
   * 記事情報を削除する
   * 
   * @param id 記事ID
   */
  public void deleteById(int id) {
    String sql = """
      WITH delete_comment_id AS (
        DELETE FROM comments
        WHERE article_id = :id
        RETURNING article_id
      )
      DELETE FROM articles
      WHERE id = :id;
        """;
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("id", id);
    template.update(sql, param);
  }
}

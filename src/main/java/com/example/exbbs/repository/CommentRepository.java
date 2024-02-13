package com.example.exbbs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.exbbs.domain.Article;
import com.example.exbbs.domain.Comment;

/**
 * コメント情報を操作するリポジトリ
 */
@Repository
public class CommentRepository {

  // NamedParameterJdbcTemplateをDI
  @Autowired
  NamedParameterJdbcTemplate template;

  // RowMapper
  private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
    Comment comment = new Comment();
    comment.setId(rs.getInt("id"));
    comment.setName(rs.getString("name"));
    comment.setComment(rs.getString("content"));
    comment.setArticleId(rs.getInt("article_id"));
    return comment;
  };

  /**
   * コメント情報一覧を取得
   * @param articleId 記事ID
   * @return コメント情報一覧
   */
  public List<Comment> findByArticleId(Integer articleId) {
    String sql = "SELECT id, name, content, article_id FROM comments WHERE article_id = :articleId ORDER BY id";
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("articleId", articleId);
    List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
    return commentList;
  }
}

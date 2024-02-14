package com.example.exbbs.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.exbbs.domain.Article;
import com.example.exbbs.domain.Comment;
import com.example.exbbs.form.ArticleForm;
import com.example.exbbs.form.CommentForm;
import com.example.exbbs.repository.ArticleRepository;
import com.example.exbbs.repository.CommentRepository;

/**
 * 記事情報を操作するコントローラ
 */
@Controller
@RequestMapping("")
public class ArticleController {

  // ArticleRepositoryをDI
  @Autowired
  private ArticleRepository articleRepository;

  // CommentRepositoryをDI
  @Autowired
  private CommentRepository commentRepository;

  /**
   * 記事一覧を表示する
   * @param model
   * @return 記事一覧画面
   */
  @GetMapping("/article")
  public String index(Model model) {
    List<Article> articleList = articleRepository.findAll();

    // for (Article article : articleList) {
    //   article.setCommentList(commentRepository.findByArticleId(article.getId()));
    // }
    
    model.addAttribute("articleList", articleList);
    return "index";
  }

  /**
   * 記事を登録する
   * @param form 記事情報
   * @return 記事一覧画面
   */
  @PostMapping("/insertArticle")
  public String insertArticle(ArticleForm form) {
    Article article = new Article();
    BeanUtils.copyProperties(form, article);
    articleRepository.insert(article);
    return "redirect:/article";
  }

  /**
   * 記事と紐づくコメントを削除する
   * @param id
   * @return 記事一覧画面
   */
  @PostMapping("/deleteArticle")
  public String deleteArticle(@RequestParam("id") int id) {
    articleRepository.deleteById(id);
    return "redirect:/article";
  }

  /**
   * コメントを登録する
   * @param form コメント情報
   * @return 記事一覧画面
   */
  @PostMapping("/insertComment")
  public String insertComment(CommentForm form) {
    Comment comment = new Comment();
    BeanUtils.copyProperties(form, comment);
    commentRepository.insert(comment);
    return "redirect:/article";
  }

  /**
   * コメントを削除する
   * @param articleId 記事ID
   * @return 記事一覧画面
   */
  @PostMapping("/deleteComment")
  public String deleteComment(@RequestParam("articleId") int articleId) {
    commentRepository.deleteByArticleId(articleId);
    return "redirect:/article";
  }
}

package com.example.exbbs.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.exbbs.domain.Article;
import com.example.exbbs.form.ArticleForm;
import com.example.exbbs.repository.ArticleRepository;
import com.example.exbbs.repository.CommentRepository;

@Controller
@RequestMapping("")
public class ArticleController {

  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private CommentRepository commentRepository;

  @GetMapping("/article")
  public String index(Model model) {
    List<Article> articleList = articleRepository.findAll();

    for (Article article : articleList) {
      article.setCommentList(commentRepository.findByArticleId(article.getId()));
    }

    model.addAttribute("articleList", articleList);
    return "index";
  }

  @PostMapping("/insertArticle")
  public String insertArticle(ArticleForm form) {
    Article article = new Article();
    BeanUtils.copyProperties(form, article);
    articleRepository.insert(article);
    return "redirect:/article";
  }
}

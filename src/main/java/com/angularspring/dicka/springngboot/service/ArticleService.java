/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angularspring.dicka.springngboot.service;

import com.angularspring.dicka.springngboot.entity.Article;
import java.util.List;

/**
 *
 * @author java-spring
 */
public interface ArticleService {
    
    List<Article> findAllArticles();
    
    Article findOneArticle(int idarticle);
    
    boolean insertArticle(Article article);
    
    void updateArticle(Article article);
    
    void deleteArticle(Article article);
    
}

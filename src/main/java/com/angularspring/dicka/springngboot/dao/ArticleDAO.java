/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angularspring.dicka.springngboot.dao;

import com.angularspring.dicka.springngboot.entity.Article;
import java.util.List;

/**
 *
 * @author java-spring
 */
public interface ArticleDAO {
    
    List<Article> findAllArticles();
    
    Article findOneArticle(int idarticle);
    
    void insertArticle(Article article);
    
    void deleteArticle(Article idarticle);
    
    void updateArticle(Article article);
    
    boolean ifArticelIsExist(String title, String category);
    
}

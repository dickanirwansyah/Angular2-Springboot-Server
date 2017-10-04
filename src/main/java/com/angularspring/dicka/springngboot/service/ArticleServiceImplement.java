/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angularspring.dicka.springngboot.service;

import com.angularspring.dicka.springngboot.dao.ArticleDAO;
import com.angularspring.dicka.springngboot.entity.Article;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author java-spring
 */
@Service
public class ArticleServiceImplement implements ArticleService{

    @Autowired
    private ArticleDAO articleDAO;
    
    //menampilkan data artikel
    @Override
    public List<Article> findAllArticles() {
      return articleDAO.findAllArticles();
    }

    //menampilkan data artikel berdasarkan kode
    @Override
    public Article findOneArticle(int idarticle) {
      return articleDAO.findOneArticle(idarticle);
    }

    //proses insert article
    @Override
    public synchronized boolean insertArticle(Article article){
        if(articleDAO.ifArticelIsExist(article.getTitle(), article.getCategory())){
            return false;
        }else{
            articleDAO.insertArticle(article);
            return true;
        }
    }

    @Override
    public void updateArticle(Article article) {
      articleDAO.updateArticle(article);
    }

    @Override
    public void deleteArticle(Article article) {
      articleDAO.deleteArticle(article);
    }

   
    
}

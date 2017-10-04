/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angularspring.dicka.springngboot.dao;

import com.angularspring.dicka.springngboot.entity.Article;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author java-spring
 */
@Transactional
@Repository
public class ArticleDAOImplement implements ArticleDAO{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Article> findAllArticles() {
       String hql="FROM Article as artic ORDER BY artic.idarticle DESC";
       return (List<Article>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Article findOneArticle(int idarticle) {
       return entityManager.find(Article.class, idarticle);
    }

    @Override
    public void insertArticle(Article article) {
      entityManager.persist(article);
    }

    @Override
    public void deleteArticle(Article article) {
      entityManager.remove(article);
    }

    @Override
    public void updateArticle(Article article) {
      Article artic = findOneArticle(article.getIdarticle());
      artic.setTitle(article.getTitle());
      artic.setCategory(article.getCategory());
      entityManager.flush();
    }

    @Override
    public boolean ifArticelIsExist(String title, String category) {
      String hql = "FROM Article as artic WHERE artic.title=? and artic.category=?";
      int hitungtabel = entityManager.createQuery(hql)
              .setParameter(1, title)
                .setParameter(2, category)
                    .getResultList().size();
      
      return hitungtabel > 0 ? true : false;
    }
    
}

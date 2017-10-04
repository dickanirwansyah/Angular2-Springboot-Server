/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angularspring.dicka.springngboot.controller;

import com.angularspring.dicka.springngboot.entity.Article;
import com.angularspring.dicka.springngboot.service.ArticleService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author java-spring
 */
@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ControllerRestArticle {
    
    private static final Logger LOGGER = 
            LoggerFactory.getLogger(ControllerRestArticle.class);
    
    @Autowired
    private ArticleService articleService;
    
    @GetMapping(value = "/all-articles")
    public ResponseEntity<List<Article>> findAllArticles(){
        
        LOGGER.info("Menampilkan api article");
        
        List<Article> listArticles= articleService.findAllArticles();
        
        if(listArticles.isEmpty()){
            LOGGER.info("article tidak ditemukan ");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<List<Article>>(listArticles, HttpStatus.OK);
    }
    
    @GetMapping(value = "/article/{idarticle}")
    public ResponseEntity<Article> findOneArticle(@PathVariable String idarticle){
        
        LOGGER.info("Menampilkan id article : "+idarticle);
        
        Article article = articleService.findOneArticle(Integer.parseInt(idarticle));
        
        return new ResponseEntity<Article>(article, HttpStatus.OK);
    }
    
    @PostMapping(value = "/insertarticle")
    public ResponseEntity<Void>insertArticle(@RequestBody Article article, UriComponentsBuilder builder){
        
        LOGGER.info("proses insert article");
        
        boolean validasi = articleService.insertArticle(article);
        if(validasi == false){
            LOGGER.info("maaf kategori dan title sudah ada dalam database");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        
        headers.setLocation(builder.path("/insertarticle?idarticle={idarticle}")
                .buildAndExpand(article.getIdarticle()).toUri());
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/updatearticle")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article){
        LOGGER.info("Proses Update data article");
        
        articleService.updateArticle(article);
        
        return new ResponseEntity<Article>(HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/deletearticle/{idarticle}")
    public ResponseEntity<Void>deleteArticle(@PathVariable String idarticle){
        
        LOGGER.info("proses delete data");
        
        Article article = articleService.findOneArticle(Integer.parseInt(idarticle));
        
        articleService.deleteArticle(article);
        
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}

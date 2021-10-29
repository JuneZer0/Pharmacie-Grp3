package com.grp3.pharmacybackend.presentation.controller;

import java.util.List;

import com.grp3.pharmacybackend.business.Services.Interfaces.IArticleService;
import com.grp3.pharmacybackend.presentation.model.ArticleDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ArticleController {
    
    @Autowired
    private IArticleService articleService;
    
    /**
     * Get the list of all articles
     * @return the list of all articles
     */
    @GetMapping("/articles")
    public List<ArticleDto> listArticles() {
        return articleService.getAllArticles();
    }
    
    /**
     * Get a list of articles with the same name
     * @param name
     * @return a list of articles with the same name
     */
    @GetMapping("/articles/byname/{name}")
    public List<ArticleDto> listArticlesByName(@PathVariable(value="name")String name) {
        return articleService.findArticlesByName(name);
    }

    /**
     * Get an article by its id
     * @param id
     * @return an article
     */
    @GetMapping("/articles/{id}")
    public ArticleDto articleById(@PathVariable(value="id") Long id) {
        return articleService.findArticleById(id);
    }

    /**
     * Create an article
     * @param articleDto
     */
    @PostMapping("/articles")
    public void createArticle(ArticleDto articleDto) {
        this.articleService.addArticle(articleDto);

    }
   

    /**
     * Update an article
     * @param id
     * @param articleDto
     */
    @PutMapping("/articles/{id}")
    public void updateArticle(@PathVariable(value="id") Long id, @RequestBody ArticleDto articleDto) {
        ArticleDto updateArticleDto = articleService.findArticleById(id);
        if (updateArticleDto != null) {
            articleService.updateArticle(id, articleDto);
        } else {
            articleService.addArticle(articleDto);
        }
    }

    /**
     * Delete an article
     * @param id
     */
    @DeleteMapping("/articles/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        this.articleService.deleteArticle(id);
    }

}
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ArticleController {
    
    @Autowired
    private IArticleService articleService;
    

    // Obtenir la liste de tous les articles créés
    @GetMapping({"/articles"})
    public List<ArticleDto> listArticles() {
        return articleService.getAllArticles();
    }
    
    // Obtenez une liste des articles filtrée par nom
    @GetMapping({"/articles?name={name}"})
    public List<ArticleDto> listArticlesByName(String name) {
        return articleService.findArticlesByName(name);
    }

    // Obtenir un article par son identifiant
    @GetMapping({"/articles/{id}"})
    public ArticleDto articleById(@PathVariable Long id) {
        return articleService.findArticleById(id);
    }

    // Créer un nouvel article : createArticle
    @PostMapping({"/articles"})

    // Mettre à jour l'article : updateArticle
    @PutMapping({"/articles/{id}"})
    public void updateArticle(@PathVariable Long id, @RequestBody ArticleDto articleDto) {
        ArticleDto updateArticleDto = articleService.findArticleById(id);
        if (updateArticleDto != null) {
            articleService.updateArticle(id, articleDto);
        } else {
            articleService.addArticle(articleDto);
        }
    }

    // Supprimer l'article par son identifiant : deleteArticle
    @DeleteMapping({"/posts/{id}"})
    public void delete(@PathVariable(value = "id") Long id){
        this.articleService.deleteArticle(id);


}

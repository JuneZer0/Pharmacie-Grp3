package com.grp3.pharmacybackend.business.Services.Impl;
import java.util.ArrayList;
import java.util.List;

import com.grp3.pharmacybackend.business.Helpers.ModelMapper;
import com.grp3.pharmacybackend.business.Services.Interfaces.IArticleService;
import com.grp3.pharmacybackend.persistance.dao.interfaces.IArticleDao;
import com.grp3.pharmacybackend.persistance.entities.Article;
import com.grp3.pharmacybackend.presentation.model.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements IArticleService{

     @Autowired
      private IArticleDao articleDao;

      private ModelMapper mapper = new ModelMapper();
   

    //Afficher la liste de tous les articles créés
        @Override
        public List<ArticleDto> getAllArticles() {
            List<ArticleDto> allArticles = new ArrayList<ArticleDto>();
            List<Article> articlesFromDatabase = articleDao.findAll(Article.class);
            allArticles = mapper.mapToListArticlesDto(articlesFromDatabase);
            return allArticles;
        }

     // trouver un article par son nom
        @Override
        public List<ArticleDto> findArticlesByName(String articleName) {
            List<ArticleDto> allArticles = new ArrayList<ArticleDto>();
            allArticles = mapper.mapToListArticlesDto(articleDao.findAllByNameContaining(articleName));
            return allArticles;
        }
       // trouver un article par son id
       @Override
       public ArticleDto findArticleById(Long id) {
           //TODO
           return null;
       }
       
        //rajouter un article
        @Override
        public void addArticle(ArticleDto articleDto) {
            
            Article article = new Article();
            article = mapper.mapToArticle(articleDto);
            articleDao.save(article);
           
        }
         // modifier un article
        
         @Override
         public void updateArticle(Long id, ArticleDto articleDto) {                      
            this.addArticle(articleDto);
         }


         // Permet de supprimer un article
        @Override
        public void deleteArticle(Long id) {
          articleDao.deleteById(id); 
    
        }

    
    }


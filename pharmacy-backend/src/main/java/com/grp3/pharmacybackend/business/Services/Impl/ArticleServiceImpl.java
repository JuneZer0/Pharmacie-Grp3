package com.grp3.pharmacybackend.business.Services.Impl;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
           ArticleDto articleDtoId = new ArticleDto();
           Optional<Article> articleDo = articleDao.findById(id);

           if(articleDo.isPresent()){
               Article article = new Article();
               article = articleDo.get();
               articleDtoId = mapper.mapToArticleDto(article);
               return articleDtoId;
           }
           throw new RuntimeException("that object does not exist");
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


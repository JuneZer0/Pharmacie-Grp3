package com.grp3.pharmacybackend.business.Services.Impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import com.grp3.pharmacybackend.business.Services.Interfaces.IArticleService;
import com.grp3.pharmacybackend.presentation.model.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Propagation.REQUIRED)
public class ArticleServiceImpl implements IArticleService{
    
        @Override
        public List<ArticleDto> getAllArticles() {
            List<ArticleDto> allArticles = new ArrayList<ArticleDto>();
            allArticles = mapToListDesArticlesDto(articleDao.findAllArticles());
            return allArticles;
        }
     // trouver un article par son nom
        @Override
        public List<ArticleDto> findArticlesByName(String articleName) {
            List<ArticleDto> allArticles = new ArrayList<ArticleDto>();
            allArticles = mapToListDesArticlesDto(articleDao.findAllByNameContaining(articleName));
            return allArticles;
        }
       // trouver un article par son id
       @Override
       public Optional<ArticleDto> findArticleById(Long id) {
           // TODO Auto-generated method stub
           return null;
       }
       
        //rajouter un article
        @Override
        public String addArticle( ArticleDto articleDto) {
            
            Article article = new Article();
            article = mapToArticle(articleDto);
    
            final Article newArticle =  articleDao.save(article);
            return newArticle.getId();
        }
         // modifier un article
        
         @Override
         public void updateArticle(Long id, ArticleDto articleDto) {
             // TODO Auto-generated method stub
             
         }


         // Permet de supprimer un article
        @Override
        public void deleteArticle(Long id) {
          this.articleDao.deleteById(id); 
    
        }
      
      
     
    
    }


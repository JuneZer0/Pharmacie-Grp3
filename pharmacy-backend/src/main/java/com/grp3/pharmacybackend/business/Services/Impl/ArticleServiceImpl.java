package com.grp3.pharmacybackend.business.Services.Impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import com.grp3.pharmacybackend.business.Services.Interfaces.IArticleService;
import com.grp3.pharmacybackend.persistance.dao.impl.ArticleDaoImpl;
import com.grp3.pharmacybackend.persistance.dao.interfaces.IArticleDao;
import com.grp3.pharmacybackend.persistance.entities.Article;
import com.grp3.pharmacybackend.presentation.model.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Propagation.REQUIRED)
public class ArticleServiceImpl implements IArticleService{

     @Autowired
      private IArticleDao articleDao;

    


    //Afficher la liste de tous les articles créés
        @Override
        public List<ArticleDto> getAllArticles() {
            List<ArticleDto> allArticles = new ArrayList<ArticleDto>();
            allArticles = mapToListDesArticlesDto(articleDao.findAll());
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
       public ArticleDto findArticleById(Long id) {
           
           return null;
       }
       
        //rajouter un article
        @Override
        public void addArticle( ArticleDto articleDto) {
            
            Article article = new Article();
            article = mapToArticle(articleDto);
            articleDao.save(article);
           
        }
         // modifier un article
        
         @Override
         public void updateArticle(Long id, ArticleDto articleDto) {
            Article article = articleDao.findById(id).get();
            article.setArticleBarcode(articleDto.getArticleBarcode());
            article.setArticleName(articleDto.getArticleName());
            article.setArticlePrice(articleDto.getArticlePrice());
            article.setArticleQuantity(articleDto.getArticleQuantity());
            articleDao.save(article);
         }


         // Permet de supprimer un article
        @Override
        public void deleteArticle(Long id) {
          this.articleDao.deleteById(id); 
    
        }

    
    }


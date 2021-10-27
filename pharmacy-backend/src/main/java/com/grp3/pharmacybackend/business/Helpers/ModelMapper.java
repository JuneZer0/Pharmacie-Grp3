package com.grp3.pharmacybackend.business.Helpers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import com.grp3.pharmacybackend.business.Services.Interfaces.IArticleService;
import com.grp3.pharmacybackend.presentation.model.ArticleDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;


public class ModelMapper {
   
        @Autowired
        private IArticleDao articleDao;
    
       
         // map un article ---> articleDto
        
        private ArticleDto mapToArticleDto(Article article) {
            ArticleDto articleDto = new ArticleDto();
            if (article == null) {
                return null;
            }
            articleDto.setId(article.getId());
            articleDto.setBarcode(article.getBarcode());
            articleDto.setArticleName(article.getArticleName());
            articleDto.setArticleQuantity(article.getArticleQuantity());
            articleDto.setarticlePrice(article.getarticlePrice());
            return articleDto;
        }
    
        
          //map un articleDto ---> article
        
        private Article mapToArticle(final ArticleDto articleDto) {
            final Article article= new Article();
            if (articleDto == null) {
                return null;
            }
            article.setArticleBarcode(articleDto.getArticleBarcode());
            article.setArticleName(articleDto.getArticleName());
            article.setArticleQuantity(articleDto.getArticleQuantity());
            article.getIsAvailability(articleDto.getArticlePrice());
            return article;
        }
    
        
          //map une liste d'objets Do ---> liste d'objets Dto


}

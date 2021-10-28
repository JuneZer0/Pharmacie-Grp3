package com.grp3.pharmacybackend.business.Services.Interfaces;
import java.util.List;
import com.grp3.pharmacybackend.presentation.model.ArticleDto;


public interface IArticleService {
    
    
    List<ArticleDto> getAllArticles();

    
    List<ArticleDto> findArticlesByName(String articleName);

 
    ArticleDto findArticleById(Long id);

  
    void addArticle(ArticleDto articleDto);

   
    void updateArticle(Long id, ArticleDto articleDto);

    
    void deleteArticle(Long id);

}

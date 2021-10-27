package com.grp3.pharmacybackend.business.Services.Interfaces;
import java.util.List;
import java.util.Optional;
import com.grp3.pharmacybackend.presentation.model.ArticleDto;


public interface IArticleService {
    
    
    List<ArticleDto> getAllArticles();

    
    List<ArticleDto> findArticlesByName(String articleName);

 
    Optional<ArticleDto> findArticleById(Long id);

  
    String addArticle(ArticleDto articleDto);

   
    void updateArticle(Long id, ArticleDto articleDto);

    
    void deleteArticle(Long id);

}

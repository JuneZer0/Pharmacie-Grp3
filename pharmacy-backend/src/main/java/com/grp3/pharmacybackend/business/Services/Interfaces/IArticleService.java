package com.grp3.pharmacybackend.business.Services.Interfaces;
import java.util.List;
import com.grp3.pharmacybackend.presentation.model.ArticleDto;


public interface IArticleService {
    
    /**
     * Calls the Dao to retrieve all articles from database then maps them to dto objects
     * @return a List containing all articles from the database, mapped to Dto objects the controller can then pass to the requester
     */
    List<ArticleDto> getAllArticles();

    
    /**
     * Calls the Dao to retrieve a list containing all articles that share a given name, then maps them to Dto objects
     * @param articleName the name of the articles to find
     * @return a List of articles having the wanted name, mapped to Dto objects that the controller can then pass to the requester
     */
    List<ArticleDto> findArticlesByName(String articleName);

 
    /**
     * Calls the Dao to retrieve an article thanks to its id, then maps it to Dto object and returns it.
     * @param id the id of the wanted article
     * @return a Dto Article object 
     */
    ArticleDto findArticleById(Long id);

  
    /**
     * Maps the Dto Article to a Do article then calls the Dao to add it to the database
     */
    void addArticle(ArticleDto articleDto);

   /**
    * Calls the save method, since it does the same thing.
    * @see {@link IArticleService#addArticle}
    * @param articleDto the article to update.
    */
    void updateArticle(ArticleDto articleDto);

    
    void deleteArticle(Long id);

}

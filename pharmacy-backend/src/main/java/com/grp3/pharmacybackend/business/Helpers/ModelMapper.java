package com.grp3.pharmacybackend.business.Helpers;
import java.util.ArrayList;
import java.util.List;
import com.grp3.pharmacybackend.persistance.entities.Article;
import com.grp3.pharmacybackend.presentation.model.ArticleDto;




public class ModelMapper {
             
      
         // map un article ---> articleDto
        
        public ArticleDto mapToArticleDto(Article article) {
            ArticleDto articleDto = new ArticleDto();
            if (article == null) {
                return null;
            }
            articleDto.setIdArticle(article.getArticleId());
            articleDto.setArticleBarcode(article.getArticleBarcode());
            articleDto.setArticleName(article.getArticleName());
            articleDto.setArticleQuantity(article.getArticleQuantity());
            articleDto.setArticlePrice(article.getArticlePrice().doubleValue());
            return articleDto;
        }
    
        
          //map un articleDto ---> article
        
        public Article mapToArticle(final ArticleDto articleDto) {
            final Article article= new Article();
            if (articleDto == null) {
                return null;
            }
            article.setArticleBarcode(articleDto.getArticleBarcode());
            article.setArticleName(articleDto.getArticleName());
            article.setArticleQuantity(articleDto.getArticleQuantity());
            article.setArticlePrice(articleDto.getArticlePrice().floatValue());
            return article;
        }
    
        
          //map une liste d'objets Do ---> liste d'objets Dto
        public List<ArticleDto> mapToListArticlesDto(final List<Article> listArticlesDo) {
            System.out.println(listArticlesDo);
            List<ArticleDto> listArticlesDto = new ArrayList<>();
            for (Article articleDo : listArticlesDo) {
                listArticlesDto.add(mapToArticleDto(articleDo));
                System.out.println(articleDo.getArticleName());
            }
            return listArticlesDto;
        }

}

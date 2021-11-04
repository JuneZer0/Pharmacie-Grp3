<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>

<!DOCTYPE html>
<html lang="en">

    <body>
        <div class="container">

            <h3>Détails de l'article</h3>
               
                <div class="ArticleDetails">
                    
                
                <p class="name">Nom : ${art.articleName}</p>
                <p class="barcode">Code barre : ${art.getArticleBarcode()}</p>
                <p class="price">Prix : ${art.getArticlePrice()} €</p>
                <p class="quantity">Quantité : ${art.getArticleQuantity()}</p>

             </div>
        </div>

        <div class="btn">
             <!-- Edit button -->
             <button type="button" class="btn btn-primary">
                <a href="${ API_UPDATE }">Modifier</a>
            </button>
                
            <!-- Delete button -->
            <button type="button" class="btn btn-danger">
                <a href="${ API_DELETE }">Supprimer</a>
            </button>
        </div>

        
        
    </body>
</html>
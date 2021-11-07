<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>

     <jsp:include page="header.jsp"></jsp:include>
     <div class="container">

            <h3>Détails de l'article</h3>
               
                <div class="ArticleDetails">
                <p class="name">Nom : ${article.getArticleName()}</p>
                <p class="barcode">Code barre : ${article.getArticleBarcode()}</p>
                <p class="price">Prix : ${article.getArticlePrice()} €</p>
                <p class="quantity">Quantité : ${article.getArticleQuantity()}</p>

             </div>
        </div>

        <div class="btn">
             <!-- Edit button -->
             <button type="button" class="btn btn-primary">
                
                <a href="${ PathResolver.API_UPDATE }/${ art.getIdArticle() }">Modifier</a>
            </button>
                
            <!-- Delete button -->
            <button type="button" class="btn btn-danger">
                <a href="${ API_DELETE }">Supprimer</a>
            </button>
        </div>

        <!-- Image ajoutée -->
        <section>
            <figure>
                <img src="http://localhost:8081/pharmacy-frontend-1.0/View/img/003-pharmacy-2.png" alt="Pharmacy">
            </figure>
        </section>
        <jsp:include page="footer.jsp"></jsp:include>
     
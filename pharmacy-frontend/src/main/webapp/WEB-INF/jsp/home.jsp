<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Boutons qui feront apparaître les différentes listes -->
<form action="#" method="post">
    <!-- Pour la liste de tous les articles -->
    <button type="submit" class="btn btn-primary btn-lg">Obtenir la liste des éléments</button>
    <!-- Pour la liste de tous des articles par nom -->
    <label for="searchArticles">Trouver des articles par nom</label>
    <input type="search" id="searchArticles" name="searchArticles" value="${requestScope.name != null ? requestScope.name : }" aria-label="Search articles">
    <button type="submit" class="btn btn-secondary btn-lg">Rechercher</button>
</form>

<!-- Solution différente du display none
<c:if test="${ requestScope.articles.size() > 0 }">
    Code
</c:if> -->
<!-- Attention display none, donc ne s'affichera pas -->
<div id="listArticles" class="${requestScope.visibility}">
    <h3>Listes des articles</h3>
    <section class="all">
        <c:forEach items="${requestScope.articles}" var="art">
            <article id="article-${art.getIdArticle()}">
                <!-- <a href="${pageContext.request.contextPath}/article?id=${art.id}">${art.name}</a> -->
                <p class="name">Nom : ${art.getArticleName()}</p>
                <p class="barcode">Code barre : ${art.getArticleBarcode()}</p>
                <p class="price">Prix : ${art.getArticlePrice()} €</p>
                <p class="quantity">Quantité : ${art.getArticleQuantity()}</p>

                <c:choose>
                    <c:when test="${art.isArticleAvailable() > 0}">
                        <!-- <img src="${pageContext.request.contextPath}/assets${art.img}" alt="Available"> -->
                        <img src="../../View/img/001-available-64.png" alt="Available">
                        <span>En stock</span>
                    </c:when>
                    <c:when test="${art.isArticleAvailable() == 0}">
                        <!-- <img src="${pageContext.request.contextPath}/assets${art.img}" alt="Not available"> -->
                        <img src="../../View/img/002-out-of-stock-64.png" alt="Not available">
                        <span>Indisponible</span>
                    </c:when>
                    <!-- <c:otherwise>
                    </c:otherwise> -->
                </c:choose>

                <!-- Edit button -->
                <button type="button" class="btn btn-primary">
                    <!-- Ajouter l'id -->
                    <a href="${ PathResolver.API_UPDATE }">Modifier</a>
                </button>
                    
                <!-- Delete button -->
                <button type="button" class="btn btn-danger">
                    <!-- Ajouter l'id -->
                    <a href="${ PathResolver.API_DELETE }">Supprimer</a>
                </button>
            </article>
        </c:forEach>
    </section>
</div>
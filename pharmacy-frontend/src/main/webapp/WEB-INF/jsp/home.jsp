<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Inclure le header -->
<jsp:include page="header.jsp"/>

<!-- Boutons qui feront apparaître les différentes listes -->
<form method="post">
    Pour la liste de tous les articles
    <button type="submit" class="btn btn-primary btn-lg" name="searchArticles" value="all">Obtenir la liste des éléments</button>
    Pour la liste de tous des articles par nom
    <label for="searchArticles">Trouver des articles par nom</label>
    <input type="search" id="searchArticles" name="searchArticles" aria-label="Search articles">
    <button type="submit" class="btn btn-secondary btn-lg">Rechercher</button>
</form> 

<c:if test="${ requestScope.message }">
    <p class='red'>Aucun résultat pour le nom : ${ requestScope.resultName }.</p>
</c:if>

<c:if test="${ requestScope.articles.size() == 0 }">
    <section class="justPicture">
        <figure>
            <img src="http://localhost:8081/pharmacy-frontend-1.0/View/img/008-online-pharmacy.png" alt="Pharmacy">
        </figure>
    </section>
</c:if>

<!-- Solution différente du display none -->
<c:if test="${ requestScope.articles.size() > 0 }">
    <div id="listArticles">
        <h3>Listes des articles</h3>
        <section class="all">
            <c:forEach items="${requestScope.articles}" var="art">
                <article id="article-${art.getIdArticle()}">
                    <!-- <a href="${pageContext.request.contextPath}/article?id</a> -->
                    <h4 class="name">Nom : ${art.getArticleName()}</h4>
                                    
                    <c:choose>
                        <c:when test="${art.getArticleQuantity() > 0}">
                            <img src="http://localhost:8081/pharmacy-frontend-1.0/View/img/001-available-64.png" alt="Available">
                            <span>En stock</span>
                        </c:when>
                        <c:when test="${art.getArticleQuantity() == 0}">
                            <img src="http://localhost:8081/pharmacy-frontend-1.0/View/img/002-out-of-stock-64.png" alt="Not available">
                            <span>Indisponible</span>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>

                    <form method="post">
                        <!-- Details button -->
                        <button type="submit" formaction="/action_page2.php" class="btn btn-success">
                            <!-- Lien vers l'URL avec l'id -->
                            <!-- De base -->
                            <!-- <a href="${ PathResolver.SRV_PRODUCT_NAME }/${ art.getIdArticle() }">Détails</a> -->
                            <!-- Test -->
                            <!-- <a href="${ PathResolver.APP_CONTEXT }${ PathResolver.APP_PRODUCT }/${ art.getIdArticle() }">Détails</a> -->
                            <a href="http://localhost:8081/pharmacy-frontend-1.0/app/article">Détails</a>
                        </button>
    
                        <!-- Edit button -->
                        <button type="button" class="btn btn-primary">
                            <!-- Lien vers l'URL avec l'id -->
                            <!-- De base -->
                            <!-- <a href="${ PathResolver.API_UPDATE }/${ art.getIdArticle() }">Modifier</a> -->
                            <!-- Test -->
                            <a href="http://localhost:8081/pharmacy-frontend-1.0/api/articles/byid/${art.getIdArticle()}">Modifier</a>
                        </button>
                        
                        <!-- Delete button -->
                        <button type="button" class="btn btn-danger">
                            <!-- Lien vers l'URL avec l'id -->
                            <a href="http://localhost:8081/pharmacy-frontend-1.0/api/articles/delete/${ art.getIdArticle() }">Supprimer</a>
                        </button>
                    </form>
                </article>
            </c:forEach>
        </section>
    </div>

    <!-- Image ajoutée -->
    <section>
        <figure>
            <img src="http://localhost:8081/pharmacy-frontend-1.0/View/img/006-medicine.png" alt="Pharmacy">
        </figure>
    </section>
</c:if>

<!-- Inclure le footer -->
<jsp:include page="footer.jsp"/>
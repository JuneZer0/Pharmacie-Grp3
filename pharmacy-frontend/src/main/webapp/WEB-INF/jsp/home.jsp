<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Attention display none, donc ne s'affichera pas -->
<div id="listAllArticles">
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
                    <a href="${ API_UPDATE }">Modifier</a>
                </button>
                    
                <!-- Delete button -->
                <button type="button" class="btn btn-danger">
                    <a href="${ API_DELETE }">Supprimer</a>
                </button>
            </article>
        </c:forEach>
    </section>
</div>
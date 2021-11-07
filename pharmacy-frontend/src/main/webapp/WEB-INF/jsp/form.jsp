<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


		<header>
			<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
		</header>

		<c:choose>
			<c:when test="${article.getIdArticle() > 0}">
				<h1>Modifier l'article</h1>
			</c:when>		
			<c:otherwise>
				<h1>Ajouter un article</h1>
			</c:otherwise>
		</c:choose>


		<form action="form" method="post">
			<c:if test = "${article.getIdArticle() > 0}">
				<label for="id">Id.</label>
				<input type="text" class="form-control input lg" name="article-id" value="${article.getIdArticle()}"readonly="true"/>
			</c:if>
			<div class="form-group col-sm-6">
				<label for="barcode">Code barre</label>
				<input type="text" required="required" class="form-control input-lg" id="barcode" name="barcode"
					value="${article.getArticleBarcode()}">
			</div>

			<div class="form-group col-sm-6">
				<label for="nom">Nom </label>
				<input type="text" required="required" class="form-control input-lg" id="name" name="name"
					value="${article.getArticleName()}">
			</div>

			<div class="form-group col-sm-6">
				<label for="price">Prix</label>
				<input type="text" required="required" class="form-control input-lg" id="price" name="price"
					value="${article.getArticlePrice()}">
			</div>

			<div class="form-group col-sm-6">
				<label for="quantity">Quantité </label>
				<input type="text" required="required" class="form-control input-lg" id="quantity" name="quantity"
					value="${article.getArticleQuantity()}">
			</div>

			<div class="form-group col-sm-12">
				<input type="submit" class="btn btn-lg btn-success pull-right" name="valider" value="Valider">
			</div>

		</form>

		<!-- Image ajoutée -->
		<section>
			<figure>
				<img src="http://localhost:8081/pharmacy-frontend-1.0/View/img/004-pills.png" alt="Pharmacy">
			</figure>
		</section>

		<%@include file="footer.jsp" %>
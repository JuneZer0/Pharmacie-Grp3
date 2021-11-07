<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


		<header>
			<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
		</header>


		<!-- <% String alertMessage=(String) request.getAttribute("alertMessage"); %> -->

			<h1>Ajouter un article</h1>

			<!-- <% if (alertMessage=="success" ) { %>
				<div class='alert alert-success'>
					Medicament ajouté avec succès!
					<button type='button' class='close' aria-label='Close' data-dismiss='alert'>
						<span aria-hidden='true'>&times;</span>
					</button>
				</div>

			<% } %>

			<% if (alertMessage=="erreur" ) { %>

				<div class='alert alert-danger'>
					Merci de remplir ce formulaire
					<button type='button' class='close' aria-label='Close' data-dismiss='alert'>
						<span aria-hidden='true'>&times; </span>
					</button>
				</div>

			<% } %>
			 -->
		<form action="form" method="post" >
			<div class="form-group col-sm-6">
				<label for="barcode">Code barre : ${art.getArticleBarcode()}</label>
				<input type="text" required="required" class="form-control input-lg" id="barcode"name="barcode">
			</div>

			<div class="form-group col-sm-6">
				<label for="nom">Nom : ${art.articleName}</label>
				<input type="text" required="required" class="form-control input-lg" id="name"name="name" value="">
			</div>

			<div class="form-group col-sm-6">
				<label for="price">Prix : ${art.getArticlePrice()} €</label>
				<input type="text" required="required" class="form-control input-lg" id="price"name="price">
			</div>

			<div class="form-group col-sm-6">
				<label for="quantity">Quantité : ${art.getArticleQuantity()}</label>
				<input type="text" required="required" class="form-control input-lg" id="quantity"name="quantity">
			</div>

			<div class="form-group col-sm-12">
				<input type="submit" class="btn btn-lg btn-default" name="modifier" value="Modifier">

				<input type="submit" class="btn btn-lg btn-success pull-right" name="valider" value="Valider">
			</div>

		</form>

		<!-- Image ajoutée -->
		<section>
			<figure>
				<img src="http://localhost:8081/pharmacy-frontend-1.0/View/img/004-pills.png"alt="Pharmacy">
			</figure>
		</section>

<<<<<<< HEAD
<%@include file="footer.jsp" %>
=======

>>>>>>> develop

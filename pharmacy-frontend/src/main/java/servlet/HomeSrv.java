package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.PathResolver;
import model.Article;

@WebServlet()
public class HomeSrv extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Article> articles = new ArrayList<>();
        String visibility;
        if (articles.size() > 0) {
            visibility = "visible";
        } else {
            visibility = "invisible";
        }
        request.setAttribute("visibility", visibility);
        request.setAttribute("articles", articles);
        this.getServletContext().getRequestDispatcher(PathResolver.APP_HOME).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Article> articles = new ArrayList<>();
        String visibility = "invisible";
        // Récupérer le nom écrit dans le champs texte
        String name = request.getParameter("searchArticles");
        if (name != null) {
                // Placer le nom en attribut pour que la jsp le garde
                request.setAttribute("name", name);
                // Faire la requête getByName(name) à envoyer au back
                        // articles = ...;
                        // PathResolver.API_BYNAME
                // Tester si la liste obtenue est vide
                if (articles.size() == 0) {
                        // Si oui, envoyer "aucun résultat pour tel nom" + rendre invisible la div
                        PrintWriter out = response.getWriter();
                        out.println("<p class='red'>Aucun résultat pour le nom : " + name +".</p>");
                } else {
                        // Sinon envoyer la liste à la jsp + rendre visible la div
                        visibility = "visible";                
                }    
        } else {
                // Faire la requête getAll()) à envoyer au back
                        // articles = ...;
                        // PathResolver.API_GETALL
                // Envoyer la liste à la jsp + rendre visible la div
                visibility = "visible";
        }
        request.setAttribute("visibility", visibility);
        request.setAttribute("articles", articles);
        this.getServletContext().getRequestDispatcher(PathResolver.APP_HOME).forward(request, response);
	}

}

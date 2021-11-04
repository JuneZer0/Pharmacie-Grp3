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
        List<Article> articles;
        // Récuperer le "name en paramètre"
        String name = (String) request.getParameter("name");
        // Récuperer la "list" en attribut
        List<Article> list = (List<Article>) request.getAttribute("list");
        // Tester si un name est présent
        if (name != null) {
                // Un getByName() a été demandé
                request.setAttribute("name", name);
                // Tester si la liste est vide
                if (list.size() > 0) {
                        // La liste contient au moins un résultat
                        articles = list;
                } else {
                        // Pas de résultat
                        articles = new ArrayList<>();
                        // Afficher un message
                        PrintWriter out = response.getWriter();
                        out.println("<p class='red'>Aucun résultat pour le nom : " + name +".</p>");
                }
        } else if (list.size() > 0) {
                // Un getAll() a été demandé
                articles = list;
        } else {
                // Pas de getByName(), ni de getAll()
                articles = new ArrayList<>();
        }
        // Envoyer la liste à la jsp
        request.setAttribute("articles", articles);
        this.getServletContext().getRequestDispatcher(PathResolver.APP_HOME).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Article> articles = new ArrayList<>();
        // Récupérer le nom écrit dans le champs texte
        String name = request.getParameter("searchArticles");
        if (name != null) {
                // Placer le nom en attribut pour que la jsp le garde
                request.setAttribute("name", name);
                // Faire la requête getByName(name) à envoyer au back
                        this.getServletContext().getRequestDispatcher(PathResolver.API_BYNAME + "/" + name).forward(request, response);   
        } else {
                // Faire la requête getAll()) à envoyer au back
                        this.getServletContext().getRequestDispatcher(PathResolver.API_GETALL).forward(request, response);
                // Envoyer la liste à la jsp
        }
        request.setAttribute("articles", articles);
        this.getServletContext().getRequestDispatcher(PathResolver.APP_HOME).forward(request, response);
	}

}

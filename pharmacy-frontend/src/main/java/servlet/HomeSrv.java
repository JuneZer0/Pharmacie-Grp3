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
import javax.servlet.http.HttpSession;

import helpers.PathResolver;
import model.Article;

@WebServlet("/home")
public class HomeSrv extends HttpServlet {
    
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // Tests
                System.out.println("--- Home called ---");
                System.out.println("--- getServletContext() = " + getServletContext());
                System.out.println("--- PathResolver.API_GETALL = " + PathResolver.API_GETALL);
                System.out.println(request.getRequestURL());
                // Créer une session
                HttpSession session = request.getSession(false);      
                // Récuperer la "list" en attribut si elle existe, sinon ca restera une liste vide                        
                List<Article> articles = new ArrayList<>();
                if(session!=null && session.getAttribute("list")!=null){
                        articles = (List<Article>) request.getSession().getAttribute("list");
                        session.invalidate();
                }
                
                // Récuperer le "name en paramètre" s'il existe             
                String name = (String) request.getAttribute("name");
                
                // Tester si un name est présent
                if (name != null) {                        
                        // Tester si la liste est vide
                        if (articles.size() == 0) {                                
                                //pas de resultat
                                PrintWriter out = response.getWriter();
                                out.println("<p class='red'>Aucun résultat pour le nom : " + name +".</p>");
                        }
                }

                // Envoyer la liste à la jsp et appeler la jsp
                request.setAttribute("articles", articles);            
                getServletContext().getRequestDispatcher(PathResolver.JSP_HOME).forward(request, response);
	}

        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                System.out.println("POST METHOD");
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
               
                        
	}

}

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
                
                System.out.println("--- Home called ---");
                System.out.println(request.getRequestURL());
                HttpSession session = request.getSession(false);      
                // Récuperer la "list" en attribut si elle existe, sinon ca restera une liste vide                        
                List<Article> articles = new ArrayList<>();
                if(session!=null && session.getAttribute("list")!=null){
                        articles = (List<Article>) request.getSession().getAttribute("list");
                }
                
                // Récuperer le "name en paramètre" s'il existe             
                String name = (String) request.getAttribute("name");
                
                // Tester si un name est présent
                if (name != null) {                        
                        // Tester si la liste est vide
                        if (articles.size() <= 0) {                                
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
                System.out.println(request.getRequestURL());
                String name = request.getParameter("button");
                String providedName = request.getParameter("providedname");
                getServletContext().getRequestDispatcher(PathResolver.API_GETALL).forward(request, response);
               
                        
	}

}

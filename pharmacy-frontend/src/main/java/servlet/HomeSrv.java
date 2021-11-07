package servlet;

import java.io.IOException;
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
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
               // Tests
                System.out.println("--- Home called ---");
                System.out.println(request.getRequestURL());
                // Créer une session
                HttpSession session = request.getSession(false);
                
                // Instancier un bouléen pour l'affichage
                Boolean message = false;

                String name = "";
                // Récuperer la "list" en attribut si elle existe, sinon ca restera une liste
                // nulle
                List<Article> articles = new ArrayList<>();

                //Array list parce qu'une list ne peut jamais être nulle/instanciée : 
                ArrayList<Article> listFromSession = null;
                if (session!=null) { 
                        System.out.println("Session attribute names :"+session.getAttributeNames().toString());
                        name = (String) session.getAttribute("name");                        
                        if(session.getAttribute("list")!=null){
                                listFromSession = (ArrayList<Article>) session.getAttribute("list");                         
                                System.out.println("List size : "+listFromSession.size());
                        }       
                        session.invalidate();                       
                        System.out.println("Invalidated session");
                       
                }

                //Si la liste récupérée n'est pas nulle, la liste des articles prend sa valeur :
                if(listFromSession!= null){
                        System.out.println("List size : "+listFromSession.size());
                        articles = listFromSession;
                        System.out.println(articles.toString());
                }

                // Récuperer le "name en paramètre" s'il existe

                // Tester si un name est présent
                if (name != null) {
                        System.out.println("Requested name : "+name);
                        // Tester si la liste est vide
                        if (articles.size() == 0) {
                                // pas de resultat
                                System.out.println("list is empty");
                                // PrintWriter out = response.getWriter();
                                // out.println("<p class='red'>Aucun résultat pour le nom : " + name + ".</p>");
                                message = true;
                                request.setAttribute("resultName", name);
                        }
                }
                // Envoyer le bouléen pour l'affichage
                request.setAttribute("message", message);
                // Envoyer la liste à la jsp et appeler la jsp
                request.setAttribute("articles", articles);
                System.out.println("--- calling jsp home ");
                getServletContext().getRequestDispatcher(PathResolver.JSP_HOME).forward(request, response);
        }

       @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                System.out.println("POST METHOD");
                // Récupérer le nom écrit dans le champs texte
                String name = request.getParameter("searchArticles");
                System.out.println("--- name : " + name);
                HttpSession session = request.getSession(true);

                if (name != null && !name.trim().isEmpty()) {
                        switch (name) {                   
                                String destination;
                                Long id = Long.parseLong(request.getParameter("id"));

                                case "all":
                                        // Faire la requête getAll()) à envoyer au back
                                        System.out.println("--- sending post request from home to api");
                                        response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.API_GETALL);
                                        break;
                        
                                case "detail":
                                        // Faire la requête getById
                                        System.out.println("--- sending post request detail from home to api");
                                        destination = PathResolver.APP_CONTEXT + PathResolver.SRV_PRODUCT_NAME;
                                        session.setAttribute("destination", destination);
                                        response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.API_BYID + "/" + id);
                                        break;

                                case "edit":
                                        // Faire la requête getById
                                        System.out.println("--- sending post request edit from home to api");
                                        destination = PathResolver.APP_CONTEXT + PathResolver.SRV_FORM_NAME;
                                        session.setAttribute("destination", destination);
                                        response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.API_BYID + "/" + id);
                                        break;
                
                                case "delete":
                                        // Faire la requête delete
                                        System.out.println("--- sending post request delete from home to api");
                                        List<Article> list = request.getAttribute("articles");
                                        session.setAttribute("list", list);
                                        response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.API_DELETE + "/" + id);
                                        break;                        

                                default : 
                                        // Faire la requête getByName(name) à envoyer au back
                                        System.out.println("--- name stored in session , redirecting to api");
                                        session.setAttribute("name", name);
                                        response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.API_BYNAME + "/" + name);
                                        break;  
                        }
                }
                
                else {
                        System.out.println("empty name, reloading page"); 
                        response.sendRedirect(PathResolver.APP_CONTEXT+PathResolver.APP_HOME);
                }

        }

}

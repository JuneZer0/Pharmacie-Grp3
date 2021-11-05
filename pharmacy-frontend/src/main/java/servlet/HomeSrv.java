package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
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
                String name = "";
                // Récuperer la "list" en attribut si elle existe, sinon ca restera une liste
                // nulle
                List<Article> articles = new ArrayList<>();

                //Array list parce qu'une list ne peut jamais être nulle/instanciée : 
                ArrayList<Article> listFromSession = null;

                if (session != null) { 
                        name = (String) session.getAttribute("name");
                        listFromSession = (ArrayList<Article>) session.getAttribute("list");
                        session.invalidate();
                        System.out.println("Invalidated session");
                }

                //Si la liste récupérée n'est pas nulle, la liste des articles prend sa valeur :
                if(listFromSession!= null){
                        articles = listFromSession;
                        System.out.println(listFromSession.toString());
                }

                // Récuperer le "name en paramètre" s'il existe

                // Tester si un name est présent
                if (name != null) {
                        System.out.println("Requested name : "+name);
                        // Tester si la liste est vide
                        if (articles.size() == 0) {
                                // pas de resultat
                                System.out.println("list is empty");
                                PrintWriter out = response.getWriter();
                                out.println("<p class='red'>Aucun résultat pour le nom : " + name + ".</p>");
                        }
                }

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
                        switch (name){                     

                       case "all":
                                // Faire la requête getAll()) à envoyer au back
                                System.out.println("--- sending post request from home to api");
                                response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.API_GETALL);
                                break;

                       default : // Faire la requête getByName(name) à envoyer au back
                                System.out.println("--- name stored in session , redirecting to api");
                                session.setAttribute("name", name);
                                response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.API_BYNAME + "/" + name);
                                break;      

                         //NOT WORKING MEME EN ETANT MIS AVANT DEFAUT. A voir s'il faut en faire des forms.       
                        /*case "delete":
                                System.out.println("name");
                                session.setAttribute("list", request.getAttribute("list"));
                                response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.API_DELETE + "/" + name);
                                break;

                        case "edit": 
                                System.out.println("edit button pressed");
                                
                                break;*/                                       
                }
        }
                //TODO : verifier (dans la jsp ? )qiue le nom rentré dans byname n'est pas vide sinon il recharge juste la page
                //TODO : quand on clique sur un des boutons de l'article
                //TODO : quand un nom n'est pas trouvé le message ne s'affiche pas. 
                else {
                        System.out.println("empty name, reloading page");
                        session.setAttribute("name", "");                        
                        response.sendRedirect(PathResolver.APP_CONTEXT+PathResolver.APP_HOME);
                }

        

}

}

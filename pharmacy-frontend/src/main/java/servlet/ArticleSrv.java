 package servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helpers.PathResolver;
import model.Article;

@WebServlet(PathResolver.SRV_PRODUCT_NAME)
public class ArticleSrv extends HttpServlet {
   
    private Article art = new Article();

    public ArticleSrv() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("--- ARTICLE PAGE---");
        ServletContext sc = this.getServletContext();
        HttpSession session = req.getSession(true);
        Article art = new Article();
    if(session.getAttribute("article")!=null){
        System.out.println(session.getAttribute("article").toString());
        art = (Article) session.getAttribute("article");   
        session.invalidate();   
    } else {
        System.out.println("article servlet called");   
            art.setIdArticle((long) 0);     
            art.setArticleName("indisponible");
            art.setArticleBarcode((long) 0);
            art.setArticlePrice(0.00);
            art.setArticleQuantity(0);
           System.out.println("article passé");        
      }
        req.setAttribute("article", art);
        System.out.println(art.getArticleName());
        RequestDispatcher rd = sc.getRequestDispatcher(PathResolver.JSP_PRODUCT);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                System.out.println("DETAIL PAGE POST METHOD");
                String destination = PathResolver.APP_HOME+PathResolver.APP_FORM;
                HttpSession session = request.getSession(true);
                if (request.getParameter("edit")!= null && !request.getParameter("edit").isEmpty()) {
                    // Faire la requête getById
                    System.out.println("--- sending post request edit from home to api");
                    destination = PathResolver.APP_CONTEXT + PathResolver.APP_FORM;
                    session.setAttribute("destination", destination);
                    Long id = Long.parseLong(request.getParameter("edit"));
                    response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.API_BYID + "/" + id);
            }

            else if (request.getParameter("delete") != null && !request.getParameter("delete").isEmpty()) {
                    // Faire la requête delete
                    System.out.println("--- sending post request delete from home to api");
                    Long id = Long.parseLong(request.getParameter("delete"));
                    response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.API_DELETE + "/" + id);
            }

        }            
    
}

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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ServletContext sc = this.getServletContext();
        HttpSession session = req.getSession(true);
    if(session.getAttribute("article")!=null){
        art = (Article) session.getAttribute("article");   
        session.invalidate();   
    } else {
        System.out.println("article servlet called");        
            art.setArticleName("indisponible");
            art.setArticleBarcode((long) 0);
            art.setArticlePrice(0.00);
            art.setArticleQuantity(0);
           System.out.println("article passé");        
      }
        req.setAttribute("article", art);
        RequestDispatcher rd = sc.getRequestDispatcher(PathResolver.JSP_PRODUCT);
        rd.forward(req, resp);
    }

    
}

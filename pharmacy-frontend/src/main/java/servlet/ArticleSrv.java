 package servlet;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        System.out.println("article servlet called");
        
            art.setArticleName("doliprane");
            art.setArticleBarcode((long) 1543);
            art.setArticlePrice(2.5);
            art.setArticleQuantity(23);
        System.out.println("article passé");
        
        req.setAttribute("art", art);

        RequestDispatcher rd = sc.getRequestDispatcher(PathResolver.JSP_PRODUCT);
        rd.forward(req, resp);
    }

    
}

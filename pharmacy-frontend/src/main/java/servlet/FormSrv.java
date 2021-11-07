package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import helpers.PathResolver;
import model.Article;

@WebServlet("/form")
public class FormSrv extends HttpServlet {

    private Article article = new Article();

    public FormSrv() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--- calling FormSrv doGet method");
        Boolean isNull = true;
        HttpSession session = req.getSession(true);
        if (session.getAttribute("article") != null) {
            isNull = false;
            article = (Article) session.getAttribute("article");
            req.setAttribute("article", article);
            System.out.println("form servlet called");           
        }
        session.invalidate();        
        System.out.println("Invalidated session");
        RequestDispatcher rd = req.getRequestDispatcher(PathResolver.JSP_FORM);
        rd.forward(req, resp);
    }

    // Récupérer l'article dans le champs texte
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération des paramètres
        Long idArticle = Long.parseLong(request.getParameter("article-id"));
        Long barcode = Long.parseLong(request.getParameter("barcode"));
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));
        String alertMessage = "erreur";
        if (request.getParameter("valider") != null) {

            if (barcode == null || (name == null) || price == null || quantity == null) {
                request.setAttribute("alertMessage", alertMessage);
                doGet(request, response);
            } else {
                alertMessage = "success";

                // le contrôleur crée un objet de type article qui correspond au modèle

                Article article = new Article(barcode, name, price, quantity);
                if(idArticle!=null){
                    article.setIdArticle(idArticle);
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("alertMessage", alertMessage);
                System.out.println("ATTRIBUTE : " + request.getAttribute("alertMessage"));
                session.setAttribute("article", article);
                // transférer le traitement à la vue
                response.sendRedirect(PathResolver.APP_CONTEXT+PathResolver.API_CREATE);
            }
        }    

    }

}
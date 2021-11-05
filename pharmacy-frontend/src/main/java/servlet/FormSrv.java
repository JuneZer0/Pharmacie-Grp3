package servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import helpers.PathResolver;


@WebServlet("/form")
public class FormSrv  extends HttpServlet {

    public FormSrv(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ServletContext sc = this.getServletContext();
        System.out.println("form servlet called");
        RequestDispatcher rd = sc.getRequestDispatcher(PathResolver.JSP_FORM);
    }
        // Récupérer l'article dans le champs texte
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        //Récupération des paramètres  
		String barcode = request.getParameter("barcode");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String quantity = request.getParameter("quantity");
		String alertMessage = "erreur";
		if (request.getParameter("ajouter") != null) {

			if(barcode == "" || name == "" || price == "" || quantity == "") {
				
				request.setAttribute("alertMessage", alertMessage);
				doGet(request, response);
			}
			else {
				alertMessage = "success";
                
                //le contrôleur crée un objet de type article qui correspond au modèle

                Article article = new Article(barcode, name, price , quantity);

                //le contrôleur enregistre cet objet comme attribut de requête pour le rendre disponible à la vue.
                request.setAttribute("article ", article );
               
				request.setAttribute("alertMessage", alertMessage);
                
                //transférer le traitement à la vue
                RequestDispatcher rd = sc.getRequestDispatcher(PathResolver.JSP_FORM);
                rd.forward(request, response);
				
			}
			
		}


		if (request.getParameter("modifier") != null) {
			
		}

	}

}


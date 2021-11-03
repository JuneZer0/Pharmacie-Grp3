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

@WebServlet("/footer")
public class FooterSrv extends HttpServlet {

    public FooterSrv(){
        super();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ServletContext sc = this.getServletContext();
        System.out.println("servlet called");
        RequestDispatcher rd = sc.getRequestDispatcher(PathResolver.JSP_FOOTER);
        rd.forward(req, resp);
    }
    
}



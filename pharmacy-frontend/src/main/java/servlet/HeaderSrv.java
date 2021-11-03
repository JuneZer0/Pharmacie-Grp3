package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.PathResolver;

@WebServlet(name="header", urlPatterns = "headertest")
public class HeaderSrv extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dsp = req.getRequestDispatcher(PathResolver.JSP_MENU);
        dsp.forward(req, resp);
    }
    
}

package controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * This servlet acts as a dispatcher. It captures all roots and forwards requests to the desired servlets. 
 */
@WebServlet(name = "dispatcher", displayName = "/app/dispatcher", urlPatterns = "/*")
public class FrontController extends HttpServlet {
       
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    
    



      

}
    



}

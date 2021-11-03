package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.CustomDispatcher;

public class FrontController extends HttpServlet {
    CustomDispatcher dispatcher = new CustomDispatcher();
    
  
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    String pathToForward = request.getRequestURI();
   
    //SECURITY STUFF HERE

    String target = dispatcher.callServlet(pathToForward);
    RequestDispatcher forwarder = request.getRequestDispatcher(target);
    forwarder.forward(request, response);
           
   }


   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response){


   }

   

}
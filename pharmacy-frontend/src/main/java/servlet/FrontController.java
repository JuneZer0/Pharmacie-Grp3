package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.CustomDispatcher;
import helpers.PathResolver;

public class FrontController  {
    CustomDispatcher dispatcher = new CustomDispatcher();
    
  /*
   public void doGet(HttpServletRequest request, HttpServletResponse response){

    String pathToForward = request.getRequestURI();

    String identifiedCall=dispatcher.identifyCall(pathToForward);

    switch (identifiedCall){
        case PathResolver.APP_BASE :

        case PathResolver.API_BASE :
    }*/

       
   }


   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response){


   }

   

}
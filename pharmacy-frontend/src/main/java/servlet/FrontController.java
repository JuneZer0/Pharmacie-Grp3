package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.resteasy.spi.HttpResponse;

import helpers.CustomDispatcher;
import helpers.PathResolver;

@WebServlet("/*")
public class FrontController extends HttpServlet {

    CustomDispatcher dispatcher = new CustomDispatcher();
    
  
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    //GET REQUEST URL
    String pathSentByServlet = request.getRequestURI();
   
    //SECURITY STUFF HERE
 
    //CHECK IF IT IS AN APP CALL OR AN API CALLString target = dispatcher.identifyCall(pathSentByServlet);
    String target = dispatcher.identifyCall(pathSentByServlet);
    if(target.equals(PathResolver.API_BASE)){
        
        String apiRequest = dispatcher.convertToRequest(pathSentByServlet);
            
        // CALL THE API
        HttpResponse apiResponse = dispatcher.manageAPI(apiRequest);
        // IF METHOD WAS GET ALL OR GET ALL BY NAME FORWARD TO SERVLET WITH A LIST OBJECT
        // IF METHOD WAS DELETE AND EMITTER SERVLET WAS THE LIST, SEND RESPONSE OK
        // IF METHOD WAS DELETE AND EMITTER SERVLET WAS PRODUCT,
        // OR IF METHOD WAS UPDATE OR CREATE => FORWARD TO HOME IF SUCCESS
        // 
          

        //FORWARD TO SERVLET

<<<<<<< HEAD
    } else {
=======
       
   
>>>>>>> develop

        //FORWARD TO PRODUCT WITH AN OBJECT
        //FORWARD TO HOME
        //FORWARD TO FORM WITHOUT OBJECT
        //FORWARD TO FORM WITH OBJECT
    }
            
    }
  

   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response){


   }

   
 public HttpResponse manageAPi(String request){
     return null;
 }
}
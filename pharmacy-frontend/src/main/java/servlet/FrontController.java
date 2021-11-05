package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.resteasy.spi.HttpResponse;

import helpers.CustomDispatcher;
import helpers.PathResolver;

@WebServlet(name="frontcontroller", urlPatterns = {"/api/*", PathResolver.APP_BASE+"/*"})
public class FrontController extends HttpServlet {

    CustomDispatcher customDispatcher = new CustomDispatcher();
    
  
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    System.out.println("FRONT CONTROLLER READY FOR DISPATCH");
    //GET REQUEST URL
    String pathSentByServlet = request.getRequestURI();
    System.out.println("PATH SENT BY SERVLET :"+pathSentByServlet);
    //SECURITY STUFF HERE
 
    //CHECK IF IT IS AN APP CALL OR AN API CALLString target = dispatcher.identifyCall(pathSentByServlet);
    String target = customDispatcher.identifyCall(pathSentByServlet);
    System.out.println("TARGET :"+target);


    if(target.equals(PathResolver.API_ARTICLE_BASE)){

        System.out.println("Entered if : target equals API_BASE");
        Map<String, String> apiRequest = new HashMap<>();
        
        try {
            apiRequest = customDispatcher.convertToRequest(pathSentByServlet);
            System.out.println("API REQUEST CONVERTED TO : "+apiRequest.get("path")+"METHOD: "+apiRequest.get("method"));
            
            // CALL THE API
            System.out.println("Calling api manager");
            customDispatcher.manageAPI(apiRequest, request, response);                    

        } catch (Exception e) {            
            e.printStackTrace();
        }
          
    }
    
    //delete after test
    else if(target.equals("WEB-INF")){
    request.getRequestDispatcher(pathSentByServlet).forward(request,response);}    

 
    else{
    System.out.println("FORWARDING TO SERVLET");
    customDispatcher.callServlet(pathSentByServlet, request, response);
    }


        //FORWARD TO PRODUCT WITH AN OBJECT

        //FORWARD TO HOME


        //FORWARD TO FORM WITHOUT OBJECT

        //FORWARD TO FORM WITH OBJECT*/
    }
            
    
  

   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response){


   }

   
 public HttpResponse manageAPi(String request){
     return null;
 }
}
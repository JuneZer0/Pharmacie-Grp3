package helpers;

import model.Article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;


import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;



/**
 * This class acts as a controller for backend calls.
 * Consumes backend api.
 */
public class ApiManager {
    ResteasyClient apiClient;

    public ApiManager(){
       apiClient = (ResteasyClient) ResteasyClientBuilder.newBuilder().build();
    }

    /**
     * Get all articles from the backend, returned as a list
     * @param request request passed by the calling servlet
     * @param response response passed by the calling servlet
     * @return a list of Article objects 
     * @throws IOException
     * @throws ServletException
     */
    public void getArticles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{  
      System.out.println("Process with getting articles...");
      
      //Create the list to send as a result
      List<Article>resultList = new ArrayList<>();  
      //Call the backend
      ResteasyWebTarget target = apiClient.target(PathResolver.API_TARGET_LIST);
      Response apiResponse = target.request().get();
      if(apiResponse.getStatus() == 441){
        System.out.println("auth required");
      }
      System.out.println("api response obtained");
      //Converts response to a list
      if(apiResponse.hasEntity()){
      String responseValue = apiResponse.readEntity(String.class);      
      ObjectMapper mapper = new ObjectMapper();
      resultList = mapper.readValue(responseValue, new TypeReference<List<Article>>(){});
      System.out.println(resultList);
      for(Article a: resultList){
        System.out.println("article : "+a.getArticleName());
      }
    }
      apiResponse.close();
      apiClient.close();

      //Puts list in session attribute.
      HttpSession session = request.getSession(true);
      session.setAttribute("list", resultList);       
      response.sendRedirect(PathResolver.APP_CONTEXT+PathResolver.APP_HOME);
    }

    
    public void getArticlesByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String param = request.getParameter("byname");
        //Create the list to send as a result
        List<Article>resultList = new ArrayList<>();  
        //Call the backend
        ResteasyWebTarget target = apiClient.target(PathResolver.API_TARGET_BYNAME+"/"+param);
        Response apiResponse = target.request().get();
        if(apiResponse.getStatus() == 441){
          System.out.println("auth required");
        }
        System.out.println("api response obtained");
        //Converts response to a list
        if(apiResponse.hasEntity()){
        String responseValue = apiResponse.readEntity(String.class);      
        ObjectMapper mapper = new ObjectMapper();
        resultList = mapper.readValue(responseValue, new TypeReference<List<Article>>(){});
        for(Article a: resultList){
          System.out.println("article : "+a.getArticleName());
        }
      }
        apiResponse.close();
        apiClient.close();
  
        //Puts list in session attribute.
        HttpSession session = request.getSession(true);
        session.setAttribute("list", resultList);   
        session.setAttribute("name", param);    
        response.sendRedirect(PathResolver.APP_CONTEXT+PathResolver.APP_HOME);
      
      
    }

    public void deleteArticle(HttpServletRequest request, HttpServletResponse response) {
      
    }

    public void addArticles(HttpServletRequest request, HttpServletResponse response) {
      //Call the backend
      String param = (String) request.getAttribute("id");
      ResteasyWebTarget target = apiClient.target(PathResolver.API_TARGET_DELETE+"/"+param);
      Response apiResponse = target.request().get();
      if(apiResponse.getStatus() == 441){
        System.out.println("auth required");
      }
    }

    
    
}

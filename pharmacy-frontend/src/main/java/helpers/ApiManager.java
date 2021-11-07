package helpers;

import model.Article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 * This class acts as a controller for backend calls. Consumes backend api.
 */
public class ApiManager {
  private ResteasyClient apiClient;
  private static final Logger LOGGER = Logger.getLogger("ApiManager");

  public ApiManager() {
    apiClient = (ResteasyClient) ResteasyClientBuilder.newBuilder().build();

  }

  /**
   * Get all articles from the backend, returned as a list
   * 
   * @param request  request passed by the calling servlet
   * @param response response passed by the calling servlet
   * @return a list of Article objects
   * @throws IOException
   * @throws ServletException
   */
  public void getArticles(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    LOGGER.info("--- ENTERED METHOD : ApiManager.getAllArticles()");
    List<Article> resultList = new ArrayList<>();
    LOGGER.info("--- created list containing result ...");
    LOGGER.info("--- Calling backend api ...");
    ResteasyWebTarget target = apiClient.target(PathResolver.API_TARGET_LIST);

    resultList = processListRequest(resultList, target);

    LOGGER.info("--- SESSION : invalidating existing ...");
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    LOGGER.info("--- SESSION : initializing new session...");
    session = request.getSession(true);
    LOGGER.info("SESSION : passing attributes to new session...");
    session.setAttribute("list", resultList);

    LOGGER.info("REDIRECT : send redirect to " + PathResolver.APP_CONTEXT + PathResolver.APP_HOME);
    response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.APP_HOME);
  }

  /**
   * Retrieves a list of articles by their name
   * 
   * @param path
   * @param request  calling servlet request
   * @param response calling servlet response
   * @throws IOException
   */
  public void getArticlesByName(String path, HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    LOGGER.info("--- ENTERED METHOD : GET ALL BY NAME. (Provided path : " + path + ")");

    List<Article> resultList = new ArrayList<>();
    LOGGER.info("--- Created empty list that will contain results...");
    LOGGER.info("--- Calling backend api ...");
    ResteasyWebTarget target = apiClient.target(path);

    resultList = processListRequest(resultList, target);
    LOGGER.info("RESULT LIST SIZE : " + resultList.size());
    // Puts list and searched name in session attribute.

    HttpSession session = request.getSession(false);
    String param = null;
    if (session != null) {
      param = (String) session.getAttribute("name");
      session.invalidate();
    }
    session = request.getSession(true);
    session.setAttribute("list", resultList);
    LOGGER.info("ATTRIBUTE LIST : " + session.getAttribute("list"));
    if (param != null && !param.trim().isEmpty()) {
      session.setAttribute("name", param);
    }

    response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.APP_HOME);

  }

  /**
   * 
   * @param path
   * @param request
   * @param response
   * @throws Exception
   */
  public void getArticleById(String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
    LOGGER.info("--- ENTERED METHOD : GET BY ID. (Provided path : " + path + ")");
    LOGGER.info("--- Calling backend api ...");
    ResteasyWebTarget target = apiClient.target(path);
    Response apiResponse = target.request().get();

    if (apiResponse.getStatus() == 441) {
      LOGGER.warning("UNAUTHAURIZED REQUEST.");
    } else {

      LOGGER.info("--- Decoding response entity...");

      if (apiResponse.hasEntity()) {
        String responseValue = apiResponse.readEntity(String.class);
        LOGGER.info("--- Stringified response entity : " + responseValue);
        LOGGER.info("--- Mapping to object ...");
        ObjectMapper mapper = new ObjectMapper();
        Article article = mapper.readValue(responseValue, new TypeReference<Article>() {
        });
        LOGGER.info(
            "--- Object created. Article name : " + article.getArticleName() + ". Closing client and response...");
        apiResponse.close();
        apiClient.close();

        LOGGER.info("--- SESSION : get existing session ...");
        HttpSession session = request.getSession(false);
        String destination = "";

        if (session != null) {
          LOGGER.info("--- Session existing. Retrieving destination attribute.");
          destination = (String) session.getAttribute("destination");
          session.invalidate();
          LOGGER.info("--- Session invalidated. Creating new session");
        }

        session = request.getSession(true);
        LOGGER.info("--- Setting destination into session attribute : " + destination);
        session.setAttribute("article", article);

        LOGGER.info("--- Redirecting to destination...");
      
        LOGGER.info("--- Calling Form servlet... ");
          response.sendRedirect(PathResolver.APP_CONTEXT+PathResolver.APP_PRODUCT);
      
      }
    }
  }

  public void deleteArticle(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    ResteasyWebTarget target = apiClient.target(PathResolver.API_TARGET_DELETE + "/" + id);
    int apiResponse = target.request().delete().getStatus();
    if (apiResponse == 441) {
      System.out.println("auth required");
    }
    HttpSession session = request.getSession(false);
    if (session != null) {
      List<Article> list = (List<Article>) session.getAttribute("list");
      if (list != null) {
        for (Article art : list) {
          if (art.getIdArticle() == Long.parseLong(id)) {
            list.remove(art);
            session.removeAttribute("list");
            session.setAttribute("list", list);
            break;
          }
        }
      }
    }
    apiClient.close();
    response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.APP_HOME);
  }

  /**
   * Send articles to the database to add or update them.
   * 
   * @param request
   * @param response
   * @throws Exception
   */

  public void addArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpSession session = request.getSession(false);
    if (session == null) {
      throw new Exception("No object stored in session");
    }
    Article article = (Article) session.getAttribute("article");
    if(article.getIdArticle()!=null){
    LOGGER.info("ARTICLE ID : "+article.getIdArticle());
    } else {LOGGER.info("NO ARTICLE ID");}
    ResteasyWebTarget target = apiClient.target(PathResolver.API_TARGET_CREATE);
    Response apiResponse = target.request().post(Entity.entity(article, MediaType.APPLICATION_JSON));
    session.invalidate();
    if (apiResponse.getStatus() == 200 || apiResponse.getStatus() == 201) {
      apiClient.close();
      apiResponse.close();
      response.sendRedirect(PathResolver.APP_CONTEXT + PathResolver.APP_HOME);
    }
    LOGGER.info("something went wrong : "+apiResponse.getStatus());
    apiClient.close();
    // apiResponse.close();*/
  }

  /**
   * Refactorisation of duplicate code in both getall and allbyname methods
   * 
   * @param resultList
   * @param target
   * @return
   * @throws JsonProcessingException
   * @throws JsonMappingException
   */
  private List<Article> processListRequest(List<Article> resultList, ResteasyWebTarget target)
      throws JsonMappingException, JsonProcessingException {
    LOGGER.info("--- PROCESSING RESULT... ");

    Response apiResponse = target.request().get();
    if (apiResponse.getStatus() == 441) {
      LOGGER.info("Authentication issue");
    }
    System.out.println("api response obtained");
    // Converts response to a list
    if (apiResponse.hasEntity()) {
      String responseValue = apiResponse.readEntity(String.class);
      ObjectMapper mapper = new ObjectMapper();
      resultList = mapper.readValue(responseValue, new TypeReference<List<Article>>() {
      });
      for (Article a : resultList) {
        System.out.println("Added article : " + a.getArticleName());
      }
    }
    apiResponse.close();
    apiClient.close();
    return resultList;
  }

}

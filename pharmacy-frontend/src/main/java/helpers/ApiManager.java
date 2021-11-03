package helpers;

import javax.ws.rs.core.Response;

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

    public void getArticles(){      
      ResteasyWebTarget target = apiClient.target(PathResolver.API_TARGET_LIST);
      Response response = target.request().get();
      String value = response.readEntity(String.class);     
      System.out.println(value);    
    }
    
}

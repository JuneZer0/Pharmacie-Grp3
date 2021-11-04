package helpers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.resteasy.spi.HttpResponse;

public class CustomDispatcher {


    public CustomDispatcher(){}



    /**
     * Identifies if the calls is destined to the app or the api 
     * @param urlToIdentify the request uri
     * @return if it is for app or api
     */
    public String identifyCall(final String urlToIdentify){
        System.out.println("IDENTIFY CALL : Url to identify :"+urlToIdentify);
        String[] parsedUrl = urlToIdentify.split("/");
        String identifiedCall ="";
        if(parsedUrl.length>=2){  
            System.out.println("ISOLATED STRING :"+parsedUrl[2]);          
            switch(parsedUrl[2]){
                
                case "api" : identifiedCall = PathResolver.API_BASE;
                             break;

                case "app" : identifiedCall = PathResolver.APP_BASE;
                             break;
                case "WEB-INF": identifiedCall= "WEB-INF";
                break;                    
            }
        }
        System.out.println("IDENTIFIED CALL PROCESSED ="+identifiedCall);
        return identifiedCall;
    }



    /**
     * Forward request to corresponding servlet
     * @throws IOException
     * @throws ServletException
     */
    public void callServlet(final String url,
                                final HttpServletRequest req,
                                final HttpServletResponse rsp) 
                                throws ServletException, IOException{
     String[]parsedUrl= url.split("/");
     String servletname = parsedUrl[3];

      RequestDispatcher dsp = req.getRequestDispatcher("/"+servletname);
      dsp.forward(req, rsp);

    }


    
    public String callJsp(final String url){
        return "";
    }


    /**
     * Convert the local url to an url for the api call
     * @param pathSentByServlet
     * @return an url to the controller
     */
    public String convertToRequest(final String pathSentByServlet) {
        String apiRequest = PathResolver.API_PORT+PathResolver.API_BASE;
        String urlParameters = getParameters(pathSentByServlet);
        return apiRequest+"/"+urlParameters;
    }



    private String getParameters(String pathSentByServlet) {
        String[]urlPieces = pathSentByServlet.split("/");
        String parameters ="";
        if(urlPieces.length>3){
            for(int i=4; i<urlPieces.length; i++)
            {
                parameters=parameters+urlPieces[i];
            }
        }
        return parameters;
        
    }


//TODO: remove
    public void manageAPI(String apiRequest, HttpServletRequest request, HttpServletResponse response) {
        
        ApiManager apiManager = new ApiManager();
        apiManager.getArticles();
        
    }
    
}
;
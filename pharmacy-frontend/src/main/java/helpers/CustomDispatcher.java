package helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.impl.io.IdentityInputStream;
import org.jboss.resteasy.spi.HttpResponse;

public class CustomDispatcher {


    public CustomDispatcher(){}



    /**
     * Identifies if the calls is destined to the app or the api 
     * @param urlToIdentify the request uri
     * @return if it is for app or api
     */
    public String identifyCall(final String urlToIdentify){
        String[] parsedUrl = urlToIdentify.split("/");
        String identifiedCall ="";
        if(parsedUrl.length>=2){            
            switch(parsedUrl[2]){
                case "api" : identifiedCall = PathResolver.API_BASE;
                             break;

                case "app" : identifiedCall = PathResolver.APP_BASE;
                default: break;                    
            }
        }
        return identifiedCall;
    }



    /**
     * Forward request to corresponding servlet
     */
    public String callServlet(final String url,
                                final HttpServletRequest req,
                                final HttpServletResponse rsp){

        return "";

    }

    public String callJsp(final String url){
        return "";
    }


    public String sendRequest (final String url){
        return "";
    }

    public HttpResponse manageAPI(final String Request){
        return null;
    }



    public String convertToRequest(final String pathSentByServlet) {
        return null;
    }
    
}
;
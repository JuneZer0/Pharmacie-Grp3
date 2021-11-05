package helpers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;


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
                
                case "api" : identifiedCall = PathResolver.API_ARTICLE_BASE;
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
     System.out.println("Servlet name :"+servletname);
     if(servletname == "home" && req.getAttribute("name")==null){
         req.setAttribute("name", "");
     }

      RequestDispatcher dsp = req.getRequestDispatcher("/"+servletname);
      dsp.forward(req, rsp);

    }


    
    public void callJsp(final String url, final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
        String[] target = url.split("/");
        String goTO ="";
        for(int i = 1; i<target.length; i++){
         goTO = goTO+target[i];
        }
        RequestDispatcher dsp = request.getRequestDispatcher(goTO);
        dsp.forward(request,response);


    }


    /**
     * Convert the local api url (/app/obj/method/params) to an url for the api call (http://localhost:port/obj/)
     * Works only for article. If other tables are to be added, that method will have to be updated as well
     * as pathresolver.
     * @param pathSentByServlet
     * @return a map with the servlet and the 
     * @throws Exception
     */
    public Map<String,String> convertToRequest(final String pathSentByServlet) throws Exception {
        System.out.println("Converting...");

        String[]parsedUrl = pathSentByServlet.split("/");  
              
        if(parsedUrl.length<3){
            throw new Exception("You made an API call but specified no method");
        }

       //get method
       String method = parsedUrl[4];
       System.out.println("method :"+method);
       HashMap<String, String> convertedRequest = new HashMap<>();

       //according to the method, convert the request to appropriate map
       switch(method){
           case PathResolver.MTHD_CREATE :
                convertedRequest.put("method", "POST");
                convertedRequest.put("path", PathResolver.API_TARGET_CREATE);
                break;
           case PathResolver.MTHD_UPDATE :
                convertedRequest.put("method", "PUT");
                convertedRequest.put("path", PathResolver.API_TARGET_UPDATE);
                break;
           case PathResolver.MTHD_LIST : 
                convertedRequest.put("method", "GET");
                convertedRequest.put("path", PathResolver.API_TARGET_LIST);
                break;
           case PathResolver.MTHD_BYNAME :
                if(parsedUrl.length<4){
                    throw new Exception("You made a query for list by name but no name has been provided");
                }   
                convertedRequest.put("method", "GET");
                convertedRequest.put("path", PathResolver.API_TARGET_BYNAME+"/"+parsedUrl[4]);
                break;
            case PathResolver.MTHD_DELETE :
                if(parsedUrl.length<4){
                    throw new Exception("You made a delete request but no id has been specified");
                }
                convertedRequest.put("method", "DELETE");
                convertedRequest.put("path", PathResolver.API_TARGET_DELETE+"/"+parsedUrl[4]);
                break;
            //should never happen since method has already been checked.
            default : throw new Exception("Malformed Request. No method specified.");  
       }                   
                System.out.println("Converted method :"+convertedRequest.get("method"));
                System.out.println("Converted url :"+convertedRequest.get("path"));
                return convertedRequest;
    }
   

    public void manageAPI(Map<String, String> apiRequest, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ApiManager apiManager = new ApiManager();
        String method = apiRequest.get("method");
        String path = apiRequest.get("path");
        switch(method){
            case "GET" :
                String[] req = path.split("/");
                if(req.length>3 && req[4].equals("byname")){
                    apiManager.getArticlesByName(request, response);
                    
                } else {
                    apiManager.getArticles(request, response);
                }
                break;
            case "POST": 
                    apiManager.addArticles(request,response);
                    break;
            
            case "DELETE" :
                    apiManager.deleteArticle(request, response);
                    break;
            }
        
    }


    
}

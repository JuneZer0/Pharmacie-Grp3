package business.helpers;

import javax.servlet.RequestDispatcher;

/** This class contains constants for easier path management */
public class PathResolver {

    public static final String ROOT_URL = "/*";
    public static final String LOCAL_BASE_URL = "app";
    public static final String API_BASE_URL = "api";
    public static final String FORWARD_PORT = "http://localhost:8080";

    public static final String HOME_URL = "home";
    public static final String PRODUCT_PAGE_URL = "product";
    public static final String CREATE_FORM_URL = "form/create";
    public static final String UPDATE_FORM_URL = "form/update";
    public static final String PRODUCT_JSP_URL = "webapp/view/jsp/product.jsp";
    public static final String FORM_JSP_URL = "webapp/view/jsp/form.jsp";
    public static final String HOME_JSP_URL  = "webapp/view/jsp/home.jsp";
    public static final String MENU_JSP = "webapp/view/jsp/menu.jsp";
    public static final String FOOTER_JSP = "webapp/view/jsp/footer.jsp";



    public static String displayJsp(String url){
            
            
        String dispatchUrl = getDispatchUrl(url);

        switch(dispatchUrl){
            case HOME_URL : return HOME_JSP_URL;
            case CREATE_FORM_URL : return FORM_JSP_URL;
            case UPDATE_FORM_URL : return FORM_JSP_URL;
            case PRODUCT_PAGE_URL : return PRODUCT_JSP_URL;
        }

    }

    public static String getDispatchUrl(String url){
        String[] servletToCall = url.split("/");
        String dispatchUrl = "/";   
        if(servletToCall.length>=3){             
        for(int i = 3; i < servletToCall.length ;i++){
             dispatchUrl = dispatchUrl+servletToCall[i];
        } 
    }
        return dispatchUrl;
    }
                
      
    }





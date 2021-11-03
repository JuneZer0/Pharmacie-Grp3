package main.java.helpers;

public class PathResolver {

    //APP URLS
    public static final String APP_BASE = "/app/";
    //API URLS
    public static final String API_PORT = "http://localhost:8080";    
    public static final String API_BASE="/api/articles";

    //API REQUESTS CALLED FROM FRONT CONTROLLER
    public static final String API_TARGET_CREATE = API_PORT+API_BASE;
    public static final String API_TARGET_LIST = API_TARGET_CREATE;
    public static final String API_TARGET_DELETE = API_TARGET_CREATE;
    public static final String API_TARGET_BYNAME = API_TARGET_CREATE+"/byname";
    public static final String API_TARGET_UPDATE = API_TARGET_CREATE;

  
    //METHOD NAMES
    public static final String MTHD_CREATE = "create";
    public static final String MTHD_DELETE = "delete";
    public static final String MTHD_LIST = "list";
    public static final String MTHD_BYNAME = "byName";
    public static final String MTHD_UPDATE  = "update";

    //API REQUESTS TO CALL FROM SERVLET OR JSP
    public static final String API_CREATE = API_BASE+"/"+MTHD_CREATE;
    public static final String API_GETALL = API_BASE+"/"+MTHD_LIST;
    public static final String API_DELETE = API_BASE+"/"+MTHD_DELETE;
    public static final String API_BYNAME = API_BASE+"/"+MTHD_BYNAME;
    public static final String API_UPDATE = API_BASE+"/"+MTHD_UPDATE;


    //SERVLET NAMES
    public static final String SRV_HOME_NAME="home";
    public static final String SRV_FORM_NAME="form";
    public static final String SRV_PRODUCT_NAME="product"; 


    //JSP Paths
    public static final String JSP_MENU="./webapp/WEB-INF/header.jsp";
    public static final String JSP_FOOTER="./webapp/WEB-INF/footer.jsp";
    public static final String JSP_HOME="";
    public static final String JSP_PRODUCT="";
    public static final String JSP_FORM= "";

    //Call servlet
    public static final String CALL_SRV_HOME= APP_BASE+SRV_HOME_NAME;
    public static final String CALL_SRV_PRODUCT= APP_BASE+SRV_PRODUCT_NAME;
    public static final String CALL_SRV_FORM= APP_BASE+SRV_FORM_NAME;






 





    
}

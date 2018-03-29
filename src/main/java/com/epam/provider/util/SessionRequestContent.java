package com.epam.provider.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created by HP on 27.03.2018.
 */
public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;

    public SessionRequestContent(){}

    public Object getAttribute(String attributeName){
        return requestAttributes.get(attributeName);
    }
    public String[] getParameter(String parameterName){
        return requestParameters.get(parameterName);
    }
    public void extractValues(HttpServletRequest req){
        requestAttributes=new HashMap<>();
        Enumeration<String> attributeNames=req.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            String attrName=attributeNames.nextElement();
            Object attrValue=req.getAttribute(attrName);
            requestAttributes.put(attrName,attrValue);
        }
        Enumeration<String> parameterNames=req.getParameterNames();
        requestParameters=(HashMap)req.getParameterMap();
        /*while (parameterNames.hasMoreElements()){
            String paramName=parameterNames.nextElement();
            String []paramValue=req.getParameterValues(paramName);
            requestParameters.put(paramName, paramValue[0]);
        }*/
    }

    public void insertAttribute(HttpServletRequest req){
        requestAttributes.forEach((k,v)->req.setAttribute(k,v));
    }
}

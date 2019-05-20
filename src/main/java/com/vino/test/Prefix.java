package com.vino.test;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class Prefix {
    /* APP PREFIX */
    public static final String appName = "Vino Test";

    /* PATH PREFIX*/
    public static final String PATH_API = "/api";
    public static final String PATH_MASTER_DATA = "/master-data";
    public static final String PATH_LOGOUT = "/logout";
    public static final String PATH_LOGIN = "/login";
    public static final String PATH_ROLE = "/role";
    public static final String PATH_USER = "/users";
    public static final String PATH_EMPLOYEE = "/employee";

    public static final String LocalComputerName(){
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            return "";
        }
        return ip.getCanonicalHostName();
    }

    public static final String getURLBase(HttpServletRequest request) throws MalformedURLException {
        URL requestURL = new URL(request.getRequestURL().toString());
        String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
        String contextPath = request.getContextPath();
        return requestURL.getProtocol() + "://" + requestURL.getHost() + port + contextPath;
    }
}

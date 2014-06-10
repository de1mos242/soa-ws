package com.github.gkislin.common.web;

import com.github.gkislin.common.LoggerWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

/**
 * User: gkislin
 * Date: 22.01.14
 */
public class ServletUtil {
    public static final String AUTHORIZATION = "Authorization";
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(ServletUtil.class);

    public static String toString(HttpServletRequest request) {
        String query = request.getQueryString();
        return request.getRequestURL() + ((query == null) ? "" : "?" + query);
    }

    public static String encodeBasicAuthHeader(String name, String passw) {
        String authString = name + ":" + passw;
        return "Basic " + DatatypeConverter.printBase64Binary(authString.getBytes());
    }

    public static void checkBasicAuth(HttpServletRequest request, HttpServletResponse response, String authHeader) {
        int code = getResponseCode(request.getHeader(AUTHORIZATION), authHeader);
        try {
            if (code != 0) {
                response.sendError(code);
                throw new SecurityException();
            }
        } catch (IOException e) {
            throw LOGGER.getIllegalStateException(e);
        }
    }

    public static int getResponseCode(String header, String authHeader) {
        if (header == null) {
            LOGGER.warn("Unauthorized access");
            return HttpServletResponse.SC_UNAUTHORIZED;
        }
        if (!authHeader.equals(header)) {
            LOGGER.warn("Wrong password access");
            return HttpServletResponse.SC_FORBIDDEN;
        }
        return 0;
    }
}

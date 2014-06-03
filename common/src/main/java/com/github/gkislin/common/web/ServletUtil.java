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
    private static final String AUTHORIZATION = "Authorization";
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
        String header = request.getHeader(AUTHORIZATION);
        try {
            if (header == null) {
                LOGGER.warn("Unauthorized access");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                throw new SecurityException();
            }
            if (!authHeader.equals(header)) {
                LOGGER.warn("Wrong password access");
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                throw new SecurityException();
            }
        } catch (IOException e) {
            throw LOGGER.getIllegalStateException(e);
        }
    }
}

package com.github.gkislin.common.web;

import javax.servlet.http.HttpServletRequest;

/**
 * User: gkislin
 * Date: 22.01.14
 */
public class ServletUtil {

    public static String toString(HttpServletRequest request) {
        String query = request.getQueryString();
        return request.getRequestURL() + ((query == null) ? "" : "?" + query);
    }
}

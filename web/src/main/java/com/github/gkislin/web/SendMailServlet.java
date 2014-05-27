package com.github.gkislin.web;

import com.github.gkislin.common.web.CommonServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * User: gkislin
 * Date: 23.01.14
 */
public class SendMailServlet extends CommonServlet {

    @Override
    protected void doProcess(HttpServletRequest request, HttpServletResponse response, Map<String, String> params) throws IOException, ServletException {
        System.out.println(params);
    }
}

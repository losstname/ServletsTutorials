package com.umrwhk.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by losstname on 5/22/17.
 */
@WebServlet(name = "HelloForm")
public class HelloForm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Method Get/Post</title>\n" +
                "</head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<ul>\n" +
                "<li><b>First Name</b>: "+ request.getParameter("first_name") + "\n" +
                "<li><b>Last Name</b>: "+ request.getParameter("last_name") + "\n" +
                "</ul>\n" +
                "</body>\n" +
                "</html>");
        writer.close();
    }
}

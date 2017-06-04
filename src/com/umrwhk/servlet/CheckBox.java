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
@WebServlet(name = "CheckBox")
public class CheckBox extends HttpServlet {
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
                "    <title>Passing Data CheckBox</title>\n" +
                "</head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<ul>\n" +
                "<li><b>Maths Flag</b>: "+ request.getParameter("maths") + "\n" +
                "<li><b>Physics Flag</b>: "+ request.getParameter("physics") + "\n" +
                "<li><b>Chemistry Flag</b>: "+ request.getParameter("chemistry") + "\n" +
                "</ul>\n" +
                "<a href=\"./\">Back to Home</a>" +
                "</body>\n" +
                "</html>");
        writer.close();
    }
}

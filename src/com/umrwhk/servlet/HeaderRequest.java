package com.umrwhk.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by losstname on 5/22/17.
 */
@WebServlet(name = "HeaderRequest")
public class HeaderRequest extends HttpServlet {
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
                "    <title>HTTP Header Request Example</title>\n" +
                "</head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<table width='100%' align='center'>\n" +
                "<tr>\n" +
                "<th>Header Name</th><th>Header Value(s)</th>\n" +
                "</tr>\n");
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String paramName = (String) headerNames.nextElement();
            String paramValue = request.getHeader(paramName);
            writer.println("<tr>\n" +
                    "<td>"+paramName+"</td>" +
                    "<td>"+paramValue+"</td>" +
                    "\n</tr>");
        }

        writer.println("</table>\n" +
                "<a href=\"./\">Back to Home</a>" +
                "</body>\n" +
                "</html>");
        writer.close();
    }
}

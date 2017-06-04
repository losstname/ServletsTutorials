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
@WebServlet(name = "ErrorHandler")
public class ErrorHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null){
            servletName = "Unknown";
        }
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null){
            requestUri = "Unknown";
        }
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Error Handler</title>\n" +
                "</head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n");
        if (throwable == null && statusCode == null){
            writer.println("<h2>Information is Missing</h2>" +
                    "Please go back to <a href='"+response.encodeRedirectURL("http://localhost:8080/Servlet")+
                    "'>Home</a>");
        }else if(statusCode != null){
            writer.println("The status code : "+statusCode);
        }else{
            writer.println("<h2>Error Information</h2>" +
                    "Servlet Name : "+servletName+"<br/>" +
                    "Exeption Type : "+throwable.getClass().getName()+"</br>" +
                    "The request URI : "+ requestUri+"</br>" +
                    "The exception message: "+throwable.getMessage());
        }
        writer.println("</body>\n" +
                "</html>");
        writer.close();
    }
}

package com.umrwhk.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by losstname on 5/22/17.
 */
@WebServlet(name = "DeleteCookies")
public class DeleteCookies extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Error Handler</title>\n" +
                "</head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n");

        if(cookies != null){
            writer.println("<h2>Found Cookies Name and Value</h2>");
            for (int i=0;i<cookies.length;i++){
                cookie = cookies[i];
                if ((cookie.getName()).compareTo("first_name")==0){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    writer.println("Deleted cookie: "+cookie.getName()+"<br/>"+
                            "Name : "+cookie.getName()+", " +
                            "Value : "+cookie.getValue()+"<br/>");
                }
            }
        }else{
            writer.println("<h2>No Cookies Found</h2>");
        }

        writer.println("<a href=\"./\">Back to Home</a>\n" +
                "</body>\n" +
                "</html>");
        writer.close();
    }
}

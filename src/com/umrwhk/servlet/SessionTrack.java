package com.umrwhk.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by losstname on 5/25/17.
 */
@WebServlet(name = "SessionTrack")
public class SessionTrack extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Date createTime = new Date(session.getCreationTime());
        Date lastAccessTime = new Date(session.getLastAccessedTime());
        Integer visitCount = new Integer(0);
        String visitCountKey = new String("visitCount");
        String userIdKey = new String("userId");
        String userId = new String("ABCD");
        String title = "Welcome Back to My Website";
        if(session.isNew()){
            title = "Welcome to My Website";
            session.setAttribute(userIdKey, userId);
        }else{
            visitCount = (Integer)session.getAttribute(visitCountKey);
            visitCount+=1;
            userId = (String)session.getAttribute(userIdKey);
        }
        session.setAttribute(visitCountKey,visitCount);

        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>"+title+"</title>\n" +
                "</head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align='center'>"+title+"</h1>\n" +
                "<h2 align='center'>Session Information</h2>\n" +
                "<table align='center'>\n" +
                "<tr><th>Session</th><th>Value</th></tr>\n" +
                "<tr><td>Id</td><td>"+session.getId()+"</td></tr>\n" +
                "<tr><td>Creation Time</td><td>" + createTime + "</td></tr>\n" +
                "<tr><td>Time of Last Access</td><td>" + lastAccessTime + "</td></tr>\n" +
                "<tr><td>User ID</td><td>" + userId + "</td></tr>\n" +
                "<tr><td>Number of Visits</td><td>" + visitCount + "</td></tr>\n" +
                "</table>" +
                "<a href=\"./\">Back to Home</a>" +
                "</body>\n" +
                "</html>");
        writer.close();
    }
}

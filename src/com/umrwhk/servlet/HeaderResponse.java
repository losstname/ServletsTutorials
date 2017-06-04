package com.umrwhk.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by losstname on 5/22/17.
 */
@WebServlet(name = "HeaderResponse")
public class HeaderResponse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setIntHeader("Refresh",5);
        response.setContentType("text/html");

        Calendar calendar = new GregorianCalendar();
        String ampm;
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        if (calendar.get(Calendar.AM_PM) == 0){
            ampm = "AM";
        }else{
            ampm = "PM";
        }
        String CT = hour+":"+minute+":"+second+" "+ampm;

        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>HTTP Header Response</title>\n" +
                "</head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align='center'>Auto Refresh</h1>" +
                "<p>Current Time is: "+CT+"</p>" +
                "</body>\n" +
                "</html>");
        writer.close();
    }
}

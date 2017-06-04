package com.umrwhk.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by losstname on 6/4/17.
 */
@WebServlet(name = "DatabaseAccess")
public class DatabaseAccess extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver",
                DB_URL = "jdbc:mysql://localhost:3306/test",
                USER = "root",
                PASS = "";
        Connection conn;

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        Statement stmt = null;
        writer.println( "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Database Access</title>\n" +
                "</head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align='center'> Database Result </h1>");
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT * FROM Employee";
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
                writer.println("ID: "+ resultSet.getInt("empId")+"<br/>" +
                        "Age: "+resultSet.getInt("age")+"<br/>" +
                        "First: "+resultSet.getString("firstName")+"<br/>" +
                        "Last: "+resultSet.getString("lastName")+"<br/>");
            }
            writer.println("</body></html>");
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt!=null){
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }
}

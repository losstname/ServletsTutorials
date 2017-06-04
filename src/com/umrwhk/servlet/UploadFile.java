package com.umrwhk.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Created by losstname on 6/4/17.
 */
@WebServlet(name = "UploadFile")
public class UploadFile extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 *1024,
        maxMemSize = 40 * 1024;
    private File file;

    public void init(){
        filePath = getServletContext().getInitParameter("file-upload");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        if(!isMultipart){
            writer.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Upload File</title>\n" +
                    "</head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<p>No file uploaded</p>\n" +
                    "</body>\n" +
                    "</html>");
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);
        factory.setRepository(new File("/opt/tomcat/apache-tomcat-8.5.15/webapps/Servlet/temp/"));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(maxFileSize);

        try {
            List fileItems = upload.parseRequest(request);
            Iterator i = fileItems.iterator();

            writer.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Upload File</title>\n" +
                    "</head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n");

            while (i.hasNext()){
                FileItem item = (FileItem) i.next();
                if(!item.isFormField()){
                    String fieldName = item.getFieldName(),
                            fileName = item.getName(),
                            contentType = item.getContentType();
                    boolean isInMemory =  item.isInMemory();
                    long sizeInBytes = item.getSize();

                    if(fileName.lastIndexOf("\\")>0){
                        file = new File(filePath+fileName.substring(fileName.lastIndexOf("\\")));
                    }else{
                        file = new File(filePath+fileName.substring(fileName.lastIndexOf("\\")+1));
                    }
                    item.write(file);
                    writer.println("Uploaded Filename: "+ fileName+ "<br/>" +
                            "Fieldname: "+fieldName+"<br/>" +
                            "Content Type: "+contentType+"<br/>" +
                            "Is In Memory: "+isInMemory+"<br/>" +
                            "Size: "+sizeInBytes);
                }
            }
            writer.println("</body></html>");
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

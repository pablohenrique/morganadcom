package edu.morgan.client;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pablohpsilva
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class AppServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
        	PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
        	
        	if(request.getParameter("code") != null){
	            out.println("<!DOCTYPE html>");
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Morgan Admissions - Web App</title>"); 
	            out.println("<link href='bootstrap.min.css' rel='stylesheet'/>");
	            out.println("</head>");
	            out.println("<body style='background-color:#eee;'>");
	            out.println("	<div class='header container' style='width:700px'>");
	            out.println("		<img src='images.png' style='width: 700px;' />");
	            out.println("	</div>");
	            
	            out.println("");
	            
	            out.println("<h3 align='center'>Do NOT use Internet Explorer. Use Google Chrome or Firefox instead.</h3>\n");
	            out.println("<h4 align='center'>Select File to Upload: </h4><br/>");
	            out.println("<div class='container' style='width:250px'>");
	            out.println("	<form action='/UploadFile?code="+ request.getParameter("code") +"' method='POST' enctype='multipart/form-data' accept='application/vnd.ms-excel'>");
	            out.println("		<input type='file' name='file'/><br/>");
	            out.println("		<button class='btn btn-primary' style='width:230px'>Submit</button>");
	            out.println("	</form>");
	            out.println("</div>");
	            
	            out.println("");
	            
	            out.println("</body>");
	            out.println("</html>");
            	//request.setAttribute("code", request.getParameter("code"));
        	}
        	else
        		out.println("error");        		
            //request.setAttribute("code", request.getAttribute("code"));
            //response.sendRedirect("https://accounts.google.com/o/oauth2/auth?access_type=offline&approval_prompt=force&client_id=892186241167-228o9c5afo7fqrnbciabv81eghdj63f5.apps.googleusercontent.com&redirect_uri=http://localhost:8084/msuadmissions/AppServlet&response_type=code&scope=https://www.googleapis.com/auth/drive%20https://www.googleapis.com/auth/drive.appdata%20https://www.googleapis.com/auth/drive.apps.readonly%20https://www.googleapis.com/auth/drive.file%20https://www.googleapis.com/auth/drive.metadata.readonly");
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


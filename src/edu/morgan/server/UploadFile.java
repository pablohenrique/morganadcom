/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.morgan.server;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.annotation.WebServlet;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.*;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.morgan.server.student.IncompleteStudent;


/**
 *
 * @author pablohpsilva
 */
@WebServlet(name = "UploadFileServlet", urlPatterns = {"/UploadFileServlet"})
public class UploadFile extends HttpServlet {
    
    private boolean isMultipart;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private ArrayList<IncompleteStudent> studentList = new ArrayList<IncompleteStudent>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    public void init() {
        // Get the file location where it would be stored.
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        // Check that we have a file upload request
    	RequestDispatcher rd;
    	response.setContentType("text/html");
    	
        isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
        	rd = request.getRequestDispatcher("fail.jsp");
        	rd.forward(request, response);
        }
        
        try {
			ServletFileUpload upload = new ServletFileUpload();
			response.setContentType("text/plain");

			FileItemIterator iterator = upload.getItemIterator(request);
			while (iterator.hasNext()) {
				FileItemStream item = iterator.next();
				InputStream stream = item.openStream();
				this.read(stream);
			}
			request.removeAttribute("incompleteStudents");
			request.setAttribute("incompleteStudents", studentList);
			
			rd = request.getRequestDispatcher("Success");
			rd.forward(request, response);

		} catch (Exception ex) {
			throw new ServletException(ex);
		}
        
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
    	RequestDispatcher rd;
    	rd = request.getRequestDispatcher("fail.jsp");
    	rd.forward(request, response);
    }
    
    private void read(InputStream inputFile) throws IOException {
    	
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputFile);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines
			
			for(int row = 1; row <= sheet.getRows(); row++){
				IncompleteStudent student = new IncompleteStudent();
				
				for(int col = 0; col < sheet.getColumns(); col++){
					Cell identifier = sheet.getCell(col, 0);
					String strIdentifier = identifier.getContents();
					
					if (strIdentifier.toLowerCase().contains("first") && strIdentifier.toLowerCase().contains("name")) {
						Cell cell = sheet.getCell(col, row);
						student.setFirstName(cell.getContents());
					}
					else if (strIdentifier.toLowerCase().contains("last") && strIdentifier.toLowerCase().contains("name")) {
						Cell cell = sheet.getCell(col, row);
						student.setLastName(cell.getContents());
					}
					else if (strIdentifier.toLowerCase().contains("date") && strIdentifier.toLowerCase().contains("birth")) {
						Cell cell = sheet.getCell(col, row);
						student.setDateOfBirth(cell.getContents());
					}
					else if (strIdentifier.toLowerCase().contains("id")) {
						Cell cell = sheet.getCell(col, row);
						student.setId(cell.getContents());
					}
					else if (strIdentifier.toLowerCase().contains("checklist")) {
						Cell cell = sheet.getCell(col, row);
						student.setChecklist(cell.getContents());
					}
					else if (strIdentifier.toLowerCase().contains("term")) {
						Cell cell = sheet.getCell(col, row);
						student.setTerm(cell.getContents());
					}
				}
				studentList.add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

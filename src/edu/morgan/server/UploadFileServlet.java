/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.morgan.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.morgan.server.student.IncompleteStudent;


/**
 *
 * @author pablohpsilva
 */
@WebServlet(name = "UploadFileServlet", urlPatterns = {"/UploadFileServlet"})
public class UploadFileServlet extends HttpServlet {
    
    private boolean isMultipart;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;

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
			
			rd = request.getRequestDispatcher("success.jsp");
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
			ArrayList<IncompleteStudent> studentList = new ArrayList<IncompleteStudent>();

			for (int j = 0; j < sheet.getColumns(); j++) {
				IncompleteStudent student = new IncompleteStudent();
				
				for (int i = 0; i < sheet.getRows(); i++) {
					Cell identifier = sheet.getCell(j, 0);
					String strIdentifier = identifier.getContents();
					
					if (strIdentifier.toLowerCase().contains("first") && strIdentifier.toLowerCase().contains("name")) {
						Cell cell = sheet.getCell(j, i);
						student.setFirstName(cell.getContents());
					}
					else if (strIdentifier.toLowerCase().contains("last") && strIdentifier.toLowerCase().contains("name")) {
						Cell cell = sheet.getCell(j, i);
						student.setLastName(cell.getContents());
					}
					else if (strIdentifier.toLowerCase().contains("date") && strIdentifier.toLowerCase().contains("birth")) {
						Cell cell = sheet.getCell(j, i);
						student.setDateOfBirth(cell.getContents());
					}
					else if (strIdentifier.toLowerCase().contains("id")) {
						Cell cell = sheet.getCell(j, i);
						student.setId(cell.getContents());
					}
					else if (strIdentifier.toLowerCase().contains("checklist")) {
						Cell cell = sheet.getCell(j, i);
						student.setChecklist(cell.getContents());
					}
					else if (strIdentifier.toLowerCase().contains("term")) {
						Cell cell = sheet.getCell(j, i);
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

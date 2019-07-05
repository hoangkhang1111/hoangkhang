package Servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

public class UpdateServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kotoba = request.getParameter("kotoba");
		int sutetasu = 1;
		String kotoba2= request.getParameter("kotoba2");
		int ko = kotoba.length()-1;
		int ko2 = kotoba2.length()-1;
		Shisutemu shi = new Shisutemu( kotoba, sutetasu);
		try {
			if(kotoba2 == null || kotoba2 == "") {
				if( ((kotoba.substring(ko)).equals("n")) == true ) {
					request.setAttribute("endgame", "endgame");
				
				}else if(kotoba == null || kotoba == "") {
					request.setAttribute("loi", "loi");
				}
			}else {
				if( ((kotoba.substring(ko)).equals("n") == true ) || (new ConnectDB().Kiemtratontaiendgame(kotoba)) != null ) {
					request.setAttribute("endgame", "endgame");
				}if( ((kotoba.substring(0)).equals(kotoba2.substring(ko2)) != true ) || kotoba == null ) {
					request.setAttribute("loi", "loi");
				} else{
					if((new ConnectDB().Kiemtratontai(kotoba2)) != null) {
						System.out.println("tiep tuc game");
						new ConnectDB().getProductByID(kotoba);
						HttpSession session = request.getSession();
						session.setAttribute("kotoba", kotoba);
						response.sendRedirect("index.jsp");
					}else {
						new ConnectDB().insertNew(shi);
						System.out.println("tiep tuc game");
						new ConnectDB().getProductByID(kotoba);
						HttpSession session = request.getSession();
						session.setAttribute("kotoba", kotoba);
						response.sendRedirect("index.jsp");
					}
				}
			}
			} catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
		 
	
		}
}

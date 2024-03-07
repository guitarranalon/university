package uniovi;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			
			HttpSession session = request.getSession();			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/uniovi", "root", "Playa.de-Barayo");
			
			String n = request.getParameter("txtName");
			String p = request.getParameter("txtPass");
			
			PreparedStatement ps = con.prepareStatement("select uname from users where uname=? and password=?");
			PreparedStatement students = con.prepareStatement("select * from students");
			
			ps.setString(1, n);
			ps.setString(2, p);
			
			ResultSet rs = ps.executeQuery();
			ResultSet rsStudents = students.executeQuery();
			
			if(rs.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
				
				request.setAttribute("error", false);
				
	            // Set the List as an attribute in the request
	            request.setAttribute("studentList", StudentDao.resultSetToList(rsStudents));
				
				session.setAttribute("uname", n);
				rd.forward(request, response);
			} else {
				out.println("Login failed!");
				out.println("<a href=login.jsp>Try again!</a>");
				
				request.setAttribute("error", true);
				// request.getRequestDispatcher("result.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

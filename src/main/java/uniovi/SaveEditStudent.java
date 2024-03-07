package uniovi;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uniovi.StudentDao;

/**
 * Servlet implementation class SaveEditStudent
 */
public class SaveEditStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveEditStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/uniovi", "root", "Playa.de-Barayo")) {
			PrintWriter out = response.getWriter();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			int id = Integer.parseInt(request.getParameter("studentId"));
			String name = request.getParameter("studentName");
			String email = request.getParameter("studentEmail");

            String sql = "UPDATE students SET name=?, email=? WHERE id =?";
            PreparedStatement students = con.prepareStatement("select * from students");

            // Crear un objeto PreparedStatement
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                // Establecer los valores de los parámetros
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setInt(3, id);

                // Ejecutar la consulta parametrizada
                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {                  
                    ResultSet rsStudents = students.executeQuery();
                    
    				RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
    				
    				request.setAttribute("error", false);

    	            // Set the List as an attribute in the request
    	            request.setAttribute("studentList", StudentDao.resultSetToList(rsStudents));
    				
    				rd.forward(request, response);
                } else {
                    out.println("La inserción no tuvo éxito.");
                }
            }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}

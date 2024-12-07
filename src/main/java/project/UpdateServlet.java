package project;


import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateServlet extends GenericServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "Krishna@630101");

            String query = "UPDATE emp SET name = ?, sal = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setDouble(2, Double.parseDouble(salary));
            ps.setInt(3, Integer.parseInt(id));

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                out.println("<h1>Employee Record Updated Successfully!</h1>");
            } else {
                out.println("<h1>Error: Employee not found.</h1>");
            }

            con.close();
            out.println("<a href='/EMP_ORG/Main.html'><button>Back to Home</button></a>");
        } catch (Exception e) {
            out.println("<h1>Error: Unable to update record.</h1>");
            e.printStackTrace(out);
        }
    }
}


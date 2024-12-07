package project;

import java.io.*;
import java.sql.*;
import javax.servlet.*;

public class DeleteServlet extends GenericServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("Deleting proces:");
        String id = req.getParameter("id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "Krishna@630101");

            String query = "DELETE FROM emp WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id));

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                out.println("<h1>Employee Record Deleted Successfully!</h1>");
            } else {
                out.println("<h1>Error: Employee not found.</h1>");
            }

            con.close();
            out.println("<a href='/EMP_ORG/Main.html'><button>Back to Home</button></a>");
        } catch (Exception e) {
            out.println("<h1>Error: Unable to delete record.</h1>");
            e.printStackTrace(out);
        }
    }
}

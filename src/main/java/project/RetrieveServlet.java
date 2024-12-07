package project;


import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RetrieveServlet extends GenericServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("HI Retrieveing process");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "Krishna@630101");

            String query = "SELECT * FROM emp";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            out.println("<h1>Employee Records</h1>");
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Salary</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("name") + "</td><td>" + rs.getDouble("sal") + "</td></tr>");
            }
            out.println("</table>");
            out.println(" ");
            out.println(" ");
            out.println("<h1> </h1>");
            out.println("<a href='/EMP_ORG/Main.html'> <button> Back to Home   </button> </a>");

            con.close();
        } catch (Exception e) {
            out.println("<h1>Error: Unable to fetch records.</h1>");
            e.printStackTrace(out);
        }
    }
}

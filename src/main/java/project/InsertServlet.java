package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class InsertServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Retrieve form parameters
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");

        // Validate input
        if (id == null || name == null || salary == null || id.isEmpty() || name.isEmpty() || salary.isEmpty()) {
            out.println("<h1>Error: All fields are required.</h1>");
            out.println("<a href='/EMP_ORG/Main.html'><button>Back to Home</button></a>");

            return;
        }

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "Krishna@630101");
                 PreparedStatement ps = con.prepareStatement("INSERT INTO emp (id, name, sal) VALUES (?, ?, ?)")) {
                 
                // Set the parameters for the prepared statement
                ps.setInt(1, Integer.parseInt(id));
                ps.setString(2, name);
                ps.setDouble(3, Double.parseDouble(salary));

                // Execute the insert query
                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    out.println("<h1>Employee Record Created Successfully!</h1>");
                } else {
                    out.println("<h1>Error: Could not insert employee.</h1>");
                }
            }

            // Link to go back to home page
            out.println("<a href='/EMP_ORG/Main.html'> <button>Back to Home</button></a>");

        } catch (Exception e) {
            // Display error and print stack trace
            out.println("<h1>Error: Unable to insert record.</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            e.printStackTrace(out);  // Optional: Can be omitted in production for security
        }
    }
}

package project;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ExitServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Print a simple message for exit
        out.println("<h2>Exiting...</h2>");

        // Log the exit action (simple message)
        System.out.println("User has exited the application.");
        
        out.println("<h2>LOGOUT.......</h2>");
        // Redirecting to the home page after a brief exit message
        
        //manually by clicking the link
       // out.println("<a href='/EMP_ORG/Login.html'> Click the Logout link </a>");

        // Redirecting to the login page after a brief exit message
        ((HttpServletResponse) res).sendRedirect("/EMP_ORG/Login.html");  // This line will redirect to Login.htm
        //System.exit(0); //server jvm all are stoped 
    }
}

package us.hirt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/health")
public class HealthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InetAddress ip;
        String ipString = "N/A";
        String hostname = "N/A";
        try {
         ip = InetAddress.getLocalHost();
         ipString = ip.toString();
         hostname = ip.getHostName();
         } catch (UnknownHostException e) {
          e.printStackTrace();
        }
        response.setContentType("text/html");
        response.getWriter().print("Alive and kickin' at " + ipString) ;
    }

}

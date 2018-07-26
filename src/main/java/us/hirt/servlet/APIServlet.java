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
@WebServlet("/api")
public class APIServlet extends HttpServlet {
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
        response.setContentType("application/json");
        response.getWriter().print("[{ \"id\": 1,\r\n \"first_name\": \"Ruth\",\r\n \"last_name\": \"Tait\",\r\n \"email\": \"rtait0@free.fr\",\r\n \"gender\": \"Female\",\r\n \"ip_address\": \"58.122.178.133\",\r\n \"account_id\": \"com.wired.Stronghold\",\r\n \"city\": \"Rzhev\",\r\n \"customer_since\": \"16/06/2012\" },\r\n { \"id\": 2,\r\n \"first_name\": \"Almeda\",\r\n \"last_name\": \"Gornal\",\r\n \"email\": \"agornal1@chronoengine.com\",\r\n \"gender\": \"Female\",\r\n \"ip_address\": \"230.193.186.64\",\r\n \"account_id\": \"com.taobao.Overhold\",\r\n \"city\": \"Palecenan\",\r\n \"customer_since\": \"09/01/2011\" },\r\n { \"id\": 3,\r\n \"first_name\": \"Phelia\",\r\n \"last_name\": \"Cranmore\",\r\n \"email\": \"pcranmore2@sciencedaily.com\",\r\n \"gender\": \"Female\",\r\n \"ip_address\": \"10.31.130.79\",\r\n \"account_id\": \"com.msn.Voltsillam\",\r\n \"city\": \"Huaquirca\",\r\n \"customer_since\": \"04/04/2018\" },\r\n { \"id\": 4,\r\n \"first_name\": \"Harald\",\r\n \"last_name\": \"Gallafant\",\r\n \"email\": \"hgallafant3@netvibes.com\",\r\n \"gender\": \"Male\",\r\n \"ip_address\": \"118.105.208.89\",\r\n \"account_id\": \"org.apache.Asoka\",\r\n \"city\": \"Sawin\",\r\n \"customer_since\": \"11/01/2015\" },\r\n { \"id\": 5,\r\n \"first_name\": \"Brett\",\r\n \"last_name\": \"Grenkov\",\r\n \"email\": \"bgrenkov4@amazon.co.uk\",\r\n \"gender\": \"Female\",\r\n \"ip_address\": \"188.246.44.59\",\r\n \"account_id\": \"com.ibm.Matsoft\",\r\n \"city\": \"Gąsocin\",\r\n \"customer_since\": \"03/03/2012\" },\r\n { \"id\": 6,\r\n \"first_name\": \"Fancie\",\r\n \"last_name\": \"Matiewe\",\r\n \"email\": \"fmatiewe5@washington.edu\",\r\n \"gender\": \"Female\",\r\n \"ip_address\": \"1.217.202.118\",\r\n \"account_id\": \"jp.ne.goo.Temp\",\r\n \"city\": \"Wilczyce\",\r\n \"customer_since\": \"07/11/2014\" },\r\n { \"id\": 7,\r\n \"first_name\": \"Addy\",\r\n \"last_name\": \"Dafter\",\r\n \"email\": \"adafter6@infoseek.co.jp\",\r\n \"gender\": \"Male\",\r\n \"ip_address\": \"216.186.88.232\",\r\n \"account_id\": \"com.blog.Aerified\",\r\n \"city\": \"Oslo\",\r\n \"customer_since\": \"25/11/2016\" },\r\n { \"id\": 8,\r\n \"first_name\": \"Isaiah\",\r\n \"last_name\": \"Halms\",\r\n \"email\": \"ihalms7@yahoo.co.jp\",\r\n \"gender\": \"Male\",\r\n \"ip_address\": \"232.67.29.133\",\r\n \"account_id\": \"net.comcast.Gembucket\",\r\n \"city\": \"Sukoreno\",\r\n \"customer_since\": \"31/10/2010\" },\r\n { \"id\": 9,\r\n \"first_name\": \"Esther\",\r\n \"last_name\": \"Seely\",\r\n \"email\": \"eseely8@hc360.com\",\r\n \"gender\": \"Female\",\r\n \"ip_address\": \"179.42.114.128\",\r\n \"account_id\": \"gov.epa.Pannier\",\r\n \"city\": \"Mweka\",\r\n \"customer_since\": \"16/05/2016\" },\r\n { \"id\": 10,\r\n \"first_name\": \"Dave\",\r\n \"last_name\": \"Trevena\",\r\n \"email\": \"dtrevena9@weather.com\",\r\n \"gender\": \"Male\",\r\n \"ip_address\": \"12.86.19.200\",\r\n \"account_id\": \"com.bloomberg.Solarbreeze\",\r\n \"city\": \"Rokietnica\",\r\n \"customer_since\": \"09/05/2018\" }]") ;
    }

}

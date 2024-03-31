package Ram;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
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

public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
{
try
{
	PrintWriter out=res.getWriter();
	res.setContentType("text/html");
  Class.forName("com.mysql.cj.jdbc.Driver");
   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/nesa","root","Kavi@2003");
   String n=req.getParameter("txtName");
   String p=req.getParameter("txtPwd");
   PreparedStatement ps=con.prepareStatement("select uname from login where uname=? and password=?");
   ps.setString(1,n);
   ps.setString(2,p);
   ResultSet rs=ps.executeQuery();
if(rs.next())
{
	RequestDispatcher rd=req.getRequestDispatcher("Welcome.jsp");
	rd.forward(req, res);
}
else
{
	out.println("<font color=red size=18>Login Failed!!<br>");
	out.println("<a href=login.jsp>Try again!</a>");
}
   
}
catch(ClassNotFoundException e)
 {
	e.printStackTrace();
 }
catch(SQLException e) 
 {
	e.printStackTrace();
 }
}
}
	
 
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserHandler;

@WebServlet(urlPatterns = "/DeleteUserFromDB")
public class DeleteUserFromDB extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int psa = Integer.parseInt(req.getParameter("id"));
		
		UserHandler uh = new UserHandler();
		
		uh.deleteById(psa);
		resp.sendRedirect("ViewUser.jsp");
	}
	

}

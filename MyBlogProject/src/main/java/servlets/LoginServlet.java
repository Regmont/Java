package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import models.Message;

public class LoginServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        if (getServletContext().getAttribute("messages") == null) {
            getServletContext().setAttribute("messages", new ArrayList<Message>());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("pass");

        if ("admin".equals(login) && "123".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", "admin");
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}
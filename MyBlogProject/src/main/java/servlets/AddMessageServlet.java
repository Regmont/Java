package servlets;

import models.Message;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class AddMessageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            String messageText = request.getParameter("message");
            if (messageText != null && !messageText.trim().isEmpty()) {
                @SuppressWarnings("unchecked")
                ArrayList<Message> messages = (ArrayList<Message>) getServletContext().getAttribute("messages");
                if (messages != null) {
                    messages.add(new Message(messageText, "admin"));
                }
            }
        }
        response.sendRedirect("index.jsp");
    }
}
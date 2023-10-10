package org.example.servlets;

import org.example.dto.UserDto;
import org.example.entity.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    UserDto userDto = new UserDto();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        Optional<User> userByLogin = userDto.getUserByLogin(login);

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>User Information</title>");
        out.println("<link rel='stylesheet' type='text/css'>");
        out.println("</head><body>");
        out.println("<header>");
        out.println("<h1>User Information</h1>");
        out.println("</header>");

        if (userByLogin.isPresent()) {
            User user = userByLogin.get();
            out.println("<main>");
            out.println("<p>Name: " + user.getName() + "</p>");
            out.println("<p>Age: " + user.getAge() + "</p>");
            out.println("<p>Email: " + user.getEmail() + "</p>");
            out.println("<p>Login: " + user.getLogin() + "</p>");
            out.println("<h3>Update Name</h3>");
            out.println("<form action='user' method='post'>");
            out.println("<input type='text' name='newName' value='" + user.getName() + "'>");
            out.println("<input type='hidden' name='login' value='" + user.getLogin() + "'>");
            out.println("<input type='submit' value='Update Name'>");
            out.println("</form>");
            out.println("<a href='index.jsp'>Exit</a>");
            out.println("</main>");
        } else {
            out.println("<p>User not found</p>");
        }
        out.println("<footer>");
        out.println("<p>&copy; 2023 Java Guru Co.</p>");
        out.println("</footer>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String newName = req.getParameter("newName");
        Optional<User> userByLogin = userDto.getUserByLogin(login);

        String tempLogin = "";
        if (userByLogin.isPresent()) {
            User user = userByLogin.get();
            user.setName(newName);
            tempLogin = user.getLogin();
            userDto.updateUserByLogin(login, user);
        }
        resp.sendRedirect("/user?login=" + tempLogin);
    }

}

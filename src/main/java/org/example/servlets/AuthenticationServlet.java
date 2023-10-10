package org.example.servlets;

import org.example.dto.UserDto;
import org.example.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/authentication")
public class AuthenticationServlet extends HttpServlet {
    UserDto userDto= new UserDto();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");


        //Validation
        String errorMessage;
        if(!userDto.isEmailExists(login)||
                !password.equals(userDto.getPasswordByLogin(login).get())){
            errorMessage = "Wrong login or password";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } else {
            request.removeAttribute("error");
            if (userDto.getUserByLogin(login).isPresent()) {
                User user = userDto.getUserByLogin(login).get();
                request.setAttribute("user", user);
            }
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }
    }
}

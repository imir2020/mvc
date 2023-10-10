package org.example.servlets;

import org.example.dto.UserDto;
import org.example.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    UserDto userDto = new UserDto();
    UserService validation = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String errorMessage = "";
        String errorAttribute = "error";

        //Validation
        if (Boolean.FALSE.equals(validation.isValidName(name))){
            errorMessage = """
                    Please enter a valid name.
                     The name should start with a capital letter and have 3 to 15 characters.
                     Use only letters (both uppercase and lowercase) without any symbols or numbers.
                    """;
        } else if (Boolean.FALSE.equals(validation.isValidAge(age))) {
            errorMessage = "Please enter a valid age of 18 or older.";
        } else if (Boolean.FALSE.equals(validation.isValidEmail(email))) {
            errorMessage = "Please enter a valid email.";
        } else if (Boolean.FALSE.equals(validation.isValidLogin(login))) {
            errorMessage = """
                    Login must start with a letter (uppercase or lowercase).
                    Login must be 6 to 30 characters long.
                    Use letters, digits, or underscores only in your login.
                    """;
        } else if (Boolean.FALSE.equals(validation.isValidPassword(password))) {
            errorMessage = """
                    Password must start with a valid character.
                    Password must contain at least one lowercase letter.
                    Password cannot contain white spaces.
                    Password must be between 5 and 15 characters in length.
                    """;
        } else if (userDto.isLoginExists(login)) {
            errorMessage = "User login already exists.";
        } else if (userDto.isEmailExists(email)) {
            errorMessage = "User email already exists.";
        }

        if (!errorMessage.isEmpty()) {
            request.setAttribute(errorAttribute, errorMessage);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        } else {
            request.removeAttribute(errorAttribute);
            User user = new User(name, age, email, login, password);
            userDto.addUser(user);
            request.setAttribute("user", user);
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }
    }

}

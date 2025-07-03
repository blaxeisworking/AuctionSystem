package com.auction.servletss;

import com.auction.dao.AuctionDAO;
import com.auction.model.Auction;
import com.auction.utils.InputValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.auction.dao.UserDAO;
import com.auction.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.http.HttpSession;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String message = "";
            String color="green";
            boolean validinputs = true;
            if (!InputValidator.isValidUsername(username)) {
                message += "Invalid Username, should be alphanumeric";
                validinputs = false;
                color = "red";
            }
            if (!InputValidator.isValidEmail(email)) {
                message += "Invalid email it should be like abc123@example.in\n";
                validinputs = false;
                color = "red";
            }
            if (!InputValidator.isValidPassword(password)) {
                message += "Password length should be at least 6\n";
                validinputs = false;
                color = "red";
            }
            if(validinputs){
                boolean success = UserDAO.registerUser(new User(username, email, password));
                message = success?"User Registered Successfully":"Registration Failed";
                color = success?"green":"red";
            }
            response.setContentType("text/html");
            out.print("<h3 style='color:"+color+"'> " + message + " </h3>");
            request.getRequestDispatcher("/register.jsp").include(request, response);


        }
        else if ("login".equals(action)) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = UserDAO.validateUser(email, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                List<Auction> auctions = AuctionDAO.getAuctionsByUser(user.getId());
                request.setAttribute("auctionList", auctions);
                request.getRequestDispatcher("auction.jsp").forward(request, response);
            }
            else {
                response.setContentType("text/html");
                out.print("<h3 style='color:red'> Invalid email or password </h3>");
                request.getRequestDispatcher("/login.jsp").include(request, response);
            }
        }
    }
}

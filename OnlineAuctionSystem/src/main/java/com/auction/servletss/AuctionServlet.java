package com.auction.servletss;

import java.io.IOException;
import java.util.List;

import com.auction.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.auction.dao.AuctionDAO;
import com.auction.model.Auction;

@WebServlet("/AuctionServlet")
public class AuctionServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemName = request.getParameter("itemName");
        double startPrice = Double.parseDouble(request.getParameter("startPrice"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        Auction auction = new Auction(itemName, startPrice, userId);
        boolean success = AuctionDAO.createAuction(auction);

        if (success) {
            response.setContentType("text/html");
            response.getWriter().print("<h3 style='color:green'> Auction <strong>" +itemName+"</strong> Created </h3>");
            request.getRequestDispatcher("/auction.jsp").include(request, response);
        } else {
            response.setContentType("text/html");
            response.getWriter().print("<h3 style='color:red'> Auction creation failed. </h3>");
            request.getRequestDispatcher("/auction.jsp").include(request, response);
        }
        List<Auction> auctions = AuctionDAO.getAuctionsByUser(userId);
        request.setAttribute("auctionList", auctions);
        request.getRequestDispatcher("auction.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String action = request.getParameter("action");
        if(action.equalsIgnoreCase("search")){
            String keyword = request.getParameter("keyword");
            List<Auction> searchResults = AuctionDAO.searchAuctions(keyword);
            request.setAttribute("searchResults", searchResults);
            request.setAttribute("searchPerformed", true);
        }

        List<Auction> auctions = AuctionDAO.getAuctionsByUser(userId);
        request.setAttribute("auctionList", auctions);
        request.getRequestDispatcher("auction.jsp").forward(request, response);
    }
}

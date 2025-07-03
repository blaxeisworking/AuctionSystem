//package com.auction.servletss;
//
//import com.auction.dao.BidDAO;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//
//@WebServlet("/BidServlet")
//public class BidServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int auctionId = Integer.parseInt(request.getParameter("auctionId"));
//        double bidAmount = Double.parseDouble(request.getParameter("bidAmount"));
//        int bidderId = Integer.parseInt(request.getParameter("bidderId"));
//
//        try {
//            if (BidDAO.placeBid(auctionId, bidAmount, bidderId)) {
//                response.sendRedirect("auction.jsp?success=Bid placed successfully!");
//            } else {
//                response.sendRedirect("place_bid.jsp?auctionId=" + auctionId + "&error=Bid failed. Try again!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

package com.auction.servletss;

import com.auction.dao.BidDAO;
import com.auction.model.Bid;
import com.auction.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/BidServlet")
public class BidServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            if (session == null || user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            PrintWriter out = response.getWriter();
            int auctionId = Integer.parseInt(request.getParameter("auctionId"));
            double bidAmount = Double.parseDouble(request.getParameter("bidAmount"));
            int userId = user.getId();

            boolean success = BidDAO.placeBid(new Bid(auctionId, userId, bidAmount));

            String message = success ? "Bid placed successfully" : "Bid placement failed";
            String color = success ? "green" : "red";

            request.setAttribute("id", auctionId);
            response.setContentType("text/html");
            out.print("<h3 style='color:"+color+"'> " + message + " </h3>");
            request.getRequestDispatcher("/place_bid.jsp").include(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

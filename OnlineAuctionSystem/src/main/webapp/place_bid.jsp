<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.auction.model.Auction, com.auction.dao.AuctionDAO" %>
<%
    int auctionId = Integer.parseInt(request.getParameter("id"));
    Auction auction = AuctionDAO.getAuctionById(auctionId);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Place a Bid</title>
    <style>
        body {
            background: url('assets/placebid.jpg') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            color: #fff;
            margin: 0;
            padding: 0 20px;
        }

        .bid-container {
            max-width: 500px;
            margin: 80px auto;
            background-color: rgba(0, 0, 0, 0.8);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.7);
            text-align: center;
        }

        h2 {
            color: #f1c40f;
            margin-bottom: 20px;
        }

        input[type="number"],
        button {
            padding: 12px;
            width: 100%;
            border-radius: 5px;
            border: none;
            margin: 10px 0;
            box-sizing: border-box;
        }

        button {
            background-color: #e67e22;
            color: white;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #d35400;
        }
    </style>
</head>
<body>
    <div class="bid-container">
        <h2>Place a Bid for <%= auction.getItemName() %></h2>
        <form action="BidServlet" method="post">
            <input type="hidden" name="auctionId" value="<%= auction.getId() %>" />
            <input type="number" name="bidAmount" step="0.01" placeholder="Enter your bid" required />
            <button type="submit">Submit Bid</button><br><br>
        </form>
    </div>
</body>
</html>

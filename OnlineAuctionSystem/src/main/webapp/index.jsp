<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.auction.dao.AuctionDAO, com.auction.model.Auction, java.util.List" %>

<%
    if (request.getAttribute("auctionList") == null) {
        List<Auction> auctionList = AuctionDAO.getAllAuctions();
        request.setAttribute("auctionList", auctionList);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Online Auction - Home</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background: url('assets/index.webp') no-repeat center center fixed;
            background-size: cover;
            color: #fff;
        }

        .container {
            background-color: rgba(0, 0, 0, 0.7);
            max-width: 700px;
            margin: 50px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.5);
        }

        h2, h3 {
            text-align: center;
            color: #f1c40f;
        }

        a {
            color: #00acee;
            text-decoration: none;
            margin: 0 10px;
        }

        a:hover {
            text-decoration: underline;
        }

        ul {
            list-style: none;
            padding: 0;
        }

        li {
            background: #2c3e50;
            margin-bottom: 15px;
            padding: 15px;
            border-radius: 8px;
            font-size: 18px;
            display: flex;
            justify-content: space-between;
        }

        .nav {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome to Online Auction System</h2>
        <div class="nav">
            <a href="login.jsp">Login</a> |
            <a href="register.jsp">Register</a>
        </div>

        <h3>Active Auctions</h3>

        <ul>
            <c:forEach var="auction" items="${auctionList}">
                <li>
                    <span>${auction.itemName}</span>
                    <span>Starting Bid: ₹${auction.startPrice}</span>
                </li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>

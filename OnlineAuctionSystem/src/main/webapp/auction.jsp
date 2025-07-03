<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.auction.model.Auction, com.auction.model.User" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Auction> auctions = (List<Auction>) request.getAttribute("auctionList");
    List<Auction> searchResults = (List<Auction>) request.getAttribute("searchResults");
    Boolean searchPerformed = (Boolean) request.getAttribute("searchPerformed");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Dashboard</title>
        <style>
            body {
                background: url('assets/auction.jpg') no-repeat center center fixed;
                background-size: cover;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                color: #fff;
                margin: 0;
                padding: 0 20px;
            }

            .header {
                background-color: rgba(0, 0, 0, 0.8);
                padding: 20px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                color: #f1c40f;
                margin-top: 10px;
                margin-bottom: 20px;
                border-radius: 10px 10px 10px 10px;
            }

            h2, h3 {
                color: #f1c40f;
                text-shadow: 1px 1px 3px black;
            }

            .auction-grid {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
                margin-bottom: 30px;
            }

            .auction-card {
                background-color: rgba(0, 0, 0, 0.75);
                padding: 15px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.7);
                flex: 1 1 calc(33.333% - 20px);
                min-width: 250px;
                max-width: 300px;
            }

            form {
                background-color: rgba(0, 0, 0, 0.75);
                padding: 20px;
                border-radius: 10px;
                margin-bottom: 30px;
                max-width: 500px;
            }

            input[type="text"], input[type="number"], button {
                padding: 10px;
                margin: 10px 10px;
                width: 100%;
                border-radius: 5px;
                border: none;
            }

            button {
                background-color: #f39c12;
                color: white;
                font-weight: bold;
                cursor: pointer;
            }

            a {
                color: #1abc9c;
                text-decoration: none;
            }

            hr {
                border: none;
                height: 2px;
                background-color: #ccc;
                margin: 30px 0;
            }
        </style>
    </head>
    <body>
        <header class="header">
            <h2>Welcome, <%= user.getUsername() %>!</h2>
            <a href="login.jsp">Logout</a>
        </header>

        <main>
            <section>
                <h3>Your Auctions</h3>
                <div class="auction-grid">
                <%
                    if (auctions != null && !auctions.isEmpty()) {
                        for (Auction auction : auctions) {
                %>
                    <div class="auction-card">
                        <h4><%= auction.getItemName() %></h4>
                        <p><strong>Start Price:</strong> ₹<%= auction.getStartPrice() %></p>
                        <p><strong>End Date:</strong> <%= auction.getEndTime() %></p>
                    </div>
                <%
                        }
                    } else {
                %>
                    <p>You have not posted any auctions yet.</p>
                <%
                    }
                %>
                </div>
            </section>

            <hr>

            <section>
                <h3>Create New Auction</h3>
                <form action="AuctionServlet" method="post">
                    <input type="hidden" name="userId" value="<%= user.getId() %>" />
                    <input type="text" name="itemName" placeholder="Item Name" required />
                    <input type="number" name="startPrice" placeholder="Start Price" required />
                    <button type="submit">Create Auction</button>
                </form>
            </section>

            <hr>

            <section>
                <h3>Search & Bid on Auctions</h3>
                <form action="AuctionServlet" method="get">
                    <input type="hidden" name="action" value="search" />
                    <input type="text" name="keyword" placeholder="Search for items" required />
                    <button type="submit">Search</button>
                </form>
                <% if (searchPerformed != null && searchPerformed && searchResults != null) { %>
                    <hr>
                    <h3>Search Results</h3>
                    <% if (!searchResults.isEmpty()) { %>
                        <div class="auction-grid">
                        <% for (Auction auction : searchResults) { %>
                            <div class="auction-card">
                                <h4><%= auction.getItemName() %></h4>
                                <p><strong>Start Price:</strong> ₹<%= auction.getStartPrice() %></p>
                                <p><strong>End Date:</strong> <%= auction.getEndTime() %></p>
                                <a href="place_bid.jsp?id=<%= auction.getId() %>">Place a Bid</a>
                            </div>
                        <% } %>
                        </div>
                    <% } else { %>
                        <p>No auctions found for your search.</p>
                    <% } %>
                <% } %>
            </section>
        </main>
    </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Online Auction</title>
    <style>
        body {
            background: url('assets/login.jpg') no-repeat center center fixed;
            background-size: cover;
            font-family: Arial, sans-serif;
        }

        .form-container {
            background-color: rgba(0, 0, 0, 0.8);
            max-width: 400px;
            margin: 100px auto;
            padding: 30px;
            color: white;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.6);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #2ecc71;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input[type="text"], input[type="email"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: none;
            border-radius: 5px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #2ecc71;
            border: none;
            border-radius: 5px;
            color: white;
            font-weight: bold;
            cursor: pointer;
        }

        a {
            color: #00acee;
            display: block;
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>User Registration</h2>
        <form action="UserServlet" method="post">
            <input type="hidden" name="action" value="register" />
            <label for="username">Username:</label>
            <input type="text" name="username" id="username" required />
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" required />
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required />
            <input type="submit" value="Register" />
        </form>
        <p>Already have an account? <a href="login.jsp">Sign in</a></p>
    </div>
</body>
</html>

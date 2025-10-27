<%@ page import="com.bankapp.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
    <style>
        body {
            min-height: 100vh;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: #fff;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            padding-top: 60px;
        }
        .dashboard-card {
            background: rgba(255 255 255 / 0.1);
            border-radius: 20px;
            padding: 40px 50px;
            max-width: 700px;
            margin: auto;
            box-shadow: 0 15px 40px rgb(0 0 0 / 0.3);
            transition: transform 0.3s ease;
            text-align: center;
        }
        .dashboard-card:hover {
            transform: translateY(-8px);
        }
        h2 {
            font-weight: 700;
            margin-bottom: 20px;
        }
        h4 {
            font-weight: 600;
            margin-bottom: 35px;
            color: #d1d1d1;
        }
        .btn-custom {
            background: linear-gradient(45deg, #6bc1ff, #90e0ef);
            color: #0b1f3f;
            font-weight: 600;
            border-radius: 8px;
            padding: 15px 30px;
            margin: 0 15px 15px 0;
            box-shadow: 0 5px 15px rgba(107, 193, 255, 0.75);
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
        }
        .btn-custom:hover {
            background: linear-gradient(45deg, #90e0ef, #6bc1ff);
            box-shadow: 0 8px 24px rgba(144, 224, 239, 0.85);
            color: #041c39;
        }
        .btn-warning {
            font-weight: 700;
            border-radius: 8px;
            padding: 15px 28px;
            box-shadow: 0 5px 15px rgba(255, 193, 7, 0.75);
            border: none;
            color: #1f2027;
            background-color: #ffc107cc;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .btn-warning:hover {
            background-color: #f9bc07;
            box-shadow: 0 8px 24px rgba(249, 188, 7, 0.95);
        }
        .btn-danger {
            font-weight: 700;
            border-radius: 8px;
            padding: 15px 30px;
            margin-top: 25px;
            width: 100%;
            box-shadow: 0 5px 15px rgba(220, 53, 69, 0.75);
            border: none;
            color: #fff;
            background-color: #dc3545;
            cursor: pointer;
            transition: background-color 0.3s ease;
            display: inline-block;
        }
        .btn-danger:hover {
            background-color: #b02a37cc;
            box-shadow: 0 8px 24px rgba(176, 42, 55, 0.95);
        }
        .message-success, .message-error {
            text-align: center;
            font-weight: 600;
            margin-top: 20px;
        }
        .message-success {
            color: #90ee90;
        }
        .message-error {
            color: #ff6b6b;
        }
    </style>
</head>
<body>
    <div class="dashboard-card" data-aos="fade-up">
        <h2>Welcome, <%= user.getName() %> 👋</h2>
        <h4>Account Balance: ₹<%= String.format("%,.2f", user.getAccountBalance()) %></h4>

        <a href="deposit" class="btn-custom btn-lg">Deposit</a>
        <a href="withdraw" class="btn-custom btn-lg">Withdraw</a>
        <form action="chequebook-request" method="post" style="display: inline;">
            <button type="submit" class="btn-warning btn-lg">Request Cheque Book</button>
        </form><br/>
        <a href="logout" class="btn-danger btn-lg mt-3">Logout</a>

        <p class="message-success">${message}</p>
        <p class="message-error">${error}</p>
    </div>

    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    <script>AOS.init();</script>
</body>
</html>

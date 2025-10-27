<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #f5f5f5;
            margin: 0;
            padding: 20px;
        }
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: #4CAF50;
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 30px;
        }
        .stats {
            display: flex;
            gap: 40px;
            margin-bottom: 30px;
        }
        .stat-card {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgb(0 0 0 / 0.1);
            flex: 1;
            text-align: center;
        }
        .stat-number {
            font-size: 2em;
            font-weight: bold;
            color: #4CAF50;
        }
        nav a {
            margin-right: 20px;
            background: white;
            padding: 15px 25px;
            border-radius: 10px;
            text-decoration: none;
            color: #333;
            font-weight: 600;
            box-shadow: 0 2px 10px rgb(0 0 0 / 0.1);
            transition: background 0.3s ease;
        }
        nav a:hover {
            background: #4caf5070;
        }
        .logout {
            background: #f44336;
            color: white !important;
        }
    </style>
</head>
<body>
<header>
    <h1>🏦 Admin Dashboard</h1>
    <a href="/admin/logout" class="logout">Logout</a>
</header>

<div class="stats">
    <div class="stat-card">
        <div class="stat-number">${userCount}</div>
        <div>Total Users</div>
    </div>
    <div class="stat-card">
        <div class="stat-number">₹<fmt:formatNumber value="${totalBalance}" type="currency" currencySymbol="" maxFractionDigits="2"/></div>

        <div>Total Balance</div>
    </div>
</div>

<nav>
    <a href="/admin/users">Manage Users</a>
    <a href="/admin/add-user">Add User</a>
</nav>
</body>
</html>

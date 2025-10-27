<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
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
        table {
            width: 100%;
            background: white;
            border-collapse: collapse;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background: #f8f9fa;
            font-weight: 600;
        }
        tr:hover {
            background: #f5f5f5;
        }
        a.btn {
            padding: 8px 16px;
            text-decoration: none;
            border-radius: 4px;
            font-size: 14px;
            margin-right: 5px;
            color: white;
        }
        a.edit {
            background: #2196F3;
        }
        a.delete {
            background: #f44336;
        }
        a.back {
            background: #666;
            margin-left: auto;
            padding: 10px 20px;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }
    </style>
</head>
<body>

<header>
    <h1>User Management</h1>
    <a href="/admin/dashboard" class="back">Back to Dashboard</a>
</header>

<table>
    <thead>
        <tr>
            <th>User ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Balance</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>₹${user.balance}</td>
                <td>
                    <a href="/admin/edit-user?id=${user.id}" class="btn edit">Edit</a>
                    <a href="/admin/delete-user?id=${user.id}" class="btn delete"
                       onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty userList}">
            <tr><td colspan="5" style="text-align:center; padding:20px;">No users found.</td></tr>
        </c:if>
    </tbody>
</table>
</body>
</html>

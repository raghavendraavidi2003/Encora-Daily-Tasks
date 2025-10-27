<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add User</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #f5f5f5;
            margin: 0;
            padding: 20px;
        }
        .form-container {
            max-width: 450px;
            margin: auto;
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        input[type="text"], input[type="email"], input[type="password"], input[type="number"] {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 6px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 14px;
            background: #4CAF50;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 12px;
        }
        button:hover {
            background: #45a049;
        }
        .error {
            color: red;
            margin-top: 12px;
            text-align: center;
        }
        a.back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #666;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Add New User</h2>
    <form action="/admin/add-user" method="post">
        <input type="text" name="name" placeholder="Full Name" required autofocus>
        <input type="email" name="email" placeholder="Email Address" required>
        <input type="password" name="pwd" placeholder="Password" required>
        <input type="number" name="balance" placeholder="Initial Balance" required step="0.01">
        <button type="submit">Add User</button>
    </form>
    <p class="error">${error}</p>
    <a href="/admin/users" class="back-link">← Back to User List</a>
</div>
</body>
</html>

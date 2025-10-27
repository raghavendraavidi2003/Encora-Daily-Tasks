<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Success</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #e8f5e8;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }
        .success-box {
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
            text-align: center;
            max-width: 400px;
        }
        .success-icon {
            font-size: 4rem;
            color: #4CAF50;
            margin-bottom: 20px;
        }
        h2 {
            color: #4CAF50;
            margin-bottom: 20px;
        }
        .btn {
            display: inline-block;
            padding: 12px 24px;
            background: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            margin: 5px;
            font-weight: 600;
        }
        .btn:hover {
            background: #45a049;
        }
        .btn-secondary {
            background: #2196F3;
        }
        .btn-secondary:hover {
            background: #0b7dda;
        }
    </style>
</head>
<body>
    <div class="success-box">
        <div class="success-icon">✅</div>
        <h2>Success!</h2>
        <p>${message}</p>
        <div>
            <a href="/admin/users" class="btn">View All Users</a>
            <a href="/admin/add-user" class="btn btn-secondary">Add Another User</a>
            <a href="/admin/dashboard" class="btn btn-secondary">Dashboard</a>
        </div>
    </div>
</body>
</html>

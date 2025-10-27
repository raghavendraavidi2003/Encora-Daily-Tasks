<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #ffebee;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }
        .error-box {
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
            text-align: center;
            max-width: 400px;
        }
        .error-icon {
            font-size: 4rem;
            color: #f44336;
            margin-bottom: 20px;
        }
        h2 {
            color: #f44336;
            margin-bottom: 20px;
        }
        .btn {
            display: inline-block;
            padding: 12px 24px;
            background: #f44336;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            margin: 5px;
            font-weight: 600;
        }
        .btn:hover {
            background: #d32f2f;
        }
        .btn-secondary {
            background: #666;
        }
        .btn-secondary:hover {
            background: #555;
        }
    </style>
</head>
<body>
    <div class="error-box">
        <div class="error-icon">❌</div>
        <h2>Operation Failed</h2>
        <p>${message}</p>
        <div>
            <a href="/admin/add-user" class="btn">Try Again</a>
            <a href="/admin/users" class="btn btn-secondary">View Users</a>
            <a href="/admin/dashboard" class="btn btn-secondary">Dashboard</a>
        </div>
    </div>
</body>
</html>

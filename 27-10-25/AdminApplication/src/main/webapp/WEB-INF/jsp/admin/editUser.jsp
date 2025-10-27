<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Edit User - Admin Portal</title>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(135deg, #ece9e6, #ffffff);
      height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      margin: 0;
    }

    .form-container {
      background: white;
      padding: 40px 50px;
      border-radius: 12px;
      box-shadow: 0 10px 25px rgba(0,0,0,0.1);
      width: 400px;
      animation: fadeIn 0.7s ease;
    }

    h2 {
      text-align: center;
      color: #333;
      margin-bottom: 20px;
    }

    label {
      font-weight: 600;
      color: #333;
      display: block;
      margin-top: 15px;
    }

    input[type="text"],
    input[type="email"],
    input[type="password"],
    input[type="number"] {
      width: 100%;
      padding: 12px;
      margin-top: 6px;
      border: 1px solid #ccc;
      border-radius: 6px;
      box-sizing: border-box;
      transition: border-color 0.3s;
    }

    input:focus {
      border-color: #4CAF50;
      outline: none;
    }

    button {
      width: 100%;
      padding: 13px;
      background: #4CAF50;
      color: white;
      border: none;
      border-radius: 6px;
      margin-top: 20px;
      cursor: pointer;
      font-size: 15px;
      font-weight: 600;
      transition: background 0.3s ease;
    }

    button:hover {
      background: #45a049;
    }

    .back-link {
      display: block;
      margin-top: 20px;
      text-align: center;
      text-decoration: none;
      color: #2196F3;
      font-weight: 600;
      transition: color 0.3s;
    }

    .back-link:hover {
      color: #0d47a1;
    }

    .user-id {
      font-size: 14px;
      color: #555;
      background: #f3f3f3;
      padding: 8px;
      border-radius: 5px;
      text-align: center;
      margin-bottom: 15px;
    }

    .error {
      color: red;
      text-align: center;
      margin-top: 10px;
      font-weight: 600;
    }

    @keyframes fadeIn {
      from {opacity: 0; transform: translateY(-15px);}
      to {opacity: 1; transform: translateY(0);}
    }
  </style>
</head>
<body>

<div class="form-container">
  <h2>Edit User Details</h2>
  <div class="user-id">User ID: <b>${user.id}</b></div>

  <form action="/admin/update-user" method="post">
    <input type="hidden" name="id" value="${user.id}" />

    <label for="name">Full Name</label>
    <input type="text" id="name" name="name" value="${user.name}" placeholder="Enter full name" required />

    <label for="email">Email Address</label>
    <input type="email" id="email" name="email" value="${user.email}" placeholder="Enter email" required />

    <label for="pwd">Password</label>
    <input type="password" id="pwd" name="pwd" value="${user.pwd}" placeholder="Enter password" required />

    <label for="balance">Account Balance</label>
    <input type="number" id="balance" name="balance" value="${user.balance}" step="0.01" placeholder="Enter balance" required />

    <button type="submit">Update User</button>
  </form>

  <p class="error">${error}</p>
  <a href="/admin/users" class="back-link">← Back to User List</a>
</div>
</body>
</html>

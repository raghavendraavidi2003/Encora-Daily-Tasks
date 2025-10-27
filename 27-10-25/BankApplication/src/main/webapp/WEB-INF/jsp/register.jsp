<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Create Account | BankApp</title>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
  <style>
    body {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      height: 100vh;
      margin: 0;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      color: white;
      display: flex;
      justify-content: center;
      align-items: center;
    }
    .register-container {
      background: rgba(255, 255, 255, 0.1);
      padding: 3rem 3rem;
      max-width: 420px;
      border-radius: 1rem;
      box-shadow: 0 10px 30px rgba(0,0,0,0.3);
      width: 100%;
      animation: fadeIn 0.7s ease forwards;
    }
    h2 {
      text-align: center;
      font-weight: 600;
      margin-bottom: 2rem;
    }
    .form-floating > input {
      background: transparent;
      border: none;
      border-bottom: 2px solid #bbb;
      margin-bottom: 1.8rem;
      padding: 1rem 0.5rem;
      font-size: 1rem;
      color: white;
    }
    .form-floating > input:focus {
      border-color: #9f7aea;
      box-shadow: 0 1px 0 0 #9f7aea;
      outline: none;
    }
    label {
      color: #ddd;
      padding-left: 0.5rem;
    }
    button {
      width: 100%;
      padding: 1rem;
      font-weight: 600;
      font-size: 1.1rem;
      background: #9f7aea;
      border: none;
      border-radius: 0.5rem;
      cursor: pointer;
      color: white;
      transition: background 0.3s;
    }
    button:hover {
      background: #7f53ac;
    }
    .message-success, .message-error {
      text-align: center;
      font-weight: 600;
      margin-top: 1rem;
    }
    .message-success {
      color: #81ecec;
    }
    .message-error {
      color: #fab1a0;
    }
    .footer-text {
      margin-top: 1.5rem;
      text-align: center;
      font-size: 0.9rem;
    }
    .footer-text a {
      color: #a78bfa;
      font-weight: 600;
      text-decoration: none;
    }
    .footer-text a:hover {
      text-decoration: underline;
    }
    @keyframes fadeIn {
      from { opacity: 0; transform: translateY(15px);}
      to { opacity: 1; transform: translateY(0);}
    }
  </style>
</head>
<body>
<div class="register-container">
  <h2>Create Account</h2>
  <form action="register" method="post" novalidate>
    <div class="form-floating">
      <input type="text" id="name" name="name" placeholder="Full Name" required autofocus />
      <label for="name">Full Name</label>
    </div>
    <div class="form-floating">
      <input type="email" id="email" name="email" placeholder="name@example.com" required />
      <label for="email">Email address</label>
    </div>
    <div class="form-floating">
      <input type="password" id="password" name="password" placeholder="Password" minlength="6" required />
      <label for="password">Password</label>
    </div>
    <button type="submit">Register</button>
  </form>
  <c:if test="${not empty message}">
    <p class="message-success">${message}</p>
  </c:if>
  <c:if test="${not empty error}">
    <p class="message-error">${error}</p>
  </c:if>
  <p class="footer-text">
    Already have an account? <a href="login">Login</a>
  </p>
</div>
</body>
</html>

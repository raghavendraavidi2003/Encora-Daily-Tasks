<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login | BankApp</title>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
  <style>
    body,html {
      height: 100%;
      margin: 0;
      font-family: 'Inter', sans-serif;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      display: flex;
      justify-content: center;
      align-items: center;
      color: #eee;
    }
    .login-box {
      background-color: rgba(26, 26, 46, 0.95);
      padding: 3rem 2.5rem;
      border-radius: 1rem;
      max-width: 400px;
      width: 100%;
      box-shadow: 0 10px 30px rgba(0,0,0,0.5);
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
      border-bottom: 2px solid #555;
      color: #eee;
      padding: 1rem 0.5rem;
      font-size: 1rem;
      margin-bottom: 1.8rem;
      border-radius: 0;
    }
    .form-floating > input:focus {
      border-color: #9f7aea;
      outline: none;
      box-shadow: 0 1px 0 0 #9f7aea;
    }
    label {
      color: #bbb;
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
      color: #fff;
      transition: background 0.3s ease;
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
      color: #6ee7b7;
    }
    .message-error {
      color: #f87171;
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

<div class="login-box">
  <h2>Login to Your Account</h2>
  <form action="login" method="post" novalidate>
    <div class="form-floating mb-4">
  <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com" required autofocus />
  <label for="email">Email address</label>
</div>

    <button type="submit">Get OTP</button>
  </form>
  <c:if test="${not empty message}">
    <p class="message-success">${message}</p>
  </c:if>
  <c:if test="${not empty error}">
    <p class="message-error">${error}</p>
  </c:if>
  <p class="footer-text">
    Don't have an account? <a href="register">Register</a>
  </p>
</div>

</body>
</html>

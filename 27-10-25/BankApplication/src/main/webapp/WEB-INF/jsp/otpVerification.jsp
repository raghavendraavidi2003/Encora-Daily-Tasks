<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>OTP Verification | BankApp</title>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
  <style>
    body,html {
      height: 100%;
      margin: 0;
      background: linear-gradient(135deg, #43cea2 0%, #185a9d 100%);
      font-family: 'Inter', sans-serif;
      color: white;
      display: flex;
      justify-content: center;
      align-items: center;
    }
    .otp-container {
      background: rgba(0,0,0,0.75);
      padding: 3rem 3rem;
      border-radius: 1rem;
      max-width: 400px;
      width: 100%;
      text-align: center;
      box-shadow: 0 10px 30px rgba(0,0,0,0.3);
      animation: fadeIn 0.7s ease forwards;
    }
    h2 {
      margin-bottom: 2rem;
      font-weight: 600;
    }
    input[type="text"] {
      width: 100%;
      padding: 1rem;
      font-size: 1.2rem;
      border-radius: 8px;
      border: none;
      outline: none;
      text-align: center;
      letter-spacing: 0.3em;
      margin-bottom: 1.5rem;
    }
    button {
      width: 100%;
      padding: 1rem;
      font-size: 1.1rem;
      font-weight: 600;
      border-radius: 8px;
      border: none;
      cursor: pointer;
      background-color: #3ddc97;
      color: #153b50;
      transition: background-color 0.3s ease;
    }
    button:hover {
      background-color: #2bb67e;
    }
    .message-error {
      margin-top: 1rem;
      font-weight: 600;
      color: #ff6b6b;
    }
    @keyframes fadeIn {
      from { opacity: 0; transform: translateY(15px);}
      to { opacity: 1; transform: translateY(0);}
    }
  </style>
</head>
<body>
<div class="otp-container">
  <h2>Verify Your OTP</h2>
  <form action="verify-otp" method="post" novalidate>
    <input type="hidden" name="email" value="${email}" />
    <input type="text" name="otp" maxlength="6" pattern="\\d{6}" placeholder="Enter 6-digit OTP" required autofocus />
    <button type="submit">Verify OTP</button>
  </form>
  <c:if test="${not empty error}">
    <p class="message-error">${error}</p>
  </c:if>
</div>
</body>
</html>

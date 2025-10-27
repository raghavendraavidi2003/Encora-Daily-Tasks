<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Success | BankApp</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
  <style>
      body {
          background: linear-gradient(to right, #00c6ff, #0072ff);
          color: #fff;
          min-height: 100vh;
          display: flex;
          align-items: center;
          justify-content: center;
          font-family: 'Segoe UI', sans-serif;
          margin: 0;
      }
      .card {
          background-color: #1c2833;
          border-radius: 15px;
          padding: 40px;
          max-width: 450px;
          width: 100%;
          text-align: center;
          box-shadow: 0 10px 20px rgba(0,0,0,0.3);
          animation: fadeIn 0.7s ease forwards;
      }
      h2 {
          margin-bottom: 20px;
          font-weight: 700;
      }
      .btn {
          margin-top: 20px;
      }
  </style>
</head>
<body>
<div class="card" data-aos="fade-in">
  <h2>Success!</h2>
  <p class="lead">${message}</p>
  <a href="dashboard" class="btn btn-light btn-lg mt-3">Back to Dashboard</a>
</div>

<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script>AOS.init();</script>
</body>
</html>

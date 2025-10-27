<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Withdraw Funds</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet"/>
    <style>
        body {
            min-height: 100vh;
            background: linear-gradient(135deg, #ff9966, #ff5e62);
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            color: white;
        }
        .withdraw-card {
            background: rgba(0,0,0,0.65);
            border-radius: 20px;
            padding: 40px 45px;
            width: 380px;
            box-shadow: 0 8px 24px rgba(0,0,0,0.35);
            transition: transform 0.3s ease;
            text-align: center;
        }
        .withdraw-card:hover {
            transform: translateY(-6px);
            box-shadow: 0 20px 40px rgba(0,0,0,0.5);
        }
        h3 {
            font-weight: 700;
            margin-bottom: 32px;
        }
        label {
            display: block;
            font-weight: 600;
            margin-bottom: 6px;
        }
        input[type="number"] {
            width: 100%;
            padding: 14px;
            font-size: 1.1rem;
            border-radius: 10px;
            border: none;
            outline: none;
            box-shadow: inset 3px 3px 6px rgba(255,255,255,0.3),
                        inset -3px -3px 6px rgba(0,0,0,0.25);
            text-align: center;
            margin-bottom: 1.8rem;
        }
        button.btn-withdraw {
            background: #dc3545;
            border: none;
            font-weight: 700;
            padding: 14px;
            font-size: 1.1rem;
            border-radius: 10px;
            cursor: pointer;
            color: white;
            width: 100%;
            box-shadow: 0 8px 20px rgba(220, 53, 69, 0.6);
            transition: background 0.3s ease;
        }
        button.btn-withdraw:hover {
            background: #b02a37cc;
        }
        .message-success, .message-error {
            margin-top: 1rem;
            font-weight: 600;
            text-align: center;
        }
        .message-success { color: #9de29e; }
        .message-error { color: #ff8b94; }
        .back-link {
            margin-top: 26px;
            font-weight: 600;
            color: #ffc6c6;
            text-decoration: none;
            display: block;
            text-align: center;
            transition: color 0.3s ease;
        }
        .back-link:hover { color: #fff; }
    </style>
</head>
<body>

<div class="withdraw-card" data-aos="zoom-in">
    <h3>Withdraw Funds</h3>
    <form action="withdraw" method="post" novalidate>
        <label for="amount">Enter amount</label>
        <input type="number" id="amount" name="amount" placeholder="₹0.00" min="1" step="0.01" required autofocus>
        <button class="btn-withdraw" type="submit">Withdraw</button>
    </form>

    <c:if test="${not empty message}">
        <p class="message-success">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p class="message-error">${error}</p>
    </c:if>

    <a href="dashboard" class="back-link">← Back to Dashboard</a>
</div>

<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script>AOS.init();</script>

</body>
</html>

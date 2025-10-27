<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Deposit Funds</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet"/>
    <style>
        body {
            min-height: 100vh;
            background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            color: #fff;
        }
        .card {
            background: rgba(255, 255, 255, 0.12);
            padding: 3rem 3.5rem;
            border-radius: 1rem;
            width: 400px;
            box-shadow: 0 0 15px 3px rgba(37, 117, 252, 0.4);
            backdrop-filter: blur(10px);
            text-align: center;
        }
        h3 {
            margin-bottom: 2rem;
            font-weight: 700;
            text-shadow: 0 1px 1px rgba(0,0,0,0.25);
        }
        label {
            font-weight: 600;
            display: block;
            margin-bottom: 0.5rem;
            text-shadow: 0 1px 1px rgba(0,0,0,0.3);
        }
        input[type="number"] {
            width: 100%;
            padding: 15px;
            border-radius: 10px;
            border: none;
            font-size: 1.2rem;
            outline: none;
            box-shadow: inset 3px 3px 6px rgba(255, 255, 255, 0.3),
                        inset -4px -4px 6px rgba(0, 0, 0, 0.2);
            margin-bottom: 2rem;
            text-align: center;
        }
        button {
            background-color: #18d26e;
            border: none;
            font-weight: 700;
            padding: 15px;
            border-radius: 10px;
            font-size: 1.2rem;
            cursor: pointer;
            box-shadow: 0 8px 15px rgba(24, 210, 110, 0.5);
            transition: background-color 0.3s;
            width: 100%;
        }
        button:hover {
            background-color: #0fad4f;
        }
        .message-success, .message-error {
            margin-top: 1rem;
            font-weight: 600;
            text-align: center;
        }
        .message-success {
            color: #a8e6cf;
        }
        .message-error {
            color: #ff8b94;
        }
        .back-link {
            margin-top: 18px;
            color: #dcedc1;
            display: block;
            text-align: center;
            font-weight: 600;
            text-decoration: none;
            transition: color 0.3s ease;
        }
        .back-link:hover {
            color: #fff;
        }
    </style>
</head>
<body>

<div class="card" data-aos="flip-up">
    <h3>Deposit Funds</h3>
    <form action="deposit" method="post" novalidate>
        <label for="amount">Enter amount</label>
        <input type="number" id="amount" name="amount" min="1" placeholder="₹0.00" step="0.01" required autofocus />
        <button type="submit">Deposit</button>
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

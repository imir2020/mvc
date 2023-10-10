<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authentication Menu</title>
    <link rel="stylesheet" href="registration.css">
</head>
<body>
<header>
    <h1>Registration Menu</h1>
</header>
<main>
    <form action="registration" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="age">Age:</label>
        <input type="text" id="age" name="age" required>
        <br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <input type="submit" value="Register">
    </form>
    <p>I have an account! <a href="index.jsp">Log in</a></p>
</main>
</body>


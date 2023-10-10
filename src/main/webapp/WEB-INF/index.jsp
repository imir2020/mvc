<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authentication Menu</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<header>
    <h1>Authentication Menu</h1>
</header>
<main>
    <form action="authentication" method="post">
        <label for="login">Name:</label>
        <input type="text" id="login" name="login" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <input type="submit" value="Login">
    </form>
    <p>Don't have an account? <a href="registration.jsp">Register here</a></p>
</main>
</body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create driver</title>
    </head>
    <body>
        <h1>Please provide driver's credentials.</h1>

        <form method="post" action="${pageContext.request.contextPath}/drivers/create">
            Driver's name:<input type="text" name="name">
            Driver's license number:<input type="text" name="license">

            <button type="submit">Create</button>
        </form>

        <p>
            <a href="${pageContext.request.contextPath}/">
                <button type="submit">Main page</button>
            </a>
        </p>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create driver</title>
    </head>
    <body>
        <h1>Please provide driver's credentials.</h1>
        <form method="post" action="${pageContext.request.contextPath}/drivers/create">
            Driver's name:<input type="text" name="driver_name" required><br/><br/>
            Driver's license number:<input type="text" name="driver_license" required><br/><br/>
            Driver's login:<input type="text" name="driver_login" required><br/><br/>
            Driver's password:<input type="text" name="driver_password" required><br/><br/>

            <button type="submit">Create</button>
        </form>
        <p>
            <a href="${pageContext.request.contextPath}/">
                <button type="submit">Main page</button>
            </a>
        </p>
    </body>
</html>

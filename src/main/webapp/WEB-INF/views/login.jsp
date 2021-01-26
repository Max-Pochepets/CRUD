<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
        <style type="text/css">
            .loginSection {
                width: 350px;
                border: solid 2px black;
                margin: 0 auto;
            }
        </style>
    </head>
    <body>
        <div style="text-align: center; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%)">
            <h2>Log in</h2>
            <div class="loginSection">
                <h5 style="color: crimson">${error_message}</h5>
                <form style="display: inline" method="post" action="${pageContext.request.contextPath}/login">
                    <h4>Login</h4>
                    <input type="text" name="login" required>
                    <h4>Password</h4>
                    <input type="password" name="password" required>
                    <br/>
                    <br/>
                    <button type="submit">Log in</button>
                </form>
                <form style="display: inline" method="get" action="${pageContext.request.contextPath}/drivers/create">
                    <button type="submit">New driver</button>
                </form>
                <br/>
                <br/>
            </div>
        </div>
    </body>
</html>

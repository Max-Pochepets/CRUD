<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
            <title>Create manufacturer</title>
        </head>
        <body>
        <h1>Please provide manufacturers credentials.</h1>

        <form method="post" action="${pageContext.request.contextPath}/manufacturers/create">
            Manufacturer's name:<input type="text" name="name">
            Manufacturer's country:<input type="text" name="country">

            <button type="submit">Create</button>
        </form>

        <p>
            <a href="${pageContext.request.contextPath}/">
                <button type="submit">Main page</button>
            </a>
        </p>
    </body>
</html>

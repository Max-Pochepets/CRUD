<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create car</title>
    </head>
    <body>
        <h1>Please provide car's credentials.</h1>

        <form method="post" action="${pageContext.request.contextPath}/cars/create">
            Car's model:<input type="text" value="${carModel}" name="car_model" required>
            Car's manufacturer's id:<input type="number" min="1" name="manufacturer_id" required>

            <button type="submit">Create</button>
        </form>

        <p>
            <a href="${pageContext.request.contextPath}/">
                <button type="submit">Main page</button>
            </a>
        </p>
    </body>
</html>

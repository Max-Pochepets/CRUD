<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver</title>
</head>
<body>
    <h1>Please provide car's and driver's id.</h1>

    <h4 style="color: crimson">${message}</h4>

    <form method="post" action="${pageContext.request.contextPath}/cars/drivers/add">
        Car's id:<input type="number" name="carID" required>
        Driver's id:<input type="number" name="driverID" required>

        <button type="submit">Add</button>
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/">
            <button type="submit">Main page</button>
        </a>
    </p>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete driver from car</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/cars/drivers/delete">
        Car's id:<input type="text" name="carID">
        Driver's id:<input type="text" name="driverID">

        <button type="submit">Create</button>
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/">
            <button type="submit">Main page</button>
        </a>
    </p>
</body>
</html>

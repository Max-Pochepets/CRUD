<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD</title>
</head>
<body>
    <h1>Greetings! Current time: ${time}</h1>
    <form style="display: inline" method="post" action="${pageContext.request.contextPath}/inject">
        <button type="submit">Inject data</button>
    </form>
    <h3>Cars</h3>
        <form action="${pageContext.request.contextPath}/cars/">
            <button type="submit">All cars</button>
        </form>
    <h3>Driver</h3>
        <form style="display: inline" method="get" action="${pageContext.request.contextPath}/drivers/">
            <button type="submit">All drivers</button>
        </form>
    <form style="display: inline" method="get" action="${pageContext.request.contextPath}/drivers/my_cars">
        <button type="submit">My cars</button>
    </form>
    <h3>Manufacturers</h3>
        <form style="display: inline" method="get" action="${pageContext.request.contextPath}/manufacturers/">
            <button type="submit">All manufacturers</button>
        </form>
</body>
</html>

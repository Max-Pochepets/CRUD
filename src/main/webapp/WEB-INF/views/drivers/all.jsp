<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>All drivers</title>
    </head>
    <body>
        <h1>All drivers</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>License number</th>
                <th>Login</th>
            </tr>
            <c:forEach var="driver" items="${drivers}">
                <tr>
                    <td>
                        <c:out value="${driver.id}"/>
                    </td>
                    <td>
                        <c:out value="${driver.name}"/>
                    </td>
                    <td>
                        <c:out value="${driver.licenseNumber}"/>
                    </td>
                    <td>
                        <c:out value="${driver.login}"/>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/drivers/delete?driver_id=${driver.id}">
                            <button type="submit">Delete</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <form style="display: inline" method="get" action="${pageContext.request.contextPath}/drivers/create">
                <button type="submit">Create a driver</button>
            </form>
            <form style="display: inline" method="get" action="${pageContext.request.contextPath}/">
                <button type="submit">Main page</button>
            </form>
        </p>
    </body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>All manufacturers</title>
    </head>
    <body>
        <h1>All manufacturers</h1>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>License number</th>
            </tr>
            <c:forEach var="manufacturer" items="${manufacturers}">
                <tr>
                    <td>
                        <c:out value="${manufacturer.id}"/>
                    </td>
                    <td>
                        <c:out value="${manufacturer.name}"/>
                    </td>
                    <td>
                        <c:out value="${manufacturer.country}"/>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manufacturers/delete?id=${manufacturer.id}">
                            <button type="submit">Delete</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <p>
            <form style="display: inline" method="get" action="${pageContext.request.contextPath}/manufacturers/create">
                <button type="submit">Create a manufacturer</button>
            </form>
            <form style="display: inline" method="get" action="${pageContext.request.contextPath}/">
                <button type="submit">Main page</button>
            </form>
        </p>
    </body>
</html>

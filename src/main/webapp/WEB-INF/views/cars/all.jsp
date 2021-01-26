<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>All cars</title>
    </head>
    <body>
        <h1>All cars</h1>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Model</th>
                <th>Manufacturer</th>
                <th>Manufacturer Country</th>
                <th>Driver</th>
                <th>License number</th>
            </tr>
            <c:forEach var="car" items="${cars}">
                <tr>
                    <td>
                        <c:out value="${car.id}"/>
                    </td>
                    <td>
                        <c:out value="${car.model}"/>
                    </td>
                    <td>
                        <c:out value="${car.manufacturer.name}"/>
                    </td>
                    <td>
                        <c:out value="${car.manufacturer.country}"/>
                    </td>
                    <td>
                        <c:forEach var="driver" items="${car.drivers}">
                            <c:out value="${driver.name}"/>
                            <br />
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="driver" items="${car.drivers}">
                            <c:out value="${driver.licenseNumber}"/>
                            <br />
                        </c:forEach>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/cars/delete?car_id=${car.id}">
                            <button type="submit">Delete</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <form style="display: inline" method="get" action="${pageContext.request.contextPath}/cars/create">
                <button type="submit">Create a car</button>
            </form>
            <form style="display: inline" method="get" action="${pageContext.request.contextPath}/cars/drivers/add">
                <button type="submit">Add driver to car</button>
            </form>
            <form style="display: inline" method="get" action="${pageContext.request.contextPath}/cars/drivers/delete">
                <button type="submit">Delete driver from car</button>
            </form>
            <form style="display: inline" method="get" action="${pageContext.request.contextPath}/">
                <button type="submit">Main page</button>
            </form>
        </p>
    </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Welcome!</title>
</head>
<body>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Flights</h2></caption>
            <tr>
                <th>Number</th>
                <th>Type</th>
                <th>Departure city</th>
                <th>Departure date</th>
                <th>Arrival city</th>
                <th>Arrival date</th>
            </tr>
            <c:forEach var="flight" items="${flightList}">
                <tr>
                    <td><c:out value="${flight.number}" /></td>
                    <td><c:out value="${flight.type}" /></td>
                    <td><c:out value="${flight.departureCity.name}" /></td>
                    <td><c:out value="${flight.departureDate}" /></td>
                    <td><c:out value="${flight.arrivalCity.name}" /></td>
                    <td><c:out value="${flight.arrivalDate}" /></td>
                </tr>
            </c:forEach>
			<tr>
				<th></th>
				<th>Local time for city ${cityName}</th>
			</tr>
			<tr>
				<td><form action="UserServlet" method="post">
					 <select name="cityName">
					  <c:forEach var="city" items="${cityList}">
					  		<option value="${city.name}">${city.name}</option>
					  </c:forEach>
					</select>
					<input type="submit" value="Check">
					</form>
					</td>
				<td>${localTime}</td>
			</tr>
        </table>
    </div>	
</body>
</html>

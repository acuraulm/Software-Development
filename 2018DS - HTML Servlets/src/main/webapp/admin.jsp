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
                <th>Administrator Operations</th>
            </tr>
            
			   <c:forEach items="${flightList}" var="flight">
			   <form  action="AdminServlet" method="post">
			    <tr>
			    	
                    <td><input name="number" value="${flight.number}" /></td>
                    <td><input name="type" value="${flight.type}" /></td>
                    <td><input name="departureCity" value="${flight.departureCity.name}" /></td>
                    <td><input name="departureDate" value="${flight.departureDate}" /></td>
                    <td><input name="arrivalCity" value="${flight.arrivalCity.name}" /></td>
                    <td><input name="arrivalDate" value="${flight.arrivalDate}" /></td>
                    
                    <td>
                    	<input type="hidden" name="id" value="${flight.id}" />
                    	<input type="submit" name="action" value="Edit"> 
                    	<input type="submit" name="action" value="Delete"> 
                    </td>
                </tr>
                </form>		
			   </c:forEach>
			   <form  action="AdminServlet" method="post">
			   <tr>
                    <td><input name="number"/></td>
                    <td><input name="type" /></td>
                    <td><input name="departureCity" /></td>
                    <td><input name="departureDate"/></td>
                    <td><input name="arrivalCity"/></td>
                    <td><input name="arrivalDate"/></td>
                 	<td>
                 		<input type="hidden" name="id"/>
                 		<input type ="submit" name="action" value="Add">
                    </td>
                </tr>  
			   </form>

           
        </table>
    </div>	
</body>
</html>

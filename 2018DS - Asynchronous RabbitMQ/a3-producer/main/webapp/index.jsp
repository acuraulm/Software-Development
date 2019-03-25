<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Welcome!</title>
</head>
<body>

    <div align="center">
    <form action="IndexServlet" method="post">
       <table border="1" cellpadding="5">
            <caption>
            	<h2>DVD Producer</h2>
            </caption>
            <tr>
                <th>Title: </th>
                <td>
                	<input type="text" name="Title" size="45" 
                		   value="<c:out value='${dvd.title}' />"/>
                </td>
            </tr>
            <tr>
                <th>Year: </th>
                <td>
                	<input type="text" name="Year" size="45"
                		   value="<c:out value='${dvd.year}' />"/>
                </td>
            </tr>
            <tr>
                <th>Price: </th>
                <td>
                	<input type="text" name="Price" size="45"
                		   value="<c:out value='${dvd.price}' />"/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Submit" />
            	</td>
            </tr>
            </table>
    </form>
    </div>

</body>
</html>

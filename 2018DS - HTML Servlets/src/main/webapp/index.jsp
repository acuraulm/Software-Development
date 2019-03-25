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
            	<h2>Login</h2>
            </caption>
            <tr>
                <th>Username: </th>
                <td>
                	<input type="text" name="username" size="45" 
                		   value="<c:out value='${appUser.username}' />"/>
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                	<input type="password" name="password" size="45"
                		   value="<c:out value='${appUser.password}' />"/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Login" />
            	</td>
            </tr>
            </table>
    </form>
    </div>

</body>
</html>

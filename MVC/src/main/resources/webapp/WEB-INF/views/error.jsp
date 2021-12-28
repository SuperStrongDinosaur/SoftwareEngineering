<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<h3>Error</h3>
<table>
    <c:forEach var="error" items="${errors}">
        <tr>
            <td>${error}</td>
        </tr>
    </c:forEach>
</table>
</html>
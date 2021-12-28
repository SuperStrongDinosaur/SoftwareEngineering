<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <title>Edit TODO List</title>
</head>
<body>

<h3>${list.getName()}: ${list.getDescription()}</h3>

<form:form name="worksForm" modelAttribute="work" method="POST" action="/set-done">
    <table>
        <c:forEach var="work" items="${works}">
            <tr>
                <td><input type="checkbox" value="${work.getId()}"
                           onclick="document.worksForm.sid.value=${work.getId()};document.worksForm.bC.click();"
                           <c:if test="${work.getIsDone() == 1}">checked="checked"</c:if>/></td>
                <td>${work.getDescription()}</td>
                <td>${work.getDate()}</td>
            </tr>
        </c:forEach>
        <form:hidden path="listId" value="${list.getId()}"/>
        <input type="hidden" name="sid"/>
        <input type="submit" style="display:none" name="bC"/>
    </table>
</form:form>

<h3>Add new work to List</h3>
<form:form modelAttribute="work" method="POST" action="/add-work">
    <table>
        <tr>
            <td><form:hidden path="listId" value="${list.getId()}"/></td>
        </tr>

        <table>
            <tr>
                <td><form:label path="description">Description:</form:label></td>
                <td><form:input path="description"/></td>
            </tr>
            <tr>
                <td>Deadline:</td> <td><input type = "date" name = "date"></td>
            <tr>
        </table>

        <td><input type="submit" value="Add"></td>
    </table>
</form:form>

<h3>Delete work </h3>
<form:form modelAttribute="work" method="GET">
    <table>
        <tr>
            <tr>
                <td><form:hidden path="listId" value="${list.getId()}"/></td>
            </tr>
            <td>Select list: </td>
            <td><form:select path="id">
                <form:options items="${works}" itemValue="id" itemLabel="description" />
            </form:select></td>
            <td><input type="submit" value="Delete" onclick="form.action='/remove-work';"></td>
        </tr>
    </table>
</form:form>

<br>
<br>
<form:form method="GET" action="/get-lists">
    <input type="submit" value="Back to TODO Lists">
</form:form>

</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Accounts page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<a href="../../index">Back to main menu</a>

<br/>
<br/>

<h1>Accounts list</h1>

<c:if test="${!empty listAccounts}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="80">Contract ID</th>
            <th width="80">Account file name</th>
            <th width="80">Date of account send to client</th>
            <th width="80">Date of account view from client</th>
            <th width="80">Date of the last reminder send to client</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listAccounts}" var="account">
            <tr>
                <td>${account.id}</td>
                <td><a href="/accountdata/${account.id}" target="_blank">${account.contractId}</a></td>
                <td>${account.fileName}</td>
                <td>${account.dateSending}</td>
                <td>${account.dateViewing}</td>
                <td>${account.dateReminderSending}</td>
                <td><a href="<c:url value='/account/edit/${account.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/account/remove/${account.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<h1>Add a Account</h1>

<c:url var="addAction" value="/account/add"/>

<form:form action="${addAction}" commandName="account">
    <table>
        <c:if test="${!empty account.contractId}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="contractId">
                    <spring:message text="Contract ID"/>
                </form:label>
            </td>
            <td>
                <form:input path="contractId"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="fileName">
                    <spring:message text="Account file name"/>
                </form:label>
            </td>
            <td>
                <form:input path="fileName"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <c:if test="${!empty account.contractId}">
                    <input type="submit"
                           value="<spring:message text="Edit Account"/>"/>
                </c:if>
                <c:if test="${empty account.contractId}">
                    <input type="submit"
                           value="<spring:message text="Add Account"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>

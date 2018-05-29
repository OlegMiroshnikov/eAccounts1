<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Contracts page</title>

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

<h1>Contracts list</h1>

<c:if test="${!empty listContracts}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="80">Company ID</th>
            <th width="80">Client ID</th>
            <th width="80">Contract number</th>
            <th width="80">Date of sign</th>
            <th width="80">Date of begin</th>
            <th width="80">Date of end</th>
            <th width="80">Day to send account to client</th>
            <th width="80">Count of days to send reminder to client</th>
            <th width="80">Status</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listContracts}" var="contract">
            <tr>
                <td>${contract.id}</td>
                <td>${contract.companyId}</td>
                <td>${contract.clientId}</td>
                <td><a href="/contractdata/${contract.id}" target="_blank">${contract.number}</a></td>
                <td>${contract.dateSign}</td>
                <td>${contract.dateBegin}</td>
                <td>${contract.dateEnd}</td>
                <td>${contract.dayToSendAccount}</td>
                <td>${contract.countDaysToSendReminder}</td>
                <td>${contract.status}</td>
                <td><a href="<c:url value='/contract/edit/${contract.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/contract/remove/${contract.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<h1>Add a Contract</h1>

<c:url var="addAction" value="/contract/add"/>

<form:form action="${addAction}" commandName="contract">
    <table>
        <c:if test="${!empty contract.number}">
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
                <form:label path="companyId">
                    <spring:message text="Company ID"/>
                </form:label>
            </td>
            <td>
                <form:input path="companyId"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="clientId">
                    <spring:message text="Client ID"/>
                </form:label>
            </td>
            <td>
                <form:input path="clientId"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="number">
                    <spring:message text="Contract number"/>
                </form:label>
            </td>
            <td>
                <form:input path="number"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="dateSign">
                    <spring:message text="Date of the sign"/>
                </form:label>
            </td>
            <td>
                <form:input path="dateSign"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="dateBegin">
                    <spring:message text="Date of the begin"/>
                </form:label>
            </td>
            <td>
                <form:input path="dateBegin"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="dateEnd">
                    <spring:message text="Date of the end"/>
                </form:label>
            </td>
            <td>
                <form:input path="dateEnd"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="dayToSendAccount">
                    <spring:message text="Day to sending the account to client"/>
                </form:label>
            </td>
            <td>
                <form:input path="dayToSendAccount"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="countDaysToSendReminder">
                    <spring:message text="Count of days to sending the reminder to client"/>
                </form:label>
            </td>
            <td>
                <form:input path="countDaysToSendReminder"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="status">
                    <spring:message text="Status of the contract"/>
                </form:label>
            </td>
            <td>
                <form:input path="status"/>
            </td>
        </tr>

        <tr>

        <tr>
            <td colspan="2">
                <c:if test="${!empty contract.number}">
                    <input type="submit"
                           value="<spring:message text="Edit Contract"/>"/>
                </c:if>
                <c:if test="${empty contract.number}">
                    <input type="submit"
                           value="<spring:message text="Add Contract"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
    <title>Contract data</title>

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

<a href="<c:url value='/contracts'/>">Back to contracts list</a>

<br/>
<br/>
<h1>Contract details</h1>

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

    </tr>
    <tr>
        <td>${contract.id}</td>
        <td>${contract.companyId}</td>
        <td>${contract.clientId}</td>
        <td>${contract.number}</td>
        <td>${contract.dateSign}</td>
        <td>${contract.dateBegin}</td>
        <td>${contract.dateEnd}</td>
        <td>${contract.dayToSendAccount}</td>
        <td>${contract.countDaysToSendReminder}</td>
        <td>${contract.status}</td>
    </tr>
</table>
</body>
</html>
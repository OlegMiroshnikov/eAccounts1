<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
    <title>Account data</title>

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

<a href="<c:url value='/accounts'/>">Back to accounts list</a>

<br/>
<br/>
<h1>Account details</h1>

<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="80">Contract ID</th>
        <th width="80">Account file name</th>
        <th width="80">Date of account send to client</th>
        <th width="80">Date of account view from client</th>
        <th width="80">Date of the last reminder send to client</th>

    </tr>
    <tr>
        <td>${account.id}</td>
        <td>${account.contractId}</td>
        <td>${account.fileName}</td>
        <td>${account.dateSending}</td>
        <td>${account.dateViewing}</td>
        <td>${account.dateReminderSending}</td>
    </tr>
</table>
</body>
</html>
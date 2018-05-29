<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
    <title>Client data</title>

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

<a href="<c:url value='/clients'/>">Back to clients list</a>

<br/>
<br/>
<h1>Client details</h1>

<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="120">Personal code</th>
        <th width="360">Name</th>
        <th width="360">Address</th>
        <th width="240">eMail</th>
    </tr>
    <tr>
        <td>${client.id}</td>
        <td>${client.personalCode}</td>
        <td>${client.name}</td>
        <td>${client.address}</td>
        <td>${client.eMail}</td>
    </tr>
</table>
</body>
</html>
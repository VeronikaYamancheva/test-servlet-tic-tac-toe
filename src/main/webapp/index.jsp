<%--
  Created by IntelliJ IDEA.
  User: Вероника
  Date: 11.08.2024
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.vhsroni.tictactoe.logic.Sign" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Tic Tac Toe</title>
    <%@taglib prefix="c" uri="jakarta.tags.core" %>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

Tic-Tac-Toe Game


<table>
    <tr>
        <td onclick="window.location='/logic?cell=1'">${data.get(0).getSign()}</td>
        <td onclick="window.location='/logic?cell=2'">${data.get(1).getSign()}</td>
        <td onclick="window.location='/logic?cell=3'">${data.get(2).getSign()}</td>
    </tr>
    <tr>
        <td onclick="window.location='/logic?cell=4'">${data.get(3).getSign()}</td>
        <td onclick="window.location='/logic?cell=5'">${data.get(4).getSign()}</td>
        <td onclick="window.location='/logic?cell=6'">${data.get(5).getSign()}</td>
    </tr>
    <tr>
        <td onclick="window.location='/logic?cell=7'">${data.get(6).getSign()}</td>
        <td onclick="window.location='/logic?cell=8'">${data.get(7).getSign()}</td>
        <td onclick="window.location='/logic?cell=9'">${data.get(8).getSign()}</td>
    </tr>
</table>

<hr>

<c:set var="CROSS" value="<%=Sign.CROSS%>"/>
<c:set var="ZERO" value="<%=Sign.ZERO%>"/>

<c:if test="${winner == CROSS}">
    CROSS WIN!!!
    <button class="button" onclick="restart()">Restart</button>
</c:if>
<c:if test="${winner == ZERO}">
    ZERO WIN!!!
    <button class="button" onclick="restart()">Restart</button>
</c:if>
<c:if test="${draw}">
    IT'S A DRAW!!!
    <button class="button" onclick="restart()">Restart</button>
</c:if>

<script>
    function restart() {
        const request = new XMLHttpRequest();
        request.open('POST', '/restart', false);
        request.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        request.send();
        if (request.status === 200) {
            location.reload();
        } else {
            console.error('Ошибка при перезапуске: ', request.status);
        }
    }
</script>
</body>
</html>

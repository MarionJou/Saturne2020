<%-- 
    Document   : affTest
    Created on : 21 févr. 2020, 16:31:17
    Author     : Marion PGROU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Liste des tests</title>
        <meta charset="UTF-8"/> 
        <link href="css/session.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/session.js"></script>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    </head>
    <body>
        <header> </header>
        <div id="gauche" class="box"></div>
        <div id="centre" class="box">
            <h1 style="text-align: center;"> Liste des évaluations </h1>
            <table>
                <c:forEach var="test" items="${listTests}">
                    <form action="repondre.do" method="POST">
                        <tr>
                            <td>${test.nomQuiz}</td>
                            <td>Durée: ${test.dureeStr}</td>
                            <td><input type="hidden" name="id" value="${test.testId}">
                                <button type="submit">Répondre</button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </table>
        </div>
        <div id="droite" class="box"> 
        </div>
    </body>
</html>

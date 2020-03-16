<%-- 
    Document   : menuProf
    Created on : 7 mars 2020, 15:20:33
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
            <h1 style="text-align: center;"> Bienvenu  ${prenom} ${nom}</h1>
            <table>
                <tr>
                    <td> 
                        <form action="versCreerQuest.do" method="POST">
                            <input type="hidden" name="id" value="${personneId}">
                            <button type="submit">Créer une question avec ses réponses</button>
                        </form> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="versCreerQuiz.do" method="POST">
                            <input type="hidden" name="id" value="${personneId}">
                            <button type="submit">Créer un quiz</button>
                        </form> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="versCreerTest.do" method="POST">
                            <input type="hidden" name="id" value="${personneId}">
                            <button type="submit">Créer un test</button>
                        </form> 
                    </td>   
                </tr>
                <tr>
                    <td><form action="versAffRes.do" method="POST">
                            <input type="hidden" name="id" value="${personneId}">
                            <button type="submit">Voir les résultats</button>
                        </form></td>
                </tr>
                <tr>
                    <td><form action="versListTest.do" method="POST">
                            <input type="hidden" name="id" value="${personneId}">
                            <button type="submit">Voir la liste des tests</button>
                        </form></td>
                </tr>
                <tr>
                    <td><form action="versMenuEleve.do" method="POST">
                            <button type="submit">Voir l'auto évaluation</button>
                        </form></td>
                </tr>
            </table>
            <form method="POST" action="Disconnect.do">
                <input type="hidden" name="code" id="code" value="${code}"/>
                <li><a href="#">Déconnexion</a></li>
            </form>
        </div>
        <div id="droite" class="box"> 
        </div>
    </body>
</html>

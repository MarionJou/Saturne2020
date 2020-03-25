<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
	<title>Ca marche !</title>
        <meta charset="UTF-8"/> 
        <link rel="stylesheet" type="text/css" media="screen" href="css/main.css"/>
        <script type="text/javascript" src="js/connexion.js"></script>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    </head>
    <body>
        <header>
            </br>
            <a href="https://www.ec-nantes.fr/version-francaise/">
                <img src="img/LogoCN_Blanc.png" style="height: 75px; position: absolute; top: 10px; left: 10px;" alt="LogoCN">
            </a>
            <form action="index.do" method="GET">
                <input type="hidden" name="code" value="${code}">
                <h2><INPUT border=0 src="img/logo5.png" type=image Value=submit style="height: 100px;">
                </h2>
            </form>
            <nav>
                <ul>
                    <li class="deroulant"><a href="#">${nom} ${prenom} &ensp;</a>
                        <ul class="sous">
                            <li><form action="affListeAutoEval.do" method="POST">
                                <input type="hidden" name="code" value="${code}">
                                <li><input type="submit" class="menu" value="Historique des auto-évaluations"></li>
                            </form></li>
                            <li><a href="#">Paramètres</a></li>
                            <form action="affResultatEtudiant.do" method="POST">
                                <input type="hidden" name="code" value="${code}">
                                <li><input type="submit" class="menu" value="Voir ses résultats"></li>
                            </form>
                            <form action="disconnect.do" method="GET">
                                <input type="hidden" name="code" value="${code}">
                                <li><input type="submit" class="menu" value="Déconnexion"></li>
                            </form>
                        </ul>
                    </li>
                </ul>
            </nav>
            </br>
        </header>
        <div class="body">
            <div id="gauche">
                <h1>Évaluations à compléter</h1>
                <table class="liste">
                    <tr>
                        <th style="width: 100px">Nom</th>
                        <th style="width: 150px">Date de création</th>
                        <th style="width: 50px"></th>
                    </tr>
                    <c:forEach var="quiz" items="${listQuizs}">
                        <TR>
                           <td>${quiz.nomquiz}</td>
                            <td>${quiz.datecreationquiz}</td>
                            <td>
                                <form action="supprimerAutoEval.do" method="POST" style="display:inline">
                                    <input type="hidden" name="id" value="${quiz.quizid}">
                                    <input type="hidden" name="code" value="${code}">
                                    <button><img src="img/delete.png" height="20"></button>
                                </form>
                                <form action="repondreAutoEval.do" method="POST" style="display:inline">
                                    <input type="hidden" name="id" value="${quiz.quizid}">
                                    <input type="hidden" name="code" value="${code}">
                                    <button><img src="img/edit.png" height="20"></button>
                                </form>
                            </td> 
                        </TR>
                        
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>

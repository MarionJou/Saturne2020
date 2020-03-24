<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
	<title>Ca marche !</title>
        <meta charset="UTF-8"/> 
        <link rel="stylesheet" type="text/css" media="screen" href="css/main.css"/>
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
                <h2><img src="img/s2.jpg" style="height: 35px;"  alt="Saturne">  
                    <input type="submit" class="titre" value="Saturne"></h2>
            </form>
            <nav>
                <ul>
                    <li class="deroulant"><a href="#" class="nom">${nom} ${prenom} &ensp;</a>
                        <ul class="sous">
                            <form action="versCreerQues.do" method="GET">
                                <input type="hidden" name="code" value="${code}">
                                <li><input type="submit" class="menu" value="Créer une question"></li>
                            </form>
                            <form action="versCreerQuiz.do" method="GET">
                                <input type="hidden" name="code" value="${code}">
                                <li><input type="submit" class="menu" value="Créer un quiz"></li>
                            </form>
                            <form action="versCreerTest.do" method="GET">
                                <input type="hidden" name="code" value="${code}">
                                <li><input type="submit" class="menu" value="Créer une session d'évaluation"></li>
                            </form>
                            <form action="versAffRes.do" method="POST">
                                <input type="hidden" name="code" value="${code}">
                                <li><input type="submit" class="menu" value="Afficher les résultats"></li>
                            </form>
                            <form action="#" method="GET">
                                <input type="hidden" name="code" value="${code}">
                                <li><input type="submit" class="menu" value="Paramètres"></li>
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
            <div id="haut">
                <h1>Évaluations terminées</h1>
                <p>Pour rendre les résultats visibles cliquez sur la flèche verte.</p>
                <table class="liste">
                    <tr>
                        <th style="width: 100px">Nom</th>
                        <th style="width: 150px">Date de début</th>
                        <th style="width: 150px">Date de fin</th>
                        <th style="width: 80px">Durée</th>
                        <th style="width: 40px"></th>
                    </tr>
                    <c:forEach var="test" items="${listTests}">
                        <TR>
                           <td>${test[1]}</td>
                            <td>${test[2]}</td>
                            <td>${test[3]}</td>
                            <td>${test[4]}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${test[5]=='visible'}">
                                        <form action="rendreInvisible.do" method="POST">
                                            <input type="hidden" name="id" value="${test[0]}">
                                            <input type="hidden" name="code" value="${code}">
                                            <button><img src="img/non.png" height="20"></button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="rendreVisible.do" method="POST">
                                            <input type="hidden" name="id" value="${test[0]}">
                                            <input type="hidden" name="code" value="${code}">
                                            <button><img src="img/oui.png" height="20"></button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td> 
                        </TR>
                        
                    </c:forEach>
                </table>
            </div>    
            <div id="droite">
                <h1>Liste des Groupes</h1>
            </div>
        </div>
    </body>
</html>
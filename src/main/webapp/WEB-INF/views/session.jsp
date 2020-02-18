<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Créer une session de test</title>
        <meta charset="UTF-8"/> 
        <link href="css/session.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/session.js"></script>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    </head>
    <body>
        <header> </header>
        <div id="gauche" class="box"> 
            <h1> Liste des sessions déjà crées </h1>
            <table>
                <tr> 
                    <th> Intitulé test </th>
                    <th> Groupe concerné </th>
                    <th> Date début </th>
                </tr>
                <c:forEach var="test" items="${listTests}">
                    <tr>
                        <th>${test.quizid.nomquiz}</th>
                        <th>${test.groupeid.nomgroupe}</th>
                        <th>${test.datedebuttest}</th>
                        <th>
                            <form action="delete.do" method="POST">
                                <input type ="hidden" name="id" value="${test.testid}" />
                                <button><img src="img/delete.png" alt="delete" height="20" /></button>
                            </form>
                        </th>
                    </tr>                    
                </c:forEach>
            </table>
        </div>
        <div id="centre" class="box">
            <h1> Créer une session de tests </h1>
            <table>
                <tr> 
                    <td>Choisissez le quiz qui sera utilisé:</td>
                    <td><select name="quiz"> 
                            <c:forEach var="quiz" items="${listQuizs}">
                                    <option value="${quiz.quizid}">${quiz.nomquiz}</option>
                            </c:forEach>
                </select></td>
                </tr>
                <tr> 
                    <td>Choisissez la date de début de disponibilité:</td>
                    <td><input name="dateDeb" type="date"/></td>
                    <td><input name="heureDeb" type="time"/></td>
                </tr>
                <tr> 
                    <td>Choisissez la date de fin de disponibilité:</td>
                    <td><input name="dateFin" type="date"/></td>
                    <td><input name="heureFin" type="time"/></td>
                </tr>
                <tr> 
                    <td>Quelle est la durée du test ?</td>
                    <td><input name="duree" type="time"/></td>
                </tr>
                <tr> 
                    <td>Quelle notation voulez-vous utiliser ?</td>
                    <td><select name="notation"> 
                            <c:forEach var="notation" items="${listNotations}">
                                <option value="${notation.notationid}">${notation.libelle}</option>
                            </c:forEach>
                        </select></td>
                </tr>
                <tr> 
                    <td>Quel(s) groupe(s) devront passer ce test?</td>
                    <td><select name="groupe"> 
                            <c:forEach var="groupe" items="${listGroupes}">
                                <c:if test="${groupe.estvalide}" >
                                    <option value="${groupe.groupeid}">${groupe.nomgroupe}</option>
                                </c:if>
                                
                            </c:forEach>
                </select></td>
                </tr>
            </table>
            <button onClick="creerTest(this);">Continuer et choisir le nombre de points par question</button>
        </div>
        <div id="droite" class="box"> 
        </div>
        <footer> Coucou </footer>
    </body>
</html>
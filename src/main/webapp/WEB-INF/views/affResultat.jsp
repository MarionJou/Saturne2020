<%-- 
    Document   : affResultat
    Created on : 6 mars 2020, 13:09:16
    Author     : Marion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Affichage des résultats</title>
        <meta charset="UTF-8"/> 
        <link href="css/session.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/resultats.js"></script>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
        <script type="text/javascript" src="js/utilities.js"></script>
    </head>
    <body>
        <header> </header>
        <div id="gauche" class="box"></div>
        <div id="centre" class="box">
            <h1>Affichage des résultats</h1>
            <table>
                <tr>
                    <td></td>
                    <td>
                        <label for="typeAff">Afficher les résultats...</label>
                        <select onChange="afficher(this);" id="typeAff">
                            <option value="0" selected>-</option>
                            <option value="1">en choisissant un groupe et un test en particulier.</option>
                            <option value="2">pour une personne en particulier.</option>
                            <option value="3">pour un test que vous avez fait.</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <select style="display:none" name="triGroupeTest" onchange="triGrpTest(this)"> 
                            <option value="0">-</option>
                            <c:forEach var="groupe" items="${listGroupe}">
                                <option value="${groupe.groupeid}">${groupe.nomgroupe}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <select style="display:none" name="triGroupePers" onchange="triGrpPers(this)"> 
                            <option value="0">-</option>
                            <c:forEach var="groupe" items="${listGroupe}">
                                <option value="${groupe.groupeid}">${groupe.nomgroupe}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <select style="display:none" name="triTest" onchange="affTest(this)"> 
                            <c:forEach var="test" items="${listTest}">
                                <option value="${test.testid}">${test.quizid.nomquiz}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td></td>
                </tr>
            </table>
            
        </div>
        <div id="droite" class="box"> 
            <form action="versMenuProf.do" method="POST">
                <input type="hidden" name="id" value="${personneId}"/>
                <button>Revenir au menu principal</button>
            </form>
        </div>
    </body>
</html>

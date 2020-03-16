<%-- 
    Document   : modifQuest
    Created on : 2 mars 2020, 13:00:34
    Author     : Mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Créer une question </title>
        <meta charset="UTF-8"/> 
        <link href="css/question.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/questionRep.js"></script>
        <script type="text/javascript" src="js/utilities.js"></script>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    </head>
    <body>
        <header> </header>
        <div id="gauche" class="box"> 
        </div>
        <div id="centre" class="box">
            <h1> Modifier une question </h1>
            <table>
                <tr> 
                    <td>Question :</td>
                    <td><input name="enonceQues" type="text" value="${question.enonce}"/>
                        <input name="idQues" type="hidden" value="${question.questionid}"/>
                        <input name="type" type="hidden" value="${typeQues}"/></td>
                </tr>
                <tr>
                    <td>La question est elle privée ? </td>
                    <td>
                        <input type="radio" name="estPrivee" value=true checked="${question.estprivee}">
                        <label for="yes">Oui</label>
                        <input type="radio" name="estPrivee" value=false checked="${!question.estprivee}">
                        <label for="no">Non</label>
                    </td>
                </tr>
                <tr>
                    <td>Réponses:</td>
                    <td></td>
                </tr>
                <c:forEach var="qcmrep" items="${listReponses}">
                    <tr><td></td>
                        <td>
                        <c:if test="${type.repunique}">
                                <input name="idRepUni" type="hidden" value="${qcmrep.qcmrepid}"/>
                                <input name="enonceRepUni" type="text" value="${qcmrep.enonce}"/>
                                <c:choose>
                                    <c:when test="${qcmrep.reponseid.correcte}">
                                        <input type="radio" name="correctesUni" value=true checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="radio" name="correctesUni" value=true>
                                    </c:otherwise>
                                </c:choose>
                                <button onclick="deleteRep(this);">X</button>
                        </c:if>
                        <c:if test="${!type.repunique}">
                                <input name="idRepMulti" type="hidden" value="${qcmrep.qcmrepid}"/>
                                <input name="enonceRepMulti" type="text" value="${qcmrep.enonce}"/>
                                <c:choose>
                                    <c:when test="${qcmrep.reponseid.correcte}">
                                        <input type="checkbox" name="correctesMulti" value=true checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" name="correctesMulti" value=true>
                                    </c:otherwise>
                                </c:choose>
                                <button onclick="deleteRep(this);">X</button>
                        </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <c:if test="${type.repunique}">
                    <td name="QcmRepUni" >
                        <button onclick="ajoutterRep(this,1);">Ajouter une réponse</button>
                    </td>
                    </c:if>
                    <c:if test="${!type.repunique}">
                    <td name="QcmRepMulti">
                        <button onclick="ajoutterRep(this,2);">Ajouter une réponse</button>
                    </td>
                    </c:if>
                </tr>
                <tr>
                    <td>Mots-clés:</td>
                    <td>
                        <c:forEach var="motCle" items="${listMotsCles}">
                            <input name="motCle" type="text" value="${motCle.mot}"/>
                            <button onclick="deleteMotCle(this);">X</button>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button onclick="ajoutterMotCle(this);">Ajouter un mot clé</button>
                    </td>
                </tr>
            </table>
            <input type="hidden" value="${personneId}" name="personneId" />
            <button onClick="validerModif(this);">Créer les réponses</button>
        </div>
        <div id="droite" class="box"> 
        </div>
        <footer> Mdr j'ai repris ton truc Marion </footer>
    </body>
</html>


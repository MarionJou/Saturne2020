<%-- 
    Document   : questRep
    Created on : 29 févr. 2020, 17:36:13
    Author     : Marion PGROU
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
            <h1> Liste des questions déjà crées </h1>
            <input type="hidden" name="personneId" value="${personneId}"/>
            <table>
                <tr> 
                    <th> Enoncé </th>
                    <th> Date de création de la question </th>
                    <th> Privée </th>
                </tr>
                <c:forEach var="question" items="${listQuestion}">
                    <tr>
                        <th>${question.enonce}</th>
                        <th>${question.datecreationquestion}</th>
                        <th>${question.estprivee}</th>
                        <th>
                            <form action="deleteQuestion.do" method="POST">
                                <input type ="hidden" name="id" value="${question.questionid}" />
                                <input type="hidden" value="${personneId}" name="personneId" />
                                <button><img src="img/delete.png" alt="delete" height="20" /></button>
                            </form>
                            <form action="modifQuestion.do" method="POST">
                                <input type ="hidden" name="id" value="${question.questionid}" />
                                <input type="hidden" value="${personneId}" name="personneId" />
                                <button><img src="img/edit.png" alt="delete" height="20" /></button>
                            </form>
                        </th>
                    </tr>                    
                </c:forEach>
            </table>
        </div>
        <div id="centre" class="box">
            <h1> Créer une question </h1>
            <table>
                <tr>
                    <td>Type:</td>
                    <td><select name="type" onChange="afficherRep(this);">
                            <option value="0">-</option>
                            <option value="1">Qcm a réponse unique</option>
                            <option value="2">Qcm normal</option>
                        </select>
                    </td>
                </tr>
                <tr> 
                    <td>Question :</td>
                    <td><input name="enonceQues" type="text" value="Entrez l'énonce ici"/></td>
                    
                </tr>
                <tr>
                    <td>La question est elle privée ? </td>
                    <td>
                        <input type="radio" name="estPrivee" value=true checked>
                        <label for="yes">Oui</label>
                        <input type="radio" name="estPrivee" value=false>
                        <label for="no">Non</label>
                    </td>
                </tr>
                <tr>
                    <td>Réponses:</td>
                    <td name="QcmRepUni" style="display:none">
                        <input name="enonceRepUni" type="text" value="Entrez l'énoncé ici"/>
                        <input type="radio" name="correctesUni" value=true>
                        <button onclick="deleteRep(this);">X</button>
                    </td>
                    <td name="QcmRepMulti" style="display:none">
                        <input name="enonceRepMulti" type="text" value="Entrez l'énoncé ici"/>
                        <input type="checkbox" name="correctesMulti" value=true>
                        <button onclick="deleteRep(this);">X</button>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td name="QcmRepUni" style="display:none">
                        <button onclick="ajoutterRep(this,1);">Ajouter une réponse</button>
                    </td>
                    <td name="QcmRepMulti" style="display:none">
                        <button onclick="ajoutterRep(this,2);">Ajouter une réponse</button>
                    </td>
                </tr>
                <tr>
                    <td>Mots-clés:</td>
                    <td>
                        <input name="motCle" type="text" />
                        <button onclick="deleteMotCle(this);">X</button>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button onclick="ajoutterMotCle(this);">Ajouter un mot clé</button>
                    </td>
                </tr>
            </table>
            <button onClick="valider(this);">Créer les réponses</button>
        </div>
        <div id="droite" class="box"> 
        </div>
        <footer> Mdr j'ai repris ton truc Marion </footer>
    </body>
</html>

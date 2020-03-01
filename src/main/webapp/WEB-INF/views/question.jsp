<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Créer une question </title>
        <meta charset="UTF-8"/> 
        <link href="css/question.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/question.js"></script>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    </head>
    <body>
        <header> </header>
        <div id="gauche" class="box"> 
            <h1> Liste des questions déjà crées </h1>
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
                                <button><img src="img/delete.png" alt="delete" height="20" /></button>
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
                    <td>Ecrivez l'énoncé :</td>
                    <td><input name="enonce" type="text"/></td>
                    
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
                    <td>Voulez-vous imposer le choix d'une seul réponse?</td>
                    <td>
                        <input type="radio" name="repUni" value=true checked>
                        <label for="yes">Oui</label>
                        <input type="radio" name="repUni" value=false>
                        <label for="no">Non</label>
                    </td>
                </tr>
            </table>
            <button onClick="creerQuestion(this);">Créer les réponses</button>
        </div>
        <div id="droite" class="box"> 
        </div>
        <footer> Mdr j'ai repris ton truc Marion </footer>
    </body>
</html>
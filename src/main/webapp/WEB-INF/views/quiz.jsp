<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Créer un quiz </title>
        <meta charset="UTF-8"/> 
        <link href="css/quiz.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/quiz.js" ></script>
        <script type="text/javascript" src="js/utilities.js"></script>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
        
    </head>
    <body>
        <header> </header>
        <div id="gauche" class="box"> 
            <h1 id="idquiz"> Créer le quiz </h1>
            <table>
                <tr>
                    <td>
                        <label for="name">nom du quiz:</label>
                <input type="text" id="nameQuiz" name="nameQuiz" >
                    </td>
                    <td>
                        <label for="name">auteur:</label>
                <input type="text" id="author" >
                    </td>
                    <td>
                        <label for="name">date :</label>
                <input type="text" id="date" >
                    </td>
                </tr>
            </table>  
            <table>
                <tr>
                    <th id="th1">Enonce</th>
                    <th id="th2"></th>
                </tr>
                <!--
                <tr id="idquestion" class="question">
                    <td>Ã©noncÃ© 1 dfkjvnfjdnvdfkjnvdkfjnvkdfnvkdfnvkdfnvkdfnvkdfnvkdfnvdfjvndjvdfkjvnfjdnvdfkjnvdkfj nvkdfnvkdfnvkdfnvkdfnvkdfnvkdfnvdfjvndjvdfkjvnfjdnvdfkjnvdkfjnvk dfnvkdfnvkdfnvkdfnvkd fnvkdfnvdfjvndjv</td>
                    <td style="text-align:center">
                        <button onclick="deleteLine(this);">
                            <img src="delete.png" alt="delete" height="20" />
                        </button>
                    </td>
                </tr>
                -->
                <tr id="addNew">
                    <td>
                        <select id="select1" name="question">
                            <c:forEach var="question" items="${listQuestions}">
                                    <option value="${question.questionid}">${question.enonce}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td style="text-align:center"><button onclick="addLine(this);"><img src="plus.png" alt="add" height="20" /></button></td>
                </tr>
            </table>
        </div>
        <div id="centre" class="box">
            <h1> CrÃ©er une question </h1>
            <button onClick="creerQuestion(this);">crÃ©er une question</button>
            <h1> Valider le quiz </h1>
            <form action="creerQuiz.do" method="POST">
                <input type="hidden" name="idQuiz">
                <button>Valider le quiz</button>
            </form>
        </div>
        <footer></footer>
    </body>
</html>
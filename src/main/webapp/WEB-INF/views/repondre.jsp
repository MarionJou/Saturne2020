<%-- 
    Document   : repondre.jsp
    Created on : 21 févr. 2020, 18:09:35
    Author     : Marion PGROU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Liste des tests</title>
        <meta charset="UTF-8"/> 
        <link href="css/repondre.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/evaluation.js"></script>
        <script type="text/javascript" src="js/session.js"></script>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    </head>
    <body>
        <header> </header>
        <div id="gauche" class="box"> <p id="compte_a_rebours"> </p>
            <script type="text/javascript">

            var diff = 50;
            var date_evenement = new Date((new Date()).getTime() + diff*1000);

            function compte_a_rebours()

            {

                var compte_a_rebours = document.getElementById("compte_a_rebours");
                var date_actuelle = new Date();
                var total_secondes = (date_evenement - date_actuelle) / 1000;

                var prefixe = "Temps restant : ";


                if (total_secondes > 0)
                {
                    var jours = Math.floor(total_secondes / (60 * 60 * 24));
                    var heures = Math.floor((total_secondes - (jours * 60 * 60 * 24)) / (60 * 60));
                    minutes = Math.floor((total_secondes - ((jours * 60 * 60 * 24 + heures * 60 * 60))) / 60);
                    secondes = Math.floor(total_secondes - ((jours * 60 * 60 * 24 + heures * 60 * 60 + minutes * 60)));

                    var et = ":";
                    var mot_jour = ":";
                    var prefixe_heure = '';
                    var mot_heure = ":";
                    var prefixe_minute = '';
                    var mot_minute = ":";
                    var prefixe_seconde = '';


                    if (jours == 0)
                    {
                        jours = '';
                        mot_jour = '';
                    }

                    if (heures == 0)
                    {
                        heures = '';
                        mot_heure = '';
                    }

                    else if (heures < 10)
                    {
                        prefixe_heures = '0';
                    }        

                    if (minutes < 10)
                    {
                        prefixe_minute = '0'
                    }


                    if (secondes < 10)

                    {
                        prefixe_seconde = '0'
                        et = '';
                    }

                    if (minutes == 0 && heures == 0 && jours == 0)
                    {
                        et = "";
                    }


                    compte_a_rebours.innerHTML = prefixe + jours + mot_jour + prefixe_heure + heures + mot_heure + prefixe_minute + minutes + mot_minute + prefixe_seconde +secondes;
                }

                else
                {
                    finTest(${nombre});
                    console.log("miaou");
                }

                var actualisation = setTimeout("compte_a_rebours();", 1000);
            }

            compte_a_rebours();

            

            </script>
        </div>
        <div id="centre" class="box">
            <h1 style="text-align: center;">${test.nomQuiz}</h1>
            <form action="envRep.do" method="POST">
                <input type="hidden" value="${nombre}" name="nombre"/>
                <input type="hidden" value="${test.testId}" name="testId"/>
                <c:forEach var="question" items="${quesRep}">
                    <c:choose>
                        <c:when test= "${question.idQues%2==0}">
                            <table id="noir">
                        </c:when>
                        <c:otherwise>
                            <table>
                        </c:otherwise>    
                    </c:choose>
                                <tr>
                                    <td>${question.enonceQues}</td>
                                <input name="q${question.ordre}" type="hidden" value="${question.idQues}"/>
                                </tr>
                                 <c:forEach var="reponse" items="${question.listeReponses}">
                                    <tr>
                                        <td>
                                            <c:choose>
                                                <c:when test = "${question.repUni == true}">
                                                    <input name="r${question.idQues}" type="radio" value="${reponse.id}"/>
                                                    <label for="${question.idQues}">${reponse.ennonce}</label>
                                                </c:when>
                                                <c:otherwise>
                                                    <input name="r${question.idQues}" type="checkbox" value="${reponse.id}"/>
                                                    <label for="${question.idQues}">${reponse.ennonce}</label>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                        </table>
                </c:forEach> 
                <button type="submit">Valider</button>
            </form>
        </div>
        <div id="droite" class="box"> 
            <table>
                <tr> 
                    <td> Test de français</td>
                    <td> 12/02/2020 </td>
                    <td> 24/02/2020 </td>
                </tr>
                <tr> 
                    <td> Test de maths </td>
                    <td> 12/02/2020 </td>
                    <td> 24/02/2020 </td>
                </tr>
            </table>
        </div>
    </body>
</html>


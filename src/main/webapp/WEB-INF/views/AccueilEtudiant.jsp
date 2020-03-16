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
        <script type="text/javascript" src="js/creerAutoEval.js"></script>
        <script type="text/javascript" src="js/utilities.js"></script>
    </head>
    <body>
        <header>
            </br>
            <a href="https://www.ec-nantes.fr/version-francaise/">
                <img src="img/LogoCN_Blanc.png" style="height: 75px; position: absolute; top: 10px; left: 10px;" alt="LogoCN">
            </a>
            <a href="index.do">
                <h1><img src="img/s2.jpg" style="height: 35px;"  alt="Saturne">  Saturne</h1>
            </a>
            <nav>
                <ul>
                    <li class="deroulant"><a href="#">${nom} ${prenom} &ensp;</a>
                        <ul class="sous">
                            <li><a href="#">Historique des auto-évaluations</a></li>
                            <li><a href="#">Paramètres</a></li>
                            <li><a href="#" onclick="disconnect('${code}')">Déconnexion</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
            </br>
        </header>
        <div id="haut">
            <h2>Évaluations à compléter</h2>
            <table>
                <tr>
                    <th style="width: 100px">Nom</th>
                    <th style="width: 150px">Date de début</th>
                    <th style="width: 150px">Date de fin</th>
                    <th style="width: 80px">Durée</th>
                    <th style="width: 40px"></th>
                </tr>
                <c:forEach var="test" items="${listTests}">
                    <td>${test[1]}</td>
                    <td>${test[2]}</td>
                    <td>${test[3]}</td>
                    <td>${test[4]}</td>
                    <td>
                        <form action="repondre.do" method="POST">
                            <input type="hidden" name="id" value="${test[0]}">
                            <input type="hidden" name="personneId" value="${persId}">
                            <input type="hidden" name="code" value="${code}">
                            <button><img src="img/edit.png" height="20"></button>
                        </form>
                    </td>
                </c:forEach>
            </table>
        </div>
        <div id="bas">
            <h2>S'entraîner</h2>
            <div name="creationEval">
                <label for='nom'>Nom de l'auto-évaluation</label>
                <input type="text" name="nom" onKeyUp="donneNom(this)"/>
                <br>
                <label for='mot'>Entrez un mot clé pour trouver des questions</label>
                <input type="text" name="mot" onKeyUp="affiche(this)"/>
                <br>
                <select name="motsCle" style="display: none;" onchange="nombreMots(this)">
                </select>
            </div>         
            <br>
            <table name="tableQuestions" style="display:none">
                <tr>
                    <th size='150'>Question</th>
                    <th size='30'>Mot</th>
                </tr>
            </table>
            <br>
            <form action="enregAutoEval.do" style="display:none" name="enregistrer" method="POST">
                <input type='hidden' name='nameQuiz'/>
                <input type='hidden' name='idPers' value="${persId}"/>
                <button>Enregistrer</button>
            </form>
            <form action="partiAutoEval.do" style="display:none" name="participer" method="POST">
                <input type='hidden' name='nameQuiz'/>
                <input type='hidden' name='idPers' value="${persId}"/>
                <button>S'auto-évaluer</button>
            </form>
        </div>
        <div id="droite">
            <h2>Résultats des dernières évaluations</h2>
        </div>
    </body>
</html>
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function deleteLine(ref) { 
    var trRef =getNextParentTag(ref,"TR");
    if (trRef !== null) trRef.parentNode.removeChild(trRef);
    console.log("delete line"+ref.tagName);
}

function getNextParentTag(currentElement, tagName) {
    // nodeType=1 is TAG = <p> <div> <tr> ...
    while ((currentElement !== null) && ((currentElement.nodeType !== 1 )
        || (currentElement.tagName!== tagName))){
            currentElement = currentElement.parentNode;
    }
    return currentElement;
}

function addLine(ref){
    var trRef =getNextParentTag(ref,"TR");

    var ligneRemplie = document.getElementById("select1");
    var selection = ligneRemplie.selectedIndex;
    var valeur = ligneRemplie.options[selection];
    console.log(valeur.textContent);
    console.log(valeur.value);
    var tr = document.createElement("TR");
    var td = document.createElement("TD");
    td.textContent=valeur.textContent;
    td.setAttribute("class","question");
    td.setAttribute("id","valeur.value");
    tr.appendChild(td);
    var td2 = document.createElement("TD");
    td2.setAttribute("style","text-align:center");
    var bouton = document.createElement("BUTTON");
    bouton.setAttribute("onclick","deleteLine(this);");
    var img = document.createElement("IMG");
    img.setAttribute("scr","delete.png");
    img.setAttribute("alt","delete");
    img.setAttribute("height","20");
    bouton.appendChild(img);
    td2.appendChild(bouton);
    tr.appendChild(td2);
    trRef.parentNode.appendChild(tr);
}

function creerQuestion(ref){
    $.ajax({
        url:"creerQuiz.do",
        data: {
            auteur: document.getElementsById("author"),
            nomQuiz: document.getElementsById("nameQuiz"),
            date:document.getElementsById("date"),
            idQuestions: document.getElementsByClassName("question").id,
            enoncesQuestions: document.getElementsByClassName("question").textContent,
        },
        method: 'POST',
        success: function(result){
        },
        error: function(resultat, statut, erreur){
            console.log(erreur);
            
        }
    })
}
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function finTest(ref,n,persId){
    console.log("c'est fini");
    var gauche = document.getElementById("gauche");
    deleteAll(gauche);
   
    var listQues = [];
    for (let i = 1; i<=n; i++){
        var quesId = document.getElementsByName("q"+i)[0].value;
        var listRepId = document.getElementsByName("r"+quesId);
        var RepId = [];
            for (let j= 0; j<(listRepId.length); j++){
                if (listRepId[j].checked ==true ){
                    RepId.push(listRepId[j].value);
                }
            }
        var question = {
            "quesId": quesId,
            "reponses": JSON.stringify(RepId)
        }
        listQues.push(question);
    }
    var testId = document.getElementsByName("testId")[0].value;
    $.ajax({
        url: "tempsEcouler.do",
        data: {
            "nombre": n,
            "questions": JSON.stringify(listQues),
            "testId": testId,
            "code": persId
        },
        method: "POST",
        success: function(result){
            var divRef = document.getElementById("centre");
            deleteAll(divRef);
            var form = document.createElement("FORM");
            var bouton = document.createElement("BUTTON");
            bouton.textContent="Revenir au menu";
            form.setAttribute("action","index.do");
            form.setAttribute("method","GET");
            var hidden = document.createElement("INPUT");
            hidden.setAttribute("name","code");
            hidden.type="hidden";
            hidden.value=persId;
            form.appendChild(hidden);
            form.appendChild(bouton);
            var h1 = document.createElement("H1");
            h1.textContent="Votre temps est écoulé.";
            divRef.appendChild(h1);
            divRef.appendChild(form);
            console.log("Youpi");
        },
        error: function(res,stat,err){
            console.log(res.responseText);
        }
        
    });
    return 0;
}
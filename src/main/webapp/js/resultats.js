/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function afficher(ref){
    var selType = ref;
    var indexType = selType.selectedIndex;
    var type = selType.options[indexType].value;
    var text = selType.options[indexType].textContent;
    var pRef=ref.parentNode;
    deleteAll(pRef);
    pRef.appendChild(document.createTextNode("Afficher "+text));
    if (type=="1"){
        var refSelect = document.getElementsByName("triGroupeTest")[0];
        refSelect.style.display = "inline";
    }else if (type=="2"){
        var refSelect = document.getElementsByName("triGroupePers")[0];
        refSelect.style.display = "inline";
    }else if (type=="3"){
        var refSelect = document.getElementsByName("triTest")[0];
        refSelect.style.display = "inline";
    }
}

function triGrpTest(ref){
    var pRef=ref.parentNode;
    var selGroupe = ref;
    var indexGroupe = selGroupe.selectedIndex;
    var groupe = selGroupe.options[indexGroupe].value;
    $.ajax({
        url: "recupTestParGroupe.do",
        data: {
            groupe: groupe
        },
        method: 'POST',
        success: function(result){
            var listTest = result.listTest;
            var id = listTest[0].id;
            var name = listTest[0].name;
            var td = getNextTag(pRef.nextSibling,"TD");
            var select = addSelect(td, "triGroupeTest", listTest);
            select.setAttribute("onChange","affGrpTest(this,"+groupe+")");
            convertElementToText(pRef);
            
        },
        error: function(res,stat,err){
            console.log(res.responseText);
        }
        
    });
}

function affGrpTest(ref,groupe){
    var pRef=ref.parentNode;
    var selTest = ref;
    var indexTest = selTest.selectedIndex;
    var test = selTest.options[indexTest].value;
    $.ajax({
        url: "affTestParGroupe.do",
        data: {
            test: test,
            groupe: groupe
        },
        method: 'POST',
        success: function(result){
            var oldTable = document.getElementsByName("affRes");
            while(oldTable.length>0){
                oldTable[0].parentNode.removeChild(oldTable[0]);
            }
            
            var div = document.getElementById("centre");
            var listEval = result.listEval;
            var moyenne = result.moyenne;
            var para =document.createElement("P");
            para.setAttribute("name","affRes");
            para.appendChild(document.createTextNode("La moyenne pour ce test est de: "+moyenne));
            div.appendChild(para);
            var table=creerTableRes(listEval);
            div.appendChild(table);
        },
        error: function(res,stat,err){
            console.log(res.responseText);
        }
        
    });
}


function creerTableRes(listEval){
    var table = document.createElement("TABLE");
    var tr1 = document.createElement("TR");

    var th1 = document.createElement("TH");
    th1.appendChild(document.createTextNode("Nom"));
    var th2 = document.createElement("TH");
    th2.appendChild(document.createTextNode("Prénom"));
    var th3 = document.createElement("TH");
    th3.appendChild(document.createTextNode("Note"));

    tr1.appendChild(th1);
    tr1.appendChild(th2);
    tr1.appendChild(th3);
    table.appendChild(tr1);

    for (let i = 0; i < listEval.length; i++){
        var tr = document.createElement("TR");
        var td1 = document.createElement("TD");
        td1.appendChild(document.createTextNode(listEval[i].nom));
        var td2 = document.createElement("TD");
        td2.appendChild(document.createTextNode(listEval[i].prenom));
        var td3 = document.createElement("TD");
        td3.appendChild(document.createTextNode(listEval[i].note));
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        table.appendChild(tr);
    }
    table.setAttribute("name","affRes");
    return table;
}


function triGrpPers(ref){
    var pRef=ref.parentNode;
    var selGroupe = ref;
    var indexGroupe = selGroupe.selectedIndex;
    var groupe = selGroupe.options[indexGroupe].value;
    $.ajax({
        url: "recupPersonneParGroupe.do",
        data: {
            groupe: groupe
        },
        method: 'POST',
        success: function(result){
            var listPers = result.listPers;
            var td = getNextTag(pRef.nextSibling,"TD");
            var select = addSelect(td, "triGroupePers", listPers);
            select.setAttribute("onChange","affGrpPers(this)");
            convertElementToText(pRef);
            
        },
        error: function(res,stat,err){
            console.log(res.responseText);
        }
        
    });
}

function affGrpPers(ref){
    var pRef=ref.parentNode;
    var selPers = ref;
    var indexPers = selPers.selectedIndex;
    var pers = selPers.options[indexPers].value;
    $.ajax({
        url: "affTestParPers.do",
        data: {
            pers: pers
        },
        method: 'POST',
        success: function(result){
            var oldTable = document.getElementsByName("affRes");
            if (oldTable.length>0){
                oldTable[0].parentNode.removeChild(oldTable[0]);
            }
            
            var div = document.getElementById("centre");
            var listEval = result.listEval;
            
            var table=creerTableResPers(listEval);
            div.appendChild(table);
        },
        error: function(res,stat,err){
            console.log(res.responseText);
        }
        
    });
}

function affTest(ref){
    var pRef=ref.parentNode;
    var selTest = ref;
    var indexTest = selTest.selectedIndex;
    var test = selTest.options[indexTest].value;
    $.ajax({
        url: "affTest.do",
        data: {
            test: test
        },
        method: 'POST',
        success: function(result){
            var oldTable = document.getElementsByName("affRes");
            while(oldTable.length>0){
                oldTable[0].parentNode.removeChild(oldTable[0]);
            }
            
            var div = document.getElementById("centre");
            var listEval = result.listEval;
            var moyenne = result.moyenne;
            var para =document.createElement("P");
            para.setAttribute("name","affRes");
            para.appendChild(document.createTextNode("La moyenne pour ce test est de: "+moyenne));
            div.appendChild(para);
            var table=creerTableRes(listEval);
            div.appendChild(table);
        },
        error: function(res,stat,err){
            console.log(res.responseText);
        }
        
    });
}

function creerTableResPers(listEval){
    var table = document.createElement("TABLE");
    var tr1 = document.createElement("TR");

    var th1 = document.createElement("TH");
    th1.appendChild(document.createTextNode("Evaluation"));
    var th3 = document.createElement("TH");
    th3.appendChild(document.createTextNode("Note"));

    tr1.appendChild(th1);
    tr1.appendChild(th3);
    table.appendChild(tr1);

    for (let i = 0; i < listEval.length; i++){
        var tr = document.createElement("TR");
        var td1 = document.createElement("TD");
        td1.appendChild(document.createTextNode(listEval[i].nomEval));
        var td3 = document.createElement("TD");
        td3.appendChild(document.createTextNode(listEval[i].note));
        tr.appendChild(td1);
        tr.appendChild(td3);
        table.appendChild(tr);
    }
    table.setAttribute("name","affRes");
    return table;
}
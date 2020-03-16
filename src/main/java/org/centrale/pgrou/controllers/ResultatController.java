/*
 * Controller qui permet d'afficher les résultats aux évaluations
 */
package org.centrale.pgrou.controllers;

import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.centrale.pgrou.items.Evaluation;
import org.centrale.pgrou.items.Personne;
import org.centrale.pgrou.items.Test;
import org.centrale.pgrou.repositories.EvaluationRepository;
import org.centrale.pgrou.repositories.PersonneRepository;
import org.centrale.pgrou.repositories.TestRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Mario
 */
@Controller
public class ResultatController {
    @Autowired
    private TestRepository testRepository;
    
    @Autowired
    private EvaluationRepository evaluationRepository;
    
    @Autowired
    private PersonneRepository personneRepository;
    
    /**
     * Fonction pour récupérer la liste des tests auquels un groupe à accès
     * @param request l'id du groupe
     * @return la liste des tests
     * @throws ParseException 
     */
    @RequestMapping(value="recupTestParGroupe.do",method=RequestMethod.POST)
    public ModelAndView recupTestParGroupe(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        JSONArray listObject = new JSONArray();
        String groupeStr = request.getParameter("groupe");
        int groupeid = Integer.parseInt(groupeStr);
        
        List<Test> listTest = testRepository.findWithGroupe(groupeid);
        for (Test aTest:listTest){
            JSONObject testObject = new JSONObject();
            testObject.accumulate("id", aTest.getTestid());
            testObject.accumulate("name", aTest.getQuizid().getNomquiz());
            listObject.put(testObject);
            
            
        }
        
        object.put("listTest", listObject);
        return returned.addObject("theResponse",object.toString());
    }
    
    /**
     * Fonction qui affiche les résultats à un test choisis après avoir choisis un groupe
     * @param request l'id du test
     * @return la liste des notes des élèves avec leur nom, prénom
     * @throws ParseException 
     */
    @RequestMapping(value="affTestParGroupe.do",method=RequestMethod.POST)
    public ModelAndView affTestParGroupe(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        JSONArray listObject = new JSONArray();
        String groupeStr = request.getParameter("groupe");
        int groupeid = Integer.parseInt(groupeStr);
        String testStr = request.getParameter("test");
        int testid = Integer.parseInt(testStr);
        
        List<Evaluation> listEvaluation = evaluationRepository.findWithGroupeAndTest(groupeid, testid);
        float nombre = 0;
        float somme = 0;
        for (Evaluation eval: listEvaluation){
            nombre = nombre+1;
            JSONObject evalObject = new JSONObject();
            evalObject.accumulate("prenom", eval.getPersonneid().getPrenom());
            evalObject.accumulate("nom", eval.getPersonneid().getNom());
            evalObject.accumulate("note", eval.getNote());
            listObject.put(evalObject);
            somme = somme+eval.getNote();
        }
        float moyenne = somme/nombre;
        object.put("moyenne", moyenne);
        object.put("listEval", listObject);
        return returned.addObject("theResponse",object.toString());
    }
   
    /**
     * Fonction pour récupérer la liste des personnes appartenant à un groupe 
     * @param request l'id du groupe
     * @return la liste des personnes
     * @throws ParseException 
     */
    @RequestMapping(value="recupPersonneParGroupe.do",method=RequestMethod.POST)
    public ModelAndView recupPersonneParGroupe(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        JSONArray listObject = new JSONArray();
        String groupeStr = request.getParameter("groupe");
        int groupeid = Integer.parseInt(groupeStr);
        
             
        List<Personne> listPers = personneRepository.findWithGroupe(groupeid);
        for (Personne pers: listPers){
            JSONObject persObject = new JSONObject();
            persObject.accumulate("id", pers.getPersonneid());
            persObject.accumulate("name", pers.getNom()+" "+pers.getPrenom());
            listObject.put(persObject);
        }
        
        object.put("listPers", listObject);
        return returned.addObject("theResponse",object.toString());
    }


    /**
     * Fonction qui affiche les notes pour toutes les évaluations qu'une personne a passées
     * @param request: l'id de la personne
     * @return le nom des évaluations et la note obtenue par l'élève
     * @throws ParseException 
     */
    @RequestMapping(value="affTestParPers.do",method=RequestMethod.POST)
    public ModelAndView affTestParPers(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        JSONArray listObject = new JSONArray();
        String persStr = request.getParameter("pers");
        int persId = Integer.parseInt(persStr);
        
        List<Evaluation> listEvaluation = evaluationRepository.findWithPers(persId);
        for (Evaluation eval: listEvaluation){
            JSONObject evalObject = new JSONObject();
            evalObject.accumulate("nomEval", eval.getTestid().getQuizid().getNomquiz());
            evalObject.accumulate("note", eval.getNote());
            listObject.put(evalObject);
            
            
        }
        
        object.put("listEval", listObject);
        return returned.addObject("theResponse",object.toString());
    }

    
    /**
     * Fonction qui affiche la liste des résultats pour un test choisi
     * @param request: id du test
     * @return liste des élèves et de leur note
     * @throws ParseException 
     */    
    @RequestMapping(value="affTest.do",method=RequestMethod.POST)
    public ModelAndView affTest(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        JSONArray listObject = new JSONArray();
        String testStr = request.getParameter("test");
        int testId = Integer.parseInt(testStr);
        
        float nombre=0;
        float somme=0;
        List<Evaluation> listEvaluation = evaluationRepository.findWithTest(testId);
        for (Evaluation eval: listEvaluation){
            nombre=nombre+1;
            JSONObject evalObject = new JSONObject();
            evalObject.accumulate("prenom", eval.getPersonneid().getPrenom());
            evalObject.accumulate("nom", eval.getPersonneid().getNom());
            evalObject.accumulate("note", eval.getNote());
            listObject.put(evalObject);
            somme=somme+eval.getNote();            
        }
        object.put("moyenne",somme/nombre);
        
        object.put("listEval", listObject);
        return returned.addObject("theResponse",object.toString());
    }
}

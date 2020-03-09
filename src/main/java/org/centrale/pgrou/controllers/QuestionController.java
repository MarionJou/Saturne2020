/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.controllers;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.centrale.pgrou.items.Contenuquiz;
import org.centrale.pgrou.items.Groupe;
import org.centrale.pgrou.items.Motcle;
import org.centrale.pgrou.items.Notation;
import org.centrale.pgrou.items.Personne;
import org.centrale.pgrou.items.Question;
import org.centrale.pgrou.items.Qcm;
import org.centrale.pgrou.items.Reponse;
import org.centrale.pgrou.items.Qcmrep;
import org.centrale.pgrou.items.Quiz;
import org.centrale.pgrou.items.Test;
import org.centrale.pgrou.repositories.ContenuquizRepository;
import org.centrale.pgrou.repositories.GroupeRepository;
import org.centrale.pgrou.repositories.MotcleRepository;
import org.centrale.pgrou.repositories.NotationRepository;
import org.centrale.pgrou.repositories.PersonneRepository;
import org.centrale.pgrou.repositories.QcmRepository;
import org.centrale.pgrou.repositories.QcmrepRepository;
import org.centrale.pgrou.repositories.QuizRepository;
import org.centrale.pgrou.repositories.QuestionRepository;
import org.centrale.pgrou.repositories.ReponseRepository;
import org.centrale.pgrou.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ECN
 */
@Controller
public class QuestionController {
    @Autowired
    private NotationRepository notationRepository;
    @Autowired
    private MotcleRepository motcleRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private QcmrepRepository qcmrepRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ReponseRepository reponseRepository;
    @Autowired
    private QcmRepository qcmRepository;
    
//    url:"creerQuesRep.do",
//        data: {
//            "type": type,
//            "question": question,
//            "reponses": listRep.JSON.stringify(),
//            "motsCles": listMotsCles.JSON.stringify()
//        },
    
    
    @RequestMapping(value="creerQuesRep.do",method=RequestMethod.POST)
    public ModelAndView creerQuestRep(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        String type = request.getParameter("type");
        String questionStr = request.getParameter("question");
        JSONObject question = new JSONObject(questionStr);
        String reponsesStr = request.getParameter("reponses");
        JSONArray reponses = new JSONArray(reponsesStr);
        String motsClesStr = request.getParameter("motsCles");
        JSONArray motsCles = new JSONArray(motsClesStr);
        String enonceQues = question.getString("enonce");
        String estPrivStr = question.getString("estPrivee");
        Boolean estPrivee = Boolean.parseBoolean(estPrivStr);
        String persIdStr = request.getParameter("personneId");
        int persId = Integer.parseInt(persIdStr);
        
        Optional<Personne> pers = personneRepository.findById(persId);

        Question aQuestion = new Question();
        
        Long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        aQuestion.setDatecreationquestion(date);
        aQuestion.setEnonce(enonceQues);
        aQuestion.setEstprivee(estPrivee);
        aQuestion.setPersonneid(pers.get());
        Question question1 = questionRepository.save(aQuestion);
        
        System.out.println("1".equals(type));
        if ("1".equals(type)){
            Qcm qcm = new Qcm();
            qcm.setQuestionid(question1);
            qcm.setRepunique(true);
            qcmRepository.save(qcm);
        }else if ("2".equals(type)){
            Qcm qcm = new Qcm();
            qcm.setQuestionid(question1);
            qcm.setRepunique(false);
            qcmRepository.save(qcm);
        }
        for (int i=0;i<reponses.length();i++){
            String enonceRep = reponses.getJSONObject(i).getString("enonce");
            Boolean estCorrecte = reponses.getJSONObject(i).getBoolean("estCorrecte"); 
            Reponse aReponse = new Reponse();
            aReponse.setCorrecte(estCorrecte);
            aReponse.setQuestionid(question1);
            Reponse reponse1 = reponseRepository.save(aReponse);
            if ("1".equals(type) || "2".equals(type)){
                Qcmrep qcmrep = new Qcmrep();
                qcmrep.setEnonce(enonceRep);
                qcmrep.setReponseid(reponse1);
                qcmrepRepository.save(qcmrep);
            }
        }
        for (int i=0;i<motsCles.length();i++){
            Optional<Motcle> OptMotCle= motcleRepository.findWithParameter(motsCles.getJSONObject(i).getString("enonce"));
            if (!OptMotCle.isPresent()){
                Motcle aMotCle = new Motcle();
                aMotCle.setMot(motsCles.getJSONObject(i).getString("enonce"));
                Collection <Question> colQues = new ArrayList();
                colQues.add(question1);
                aMotCle.setQuestionCollection(colQues);
                motcleRepository.save(aMotCle);
            }else{
                Motcle aMotCle = OptMotCle.get();
                Collection <Question> colQues=aMotCle.getQuestionCollection();
                colQues.add(question1);
                aMotCle.setQuestionCollection(colQues);
                motcleRepository.save(aMotCle);
            }
        }
        return returned.addObject("theResponse",object.toString());
    }
	

 

    
    @RequestMapping(value="deleteQuestion.do",method=RequestMethod.POST)
    public ModelAndView deleteQuestion(HttpServletRequest request) {
        ModelAndView returned = new ModelAndView("question");
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            Optional<Question> question = questionRepository.findById(id);
            if (question.isPresent()){
                Question laQuestion = question.get();
                Qcm aQcm = qcmRepository.findWithParameters(laQuestion.getQuestionid());
                qcmRepository.delete(aQcm);
                List<Reponse> listRep= reponseRepository.findWithParameter(laQuestion);
                List<Qcmrep> listQcmRep= qcmrepRepository.findWithParameters(laQuestion.getQuestionid());
                for (Qcmrep aQcmRep: listQcmRep){
                    qcmrepRepository.delete(aQcmRep);
                }
                for (Reponse aRep:listRep){
                    reponseRepository.delete(aRep);
                }
                questionRepository.delete(laQuestion);
                
            }
        }
        String persIdStr = request.getParameter("personneId");
        int id = Integer.parseInt(persIdStr);
        List<Question> listQuestion = questionRepository.findWithPersonne(id);
        returned = new ModelAndView("question");
        returned.addObject("listQuestion",listQuestion);
        returned.addObject("personneId",id);

        return returned;
    }


    @RequestMapping(value="modifQuestion.do",method=RequestMethod.POST)
    public ModelAndView modifQuestion(HttpServletRequest request){
    ModelAndView returned = new ModelAndView("modifQuest");
    String idQuesStr = request.getParameter("id");
    int idQues = Integer.parseInt(idQuesStr);
    Optional<Question> optQues= questionRepository.findById(idQues);
    Question aQuestion = optQues.get();
    returned.addObject("question",aQuestion);
    
    Qcm qcm = qcmRepository.findWithParameters(aQuestion.getQuestionid());
    int typeQues = 0;
    returned.addObject("type",qcm);
    if(qcm.getRepunique()){
        typeQues=1;
    }else{
        typeQues=2;
    }
    List<Reponse> listRep = reponseRepository.findWithParameter(aQuestion);
    List<Qcmrep> listQcmRep= qcmrepRepository.findWithParameters(aQuestion.getQuestionid());
//    for (Reponse rep:colReponses){
//        Collection<Qcmrep> colQcmRep=rep.getQcmrepCollection();
//        for (Qcmrep qcmRep:colQcmRep){//Y en a qu'un 
//            listQcmRep.add(qcmRep);
//        }
//    }
    returned.addObject("listReponses",listQcmRep);
    
    List<Motcle> colMotCle = motcleRepository.findWithIdQues(aQuestion.getQuestionid());

    String idPers = request.getParameter("personneId");
    returned.addObject("listMotsCles",colMotCle); 
    returned.addObject("typeQues",typeQues);
    returned.addObject("personneId",idPers);

    return returned;
    }

    @RequestMapping(value="modifQuesRep.do",method=RequestMethod.POST)
    public ModelAndView modifQuestRep(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        String type = request.getParameter("type");
        String questionStr = request.getParameter("question");
        JSONObject question = new JSONObject(questionStr);
        String reponsesStr = request.getParameter("reponses");
        JSONArray reponses = new JSONArray(reponsesStr);
        String motsClesStr = request.getParameter("motsCles");
        JSONArray motsCles = new JSONArray(motsClesStr);
        String enonceQues = question.getString("enonce");
        String estPrivStr = question.getString("estPrivee");
        Boolean estPrivee = Boolean.parseBoolean(estPrivStr);
        String idQuesStr = request.getParameter("idQues");
        int idQues = Integer.parseInt(idQuesStr);
        
        Optional<Question> optQuestion = questionRepository.findById(idQues);
        Question aQuestion = optQuestion.get();
        
        aQuestion.setEnonce(enonceQues);
        aQuestion.setEstprivee(estPrivee);
        aQuestion.setMotcleCollection(new ArrayList());
        Question question1 = questionRepository.save(aQuestion);
        questionRepository.flush();
        
//        List<Motcle> colMC = motcleRepository.findWithIdQues(idQues);
//        for (Motcle motCle:colMC){
//            motcleRepository.delete(motCle);
//        }
        motcleRepository.deleteWithParameter(aQuestion.getQuestionid());
        
        List<Reponse> listRep = reponseRepository.findWithParameter(aQuestion);
        List<Qcmrep> listQcmRep= qcmrepRepository.findWithParameters(aQuestion.getQuestionid());
        for (Qcmrep aQcmRep: listQcmRep){
            qcmrepRepository.delete(aQcmRep);
        }
        for (Reponse aRep:listRep){
            reponseRepository.delete(aRep);
        }
        
        if ("1".equals(type)){
            Qcm qcm = qcmRepository.findWithParameters(aQuestion.getQuestionid());
            qcm.setQuestionid(question1);
            qcm.setRepunique(true);
            qcmRepository.save(qcm);
        }else if ("2".equals(type)){
            Qcm qcm = qcmRepository.findWithParameters(aQuestion.getQuestionid());
            qcm.setQuestionid(question1);
            qcm.setRepunique(false);
            qcmRepository.save(qcm);
        }
        for (int i=0;i<reponses.length();i++){
            String enonceRep = reponses.getJSONObject(i).getString("enonce");
            Boolean estCorrecte = reponses.getJSONObject(i).getBoolean("estCorrecte"); 
            Reponse aReponse = new Reponse();
            aReponse.setCorrecte(estCorrecte);
            aReponse.setQuestionid(question1);
            Reponse reponse1 = reponseRepository.save(aReponse);
            if ("1".equals(type) || "2".equals(type)){
                Qcmrep qcmrep = new Qcmrep();
                qcmrep.setEnonce(enonceRep);
                qcmrep.setReponseid(reponse1);
                qcmrepRepository.save(qcmrep);
            }
        }
        for (int i=0;i<motsCles.length();i++){
            Optional<Motcle> OptMotCle= motcleRepository.findWithParameter(motsCles.getJSONObject(i).getString("enonce"));
            if (!OptMotCle.isPresent()){
                Motcle aMotCle = new Motcle();
                aMotCle.setMot(motsCles.getJSONObject(i).getString("enonce"));
                Collection <Question> colQues = new ArrayList();
                colQues.add(question1);
                aMotCle.setQuestionCollection(colQues);
                motcleRepository.save(aMotCle);
            }else{
                Motcle aMotCle = OptMotCle.get();
                Collection <Question> colQues=aMotCle.getQuestionCollection();
                colQues.add(question1);
                aMotCle.setQuestionCollection(colQues);
                motcleRepository.save(aMotCle);
            }
        }
        motcleRepository.flush();
        questionRepository.flush();
        return returned.addObject("theResponse",object.toString());
    }

    @RequestMapping(value="ecranCreation.do",method=RequestMethod.POST)
    public ModelAndView ecranCreation(HttpServletRequest request) throws ParseException {
        String id = request.getParameter("personneId");
        ModelAndView returned = new ModelAndView("questRep");
        returned.addObject("personneId",id);

        return returned;
    }
}
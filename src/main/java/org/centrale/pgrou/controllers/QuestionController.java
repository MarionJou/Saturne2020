/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.centrale.pgrou.items.Contenuquiz;
import org.centrale.pgrou.items.Groupe;
import org.centrale.pgrou.items.Notation;
import org.centrale.pgrou.items.Question;
import org.centrale.pgrou.items.Qcm;
import org.centrale.pgrou.items.Reponse;
import org.centrale.pgrou.items.Qcmrep;
import org.centrale.pgrou.items.Quiz;
import org.centrale.pgrou.items.Test;
import org.centrale.pgrou.repositories.ContenuquizRepository;
import org.centrale.pgrou.repositories.GroupeRepository;
import org.centrale.pgrou.repositories.NotationRepository;
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
    private GroupeRepository groupeRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QcmrepRepository qcmrepRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ReponseRepository reponseRepository;
    @Autowired
    private QcmRepository qcmRepository;
    
    
    
	
    @RequestMapping(value="creerQuestion.do",method=RequestMethod.POST)
    public ModelAndView creerQuestion(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        
        String enonce = request.getParameter("enonce");
        String estPriveeString = request.getParameter("estPrivee");
        Boolean estPrivee = Boolean.parseBoolean(estPriveeString);
        
        Question question = new Question();
        question.setEnonce(enonce);
        question.setEstprivee(estPrivee);
        
        Long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        question.setDatecreationquestion(date);
        
        Question question1 = questionRepository.save(question);
        
        String repUniqueString = request.getParameter("repUni");
        Boolean repUnique = Boolean.parseBoolean(repUniqueString);
        
        Qcm qcm = new Qcm();
        qcm.setQuestionid(question1);
        qcm.setRepunique(repUnique);
        
        Qcm qcm1 = qcmRepository.save(qcm);
        System.out.println(qcm1.getQcmid());
        System.out.println("Est ce que ça marche?");
        
        object.put("enonceQues", enonce);
        object.put("idQues",question1.getQuestionid());
        return returned.addObject("theResponse",object.toString());
        
    }
    
    @RequestMapping(value="creerReponse.do",method=RequestMethod.POST)
    public ModelAndView creerReponse(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        
        String correcteString = request.getParameter("correcte");
        Boolean correcte = Boolean.parseBoolean(correcteString);
        String questionId = request.getParameter("idQues");
        
        Reponse reponse = new Reponse();
        reponse.setCorrecte(correcte);
        Optional<Question> question = questionRepository.findById(Integer.parseInt(questionId));
        
        reponse.setQuestionid(question.get());
        
        Reponse reponse1 = reponseRepository.save(reponse);
        
        //Création du QCMRep
        String enonce = request.getParameter("enonceRep");
        System.out.println("J'adore ma vie");
        Qcmrep qcmrep = new Qcmrep();
        qcmrep.setEnonce(enonce);
        qcmrep.setReponseid(reponse1);
        System.out.println(reponse1.getReponseid());
        
        Qcmrep qcmrep1 = qcmrepRepository.save(qcmrep);
        System.out.println("Buttez moi!!! "+qcmrep1.getQcmrepid());
        
        object.put("enoncePre", enonce);
        object.put("correcte",correcte);
        return returned.addObject("theResponse",object.toString());
        
    }

    
    @RequestMapping(value="terminerReponse.do",method=RequestMethod.GET)
    public ModelAndView terminerReponse() throws ParseException {
        List<Question> listQuestion = questionRepository.findAll();
        ModelAndView returned = new ModelAndView("question");
        returned = new ModelAndView("question");
        returned.addObject("listQuestion",listQuestion);
        
        return returned;
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
                Qcm qcm = qcmRepository.findWithParameters(laQuestion.getQuestionid());
                qcmRepository.delete(qcm);
                questionRepository.delete(laQuestion);
                
                
            }
        }
        List<Question> listQuestion = questionRepository.findAll();
        returned = new ModelAndView("question");
        returned.addObject("listQuestion",listQuestion);
        return returned;
    }
}

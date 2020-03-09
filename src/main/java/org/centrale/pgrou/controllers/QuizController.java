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
 * @author Pierre
 */
@Controller
public class QuizController {
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
    
    
    
	
    @RequestMapping(value="creerQuiz.do",method=RequestMethod.POST)
    public ModelAndView creerQuiz(HttpServletRequest request) throws ParseException {
//        ModelAndView returned = new ModelAndView("ajax");
//        JSONObject object = new JSONObject();
//        
//        Collection<Contenuquiz> listeQuestion = request.getParameter("idQuestions");
//        String auteur =request.getParameter("auteur");
//        String date = request.getParameter("date");
//        String nomQuiz = request.getParameter("nomQuiz");
//        
//        Quiz quiz = new Quiz();
//
//        quiz.setQuizid("je ne sais pas quoi mettre ici");
//        quiz.setNomquiz(nomQuiz);
//        quiz.setDatecreationquiz(date);
//        quiz.setPersonneid(auteur);
//        quiz.setContenuquizcollection(listeQuestion)
//        
//        return returned.addObject("theResponse",object.toString());
        String idName = request.getParameter("idQuiz");
        String quizName = request.getParameter("nameQuiz");
        return new ModelAndView("quiz");
    }
}
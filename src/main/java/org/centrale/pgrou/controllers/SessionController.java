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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.centrale.pgrou.items.Contenuquiz;
import org.centrale.pgrou.items.Groupe;
import org.centrale.pgrou.items.Notation;
import org.centrale.pgrou.items.Question;
import org.centrale.pgrou.items.Quiz;
import org.centrale.pgrou.items.Test;
import org.centrale.pgrou.repositories.ContenuquizRepository;
import org.centrale.pgrou.repositories.GroupeRepository;
import org.centrale.pgrou.repositories.NotationRepository;
import org.centrale.pgrou.repositories.PersonneRepository;
import org.centrale.pgrou.repositories.QuestionRepository;
import org.centrale.pgrou.repositories.QuizRepository;
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
public class SessionController {
    @Autowired
    private NotationRepository notationRepository;
    @Autowired
    private GroupeRepository groupeRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private ContenuquizRepository contenuquizRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private QuestionRepository questionRepository;
    
    
//    @RequestMapping(value="index.do",method=RequestMethod.GET)
//    public ModelAndView handleGet() {
//        return new ModelAndView("index");
//    }
//    
//    @RequestMapping(value="index.do",method=RequestMethod.POST)
//    public ModelAndView handlePost(@ModelAttribute("User")User anUser) {
//        ModelAndView returned;
//        if ((anUser.getUser().equals("Marion")) && (anUser.getPasswd().equals("admin"))){
//            List<Notation> listNotation = notationRepository.findAll();
//            List<Groupe> listGroupe = groupeRepository.findAll();
//            List<Quiz> listQuiz = quizRepository.findAll();
//            List<Test> listTest = testRepository.findAll();
//            returned = new ModelAndView("session");
//            returned.addObject("listNotations", listNotation);
//            returned.addObject("listGroupes", listGroupe);
//            returned.addObject("listQuizs",listQuiz);
//            returned.addObject("listTests",listTest);
//        }else if ((anUser.getUser().equals("Judith")) && (anUser.getPasswd().equals("admin"))){
//            List<Test> listTest = testRepository.findWithParameters(new java.util.Date(),1);
//            List<TestAff> listTestAff = new ArrayList();
//            String format = "HH:mm";
//            java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
//            for (Test aTest: listTest){
//                TestAff aTestAff = new TestAff(aTest.getTestid(),aTest.getQuizid().getNomquiz());
//                aTestAff.setDureeStr(formater.format(aTest.getDureemaxtest()));
//                int duree = aTestAff.stringToInt(formater.format(aTest.getDureemaxtest()));
//                aTestAff.setDureeInt(duree);
//                listTestAff.add(aTestAff);
//            }
//            returned = new ModelAndView("affTest");
//            returned.addObject("listTests",listTestAff);
//        } else if ((anUser.getUser().equals("Alban")) && (anUser.getPasswd().equals("admin"))){
//            List<Question> listQuestion = questionRepository.findAll();
//            returned = new ModelAndView("question");
//            returned.addObject("listQuestion",listQuestion);
//        } else if ((anUser.getUser().equals("Pierre")) && (anUser.getPasswd().equals("admin"))){
////            List<Question> listQuestion = questionRepository.findAll();
//            returned = new ModelAndView("quiz");
////            returned.addObject("listQuestion",listQuestion);
//        } else if ((anUser.getUser().equals("Amine")) && (anUser.getPasswd().equals("admin"))){
//            List<Groupe> listGroupe = groupeRepository.findAll();
//            List<Test> listTest = testRepository.findWithPersonne(1);
//            returned = new ModelAndView("affResultat");
//            returned.addObject("listGroupe",listGroupe);
//            returned.addObject("listTest",listTest);
//        }else{
//            returned = new ModelAndView("index");
//        }
//        return returned;
//    }
//    
    @RequestMapping(value="creerTest.do",method=RequestMethod.POST)
    public ModelAndView creerTest1(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        JSONArray questions = new JSONArray();
        
        String dateDeb = request.getParameter("dateDeb");
        String heureDeb = request.getParameter("heureDeb");
        String dateFin = request.getParameter("dateFin");
        String heureFin = request.getParameter("heureFin");
        String duree = request.getParameter("duree");
        String notationId = request.getParameter("notationId");
        String groupeId = request.getParameter("groupeId");
        String quizId = request.getParameter("quizId");
        
        Test test = new Test();
        Optional<Groupe> groupe = groupeRepository.findById(Integer.parseInt(groupeId));
        Optional<Notation> notation = notationRepository.findById(Integer.parseInt(notationId));
        Optional<Quiz> quiz = quizRepository.findById(Integer.parseInt(quizId));
        
        if (groupe.isPresent()){
            Groupe unGroupe = groupe.get();
            test.setGroupeid(unGroupe);
        }
        if (notation.isPresent()){
            Notation uneNotation = notation.get();
            test.setNotationid(uneNotation);
        }
        if (quiz.isPresent()){
            Quiz unQuiz = quiz.get();
            test.setQuizid(unQuiz);
            List<Contenuquiz> quizCont = contenuquizRepository.findWithParameter(unQuiz);
            for (Contenuquiz c: quizCont){
                JSONObject jsonQuestion = new JSONObject();
                Question question = c.getQuestionid();
                jsonQuestion.accumulate("enonce", question.getEnonce());
                jsonQuestion.accumulate("contenuQuiz", c.getContenuquizid());
                questions.put(jsonQuestion);
            }
            object.put("questions",questions);
        }
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date timeDeb =  df.parse(dateDeb+" "+heureDeb);
        Date timeFin = df.parse(dateFin+" "+heureFin);
        
        DateFormat dfb = new SimpleDateFormat("HH:mm");
        Date timeDuree = dfb.parse(duree);
        
        test.setDatedebuttest(timeDeb);
        test.setDatefintest(timeFin);
        test.setDureemaxtest(timeDuree);
        
        Test test1 = testRepository.save(test);
        
        
        return returned.addObject("theResponse",object.toString());
        
    }
    
    @RequestMapping(value="allouerPts.do",method=RequestMethod.POST)
    public ModelAndView creerTest2(HttpServletRequest request){
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        
        String listePoints = request.getParameter("listePoints");
        JSONArray points = new JSONArray(listePoints);
        String listeIDs = request.getParameter("listeIDs");
        JSONArray contenuQuizIds = new JSONArray(listeIDs);
        
        for (int i=0; i<points.length();i++){
            Optional<Contenuquiz> c = contenuquizRepository.findById(Integer.parseInt(contenuQuizIds.getString(i)));
            if (c.isPresent()){
                Contenuquiz unContenuQuiz = c.get();
                unContenuQuiz.setNombrepoints(Integer.parseInt(points.getString(i)));
                contenuquizRepository.save(unContenuQuiz);
            }
        }
        
        object.put("res", contenuQuizIds);
        
        return returned.addObject("theResponse", object.toString());
    }

        
    @RequestMapping(value="valider.do",method=RequestMethod.POST)
    public ModelAndView handlePost2(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        ModelAndView returned = new ModelAndView();
        List<Notation> listNotation = notationRepository.findAll();
        List<Groupe> listGroupe = groupeRepository.findAll();
        List<Quiz> listQuiz = quizRepository.findWithPersonne(id);
        List<Test> listTest = testRepository.findWithPersonne(id);
        returned = new ModelAndView("session");
        returned.addObject("listNotations", listNotation);
        returned.addObject("listGroupes", listGroupe);
        returned.addObject("listQuizs",listQuiz);
        returned.addObject("listTests",listTest);
        returned.addObject("personneId",id);
        return returned;
    }
    
    @RequestMapping(value="delete.do",method=RequestMethod.POST)
    public ModelAndView handleDelete(HttpServletRequest request) {
        ModelAndView returned = new ModelAndView("session");
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            Optional<Test> test = testRepository.findById(id);
            if (test.isPresent()){
                testRepository.delete(test.get());
            }
        }
        String persIdStr = request.getParameter("personneId");
        int persId = Integer.parseInt(persIdStr);
        List<Notation> listNotation = notationRepository.findAll();
        List<Groupe> listGroupe = groupeRepository.findAll();
        List<Quiz> listQuiz = quizRepository.findWithPersonne(persId);
        List<Test> listTest = testRepository.findWithPersonne(persId);
        returned = new ModelAndView("session");
        returned.addObject("listNotations", listNotation);
        returned.addObject("listGroupes", listGroupe);
        returned.addObject("listQuizs",listQuiz);
        returned.addObject("listTests",listTest);
        returned.addObject("personneId",persId);
        return returned;
    }

    
}

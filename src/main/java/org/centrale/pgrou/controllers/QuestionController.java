/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.centrale.pgrou.items.*;
import org.centrale.pgrou.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private ConnexionRepository connexionRepository;
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

    /**
     * Fonction qui récupère les informations nécessaires à la création d'une
     * question et qui la créée
     *
     * @param request
     * @return un objet nul
     * @throws ParseException
     */
    @RequestMapping(value = "creerQuesRep.do", method = RequestMethod.POST)
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
        if ("1".equals(type)) {
            Qcm qcm = new Qcm();
            qcm.setQuestionid(question1);
            qcm.setRepunique(true);
            qcmRepository.save(qcm);
        } else if ("2".equals(type)) {
            Qcm qcm = new Qcm();
            qcm.setQuestionid(question1);
            qcm.setRepunique(false);
            qcmRepository.save(qcm);
        }
        for (int i = 0; i < reponses.length(); i++) {
            String enonceRep = reponses.getJSONObject(i).getString("enonce");
            Boolean estCorrecte = reponses.getJSONObject(i).getBoolean("estCorrecte");
            Reponse aReponse = new Reponse();
            aReponse.setCorrecte(estCorrecte);
            aReponse.setQuestionid(question1);
            Reponse reponse1 = reponseRepository.save(aReponse);
            if ("1".equals(type) || "2".equals(type)) {
                Qcmrep qcmrep = new Qcmrep();
                qcmrep.setEnonce(enonceRep);
                qcmrep.setReponseid(reponse1);
                qcmrepRepository.save(qcmrep);
            }
        }
        for (int i = 0; i < motsCles.length(); i++) {
            Optional<Motcle> OptMotCle = motcleRepository.findWithParameter(motsCles.getJSONObject(i).getString("enonce"));
            if (!OptMotCle.isPresent()) {
                Motcle aMotCle = new Motcle();
                aMotCle.setMot(motsCles.getJSONObject(i).getString("enonce"));
                Collection<Question> colQues = new ArrayList();
                colQues.add(question1);
                aMotCle.setQuestionCollection(colQues);
                motcleRepository.save(aMotCle);
            } else {
                Motcle aMotCle = OptMotCle.get();
                Collection<Question> colQues = aMotCle.getQuestionCollection();
                colQues.add(question1);
                aMotCle.setQuestionCollection(colQues);
                motcleRepository.save(aMotCle);
            }
        }
        Qcm qcmQues = qcmRepository.findWithParameters(question1.getQuestionid());
        //Collection<Reponse> repQues = question1.getReponseCollection();
        List<Reponse> repQues = reponseRepository.findWithParameter(question1);
        JSONArray listReponses = new JSONArray();
        for(Reponse r: repQues){
            JSONObject rep = new JSONObject();
            rep.put("repId", r.getReponseid());
            rep.put("estCorrecte", r.getCorrecte());
            Qcmrep qcmrep = qcmrepRepository.findWithParameter(r);
            rep.put("enonceRep", qcmrep.getEnonce());
            listReponses.put(rep);
        }
        object.put("quesId", question1.getQuestionid());
        object.put("enonce", question1.getEnonce());
        object.put("repUnique", qcmQues.getRepunique());
        object.put("listeReponses", listReponses);
        return returned.addObject("theResponse",object.toString());
    }

    /**
     * Fonction qui permet de supprimer une question
     *
     * @param request on récupère l'id de la question que l'on veut supprimer
     * @return la page avec la liste des questions déjà crées
     */
    @RequestMapping(value = "deleteQuestion.do", method = RequestMethod.POST)
    public ModelAndView deleteQuestion(HttpServletRequest request) {
        ModelAndView returned = new ModelAndView("question");
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            Optional<Question> question = questionRepository.findById(id);
            if (question.isPresent()) {
                Question laQuestion = question.get();
                Qcm aQcm = qcmRepository.findWithParameters(laQuestion.getQuestionid());
                if (aQcm!=null){
                    qcmRepository.delete(aQcm);
                }
                List<Reponse> listRep = reponseRepository.findWithParameter(laQuestion);
                List<Qcmrep> listQcmRep = qcmrepRepository.findWithParameters(laQuestion.getQuestionid());
                for (Qcmrep aQcmRep : listQcmRep) {
                    qcmrepRepository.delete(aQcmRep);
                }
                for (Reponse aRep : listRep) {
                    reponseRepository.delete(aRep);
                }
                questionRepository.delete(laQuestion);

            }
        }
        String code = request.getParameter("code");
        Optional<Connexion> coResult = connexionRepository.findById(code);
        if (coResult.isPresent()) {
            Connexion connexion = coResult.get();
            Security.setDefaultData(returned, connexion);
            Collection<Question> listQuestion = questionRepository.findWithPersonne(connexion.getPersonneid().getPersonneid());
            returned.addObject("listQuestion", listQuestion);
        }

        return returned;
    }

    /**
     * Fonction qui permet de modifier une question (sans modifier son type)
     *
     * @param request on récupère l'id de la question
     * @return on renvoie un formulaire pré remplie avec les informations de la
     * question
     */
    @RequestMapping(value = "modifQuestion.do", method = RequestMethod.POST)
    public ModelAndView modifQuestion(HttpServletRequest request) {
        ModelAndView returned = new ModelAndView("modifQuest");
        String code = request.getParameter("code");
        Optional<Connexion> coResult = connexionRepository.findById(code);
        if (coResult.isPresent()) {
            Connexion connexion = coResult.get();
            Security.setDefaultData(returned, connexion);
        }
        String idQuesStr = request.getParameter("id");
        int idQues = Integer.parseInt(idQuesStr);
        Optional<Question> optQues = questionRepository.findById(idQues);
        Question aQuestion = optQues.get();
        returned.addObject("question", aQuestion);

        Qcm qcm = qcmRepository.findWithParameters(aQuestion.getQuestionid());
        int typeQues = 0;
        returned.addObject("type", qcm);
        if (qcm.getRepunique()) {
            typeQues = 1;
        } else {
            typeQues = 2;
        }
        List<Reponse> listRep = reponseRepository.findWithParameter(aQuestion);
        List<Qcmrep> listQcmRep = qcmrepRepository.findWithParameters(aQuestion.getQuestionid());
        returned.addObject("listReponses", listQcmRep);

        List<Motcle> colMotCle = motcleRepository.findWithIdQues(aQuestion.getQuestionid());

        returned.addObject("listMotsCles", colMotCle);
        returned.addObject("typeQues", typeQues);
        return returned;
    }

    /**
     * Fonction qui permet d'éditer les modifications faîtes à une fonction
     *
     * @param request: on récupère la question modifiée
     * @return objet null
     * @throws ParseException
     */
    @RequestMapping(value = "modifQuesRep.do", method = RequestMethod.POST)
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

        motcleRepository.deleteWithParameter(aQuestion.getQuestionid());

        List<Reponse> listRep = reponseRepository.findWithParameter(aQuestion);
        List<Qcmrep> listQcmRep = qcmrepRepository.findWithParameters(aQuestion.getQuestionid());
        for (Qcmrep aQcmRep : listQcmRep) {
            qcmrepRepository.delete(aQcmRep);
        }
        for (Reponse aRep : listRep) {
            reponseRepository.delete(aRep);
        }

        if ("1".equals(type)) {
            Qcm qcm = qcmRepository.findWithParameters(aQuestion.getQuestionid());
            qcm.setQuestionid(question1);
            qcm.setRepunique(true);
            qcmRepository.save(qcm);
        } else if ("2".equals(type)) {
            Qcm qcm = qcmRepository.findWithParameters(aQuestion.getQuestionid());
            qcm.setQuestionid(question1);
            qcm.setRepunique(false);
            qcmRepository.save(qcm);
        }
        for (int i = 0; i < reponses.length(); i++) {
            String enonceRep = reponses.getJSONObject(i).getString("enonce");
            Boolean estCorrecte = reponses.getJSONObject(i).getBoolean("estCorrecte");
            Reponse aReponse = new Reponse();
            aReponse.setCorrecte(estCorrecte);
            aReponse.setQuestionid(question1);
            Reponse reponse1 = reponseRepository.save(aReponse);
            if ("1".equals(type) || "2".equals(type)) {
                Qcmrep qcmrep = new Qcmrep();
                qcmrep.setEnonce(enonceRep);
                qcmrep.setReponseid(reponse1);
                qcmrepRepository.save(qcmrep);
            }
        }
        for (int i = 0; i < motsCles.length(); i++) {
            Optional<Motcle> OptMotCle = motcleRepository.findWithParameter(motsCles.getJSONObject(i).getString("enonce"));
            if (!OptMotCle.isPresent()) {
                Motcle aMotCle = new Motcle();
                aMotCle.setMot(motsCles.getJSONObject(i).getString("enonce"));
                Collection<Question> colQues = new ArrayList();
                colQues.add(question1);
                aMotCle.setQuestionCollection(colQues);
                motcleRepository.save(aMotCle);
            } else {
                Motcle aMotCle = OptMotCle.get();
                Collection<Question> colQues = aMotCle.getQuestionCollection();
                colQues.add(question1);
                aMotCle.setQuestionCollection(colQues);
                motcleRepository.save(aMotCle);
            }
        }
        motcleRepository.flush();
        questionRepository.flush();
        return returned.addObject("theResponse", object.toString());
    }

    /**
     * Fonction qui permet d'aller sur la page de création d'une question
     *
     * @param request:
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "versCreerQues.do", method = RequestMethod.GET)
    public ModelAndView ecranCreation(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("questRep");
        String code = request.getParameter("code");
        Optional<Connexion> coResult = connexionRepository.findById(code);
        if (coResult.isPresent()) {
            Connexion connexion = coResult.get();
            Security.setDefaultData(returned, connexion);
            Integer persId = connexion.getPersonneid().getPersonneid();
            Collection<Question> listQuestion = questionRepository.findWithPersonne(persId);
            returned.addObject("listQuestion", listQuestion);
        }
        return returned;
    }
}

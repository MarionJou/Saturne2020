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
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.centrale.pgrou.items.*;
import org.centrale.pgrou.repositories.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Marion PGROU
 */
@Controller
public class RpseTestController {
    @Autowired
    private QcmrepRepository qcmrepRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private ContenuquizRepository contenuquizRepository;
    @Autowired
    private ReponseRepository reponseRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private EvaluationquestionRepository evaluationquestionRepository;
    @Autowired
    private EvaluationreponseRepository evaluationreponseRepository;
    @Autowired
    private QcmrepevalRepository qcmrepevalRepository;
    @Autowired
    private NotationRepository notationRepository;
    @Autowired
    private QcmRepository qcmRepository;
    
    /**
     * Fonction qui affiche les questions avec les réponses du test choisi pour permettre aux élèves de répondre
     * @param request: l'id du test
     * @return la page permettant de répondre
     */
    @RequestMapping(value="repondre.do",method=RequestMethod.POST)
    public ModelAndView handlePost(HttpServletRequest request) {
        ModelAndView returned;
        List<QuesRepQCM> listQuesRep = new ArrayList();
        
        String testId = request.getParameter("id");
        Optional<Test> test = testRepository.findById(Integer.parseInt(testId));
        if (test.isPresent()){
            returned = new ModelAndView("repondre");
            Test unTest = test.get();
            TestAff unTestAff = new TestAff(unTest.getTestid(),unTest.getQuizid().getNomquiz());
            String format = "HH:mm";
            java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
            String dureeStr = formater.format(unTest.getDureemaxtest());
            unTestAff.setDureeStr(dureeStr);
            int dureeInt = unTestAff.stringToInt(dureeStr);
            unTestAff.setDureeInt(dureeInt);
            returned.addObject("test",unTestAff);
            List<Contenuquiz> quizCont = contenuquizRepository.findWithParameter(unTest.getQuizid());
            Integer i = 1;
            for (Contenuquiz c: quizCont){
                Question question = c.getQuestionid();
                List<Reponse> reponses = reponseRepository.findWithParameter(question);
                List<ReponseQCM> listRepQCM = new ArrayList();
                for (Reponse rep: reponses){
                    Qcmrep qr = qcmrepRepository.findWithParameter(rep);
                    ReponseQCM repQCM = new ReponseQCM(qr.getEnonce(),qr.getQcmrepid(),rep.getCorrecte());
                    listRepQCM.add(repQCM);                    
                }
                List<ReponseQCM> listRepQCMRand = new ArrayList();
                int size = listRepQCM.size();
                Random randRep = new Random();
                for(int k=0; k<size;k++){
                    int index =randRep.nextInt(size-k);
                    listRepQCMRand.add(listRepQCM.get(index));
                    listRepQCM.remove(index);
                }
                Qcm qcm = qcmRepository.findWithParameters(question.getQuestionid());
                Boolean repUni= qcm.getRepunique();
                QuesRepQCM ques = new QuesRepQCM(question.getEnonce(),c.getContenuquizid(),listRepQCMRand, repUni, i);
                listQuesRep.add(ques);
                i=i+1;
            }
            List<QuesRepQCM> listQuesRepRand= new ArrayList();
            int size = i-1;
            Random rand = new Random();
            for(int k=0; k<(i-1);k++){
                int index =rand.nextInt(size-k);
                listQuesRepRand.add(listQuesRep.get(index));
                listQuesRep.remove(index);
            }
            returned.addObject("nombre",i-1);
            returned.addObject("quesRep", listQuesRepRand);
            String personneId = request.getParameter("personneId");
            returned.addObject("personneId", personneId);
            returned.addObject("currentDate",System.currentTimeMillis());
        }else{
        returned = new ModelAndView("index");
    }
    return returned;
    }
    
    /**
     * Fonction qui permet de sauvegarder la réponse de l'élève quand celui-ci à valider
     * @param request: la liste des cases cochées et l'id de l'étudiant ainsi que celui du test
     * @return la liste des tests faisable par l'étudiant
     * @throws ParseException 
     */
    @RequestMapping(value="envRep.do",method=RequestMethod.POST)
    public ModelAndView envRep(HttpServletRequest request) throws ParseException {
        ModelAndView returned;
        String personneIdStr = request.getParameter("personneId");
        int personneId = Integer.parseInt(personneIdStr);
        List<Test> listTest = testRepository.findWithParameters(new java.util.Date(),personneId);
        List<TestAff> listTestAff = new ArrayList();
        String format = "HH:mm";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
        for (Test aTest: listTest){
            TestAff aTestAff = new TestAff(aTest.getTestid(),aTest.getQuizid().getNomquiz());
            aTestAff.setDureeStr(formater.format(aTest.getDureemaxtest()));
            int duree = aTestAff.stringToInt(formater.format(aTest.getDureemaxtest()));
            aTestAff.setDureeInt(duree);
            listTestAff.add(aTestAff);
        }
        
        
        Evaluation eval = new Evaluation();
        
        //Création de l'évaluation
        String testId = request.getParameter("testId");
        Optional<Test> unTest = testRepository.findById(Integer.parseInt(testId));
        if (unTest.isPresent()){
            Test leTest = unTest.get();
            eval.setTestid(leTest);
            
            Optional<Personne> unePers = personneRepository.findById(personneId); 
            if (unePers.isPresent()){
                Personne laPers = unePers.get();
                eval.setPersonneid(laPers); 
                String dateDebStr = request.getParameter("currentDate");
                long dateDebInt = Long.parseLong(dateDebStr);
                Date dateDeb = new Date(dateDebInt);
//                java.util.Date date = new java.util.Date(); //Je mets la date d'aujourd'hui
                eval.setDatedebutevaluation(dateDeb);
                DateFormat dfb = new SimpleDateFormat("HH:mm:ss");
                long dureeInt = System.currentTimeMillis()-dateDebInt;
                String dureeStr = this.millisecToString(dureeInt);
                Date timeDuree = dfb.parse(dureeStr);
//                Date timeDuree = new Date(dureeInt+3600000);
//                Date timePlus = new Date(0);
                eval.setDureeevaluation(timeDuree);
                Evaluation evalId = evaluationRepository.save(eval);
                
                
                //Crééons maintenant evaluationQuestion et evaluationReponse
                String strNombre = request.getParameter("nombre");
                Integer nombre = Integer.parseInt(strNombre);
                for (int i = 1; i <= nombre; i++){
                    Evaluationquestion evaQues = new Evaluationquestion();
                    evaQues.setEvaluation(evalId);
                    String strIdQues = request.getParameter("q"+i);
                    Integer idQues = Integer.parseInt(strIdQues);
                    Optional<Contenuquiz> aContQuiz= contenuquizRepository.findById(idQues); //////////
                    if(aContQuiz.isPresent()){
                        Contenuquiz theContQuiz = aContQuiz.get();
                        evaQues.setContenuquiz(theContQuiz);
                        
                        EvaluationquestionPK evaQuesPK = new EvaluationquestionPK(evalId.getEvaluationid(),theContQuiz.getContenuquizid());
                        evaQues.setEvaluationquestionPK(evaQuesPK);
                        Evaluationquestion evaQues2 = evaluationquestionRepository.save(evaQues); 
                        Collection<Reponse> colRep = theContQuiz.getQuestionid().getReponseCollection(); ////
                        for (Reponse rep: colRep){
                            Collection<Qcmrep> colQcmRep = rep.getQcmrepCollection();
                            for (Qcmrep qcmRep: colQcmRep){
                                Evaluationreponse evaRep = new Evaluationreponse();
                                evaRep.setEvaluationquestion(evaQues2);
                                Evaluationreponse evaRep2 = evaluationreponseRepository.save(evaRep);
                                Qcmrepeval qcmRepEval = new Qcmrepeval();
                                qcmRepEval.setEvaluationreponseid(evaRep2);
                                qcmRepEval.setQcmrepid(qcmRep);
                                qcmRepEval.setCochee(false);/////////////////
                                String[] strIdRep = request.getParameterValues("r"+idQues);//On récupère les réponses cochées
                                if (strIdRep!=null){
                                    for (int j =0; j<strIdRep.length; j++){ //Régler le pb si c'est vide?
                                        int IdQCMRep = Integer.parseInt(strIdRep[j]);
                                        int azer = qcmRep.getQcmrepid();
                                        if (IdQCMRep==azer){
                                            qcmRepEval.setCochee(true);
                                        }
                                    }
                                }
                                
                                Qcmrepeval qcmRepEval2 = qcmrepevalRepository.save(qcmRepEval);
                            }
                            
                        }
                    }
                    
                }
                float note = corriger(evalId.getEvaluationid());
                System.out.println("Vous avez obtenu la note de: "+note); 
                evalId.setNote(note);
                evaluationRepository.save(evalId);
            }
        }
        
        returned = new ModelAndView("affTest");
        returned.addObject("listTests",listTestAff);
        returned.addObject("personneId",personneId);
        return returned;
    }

    /**
     * Fonction qui récupère les réponses de l'étudiant si le temps est écoulé
     * @param request la liste des cases cochées, l'id de la personne et celui du test
     * @return rien
     * @throws ParseException 
     */
    @RequestMapping(value="tempsEcouler.do",method=RequestMethod.POST)
    public ModelAndView tempsEcoulerPOST(HttpServletRequest request) throws ParseException{
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        String personneIdStr = request.getParameter("personneId");
        int personneId = Integer.parseInt(personneIdStr);
        List<Test> listTest = testRepository.findAll();
        Evaluation eval = new Evaluation();
        
        //Création de l'évaluation
        String testId = request.getParameter("testId");
        Optional<Test> unTest = testRepository.findById(Integer.parseInt(testId));
        if (unTest.isPresent()){
            Test leTest = unTest.get();
            eval.setTestid(leTest);
            Optional<Personne> unePers = personneRepository.findById(personneId); //Tant que y a pas de co je mets une personne de façon arbitraire
            if (unePers.isPresent()){
                Personne laPers = unePers.get();
                eval.setPersonneid(laPers);
                java.util.Date date = new java.util.Date(); //Je mets la date d'aujourd'hui
                eval.setDatedebutevaluation(date);
                DateFormat dfb = new SimpleDateFormat("HH:mm");
                Date timeDuree = leTest.getDureemaxtest();
                eval.setDureeevaluation(timeDuree);
                Evaluation evalId = evaluationRepository.save(eval);
                
                //Crééons maintenant evaluationQuestion et evaluationReponse
                String listeQuestions = request.getParameter("questions");
                JSONArray questions = new JSONArray(listeQuestions);
                for (int i=0; i<questions.length();i++){ 
                    Evaluationquestion evaQues = new Evaluationquestion();
                    evaQues.setEvaluation(evalId);
                    JSONObject aQuestion = questions.getJSONObject(i);
                    int quesId = aQuestion.getInt("quesId");
                    Optional<Contenuquiz> aContQuiz= contenuquizRepository.findById(quesId);
                    if(aContQuiz.isPresent()){
                        Contenuquiz theContQuiz = aContQuiz.get();
                        evaQues.setContenuquiz(theContQuiz);
                        EvaluationquestionPK evaQuesPK = new EvaluationquestionPK(evalId.getEvaluationid(),theContQuiz.getContenuquizid());
                        evaQues.setEvaluationquestionPK(evaQuesPK);
                        Evaluationquestion evaQues2 = evaluationquestionRepository.save(evaQues); 
                        Collection<Reponse> colRep = theContQuiz.getQuestionid().getReponseCollection();
                        for (Reponse rep: colRep){
                            Collection<Qcmrep> colQcmRep = rep.getQcmrepCollection();
                            for (Qcmrep qcmRep: colQcmRep){
                                Evaluationreponse evaRep = new Evaluationreponse();
                                evaRep.setEvaluationquestion(evaQues2);
                                Evaluationreponse evaRep2 = evaluationreponseRepository.save(evaRep);
                                Qcmrepeval qcmRepEval = new Qcmrepeval();
                                qcmRepEval.setEvaluationreponseid(evaRep2);
                                qcmRepEval.setQcmrepid(qcmRep);
                                qcmRepEval.setCochee(false);
                                String listReponses = aQuestion.getString("reponses");
                                JSONArray reponses = new JSONArray(listReponses);
                                for (int j=0; j<reponses.length();j++){
                                    int reponseId = reponses.getInt(j);
                                    if(reponseId==qcmRep.getQcmrepid()){
                                        qcmRepEval.setCochee(true);
                                    }
                                }
                                Qcmrepeval qcmRepEval2 = qcmrepevalRepository.save(qcmRepEval);
                            }
                        }
                    }
                }
                float note = corriger(evalId.getEvaluationid());
                evalId.setNote(note);
                evaluationRepository.save(evalId);
            }
        }
                    
        
        object.put("res", 0);
        return returned.addObject("theResponse", object.toString());
    }
    

    /**
     * Fonction qui renvoie la note obtenu par l'étudiant suivant la notation choisie
     * @param idEval : id de l'évaluation
     * @return la note obtenue
     */
    public float corriger (int idEval){
        float note = 0;
        int notationId = notationRepository.findNotationid(idEval);
        List<Contenuquiz> listQuestion = contenuquizRepository.findWithIdEval(idEval);
        for (Contenuquiz question: listQuestion){
            Qcm aQcm=qcmRepository.findWithParameters(question.getQuestionid().getQuestionid());
            Evaluationquestion evalQues = evaluationquestionRepository.findWithContenuQuiz(question.getContenuquizid(),idEval);
            Boolean repUni = aQcm.getRepunique();
            List<Boolean> listCorrecte = reponseRepository.findCorrectes(question.getQuestionid().getQuestionid());
            List<Boolean> listCochee =qcmrepevalRepository.findCochees(question.getQuestionid().getQuestionid(),idEval);
            List<Evaluationreponse> evalRep = evaluationreponseRepository.findWithParameters(question.getContenuquizid(),idEval);
            switch(notationId){
                case 1: //Tout ou rien
                    Boolean questionJuste = true; 
                    for (int i=0;i<listCorrecte.size();i++){
                        if (listCorrecte.get(i)!=listCochee.get(i)){
                            questionJuste = false;
                            evalRep.get(i).setJuste(false);
                        }else{
                            evalRep.get(i).setJuste(true);
                        }
                    }
                    if (questionJuste){
                        evalQues.setNotequestion((float)question.getNombrepoints());
                        note=note+question.getNombrepoints();
                    }else{
                        evalQues.setNotequestion((float)0.0);
                    }
                    break;
                case 2: //Pourcentage
                    int noteQuestion = 0;
                    if (!repUni){
                        for (int i=0;i<listCorrecte.size();i++){
                            if (listCorrecte.get(i)==listCochee.get(i)){
                                noteQuestion=noteQuestion+1;
                                evalRep.get(i).setJuste(true);
                            }else{
                                evalRep.get(i).setJuste(false);
                            }
                        }
                    }else{ //Si c'est à réponse unique on fait comme pour le TOR
                        Boolean questionJuste2 = true; 
                        for (int i=0;i<listCorrecte.size();i++){
                            if (listCorrecte.get(i)!=listCochee.get(i)){
                                questionJuste2 = false;
                                evalRep.get(i).setJuste(false);
                            }else{
                                evalRep.get(i).setJuste(true);
                            }
                        }
                        if (questionJuste2){
                            noteQuestion=listCorrecte.size();
                        }
                    }
                    evalQues.setNotequestion(((float) noteQuestion/listCorrecte.size())*question.getNombrepoints());
                    note= note + ( (float) noteQuestion/listCorrecte.size())*question.getNombrepoints();
                    break;
                case 3: //Points négatifs: on retire 0.5 à chaque mauvaise réponse
                    int nbrBonneRep = 0;
                    int nbrMauvaiseRep = 0;
                    int nbreTotBonne = 0;
                    float ptsNeg = (float) 0.5;
                    
                    for (int i=0;i<listCorrecte.size();i++){
                        if (listCochee.get(i) && listCorrecte.get(i)){
                            nbrBonneRep=nbrBonneRep+1;
                        }else if (!listCorrecte.get(i) && listCochee.get(i)){
                            nbrMauvaiseRep=nbrMauvaiseRep+1;
                        }
                        if (listCorrecte.get(i)){
                            nbreTotBonne=nbreTotBonne+1;
                        }
                        if (listCochee.get(i)==listCorrecte.get(i)){
                            evalRep.get(i).setJuste(true);
                        }else{
                            evalRep.get(i).setJuste(false);
                            
                        }
                    }
                    evalQues.setNotequestion(((float)nbrBonneRep/nbreTotBonne)*question.getNombrepoints() - (float)nbrMauvaiseRep*ptsNeg);
                    note= note + ((float)nbrBonneRep/nbreTotBonne)*question.getNombrepoints() - (float)nbrMauvaiseRep*ptsNeg;
                    break;
            }
            for (int i=0;i<evalRep.size();i++){
                evaluationreponseRepository.save(evalRep.get(i));
            }
            evaluationquestionRepository.save(evalQues);
        }
        return note;
    }
    
    /**
     * Fonction pour récupérer la durée d'évaluation de l'élève: elle transforme un nombre de millisecondes en 
     * un string représentant un durée
     * @param time un entier long
     * @return une durée écrite sous forme dans string
     */
    public String millisecToString(long time){
        long hour = time/(3600*1000);
        long minute = (time-hour)/(60*1000);
        long seconde = (time-hour-minute)/(1000);
        return hour+":"+minute+":"+seconde;
        
        
    }
}

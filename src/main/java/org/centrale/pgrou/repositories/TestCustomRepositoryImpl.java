/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.centrale.pgrou.items.Test;
import org.centrale.pgrou.tools.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author louis-alexandre
 */
public class TestCustomRepositoryImpl implements TestCustomRepository {
    
    @Lazy
    @Autowired
    TestRepository testRepository;

    @Override
    public List<List<String>> affichageProchainsTests(String loginPers) {
        List<List<String>> returned = new ArrayList();
        
        Collection<Test> listTest = testRepository.getNextTestsByLogin(loginPers, Utilities.getCurrentDate());
        for(Test t:listTest){
            List<String> testText = new ArrayList();
            testText.add(t.getTestid().toString());
            testText.add(testRepository.getNomTest(t.getTestid()));
            String formatDate = "YYYY-MM-dd HH:mm:SS";
            java.text.SimpleDateFormat formaterDate = new java.text.SimpleDateFormat( formatDate );
            testText.add(formaterDate.format(t.getDatedebuttest()));
            testText.add(formaterDate.format(t.getDatefintest()));
            String formatTemps = "HH:mm";
            java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( formatTemps );
            String dureeStr = formater.format(t.getDureemaxtest());
            testText.add(dureeStr);
            returned.add(testText);
        }
        return returned;
    }
    
   @Override
    public List<List<String>> affichagePrecedentsTests(int persId) {
        List<List<String>> returned = new ArrayList();
        
        Collection<Test> listTest = testRepository.getPreviousTestsById(persId, Utilities.getCurrentDate());
        for(Test t:listTest){
            List<String> testText = new ArrayList();
            testText.add(t.getTestid().toString());
            testText.add(testRepository.getNomTest(t.getTestid()));
            String formatDate = "YYYY-MM-dd HH:mm:SS";
            java.text.SimpleDateFormat formaterDate = new java.text.SimpleDateFormat( formatDate );
            testText.add(formaterDate.format(t.getDatedebuttest()));
            testText.add(formaterDate.format(t.getDatefintest()));
            String formatTemps = "HH:mm";
            java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( formatTemps );
            String dureeStr = formater.format(t.getDureemaxtest());
            testText.add(dureeStr);
            if(t.getResultatvisible()!=null){
                if(t.getResultatvisible()){
                    testText.add("visible");
                }else{
                    testText.add("pasVisible");
                }
            }else{
                testText.add("pasVisible");
            }
                
            returned.add(testText);
        }
        return returned;
    }
    
}

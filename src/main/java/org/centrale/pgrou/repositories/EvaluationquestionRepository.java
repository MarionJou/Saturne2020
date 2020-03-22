/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.repositories;

import java.util.List;
import org.centrale.pgrou.items.Evaluationquestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public interface EvaluationquestionRepository extends JpaRepository<Evaluationquestion,Integer>{
    @Query(value="SELECT * FROM evaluationquestion WHERE contenuquizid=?1 AND evaluationid=?2;",nativeQuery=true)
    public Evaluationquestion findWithContenuQuiz(int idContenuQuiz,int evalId);
}

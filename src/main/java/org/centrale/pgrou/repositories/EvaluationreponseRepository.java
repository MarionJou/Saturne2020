/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.repositories;

import java.util.List;
import org.centrale.pgrou.items.Evaluationreponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public interface EvaluationreponseRepository extends JpaRepository<Evaluationreponse,Integer>{
     @Query(value="SELECT * FROM public.evaluationreponse INNER JOIN qcmrepeval USING (evaluationreponseid)"
            + "INNER JOIN qcmrep USING(qcmrepid) \n"  +
            "WHERE contenuquizid=?1 AND evaluationid=?2 ORDER BY reponseid;",nativeQuery=true)
    public  List<Evaluationreponse> findWithParameters(int id,int evalId);
}

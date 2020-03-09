/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.repositories;


import java.util.List;
import org.centrale.pgrou.items.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Integer>{

    
    @Query(value="SELECT * FROM evaluation WHERE testid=?2 \n" +
"AND personneid IN(SELECT personneid FROM contenugroupe WHERE groupeid=?1);", nativeQuery=true)
    public List<Evaluation> findWithGroupeAndTest(int groupeid, int testid);
    
    @Query(value="SELECT * FROM evaluation WHERE personneid=?1;", nativeQuery=true)
    public List<Evaluation> findWithPers(int persid);
    
    @Query(value="SELECT * FROM evaluation WHERE testid=?1;", nativeQuery=true)
    public List<Evaluation> findWithTest(int testid);

}

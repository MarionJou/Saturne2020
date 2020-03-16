/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.repositories;

import java.util.Date;
import java.util.List;
import org.centrale.pgrou.items.Personne;
import org.centrale.pgrou.items.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marion
 */
@Repository
public interface TestRepository extends JpaRepository<Test,Integer>{
    @Query(value="SELECT * FROM public.test \n" +
            "INNER JOIN contenugroupe\n" +
            "USING (groupeid)\n" +
            "WHERE personneid=?2 AND datedebuttest<=?1 AND datefintest>=?1 AND testid NOT IN (SELECT testid FROM evaluation);",nativeQuery=true) //
    //List<Test> findWithParameters(@Param("date")Date date,@Param("personne")Personne personne);
    public List<Test> findWithParameters(Date date,int personne);
    
    @Query(value="SELECT * FROM test INNER JOIN quiz USING (quizid) WHERE personneid=?1;", nativeQuery=true)
    public List<Test> findWithPersonne(int personneid);
    
    //SELECT * FROM test WHERE groupeid=2;
    @Query(value="SELECT * FROM test WHERE groupeid=?1;", nativeQuery=true)
    public List<Test> findWithGroupe(int groupeid);
}

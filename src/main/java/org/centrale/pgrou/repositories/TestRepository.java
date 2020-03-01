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
 * @author Mario
 */
@Repository
public interface TestRepository extends JpaRepository<Test,Integer>{
    @Query(value="SELECT * FROM public.test \n" +
            "INNER JOIN contenugroupe\n" +
            "USING (groupeid)\n" +
            "WHERE personneid=?2 AND datedebuttest<=?1 AND datefintest>=?1;",nativeQuery=true)
    //List<Test> findWithParameters(@Param("date")Date date,@Param("personne")Personne personne);
    public List<Test> findWithParameters(Date date,int personne);
//Rajouter le groupe: donc le récupérer -> voir comment faire puisqu'une personne a plusieurs groupe mais 1 test en a 1 
    
}

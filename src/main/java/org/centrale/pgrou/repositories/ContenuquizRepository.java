/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.repositories;

import java.util.List;
import org.centrale.pgrou.items.Contenuquiz;
import org.centrale.pgrou.items.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public interface ContenuquizRepository extends JpaRepository<Contenuquiz,Integer>{
    
    @Query(value="SELECT c FROM Contenuquiz c WHERE c.quizid=:quizid")
    List<Contenuquiz> findWithParameter(@Param("quizid")Quiz quizid);
}

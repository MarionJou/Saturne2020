/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.repositories;

import org.centrale.pgrou.items.Evaluationreponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public interface EvaluationreponseRepository extends JpaRepository<Evaluationreponse,Integer>{
    
}

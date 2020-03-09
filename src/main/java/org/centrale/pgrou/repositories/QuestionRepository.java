/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.repositories;


import java.util.List;
import org.centrale.pgrou.items.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer>{

    @Query(value="SELECT * FROM question WHERE personneid=?1;", nativeQuery=true)
    public List<Question> findWithPersonne(int personneid);

}

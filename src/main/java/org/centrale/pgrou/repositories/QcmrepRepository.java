/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.repositories;

import java.util.List;
import org.centrale.pgrou.items.Qcmrep;
import org.centrale.pgrou.items.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public interface QcmrepRepository extends JpaRepository<Qcmrep,Integer>{
    @Query(value="SELECT q FROM Qcmrep q WHERE q.reponseid=:reponseid")
    List<Qcmrep> findWithParameter(@Param("reponseid")Reponse reponseid);
}

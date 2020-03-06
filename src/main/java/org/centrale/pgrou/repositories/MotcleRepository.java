/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.repositories;


import java.util.List;
import java.util.Optional;
import org.centrale.pgrou.items.Motcle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marion
 */
@Repository
public interface MotcleRepository extends JpaRepository<Motcle,Integer>{
    @Query(value="SELECT m FROM Motcle m WHERE m.mot = :mot")
    Optional<Motcle> findWithParameter(@Param("mot")String mot);
    @Query(value="DELETE FROM motclequestion WHERE \n" +
        "questionid =?1 RETURNING *;",nativeQuery=true)
    List<Motcle> deleteWithParameter(int id);
    @Query(value="SELECT * FROM motcle INNER JOIN motclequestion USING(motcleid) WHERE \n" +
        "questionid =?1;",nativeQuery=true)
    List<Motcle> findWithIdQues(int id);
}

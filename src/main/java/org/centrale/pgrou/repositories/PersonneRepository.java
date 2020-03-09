/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.repositories;


import java.util.Collection;
import java.util.List;
import org.centrale.pgrou.items.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository

public interface PersonneRepository extends JpaRepository<Personne,Integer>, PersonneCustomRepository{
    @Query(value="SELECT * FROM personne INNER JOIN contenugroupe USING (personneid) WHERE groupeid=?1;", nativeQuery=true)
    public List<Personne> findWithGroupe(int groupeid);
    
    @Query(name = "Personne.findByLogin")
    public Collection<Personne> findByPersonLogin(@Param("login")String personneLogin);
    
    @Query(value="SELECT p FROM Personne WHERE p.nom = ?1 AND p.prenom = ?2", nativeQuery=true)
    public Collection<Personne> findByPersonNomPrenom(@Param("nom")String nom, @Param("prenom")String prenom);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.repositories;

import java.util.Collection;
import org.centrale.pgrou.items.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author louis-alexandre
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>, MenuCustomRepository {
    /**
     * Get menu collection from a connection code
     *
     * @param code
     * @return
     */
    @Query(value="SELECT DISTINCT Menu.* FROM Connexion"
        + "INNER JOIN Rolepersonne USING (Personneid)"
        + "INNER JOIN Menuaccessible USING (Roleid)"
        + "INNER JOIN Menu USING (Menuid)"
        + "WHERE Connexion.connexionid = ?1", nativeQuery=true)
    /**@Query(value="SELECT DISTINCT Menu.* FROM Menu"
            + "INNER JOIN Menuaccessible USING (Menuid)"
            + "INNER JOIN Rolepersonne USING (Roleid)"
            + "INNER JOIN Connexion USING (Personneid)"
            + "WHERE Connection.Connection_ID = ?1", nativeQuery=true)**/
    public Collection<Menu> getAvailableMenus(String code);
    
    /**
     * Get menu collection from a person ID
     *
     * @param personId
     * @return
     */
    @Query(value="SELECT DISTINCT Menu.* FROM Rolepersonne"
        + "INNER JOIN Menuaccessible USING (Roleid)"
        + "INNER JOIN Menu USING (Menuid)"
        + "WHERE Rolepersonne.personneid = ?1", nativeQuery=true)
    public Collection<Menu> getAvailableMenus(Integer personId);
    
    /**
     * Check menu access from a person ID
     *
     * @param personId
     * @param nom
     * @return
     */
    @Query(value="SELECT DISTINCT Menu.* FROM Rolepersonne"
        + "INNER JOIN Menuaccessible USING (Roleid)"
        + "INNER JOIN Menu USING (Menuid)"
        + "WHERE Rolepersonne.personneid = ?1 AND Menu.nom = ?2", nativeQuery=true)
            
    public Collection<Menu> checkMenuLink(Integer personId, String nom);
}

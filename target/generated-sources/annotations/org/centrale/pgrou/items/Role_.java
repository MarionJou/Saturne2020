package org.centrale.pgrou.items;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Menu;
import org.centrale.pgrou.items.Personne;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile CollectionAttribute<Role, Personne> personneCollection;
    public static volatile SingularAttribute<Role, Integer> roleid;
    public static volatile SingularAttribute<Role, String> libelle;
    public static volatile CollectionAttribute<Role, Menu> menuCollection;

}